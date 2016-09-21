package com.jzeen.travel.wechat.service;

import com.jzeen.travel.wechat.message.resp.BaseMessage;
import com.jzeen.travel.wechat.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Title: X2OUR_TRAVEL
 * Description: 核心处理器
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */

@Service
public class CoreService {

    private static Logger log = LoggerFactory.getLogger(CoreService.class);

    @Autowired
    EventMessageService eventMessageService;

    @Autowired
    TextMessageService textMessageService;

    @Autowired
    VoiceMessageService voiceMessageService;

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public String processRequest(HttpServletRequest request) {
        // 返回给微信服务器的消息,默认为null

        log.info("开始处理消息");

        String respMessage = null;

        try {

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            log.info(String.format("消息参数 fromUserName：{%s}  toUserName:{%s}  msgType:{%s}", fromUserName, toUserName, msgType));


            // 回复的基本信息
            BaseMessage message = new BaseMessage();
            message.setToUserName(fromUserName);
            message.setFromUserName(toUserName);
            message.setCreateTime(new Date().getTime());
            message.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            message.setFuncFlag(0);

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                // 文本消息
                return textMessageService.processMessage(requestMap, message);

            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                // 图片消息
                return new ImageMessageService().processMessage(requestMap, message);

            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                // 语音消息
                return voiceMessageService.processMessage(requestMap, message);
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                // 视频消息
                return new VideoMessageService().processMessage(requestMap, message);
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                // 地理位置消息
                return new LocationMessageService().processMessage(requestMap, message);
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                // 链接消息
                return new LinkMessageService().processMessage(requestMap, message);
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件推送
                return eventMessageService.processMessage(requestMap, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }



    // 判断是否为数字
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

