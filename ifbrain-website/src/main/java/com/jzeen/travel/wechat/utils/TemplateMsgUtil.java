package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: X2OUR_TRAVEL
 * Description: 模板消息工具类
 * Date: 2015年 08月 04日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class TemplateMsgUtil {

    private static Logger log = LoggerFactory.getLogger(TemplateMsgUtil.class);


    /**
     * 设置行业信息
     *
     * @param industry_id1
     * @param industry_id2
     * @return void
     *
     */
    public static void setIndustry(String industry_id1, String industry_id2) {
         // 拼接请求地址
        String requestUrl = WeChatConts.templateIndustryUrl;
        requestUrl = requestUrl.replace("ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());

        // 需要提交的json数据
        String jsonData = "{\"industry_id1\":\"%s\", \"industry_id2\":\"%s\"}";

        // 设置行业
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, industry_id1, industry_id2));
    }


    /**
     * 设置模板ID
     *
     * @param templateIdShort
     * @return String 0表示创建成功，其他表示创建模板失败
     *
     */
    public static String addTemplate(String templateIdShort) {
        // 拼接请求地址
        String requestUrl = WeChatConts.templateAddtemplUrl;
        requestUrl = requestUrl.replace("ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());

        // 需要提交的json数据
        String jsonData = "{\"template_id_short\":\"%s\"}";

        // 设置行业
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, templateIdShort));

        if (null != jsonObject) {
            try {
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                String template_id = jsonObject.getString("template_id");

                log.error("新增模板成功 errcode:{} errmsg:{} template_id：{}", errorCode, errorMsg, template_id);
                return template_id;
            } catch (JSONException e) {
                 log.error("新增模板失败");
            }
        }
        return "";
    }

    /**
     * @param keywords
     *
     * 参数顺序：
     * String touser,String templateId, String url, String first, String keyword1, String keyword2, String keyword3, String remark
     * @return String
     */
    public static String sendTemplateMsg(String... keywords){
        // 拼接请求地址
        String requestUrl = WeChatConts.templateSendMegUrl;
        requestUrl = requestUrl.replace("ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());

        // 需要提交的json数据
//        String jsonData = "{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":\"%s\",\"topcolor\":\"#FF0000\",\"data\":{\"first\": {\"value\":\"您的美国签证面试预约成功，面试时间为2015年 7月6日。\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"美国个人旅游签证\", \"color\":\"#173177\"},\"keyword2\":{\"value\":\"36378949\", \"color\":\"#173177\"},\"keyword3\":{\"value\":\"2015年3月5日\",\"color\":\"#173177\"},\"remark\":{\"value\":\"点击查进度详情\",\"color\":\"#173177\"}}}";

        String jsonData = "";

        if (keywords.length == 8) {
            //3个keyword
            jsonData = "{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":\"%s\",\"topcolor\":\"#FF0000\",\"data\":{\"first\": {\"value\":\"%s\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"%s\", \"color\":\"#173177\"},\"keyword2\":{\"value\":\"%s\", \"color\":\"#173177\"},\"keyword3\":{\"value\":\"%s\",\"color\":\"#173177\"},\"remark\":{\"value\":\"%s\",\"color\":\"#173177\"}}}";
        } else if (keywords.length == 9) {
            //4个keyword
            jsonData = "{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":\"%s\",\"topcolor\":\"#FF0000\",\"data\":{\"first\": {\"value\":\"%s\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"%s\", \"color\":\"#173177\"},\"keyword2\":{\"value\":\"%s\", \"color\":\"#173177\"},\"keyword3\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"%s\",\"color\":\"#173177\"},\"remark\":{\"value\":\"%s\",\"color\":\"#173177\"}}}";
        } else {
            //默认为3个keyword
            jsonData = "{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":\"%s\",\"topcolor\":\"#FF0000\",\"data\":{\"first\": {\"value\":\"%s\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"%s\", \"color\":\"#173177\"},\"keyword2\":{\"value\":\"%s\", \"color\":\"#173177\"},\"keyword3\":{\"value\":\"%s\",\"color\":\"#173177\"},\"remark\":{\"value\":\"%s\",\"color\":\"#173177\"}}}";
        }


        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, keywords));

        if (null != jsonObject) {
            try {
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                String msgid = jsonObject.getString("msgid");

                log.info("发送模板消息成功 errcode:{} errmsg:{} template_id：{}", errorCode, errorMsg, msgid);
                return msgid;
            } catch (JSONException e) {
                log.error("发送模板消息失败");
            }
        }
        return "";
    }

    public static void main(String[] args) {

        // 签证
        // 线路预约成功 old TUFh0aWqLIwDJ48aGBcwOmnISWdMaRVGyS9Qpgn0NDc  bGO2m1FNM_T6v6o8GtDn9d-VTkCLT_2Xg8GIHDn45QE
        TemplateMsgUtil.sendTemplateMsg("ovFm4uK9DYRtW1YOoSEO3XOeadXM", "bGO2m1FNM_T6v6o8GtDn9d-VTkCLT_2Xg8GIHDn45QE", "http://www.x2our.com",
                  "您的美国签证面试预约成功，面试时间为2015年 7月6日。", "美国个人旅游签证", "2015年9月5日", "8人","点击查进度详情");

        //行程安排
        // 行程安排提醒 old Q2AfAMyzGlvi1QFC0pCO3G-RenGMjLgQH6-VsFNMfSI ZzBlost9o69xjGL-4TkG2POxKYzenT4pUBTcySX8nMo
        TemplateMsgUtil.sendTemplateMsg("ovFm4uK9DYRtW1YOoSEO3XOeadXM", "ZzBlost9o69xjGL-4TkG2POxKYzenT4pUBTcySX8nMo", "http://www.x2our.com",
                 "Jerry，您好！根据您刚刚提交的需求，我帮您安排的行程如下", "美国西部小环线12日游", "2015年9月25日", "6位成人，2位儿童", "有任何建议或疑问，请您直接回复给我，谢谢！");
    }


}