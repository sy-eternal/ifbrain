package com.jzeen.travel.wechat.service;

import com.jzeen.travel.wechat.message.model.UserLocation;
import com.jzeen.travel.wechat.message.resp.BaseMessage;
import com.jzeen.travel.wechat.message.resp.TextMessage;
import com.jzeen.travel.wechat.utils.BaiduMapUtil;
import com.jzeen.travel.wechat.utils.MessageUtil;

import java.util.Map;

/**
 * Created by limin on 15-7-29.
 */
public class LocationMessageService extends BaseMessageService {

    @Override
    public String processMessage(Map<String, String> requestMap, BaseMessage baseMessage) {
        TextMessage textMessage = new TextMessage(baseMessage);

        // 用户发送的经纬度
        String lng = requestMap.get("Location_Y");
        String lat = requestMap.get("Location_X");
        // 坐标转换后的经纬度
        String bd09Lng = null;
        String bd09Lat = null;
        // 调用接口转换坐标
        UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
        if (null != userLocation) {
            bd09Lng = userLocation.getBd09Lng();
            bd09Lat = userLocation.getBd09Lat();
        }
        // 保存用户地理位置
        // 暂不使用，不保存到数据库，by limin
        // MySQLUtil.saveUserLocation(fromUserName, lng, lat, bd09Lng, bd09Lat);

        StringBuffer buffer = new StringBuffer();
        buffer.append("[愉快]").append("成功接收您的位置！").append("\n\n");
        buffer.append("您可以输入搜索关键词获取周边信息了，例如：").append("\n");
        buffer.append("        周边ATM").append("\n");
        buffer.append("        周边KTV").append("\n");
        buffer.append("        周边厕所").append("\n");
        buffer.append("必须以“周边”两个字开头！");
        textMessage.setContent(buffer.toString());
        respMessage = MessageUtil.messageToXml(textMessage);
        return respMessage;
    }
}
