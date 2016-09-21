package com.jzeen.travel.wechat.message.resp;

/**
 * 文本消息
 *
 * @author Administrator
 */
public class TextMessage extends BaseMessage {
    // 回复的消息内容
    private String Content;

    public TextMessage(BaseMessage baseMessage) {
        super(baseMessage);
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


}
