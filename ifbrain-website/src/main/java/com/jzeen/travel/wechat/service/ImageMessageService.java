package com.jzeen.travel.wechat.service;

import com.jzeen.travel.wechat.message.resp.BaseMessage;
import com.jzeen.travel.wechat.message.resp.TextMessage;
import com.jzeen.travel.wechat.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Title: X2OUR_TRAVEL
 * Description: 图像服务
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */
public class ImageMessageService extends BaseMessageService {

    private static Logger log = LoggerFactory.getLogger(ImageMessageService.class);


    @Override
    public String processMessage(Map<String, String> requestMap, BaseMessage baseMessage) {
        // 取得图片地址
        String picUrl = requestMap.get("PicUrl");
        String MediaId = requestMap.get("MediaId");
        Long MsgId = Long.valueOf(requestMap.get("MsgId"));

        log.info("提交图片成功 picUrl:{} MediaId:{} MediaId:{}", picUrl, MediaId, String.valueOf(MsgId));

        TextMessage textMessage = new TextMessage(baseMessage);
        textMessage.setContent(String.format("您提交的图片是 picUrl:{%s} MediaId:{%s} MediaId:{%s}", picUrl, MediaId, String.valueOf(MsgId)));
        respMessage = MessageUtil.messageToXml(textMessage);

        return respMessage;
    }
}
