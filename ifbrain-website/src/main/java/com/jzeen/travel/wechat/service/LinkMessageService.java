package com.jzeen.travel.wechat.service;

import com.jzeen.travel.wechat.message.resp.BaseMessage;
import com.jzeen.travel.wechat.message.resp.TextMessage;
import com.jzeen.travel.wechat.utils.MessageUtil;

import java.util.Map;

/**
 * Title: X2OUR_TRAVEL
 * Description: 链接消息服务
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public class LinkMessageService extends BaseMessageService {

    @Override
    public String processMessage(Map<String, String> requestMap, BaseMessage baseMessage) {
        TextMessage textMessage = new TextMessage(baseMessage);
        textMessage.setContent("您发送的是链接消息！暂未开通该类消息相关功能，敬请期待！");
        respMessage = MessageUtil.messageToXml(textMessage);
        return respMessage;
    }
}
