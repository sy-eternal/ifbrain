package com.jzeen.travel.wechat.service;

import com.jzeen.travel.data.entity.WeChatCustServEvent;
import com.jzeen.travel.data.repository.WeChatCustServEventRepository;
import com.jzeen.travel.wechat.message.resp.BaseMessage;
import com.jzeen.travel.wechat.utils.MessageUtil;
import com.jzeen.travel.wechat.utils.TulingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Title: X2OUR_TRAVEL
 * Description: 语音服务
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */

@Service
public class VoiceMessageService extends BaseMessageService {

    private static Logger log = LoggerFactory.getLogger(VoiceMessageService.class);

    @Autowired
    private WeChatCustServEventRepository weChatCustServEventRepository;

    @Override
    public String processMessage(Map<String, String> requestMap, BaseMessage baseMessage) {
        // 判断是否发送多客服
        try {
            if (isSendCustomerServ(baseMessage.getToUserName())) {
                BaseMessage custServMsg = new BaseMessage();
                custServMsg.setToUserName(baseMessage.getToUserName());
                custServMsg.setFromUserName(baseMessage.getFromUserName());
                custServMsg.setCreateTime(new Date().getTime() / 1000);
                custServMsg.setMsgType(MessageUtil.MUTI_CUSTOMER_SERV);

                return MessageUtil.messageToXml(custServMsg);
            }
        } catch (Exception e) {
            //如果发送多客服失败，则发送到机器人
            log.error("发送消息到多客服系统异常:{}", e.toString());
        }


        // 以下为发送机器人处理

        // 语音消息文件的标识
        String mediaId = requestMap.get("MediaId");
        // 语音格式：amr
        String format = requestMap.get("Format");
        // 语音识别结果
        String recognition = requestMap.get("Recognition");

        log.info("接收语音信息，语音消息文件的标识:{}, 语音格式:{}, 语音识别结果:{}", mediaId, format, recognition);

        respMessage = TulingUtil.getTullingInfo2Wechat(baseMessage, recognition);

        return respMessage;
    }


    /**
     * 判断是否需要发送客服．
     * 如果用户点击了客服菜单，并且点击时间在２个小时以内，则消息转发给客服．否则转发给机器人
     * @param openid
     * @return
     */
    private boolean isSendCustomerServ(String openid) {
        int clickTime = Long.valueOf((new Date().getTime() - 1000 * 60 * 60 * 2) / 1000).intValue();
        List<WeChatCustServEvent> eventList = weChatCustServEventRepository.findByOpenidClickTime(openid, clickTime);

        return eventList == null ? false : eventList.size() > 0;
    }
}
