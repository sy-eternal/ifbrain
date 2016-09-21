package com.jzeen.travel.wechat.controller;

import com.jzeen.travel.data.entity.WeChatRedpackActivity;
import com.jzeen.travel.data.repository.WeChatRedpackActivityRepository;
import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.service.RedPackService;
import com.jzeen.travel.wechat.utils.CustomMsgUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: X2OUR_TRAVEL
 * Description: 红包活动控制器
 * Date: 2015-08-02
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */

@Controller
@RequestMapping("/redpackactivity")
public class RedPackActivityController {

    private static Logger log = LoggerFactory.getLogger(RedPackActivityController.class);

    private static Map<String, Integer> KVMap = new HashMap<String, Integer>();

    // 修改为0
    public static int SIZE = -1;



    @Autowired
    WeChatRedpackActivityRepository redpackActivityRepository;

    @Autowired
    RedPackService redPackService;


    /**
     * 参与用户关注抢红包活动
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {


        String openId = request.getParameter("openId");
        String signature = request.getParameter("signature");

        log.info("进入抢红包方法");
        log.info("openId :{}", openId);

        //------------校验链接有效性------

        String[] arr = new String[]{WeChatConts.bindToken, openId};
        // 将token、openId 两个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将两个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;

        //------------校验链接有效性，非法链接不处理------

        if (tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false) {
            // 有效请求
            log.info("抢红包活动链接有效，openId：{},signature:{}", openId, signature);
        } else {
            // 发送客服消息
//            CustomMsgUtil.sendTextMsg(openId, "链接异常，参与抽奖活动失败！请重新尝试或者点击【随便问问】菜单联系客服人员。");
            log.error("抢红包活动链接异常，openId：{}, signature:{}", openId, signature);
        }


        String url = WeChatConts.webURL + "/wechatimage/activity/";

        try {

            // 查询是否点击过
            WeChatRedpackActivity activity = redpackActivityRepository.findByOpenidAndActType(openId, "1");

            //新用户点击，保存一条记录
            if (activity == null) {
                activity = new WeChatRedpackActivity();
                activity.setOpenid(openId);
                activity.setActType("1");
                activity.setTakepartinTime(new Date());
                activity.setCreateTime(new Date());
                redpackActivityRepository.save(activity);
            }


            long currentTime = new Date().getTime();
            log.info("当前时间 :{}", currentTime);
            log.info("抽奖时间 :{}", getCurrentDayTimeOfNoon());

            if (currentTime < getCurrentDayTimeOfNoon()) {
                // 当前时间 小于活动开始时间 或者 小于活动当天中午12点
                // 时间还没到

                log.info("抢红包时间未到");
                url += "notontime.jpg";
                model.addAttribute("url", url);
                return "/wechat/redpackactivity";
            }

            // 先判断是否有点击参与抢红包活动
            WeChatRedpackActivity dbActivity = redpackActivityRepository.findByOpenidAndActType(openId, "1");

            if (dbActivity != null) {

                //曾经已经抢到过红包
                if (dbActivity.getResult() != null && dbActivity.getResult() == 1) {

                    log.info("用户已经抢到过红包 openId:{}", openId);
                    url += "havegotredpack.jpg";
                    model.addAttribute("url", url);
                    return "/wechat/redpackactivity";
                }


                log.info("已经发放红包数量 数量:{}", RedPackActivityController.KVMap.size());
                log.info("红包总数 openId:{}", RedPackActivityController.SIZE);

                if (RedPackActivityController.KVMap.size() > RedPackActivityController.SIZE) {
                    // 没有抢到红包

                    dbActivity.setClickTime(Long.valueOf(currentTime).intValue());
                    dbActivity.setResult(2);
                    redpackActivityRepository.save(dbActivity);


                    Date dt = new Date();
                    SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = matter1.format(dt);

                    if ("2015-08-29".equals(strDate)) {
                        url += "nomoreredpack.jpg";
                    } else {
                        url += "activityend.jpg";
                    }

                    log.info("红包已经抢完 openId:{}", openId);
                    model.addAttribute("url", url);
                    return "/wechat/redpackactivity";
                }


                log.info("用户是否已经抢到红包，重复点击，点击时间:{}", RedPackActivityController.KVMap.get(openId));

                if (RedPackActivityController.KVMap.get(openId) != null) {
                    // 已经抢到,红包尚未发放到用户手中
                    log.info("已经抢到, 重复点击，红包尚未发放到用户手中 openId:{}", openId);
                    url += "havenotsent.jpg";
                    model.addAttribute("url", url);
                    return "/wechat/redpackactivity";
                }


                // 抢到红包
                log.info("成功抢到红包 openId:{}, time:{}", openId, Long.valueOf(currentTime).intValue());

                RedPackActivityController.KVMap.put(openId, Long.valueOf(currentTime).intValue());
                dbActivity.setClickTime(Long.valueOf(currentTime).intValue());
                dbActivity.setSendFlag(1);
                dbActivity.setResult(1);
                redpackActivityRepository.save(dbActivity);

                int totol = (20 + RandomUtils.nextInt(20)) * 5;
                redPackService.sentRedPack(dbActivity, totol, "嘻游定制旅行 最专业的定制旅行！", "115.29.100.19", "关注嘻游抢红包活动", "红包多多，惊喜连连，关注嘻游，关注旅游");

                url += "getredpack.jpg";
                model.addAttribute("url", url);
                return "/wechat/redpackactivity";

            } else {

                url += "activityend.jpg";
                model.addAttribute("url", url);
                log.info("系统异常，未能获取数据库中用户数据 openId:{}", openId);
                CustomMsgUtil.sendTextMsg(openId, "亲，非常抱歉，由于活动人数过多导致系统异常，请点击 随便问问，联系客服");
            }

        } catch (ParseException e) {
            log.error("抢红包异常：{}", e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return "/wechat/redpackactivity";
    }

    /**
     * 参加红包活动
     *
     * @param openId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/act", method = RequestMethod.POST)
    public String visains(@RequestParam String openId, HttpServletRequest request) {

        log.info("进入抢红包方法");
        log.info("openId :{}", openId);

        if (openId == null) {
            CustomMsgUtil.sendTextMsg(openId, "系统异常！请重新尝试或者点击【随便问问】菜单联系客服人员");
            return "";
        }

        try {

            long currentTime = new Date().getTime();

            log.info("当前时间 :{}", currentTime);
            log.info("抽奖时间 :{}", getCurrentDayTimeOfNoon());

            if (currentTime < getStartRedPackDayTime() || currentTime < getCurrentDayTimeOfNoon()) {
                // 当前时间 小于活动开始时间 或者 小于活动当天中午12点
                // 时间还没到
                CustomMsgUtil.sendTextMsg(openId, "亲，时间还没到，请不要着急哦，8月28日-8月30日每天12点整准时约哦");

                log.info("抢红包时间还未到");

            } else {

                // 先判断是否有点击参与抢红包活动
                WeChatRedpackActivity activity = redpackActivityRepository.findByOpenidAndActType(openId, "1");

                if (activity != null) {

                    //曾经已经抽中红包
                    if (activity.getResult() != null && activity.getResult() == 1) {

                        CustomMsgUtil.sendTextMsg(openId, "亲，您已经抢到了红包，后续我们还将有更多活动更多大奖期待您参与！");
                        log.info("用户已经抢到过红包 openId:{}", openId);
                        return "";
                    }

                    log.info("用户点击过参与抢红包活动 openId:{}", activity.getOpenid());
                    log.info("已经发放红包数量 数量:{}", RedPackActivityController.KVMap.size());
                    log.info("红包总数 openId:{}", RedPackActivityController.SIZE);

                    if (RedPackActivityController.KVMap.size() > RedPackActivityController.SIZE) {
                        // 没有抢到红包

                        activity.setClickTime(Long.valueOf(currentTime).intValue());
                        activity.setResult(2);

                        CustomMsgUtil.sendImageMsg(openId, "grr-erLC8-vZzjFl3ah1SFLhfm9BhTAaTzZaJ0hLApnC3CxtE3RLUwzBpRxVWooo");

                    } else {

                        log.info("是否已经抢到红包，重复点击，点击时间:{}", RedPackActivityController.KVMap.get(openId));

                        if (RedPackActivityController.KVMap.get(openId) != null) {
                            // 已经抢到,重复点击
                            CustomMsgUtil.sendTextMsg(openId, "亲，恭喜您！已经成功抢到一次获得红包的机会，此时，红包正在给您派送中，请耐心等待，稍后查收！");

                        } else {

                            log.info("成功抢到红包 openId:{}, time:{}", openId, Long.valueOf(currentTime).intValue());

                            RedPackActivityController.KVMap.put(openId, Long.valueOf(currentTime).intValue());
                            // 抢到红包
                            activity.setClickTime(Long.valueOf(currentTime).intValue());
                            activity.setSendFlag(0);
                            activity.setResult(1);

                            //抢到红包，不再发送抢到红包提示图片
                            //CustomMsgUtil.sendImageMsg(openId, "8xiIxYaYCeLWewYZxriH4w38K74pRJFgPjD7pju0NcxnaGhLeaDtLUFAU1Zctxvg");
                        }
                    }

                    redpackActivityRepository.save(activity);
                } else {

                    log.info("用户未点击参与红包活动 openId:{}", openId);
                    // 不是粉丝或者没有点击参与活动
                    // 时间还没到
                    CustomMsgUtil.sendTextMsg(openId, "亲，您未点击参与红包活动，请点击参与哦，如果有问题请联系客服");
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }





    /**
     * 活动开始时间
     * @return
     * @throws ParseException
     */
    private long getStartRedPackDayTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2015-08-28 00:00:00");

        return date.getTime();
    }


    private long getCurrentDayTimeOfNoon() throws ParseException {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = matter1.format(dt) + " 12:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(strDate);

        return date.getTime();
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
