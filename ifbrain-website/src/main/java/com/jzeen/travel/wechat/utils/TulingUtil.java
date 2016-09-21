package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.message.model.Article;
import com.jzeen.travel.wechat.message.resp.BaseMessage;
import com.jzeen.travel.wechat.message.resp.NewsMessage;
import com.jzeen.travel.wechat.message.resp.TextMessage;
import com.jzeen.travel.wechat.pojo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description:图灵机器人
 * Date: 2015年 08月 19日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class TulingUtil {

    private static Logger log = LoggerFactory.getLogger(TulingUtil.class);

    public static String getTullingInfo2Wechat(BaseMessage baseMessage, String content) {

        String respMessage;

        TulingInfo tulingInfo = TulingUtil.getTulingInfo(content, baseMessage.getToUserName());

        int code = tulingInfo.getCode();

        try {

            if (code == 100000) {
                TextMessage textMessage = new TextMessage(baseMessage);
                textMessage.setContent(tulingInfo.getText());
                respMessage = MessageUtil.messageToXml(textMessage);

            } else if (code == 200000) {
                TextMessage textMessage = new TextMessage(baseMessage);
                String reMsg = tulingInfo.getText() + "<a href=\"" + tulingInfo.getUrl() + "\">" + "点击链接" + "</a>";
                textMessage.setContent(reMsg);
                respMessage = MessageUtil.messageToXml(textMessage);

            } else if (code == 302000) {
                if (tulingInfo.getNewsList() != null) {
                    List<Article> articleList = new ArrayList<Article>();

                    NewsMessage newsMessage = new NewsMessage();
                    newsMessage.setToUserName(baseMessage.getToUserName());
                    newsMessage.setFromUserName(baseMessage.getFromUserName());
                    newsMessage.setCreateTime(new Date().getTime());
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    newsMessage.setFuncFlag(0);

                    int length = tulingInfo.getNewsList().size() > 10 ? 10 : tulingInfo.getNewsList().size();

                    for (int i = 0; i < length; i++) {

                        TulingInfoNews news = tulingInfo.getNewsList().get(i);

                        Article article = new Article();
                        article.setTitle(news.getArticle());
                        article.setDescription("来源:" + news.getSource());
                        article.setPicUrl(news.getIcon());
                        article.setUrl(news.getDetailurl());

                        articleList.add(article);
                    }

                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.messageToXml(newsMessage);
                } else {
                    //内容为空,至返回text
                    TextMessage textMessage = new TextMessage(baseMessage);
                    textMessage.setContent(tulingInfo.getText());
                    respMessage = MessageUtil.messageToXml(textMessage);
                }

            } else if (code == 305000) {
                if (tulingInfo.getTrainList() != null) {
                    List<Article> articleList = new ArrayList<Article>();

                    NewsMessage newsMessage = new NewsMessage();
                    newsMessage.setToUserName(baseMessage.getToUserName());
                    newsMessage.setFromUserName(baseMessage.getFromUserName());
                    newsMessage.setCreateTime(new Date().getTime());
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    newsMessage.setFuncFlag(0);

                    int length = tulingInfo.getTrainList().size() > 10 ? 10 : tulingInfo.getTrainList().size();

                    for (int i = 0; i < length; i++) {
                        TulingInfoTrain train = tulingInfo.getTrainList().get(i);

                        Article article = new Article();
                        String title = train.getTrainnum() + "  " + train.getStart() + " - " +
                                train.getTerminal() + "    " + train.getStarttime() + " - " +
                                train.getEndtime();

                        article.setTitle(title);
                        article.setDescription(title);
                        article.setPicUrl(train.getIcon());
                        article.setUrl(train.getDetailurl());

                        articleList.add(article);
                    }

                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.messageToXml(newsMessage);
                } else {
                    //内容为空,至返回text
                    TextMessage textMessage = new TextMessage(baseMessage);
                    textMessage.setContent(tulingInfo.getText());
                    respMessage = MessageUtil.messageToXml(textMessage);
                }

            } else if (code == 308000) {
                if (tulingInfo.getCookList() != null) {
                    List<Article> articleList = new ArrayList<Article>();

                    NewsMessage newsMessage = new NewsMessage();
                    newsMessage.setToUserName(baseMessage.getToUserName());
                    newsMessage.setFromUserName(baseMessage.getFromUserName());
                    newsMessage.setCreateTime(new Date().getTime());
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    newsMessage.setFuncFlag(0);

                    //最大返回10条消息
                    int length = tulingInfo.getCookList().size() > 10 ? 10 : tulingInfo.getCookList().size();

                    for (int i = 0; i < length; i++) {
                        TulingInfoCook cook = tulingInfo.getCookList().get(i);

                        Article article = new Article();
                        article.setTitle(cook.getName() + " -  " + cook.getInfo());
                        article.setDescription(cook.getName() + " -  " + cook.getInfo());
                        article.setPicUrl(cook.getIcon());
                        article.setUrl(cook.getDetailurl());

                        articleList.add(article);
                    }

                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.messageToXml(newsMessage);
                } else {
                    //内容为空,至返回text
                    TextMessage textMessage = new TextMessage(baseMessage);
                    textMessage.setContent(tulingInfo.getText());
                    respMessage = MessageUtil.messageToXml(textMessage);
                }

            } else if (String.valueOf(code).startsWith("400")) {
                // 返回错误
                TextMessage textMessage = new TextMessage(baseMessage);
                textMessage.setContent("您提的问题我们将尽快给您回复.");
                respMessage = MessageUtil.messageToXml(textMessage);

            } else {
                TextMessage textMessage = new TextMessage(baseMessage);
                textMessage.setContent(tulingInfo.getText());
                respMessage = MessageUtil.messageToXml(textMessage);
            }

        } catch (Exception e){
            log.error("机器人消息转换成微信消息出错:{}", e.toString());

            TextMessage textMessage = new TextMessage(baseMessage);
            textMessage.setContent("您提的问题我们将尽快给您回复.");
            respMessage = MessageUtil.messageToXml(textMessage);
        }
        return respMessage;
    }


    /**
     * 获取图灵机器人信息
     *
     * @param info 发送消息
     * @param userid 用户标示
     * @return
     */
    public static TulingInfo getTulingInfo(String info, String userid) {

        try {
            info = URLEncoder.encode(info, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
        }

        String requestUrl = WeChatConts.TULING_API + "?key=" + WeChatConts.TULING_KEY + "&info=" + info + "&userid=" + userid;

        log.info("向机器人问问题:{}",info);

        JSONObject jsonObject = JSONObject.fromObject(HttpRequestUtil.httpRequest(requestUrl));

        log.info("机器人回复:{}",jsonObject.toString());


        TulingInfo tulingInfo = null;

        if (null != jsonObject) {
            try {
                // code代码
                // 100000	文本类数据
                // 305000	列车
                // 200000	网址类数据
                // 302000	新闻
                // 308000	菜谱、视频、小说
                // 40001	key的长度错误（32位）
                // 40002	请求内容为空
                // 40003	key错误或帐号未激活
                // 40004	当天请求次数已用完
                // 40005	暂不支持该功能
                // 40006	服务器升级中
                // 40007	服务器数据格式异常
                int code = jsonObject.getInt("code");

                if (code == 100000) {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);
                } else if (code == 200000) {
                    String text = jsonObject.getString("text");
                    String url = jsonObject.getString("url");
                    tulingInfo = new TulingInfo(code, text, url);
                } else if (code == 302000) {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);

                    List<TulingInfoNews> newsList = JSONArray.toList(jsonObject.getJSONArray("list"), TulingInfoNews.class);
                    tulingInfo.setNewsList(newsList);
                } else if (code == 305000) {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);

                    List<TulingInfoTrain> trainList = JSONArray.toList(jsonObject.getJSONArray("list"), TulingInfoTrain.class);
                    tulingInfo.setTrainList(trainList);
                } else if (code == 308000) {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);

                    List<TulingInfoCook> cookList = JSONArray.toList(jsonObject.getJSONArray("list"), TulingInfoCook.class);
                    tulingInfo.setCookList(cookList);
                } else if (String.valueOf(code).startsWith("400")) {
                    // 返回错误
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);
                } else {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);
                }

            } catch (Exception e) {
                log.error("机器人获取对话信息失败:{}", e.toString());
            }
        }
        return tulingInfo;
    }


    /**
     * 获取图灵机器人信息
     *
     * @param info
     *            用户标识
     * @return String
     */
    public static TulingInfo getTulingInfo(String info) {

        try {
            info = URLEncoder.encode(info, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
        }

        String requestUrl = WeChatConts.TULING_API + "?key=" + WeChatConts.TULING_KEY + "&info=" + info;

        log.info("向机器人问问题:{}", info);

        JSONObject jsonObject = JSONObject.fromObject(HttpRequestUtil.httpRequest(requestUrl));

        log.info("机器人回复:{}", jsonObject.toString());


        TulingInfo tulingInfo = null;

        if (null != jsonObject) {
            try {
                int code = jsonObject.getInt("code");

                if (code == 100000) {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);
                } else if (code == 200000) {
                    String text = jsonObject.getString("text");
                    String url = jsonObject.getString("url");
                    tulingInfo = new TulingInfo(code, text, url);
                } else if (code == 302000) {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);

                    List<TulingInfoNews> newsList = JSONArray.toList(jsonObject.getJSONArray("list"), TulingInfoNews.class);
                    tulingInfo.setNewsList(newsList);

                } else if (code == 305000) {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);

                    List<TulingInfoTrain> trainList = JSONArray.toList(jsonObject.getJSONArray("list"), TulingInfoTrain.class);
                    tulingInfo.setTrainList(trainList);
                } else if (code == 308000) {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);

                    List<TulingInfoCook> cookList = JSONArray.toList(jsonObject.getJSONArray("list"), TulingInfoCook.class);
                    tulingInfo.setCookList(cookList);
                } else if (String.valueOf(code).startsWith("400")) {
                    // 返回错误
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);
                } else {
                    String text = jsonObject.getString("text");
                    tulingInfo = new TulingInfo(code, text);
                }

            } catch (Exception e) {
                log.error("机器人获取对话信息失败:{}", e.toString());
            }
        }
        return tulingInfo;
    }


    public static void main(String args[]) throws UnsupportedEncodingException {
//        System.out.println(getTulingInfo("去美国旅游"));
//        System.out.println(getTulingInfo("今天 新闻"));
//        System.out.println(getTulingInfo("北京 广州 火车"));
//
        System.out.println(getTulingInfo("天气", "ovFm4uAeu-ofAntLDiY1Ww76d9Cw"));
        System.out.println(getTulingInfo("北京", "ovFm4uAeu-ofAntLDiY1Ww76d9Cw"));
        System.out.println(getTulingInfo("新闻", "ovFm4uAeu-ofAntLDiY1Ww76d9Cw"));
        System.out.println(getTulingInfo("图片", "ovFm4uAeu-ofAntLDiY1Ww76d9Cw"));
        System.out.println(getTulingInfo("东坡肉怎么做", "ovFm4uAeu-ofAntLDiY1Ww76d9Cw"));

    }

}