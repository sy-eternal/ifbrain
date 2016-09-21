package com.jzeen.travel.wechat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzeen.travel.data.entity.WeChatCustServEvent;
import com.jzeen.travel.data.entity.WeChatUser;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.WeChatCustServEventRepository;
import com.jzeen.travel.data.repository.WeChatUserRepository;
import com.jzeen.travel.service.wecaht.WeChatUserService;
import com.jzeen.travel.website.setting.WebUrlSetting;
import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.message.model.Article;
import com.jzeen.travel.wechat.message.resp.BaseMessage;
import com.jzeen.travel.wechat.message.resp.NewsMessage;
import com.jzeen.travel.wechat.message.resp.TextMessage;
import com.jzeen.travel.wechat.utils.HttpRequestUtil;
import com.jzeen.travel.wechat.utils.MessageUtil;
import com.jzeen.travel.wechat.utils.UserUtil;

/**
 * Title: X2OUR_TRAVEL
 * Description: 事件处理器
 * Date: 2015年08月02日
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */
@Service
public class EventMessageService extends BaseMessageService {

    private static Logger log = LoggerFactory.getLogger(EventMessageService.class);
    @Autowired
    private WeChatUserRepository weChatUserRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private WebUrlSetting  webUrlSetting;

    @Autowired
    private WeChatUserService weChatUserService;

    @Autowired
    private WeChatCustServEventRepository weChatCustServEventRepository;

    public String processMessage(Map<String, String> requestMap, BaseMessage baseMessage) {

        String respMessage = "";

        String webRoot = webUrlSetting.getRootUrl();

        // 事件类型
        String eventType = requestMap.get("Event");
        // 用户关注
        if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
            TextMessage textMessage = new TextMessage(baseMessage);

//            textMessage.setContent("终于来到西游旅行了，想要玩转美国吗？那就与西游一起前去吧！\n\n" +
//                    "骑马？打猎?潜水？跳伞？攀岩？冲浪？24小时之内就可以帮您实现专属您的美国之行。\n\n" +
//                    "西游在中国和美国，拥有最靠谱、最强大的旅游资源，绝对给你最地道的体验。\n\n" +
//                    "你带着她，西游带着你！LET’S GO !红包可别忘了拿。");

//            String signature = SignUtil.genSHADoubleStr(WeChatConts.bindToken, baseMessage.getToUserName());
//            String url = WeChatConts.webURL + "/redpackactivity/add" + "?openId=" + baseMessage.getToUserName() + "&signature=" + signature;

//            String content = "终于来到嘻游定制旅行了。\n\n" +
//                    "我们每天任性的发放1万元红包，看您敢不敢接！\n\n" +
//                    "点此开始 <a href=\"" + url + "\">" + "【抢红包】" + "</a>\n\n" +
//                    "抢红包时间：8月28日-8月30日中午12点。\n\n" +
//                    "千万不要错过抢红包的最佳时机哦！\n\n" +
//                    "嘻游定制旅行，做最专业的定制旅行！\n\n" +
//                    "直接回复文字或语音可以联系我们，欢迎互动哦！";
            //获取用户信息，并保存到数据库中
            String content;
            WeChatUser weChatUser = UserUtil.getUserInfo(baseMessage.getToUserName());
            	content="感谢您对儿童财经素养的关注!";
            textMessage.setContent(content);
            respMessage = MessageUtil.messageToXml(textMessage);
            // 如果获取到的用户openid为空，重新获取一遍
           if (weChatUser.getOpenid() == null) {
                weChatUser = UserUtil.getUserInfo(baseMessage.getToUserName());
            }
           weChatUserService.userSubscribe(weChatUser);
        }
        // 取消关注
        else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
            // 取消关注，修改数据库状态
            weChatUserService.userUnSubscribe(baseMessage.getToUserName());
        }
        // 扫描带参二维码
        else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
            log.info("进入扫描带参数的二维码事件");

            TextMessage textMessage = new TextMessage(baseMessage);
            textMessage.setContent("欢迎扫描嘻游二维码，更多红包大奖等你来哦，转发也多红包也多。");
            respMessage = MessageUtil.messageToXml(textMessage);
        }
        // 上报地理位置
        else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
            // TODO 处理上报地理位置事件
        }
        // 自定义菜单点击事件
        else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
            // 事件KEY值，与创建自定义菜单时指定的KEY值对应
            String eventKey = requestMap.get("EventKey");

            if (eventKey.equals("12")) {
                //帮我定制
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(baseMessage.getToUserName());
                newsMessage.setFromUserName(baseMessage.getFromUserName());
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setFuncFlag(0);

                List<Article> articleList = new ArrayList<Article>();

                Article article1 = new Article();
                article1.setTitle("美国西部线路之西部小环线，体验真正的风土人情");
                article1.setDescription("美国西部线路之西部小环线，体验真正的风土人情");
                article1.setPicUrl(WeChatConts.webURL + "/wechatimage/menu/plan/w12days/b14c387fc30a0a7d889da99eba8a4185-sz_225554.jpg");
                article1.setUrl(WeChatConts.webURL + "/wechatmenuevent/plan?name=w12days");

                Article article2 = new Article();
                article2.setTitle("美国东西海岸精品定制游，够酷够炫够痛快。");
                article2.setDescription("美国东西海岸精品定制游，够酷够炫够痛快");
                article2.setPicUrl(WeChatConts.webURL + "/wechatimage/menu/plan/ew17days/03c7de468d0cf84696a2321d04195e9e-sz_133444.jpg");
                article2.setUrl(WeChatConts.webURL + "/wechatmenuevent/plan?name=ew17days");

                Article article3 = new Article();
                article3.setTitle("没意思？不合胃口？登陆嘻游官方网站，有更多好玩的好吃的线路哦。");
                article3.setDescription("登陆嘻游官方网站，有更多好玩的好吃的线路哦。");
                article3.setPicUrl(WeChatConts.webURL + "/assets/frontend/onepage/img/wechatQR.jpg");
                article3.setUrl(WeChatConts.webURL);

                articleList.add(article1);
                articleList.add(article2);
                articleList.add(article3);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respMessage = MessageUtil.messageToXml(newsMessage);
            }else if (eventKey.equals("31")) {
            	 NewsMessage newsMessage = new NewsMessage();
                 newsMessage.setToUserName(baseMessage.getToUserName());
                 newsMessage.setFromUserName(baseMessage.getFromUserName());
                 newsMessage.setCreateTime(new Date().getTime());
                 newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                 newsMessage.setFuncFlag(0);
                 List<Article> articleList = new ArrayList<Article>();
            	Article article = new Article();
            	article.setTitle("给孩子自信做决定的机会，该跟选择困难症说拜拜喽");
            	article.setPicUrl(WeChatConts.webURL + "/assets/frontend/onepage/img/please.png");
            	article.setDescription("诚邀您和孩子参加 Financial Literacy 财脑课程B2级体验!  面对选择这个人生难题,"
            			+ "作为父母经常都会束手无策,最后可能常常以凭感觉草草了事."
            			+ "其实孩子也会面对和您一样的困扰,甚至连简单的选择买哪件玩具、哪本书、哪样零食都百般纠结."
            			+ "这时往往父母就代替孩子做了决定,为此，孩子发个脾气,哭闹一场真是家常便饭."
            			+ "那到底为什么会这样？是不是孩子长大了问题自然就解决了？做选择对孩子来说到底有这么难吗?"
            			);
            	articleList.add(article);
            	newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
               /* TextMessage textMessage = new TextMessage(baseMessage);
                textMessage.setContent("大爷的!");*/
                respMessage = MessageUtil.messageToXml(newsMessage);
            }else if (eventKey.equals("14")) {
                //登陆嘻游

                TextMessage textMessage = new TextMessage(baseMessage);
                textMessage.setContent(userService.getLoginContent(textMessage.getToUserName()));
                respMessage = MessageUtil.messageToXml(textMessage);
            } /*else if (eventKey.equals("21")) {
                // 规划师介绍
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(baseMessage.getToUserName());
                newsMessage.setFromUserName(baseMessage.getFromUserName());
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setFuncFlag(0);

                List<Article> articleList = new ArrayList<Article>();

                Article article1 = new Article();
                article1.setTitle("James Ding 嘻游首席旅行规划师,为您量身打造专属旅行。");
                article1.setDescription("James Ding 嘻游首席旅行规划师");
                article1.setPicUrl(WeChatConts.webURL + "/wechatimage/menu/guide/james1.png");
                article1.setUrl(WeChatConts.webURL + "/wechatmenuevent/guide?name=james.jpg");

                Article article2 = new Article();
                article2.setTitle("叶小欣 最劲酷的专业玩家，带你玩转带你飞。");
                article2.setDescription("叶小欣 最劲酷的专业玩家");
                article2.setPicUrl(WeChatConts.webURL + "/wechatimage/menu/guide/yexx1.jpg");
                article2.setUrl(WeChatConts.webURL + "/wechatmenuevent/guide?name=yexx.jpg");

                Article article3 = new Article();
                article3.setTitle("太少了?不合胃口？嘻游网拥有数十位专业资深规划师导游，了解更多请访问官方网站");
                article3.setDescription("嘻游网拥有数十位专业资深规划师导游，更多请访问官方官网");
                article3.setPicUrl(WeChatConts.webURL + "/assets/frontend/onepage/img/wechatQR.jpg");
                article3.setUrl(WeChatConts.webURL);

                articleList.add(article1);
                articleList.add(article2);
                articleList.add(article3);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respMessage = MessageUtil.messageToXml(newsMessage);

            }*/ else if (eventKey.equals("22")) {
                // 美国游记，图文信息

                // 创建图文消息
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(baseMessage.getToUserName());
                newsMessage.setFromUserName(baseMessage.getFromUserName());
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setFuncFlag(0);

                List<Article> articleList = new ArrayList<Article>();

                Article article1 = new Article();
                article1.setTitle("探寻“微型”国游记--摩洛西共和国");
                article1.setDescription("什么？？就在我们身边里面还有一个微型国家？还不去看看？2015年7月18日，叶小心随同一名会照相的好司机去探究了传说中的摩洛西共和国。");
                article1.setPicUrl(WeChatConts.webURL + "/wechatimage/menu/notes/mini/761151.jpg");
                article1.setUrl(WeChatConts.webURL + "/wechatmenuevent/notes");

                articleList.add(article1);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respMessage = MessageUtil.messageToXml(newsMessage);

            } else if (eventKey.equals("23")) {
                // 个人信息
                if (userService.hasBindOpenId(baseMessage.getToUserName())) {
                    TextMessage textMessage = new TextMessage(baseMessage);
                    textMessage.setContent("开发中，请稍后。");
                    respMessage = MessageUtil.messageToXml(textMessage);
                } else {
                    String unBindContent = userService.getBindtent(baseMessage.getToUserName());

                    TextMessage textMessage = new TextMessage(baseMessage);
                    textMessage.setContent(unBindContent);
                    respMessage = MessageUtil.messageToXml(textMessage);
                }
            } else if (eventKey.equals("24")){
                TextMessage textMessage = new TextMessage(baseMessage);
                textMessage.setContent("您好，今天签到成功，获得50积分奖励，目前积分为1075分。 本月再签到3天，可再获得200分奖励。");
                respMessage = MessageUtil.messageToXml(textMessage);
            } /*else if (eventKey.equals("31")) {

                try {
                    WeChatCustServEvent custServEvent = new WeChatCustServEvent();
                    custServEvent.setOpenid(baseMessage.getToUserName());
                    custServEvent.setClickTime(Long.valueOf(new Date().getTime() / 1000).intValue());
                    custServEvent.setCreateTime(new Date());
                    weChatCustServEventRepository.save(custServEvent);
                } catch (Exception e) {
                    log.error("保存用户点击客服菜单事件异常:{}", e.toString());
                }

                // 随便问问，回复文本，引导进入客服
                TextMessage textMessage = new TextMessage(baseMessage);
                textMessage.setContent("您好，有什么可以帮助到您？\n您可以直接在对话框里，输入想要知道的任何事情。\n嘻游在这里等待您的提问哦！");
                respMessage = MessageUtil.messageToXml(textMessage);

            }*/ else if (eventKey.equals("32")) {
                //修改为签证攻略，不再使用

                // 绑定微信
                TextMessage textMessage = new TextMessage(baseMessage);
                textMessage.setContent(userService.bindOpenId(textMessage.getToUserName()));
                respMessage = MessageUtil.messageToXml(textMessage);

            } else if (eventKey.equals("33")) {
                // 使用帮助
                // 创建图文消息
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(baseMessage.getToUserName());
                newsMessage.setFromUserName(baseMessage.getFromUserName());
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setFuncFlag(0);

                List<Article> articleList = new ArrayList<Article>();

                Article article1 = new Article();
                article1.setTitle("嘻游公司是国内规模最大外海导游数量最多的旅游资源最丰富的专业旅游公司，是您最优的选择!");
                article1.setDescription("嘻游公司是国内规模最大外海导游数量最多的旅游资源最丰富的专业旅游公司，是您最优的选择");
                article1.setPicUrl(webRoot + "/assets/frontend/onepage/img/wechatQR.jpg");
                article1.setUrl(webRoot + "/home/aboutus");

                Article article2 = new Article();
                article2.setTitle("中信旅游是嘻游的战略合作伙伴，值得信赖！");
                article2.setDescription("中信旅游是嘻游的战略合作伙伴，值得信赖！");
                article2.setPicUrl(webRoot + "/assets/frontend/onepage/img/partners/zhongxin.png");
                article2.setUrl("http://www.zjcitic.com.cn/");

                Article article3 = new Article();
                article3.setTitle("忘记密码了吧，不用怕，戳这里进去修改吧");
                article3.setDescription("忘记密码了吧，不用怕，戳这里进去修改吧");
                article3.setPicUrl(webRoot + "/assets/frontend/onepage/img/partners/aliyun.png");
                article3.setUrl(String.format(WeChatConts.testOauth, HttpRequestUtil.urlEncodeUTF8(webRoot + "/wechatoauth/personalcenter")));

                articleList.add(article1);
                articleList.add(article2);
                articleList.add(article3);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respMessage = MessageUtil.messageToXml(newsMessage);

            }
        }
        return respMessage;
    }
    public static void main(String[] args) {
    	
	}
}
