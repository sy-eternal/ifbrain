package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.RedPack;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.jzeen.travel.wechat.pojo.RedPackSentResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description: 发送红包工具类
 * Date: 2015年 08月 07日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class RedPackUtil {

    private static Logger log = LoggerFactory.getLogger(RedPackUtil.class);

    /**
     * 发送红包
     *
     * @param redPack   红包对象
     * @return true成功 false失败
     */
    public static RedPackSentResult sendRedPack(RedPack redPack) throws IllegalAccessException, UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        String accessToken = WeChatConts.getTokenInst().getToken();
        String url = WeChatConts.SEND_REDPACK_API.replace("ACCESS_TOKEN", accessToken);

        //设置随机数
        redPack.setNonce_str(RandomStringUtils.randomAlphanumeric(32));

        //设置签名
        redPack.setSign(genSign(redPack));


        String jsonRedPack = redPackToXml(redPack);

        log.info("提交的数据是");
        log.info(jsonRedPack);

        HttpsRequest httpsRequest = new HttpsRequest();
        String returnStr = httpsRequest.sendPost(url, jsonRedPack);

        log.info("返回的数据是");
        log.info(returnStr);

        RedPackSentResult result = new RedPackSentResult();

        if (null != returnStr) {

            JSONObject jsonObject = new XMLSerializer().readObject(returnStr);

            // SUCCESS/FAIL
            // 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
            String returnCode = jsonObject.getString("return_code");
            String returnMsg = jsonObject.getString("return_msg");

            result.setReturnCode(returnCode);
            result.setReturnMsg(returnMsg);

            if (returnCode.contains("SUCCESS")) {

                // 签名	sign	是	C380BEC2BFD727A4B6845133519F3AD6	String(32)	签名，详见签名算法
                // 没有返回签名
                // String sign = jsonObject.getString("sign");
                // 业务结果	result_code	是	SUCCESS	String(16)	SUCCESS/FAIL
                String resultCode = jsonObject.getString("result_code");

                // 以下数据可能有返回，也可能没有返回
                String errCode = "";
                String errCodeDes = "";

                try {
                    // 错误代码	err_code	否	SYSTEMERROR	String(32)	错误码信息
                    errCode = jsonObject.getString("err_code");
                } catch (Exception e) {

                }

                try {
                    //  错误代码描述	err_code_des	否	系统错误	String(128)	结果信息描述
                    errCodeDes = jsonObject.getString("err_code_des");
                } catch (Exception e) {

                }

                String mchBillno = "";
                String mchId = "";
                String wxappid = "";
                String reOpenid = "";
                int totalAmount = 0;
                int sendTime = 0;
                String sendListid = "";

                if (resultCode.contains("SUCCESS")) {
                    //商户订单号	mch_billno	是	10000098201411111234567890	String(28)
                    //商户订单号（每个订单号必须唯一）
                    //组成：mch_id+yyyymmdd+10位一天内不能重复的数字
                    mchBillno = jsonObject.getString("mch_billno");

                    // 商户号	mch_id	是	10000098	String(32)	微信支付分配的商户号
                    mchId = jsonObject.getString("mch_id");

                    // 公众账号appid	wxappid	是	wx8888888888888888	String(32)	商户appid
                    wxappid = jsonObject.getString("wxappid");

                    // 用户openid	re_openid	是	oxTWIuGaIt6gTKsQRLau2M0yL16E	String(32)
                    // 接受收红包的用户
                    // 用户在wxappid下的openid
                    reOpenid = jsonObject.getString("re_openid");

                    //付款金额	total_amount	是	1000	int	付款金额，单位分
                    totalAmount = jsonObject.getInt("total_amount");

                    //  发放成功时间	send_time	是	20150520102602	int	红包发送时间
                    sendTime = jsonObject.getInt("send_time");

                    // 微信单号	send_listid	是	100000000020150520314766074200	String(32)	红包订单的微信单号
                    sendListid = jsonObject.getString("send_listid");
                }

                log.info(String.format("红包发送成功 return_code:{%s}  return_msg:{%s}  result_code：{%s}  err_code：{%s}  err_code_des：{%s} " +
                                " mch_billno:{%s}  mch_id:{%s}   wxappid:{%s} re_openid:{%s}  total_amount:{%d} send_time:{%d} send_listid:{%s}",
                        returnCode, returnMsg, resultCode, errCode, errCodeDes, mchBillno, mchId, wxappid, reOpenid, totalAmount,
                        sendTime, sendListid));

                result.setResultCode(resultCode);
                result.setErrCode(errCode);
                result.setErrCodeDes(errCodeDes);
                result.setMchBillno(mchBillno);
                result.setMchId(mchId);
                result.setWxappid(wxappid);
                result.setReOpenid(reOpenid);
                result.setTotalAmount(totalAmount);
                result.setSendTime(sendTime);
                result.setSendListid(sendListid);
            } else {
                // 红包发送失败

                String resultCode = jsonObject.getString("result_code");
                String errCode = jsonObject.getString("err_code");
                String errCodeDes = jsonObject.getString("err_code_des");
                String mchBillno = jsonObject.getString("mch_billno");
                String mchId = jsonObject.getString("mch_id");
                String wxappid = jsonObject.getString("wxappid");
                String reOpenid = jsonObject.getString("re_openid");
                int totalAmount = jsonObject.getInt("total_amount");

                log.error(String.format("红包发送失败 return_code: {%s}  return_msg:{%s}  result_code：{%s}  err_code：{%s}  err_code_des：{%s} " +
                                " mch_billno:{%s}  mch_id:{%s}   wxappid:{%s} re_openid:{%s}  total_amount:{%d}",
                        returnCode, returnMsg, resultCode, errCode, errCodeDes, mchBillno, mchId, wxappid, reOpenid, totalAmount));

                result.setResultCode(resultCode);
                result.setErrCode(errCode);
                result.setErrCodeDes(errCodeDes);
                result.setMchBillno(mchBillno);
                result.setMchId(mchId);
                result.setWxappid(wxappid);
                result.setReOpenid(reOpenid);
                result.setTotalAmount(totalAmount);
            }
        }

        return result;
    }


    public static String genSign(Object model) throws IllegalAccessException {
        String sign = RedPackUtil.strArraySort(model) + "&key=" + WeChatConts.mmpayKey;

        log.error("红包签名成功 sign:{} ", sign);

        return DigestUtils.md5Hex(getContentBytes(sign, "utf-8")).toUpperCase();
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset)
    {
        if (charset == null || "".equals(charset))
        {
            return content.getBytes();
        }
        try
        {
            return content.getBytes(charset);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    public static String strArraySort(Object model) throws IllegalAccessException {
        String[] arr = RedPackUtil.classReflect(model);

        Arrays.sort(arr);

        StringBuilder content = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            content.append((i == arr.length - 1) ? arr[i] : arr[i] + "&");
        }
        return content.toString();
    }

    private static String[] classReflect(Object model) throws IllegalAccessException, IllegalArgumentException {
        Field[] field = model.getClass().getDeclaredFields();

        List<String> arr = new ArrayList<String>();

        for (int i = 0; i < field.length; i++) {

            //获取属性的名字

            field[i].setAccessible(true);

            String name = field[i].getName();

            String value = String.valueOf(field[i].get(model));

            //如果属性值不为空，拼裝 属性名=属性值方式
            if (value != "null") {
                arr.add(name + "=" + value);
            }
        }

        return arr.toArray(new String[arr.size()]);
    }

    /**
     * 红包消息对象转换成xml
     *
     * @param redPack 红包对象
     *
     * @return xml
     */
    public static String redPackToXml(RedPack redPack) {
        xstream.alias("xml", redPack.getClass());
        String xmlString =  xstream.toXML(redPack);

        // 对两个下划线字符的处理，如果在内容中出现__需要特殊处理，避免在内容中出现__
        return xmlString.replaceAll("__", "_");
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, KeyManagementException, KeyStoreException {

        RedPack redPack = new RedPack("RET000006", "ovFm4uNOKKcB01_1vkEQSe6Mfxeg", 100, "嘻游网祝您happy every",
                "192.168.1.157", "嘻游网喜迎中秋大放价", "红包多多，惊喜连连，关注嘻游，关注旅游");
        RedPackUtil.sendRedPack(redPack);
    }

}