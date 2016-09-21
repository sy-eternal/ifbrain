package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.Transfers;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description: 企业转账工具类
 * Date: 2015年 08月 07日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class TransfersUtil {

    private static Logger log = LoggerFactory.getLogger(TransfersUtil.class);

    /**
     * 发送红包
     *
     * @param transfers   转账对象
     * @return true成功 false失败
     */
    public static boolean transferPay(Transfers transfers) throws IllegalAccessException, UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        String accessToken = WeChatConts.getTokenInst().getToken();
        String url = WeChatConts.TRANSFERS_API.replace("ACCESS_TOKEN", accessToken);

        //设置随机数
        transfers.setNonce_str(RandomStringUtils.randomAlphanumeric(32));

        //设置签名
        transfers.setSign(genSign(transfers));


        String transfersJson = transfersToXml(transfers);

        HttpsRequest httpsRequest = new HttpsRequest();
        String returnStr = httpsRequest.sendPost(url, transfersJson);

        log.info("返回的数据是");
        log.info(returnStr);

        boolean result = false;

        if (null != returnStr) {

            JSONObject jsonObject = new XMLSerializer().readObject(returnStr);

            // SUCCESS/FAIL
            // 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
            String returnCode = jsonObject.getString("return_code");
            String returnMsg = jsonObject.getString("return_msg");

            if (returnCode.contains("SUCCESS")) {
                result = true;

                // 商户appid	mch_appid	是	wx8888888888888888	String	微信分配的公众账号ID（企业号corpid即为此appId）
                String mchAppid = jsonObject.getString("mch_appid");
                // 商户号	mchid	是	1900000109	String(32)	微信支付分配的商户号
                String mchid = jsonObject.getString("mchid");
                // 随机字符串	nonce_str	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	String(32)	随机字符串，不长于32位
                String nonceStr = jsonObject.getString("nonce_str");
                // 签名	sign	是	C380BEC2BFD727A4B6845133519F3AD6	String(32)	签名，详见签名算法
                // 没有返回签名
                // String sign = jsonObject.getString("sign");
                // 业务结果	result_code	是	SUCCESS	String(16)	SUCCESS/FAIL
                String resultCode = jsonObject.getString("result_code");

                // 以下数据可能有返回，也可能没有返回
                String deviceInfo = "";
                String errCode = "";
                String errCodeDes = "";
                try{
                    // 设备号	device_info	否	013467007045764	String(32)	微信支付分配的终端设备号，
                    deviceInfo = jsonObject.getString("device_info");
                } catch (Exception e) {

                }

                try{
                    // 错误代码	err_code	否	SYSTEMERROR	String(32)	错误码信息
                    errCode = jsonObject.getString("err_code");
                } catch (Exception e) {

                }

                try{
                    //  错误代码描述	err_code_des	否	系统错误	String(128)	结果信息描述
                    errCodeDes = jsonObject.getString("err_code_des");
                } catch (Exception e) {

                }

                String partnerTradeNo = "";
                String paymentNo = "";
                String paymentTime = "";
                if (resultCode.contains("SUCCESS")) {
                    // 商户订单号	partner_trade_no	是	1217752501201407033233368018	String(32)	商户订单号，需保持唯一性
                    partnerTradeNo = jsonObject.getString("partner_trade_no");
                    // 微信订单号	payment_no	是	1007752501201407033233368018	String	企业付款成功，返回的微信订单号
                    paymentNo = jsonObject.getString("payment_no");
                    // 微信支付成功时间	payment_time	是	2015-05-19 15：26：59	String	企业付款成功时间
                    paymentTime = jsonObject.getString("payment_time");
                }


                log.info(String.format("企业支付成功 return_code: {%s}  return_msg:{%s}  mch_appid：{%s}  mchid：{%s}  nonce_str：{%s}  " +
                                " result_code：{%s}  device_info：{%s}  err_code：{%s}  err_code_des：{%s} partner_trade_no:{%s}  " +
                                " payment_no: {%s}  payment_time : {%s} ",
                        returnCode, returnMsg, mchAppid, mchid, nonceStr, resultCode, deviceInfo, errCode, errCodeDes,
                        partnerTradeNo, paymentNo, paymentTime));
            } else {
                result = false;

                String resultCode = "";
                String errCode = "";
                String errCodeDes = "";

                try{
                     resultCode = jsonObject.getString("result_code");
                } catch (Exception e) {

                }

                try{
                    errCode = jsonObject.getString("err_code");
                } catch (Exception e) {

                }

                try{
                    errCodeDes = jsonObject.getString("err_code_des");
                } catch (Exception e) {

                }

                log.error(String.format("企业支付失败 return_code: {%s}  return_msg:{%s}  result_code：{%s}  err_code：{%s}  err_code_des：{%s} ",
                        returnCode, returnMsg, resultCode,  errCode, errCodeDes));
            }
        }

        return result;
    }


    public static String genSign(Object model) throws IllegalAccessException {
        String sign = TransfersUtil.strArraySort(model) + "&key=" + WeChatConts.mmpayKey;

        log.error("企业支付签名成功 sign:{} ", sign);

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
        String[] arr = TransfersUtil.classReflect(model);

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
     * 转账对象转换成xml
     *
     * @param transfers 红包对象
     *
     * @return xml
     */
    public static String transfersToXml(Transfers transfers) {
        xstream.alias("xml", transfers.getClass());
        String xmlString = xstream.toXML(transfers);

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

        Transfers transfers = new Transfers("X2T0000006", "ovFm4uAeu-ofAntLDiY1Ww76d9Cw", 1, "嘻游上市红包大放送测试", "192.168.1.157");
        TransfersUtil.transferPay(transfers);
    }

}