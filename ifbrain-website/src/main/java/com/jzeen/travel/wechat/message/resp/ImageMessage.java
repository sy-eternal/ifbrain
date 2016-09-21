package com.jzeen.travel.wechat.message.resp;

import com.jzeen.travel.wechat.message.model.Image;

/**
 * 图片消息
 *
 * @author limin.tony@x2our.com
 */
public class ImageMessage extends BaseMessage {
    // 图片
    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }

}
