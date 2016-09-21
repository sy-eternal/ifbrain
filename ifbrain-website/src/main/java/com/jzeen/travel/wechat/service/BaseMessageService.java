package com.jzeen.travel.wechat.service;

import com.jzeen.travel.wechat.message.resp.BaseMessage;

import java.util.Map;

/**
 * Title: X2OUR_TRAVEL
 * Description: 抽象消息服务
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public abstract class BaseMessageService {

    protected String respMessage = "";

    public abstract String processMessage(Map<String, String> requestMap, BaseMessage baseMessage);

}
