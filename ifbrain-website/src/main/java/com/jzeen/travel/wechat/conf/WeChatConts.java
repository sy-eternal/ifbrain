package com.jzeen.travel.wechat.conf;

import com.jzeen.travel.wechat.pojo.AccessToken;
import com.jzeen.travel.wechat.utils.CommonUtil;
import com.jzeen.travel.wechat.utils.HttpRequestUtil;


/**
 * Title: X2OUR_TRAVEL
 * Description: 常量类
 * Date: 2015年 08月 03日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class WeChatConts {

    public static final String token = "ifbrain";

    public static final String getTicket="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    // 接口测试号
//    public static final String webURL = "http://115.28.5.128:8028";
//    public static final String appID = "wx2245394660fd6fd5";
//    public static final String appsecret = "f9ba63ab58a68aba7fc461a9ae35dada";
//    public static final String certLocalPath = "/home/limin/upload/wechat/cert/xiyoutour/apiclient_cert.p12";



    // 正式接口
    public static final String DOMAIN_NAME = "www.ifbrain.com";
    public static final String webURL = "http://" + DOMAIN_NAME;
    // yousee8090 公众号
//    public static final String appID = "wx2f8c219d8b486a36";
//    public static final String appsecret =  "e74993e1cca53909aeb4353c9459942d";
//    public static final String encodingAESKey = "7OnwxWmWoZWiLvDG97Pm1tAuerNKGXVf4FP5bZf9V8d";
//    public static final String certLocalPath = "/home/travel/upload/wechat/cert/apiclient_cert.p12";
    // xiyoutours 公众号
    public static final String appID = "wx376e9e0842a14b11";
    public static final String appsecret =  "903264bb7c6bd7ad2434317deb120191";
    public static final String encodingAESKey = "Gl0X3xW749p4MtxZBiz1pOhcYXwdVOsllAnypLW10Fw";
    public static final String certLocalPath = "/home/travel/upload/wechat/cert/apiclient_cert.p12";

    public static final String  codeGet="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";

    // 商户号
    // 8090商户号
//    public static final String MCH_ID = "1259395501";
//    public static final String CERT_PASSWORD = "1259395501";
//    public static final String mmpayKey = "zk89dkld20dkc0kdk12kddlcopa17dmb";

    // xiyoutours商户号
    public static final String MCH_ID = "1285316201";
    public static final String CERT_PASSWORD = "1266495301";
    //public static final String mmpayKey = "k97dncV93dxopV3vqLmIs6CpweR87Fgt";
    public static final String mmpayKey = "1234567890123456789012345ifbrain";

    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public final static String bindToken = "ifbrain";

    // AccessToken 静态类
    public static AccessToken accessToken = null;

    public static AccessToken getTokenInst() {
    	if (WeChatConts.accessToken == null) {
            WeChatConts.accessToken = CommonUtil.getAccessToken(WeChatConts.appID, WeChatConts.appsecret);
        }
        return WeChatConts.accessToken;
    }


    // 定义数据分隔符
    public static final String boundary = "------------7da2e536604c8";


    // 多媒体文件上传接口
    public static final String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    // 多媒体文件下载接口
    public static final String downloadMediaUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";


    // 临时素材(图片)上传
    public static final String uploadTempPicUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";




    // ----Oauth页面授权start-----------------------------------------------------------------
    public static final String oauth2State = "t4state";

    // 使用IP地址，待修改
    public static final String oauthRedirectUrl = webURL + "/wechatoauth/redirect";



    //微信公众号中菜单子菜单我的。
    public static final String TRIPS_URL = webURL + "/wechatoauth/trips";
    //微信公众号中评估
    public static final String ASSEMENT = webURL + "/wechatoauth/assementwechat";
    //微信公众号中的任务 
    public static final String WORK = webURL + "/wechatoauth/projectwechat";
    //微信公众号中的购物
    public static final String SHOP = webURL + "/wechatoauth/commoditywechat";
    //微信公众号中的课程
    public static final String COURSE = webURL + "/wechatoauth/coursewechat";
    //微信公众号中的活动
    public static final String ACTIVITY = webURL + "/wechatoauth/activitywechat";
    
    
    
     //微信公众号中菜单子菜单我的。
    public static final String OAUTH_TRIPS_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=" + HttpRequestUtil.urlEncodeUTF8(TRIPS_URL) + "&response_type=code&scope=snsapi_userinfo&state=" + oauth2State + "#wechat_redirect";
     //微信公众号中评估
    public static final String OAUTH_WECHAT_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=" + HttpRequestUtil.urlEncodeUTF8(ASSEMENT) + "&response_type=code&scope=snsapi_userinfo&state=" + oauth2State + "#wechat_redirect";
    //微信公众号中菜单中的任务
    public static final String OAUTH_WORK_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=" + HttpRequestUtil.urlEncodeUTF8(WORK) + "&response_type=code&scope=snsapi_userinfo&state=" + oauth2State + "#wechat_redirect";;
    //微信公众号中菜单中的购物
    public static final String OAUTH_SHOP_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=" + HttpRequestUtil.urlEncodeUTF8(SHOP) + "&response_type=code&scope=snsapi_userinfo&state=" + oauth2State + "#wechat_redirect";;
    //微信公众号中菜单中的课程
    public static final String OAUTH_COURSE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=" + HttpRequestUtil.urlEncodeUTF8(COURSE) + "&response_type=code&scope=snsapi_userinfo&state=" + oauth2State + "#wechat_redirect";;
    //微信公众号中菜单中的活动
    public static final String  OAUTH_ACTIVITY_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=" + HttpRequestUtil.urlEncodeUTF8(ACTIVITY) + "&response_type=code&scope=snsapi_userinfo&state=" + oauth2State + "#wechat_redirect";;
    
    
    
    public static final String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=" + HttpRequestUtil.urlEncodeUTF8(oauthRedirectUrl) + "&response_type=code&scope=snsapi_userinfo&state=" + oauth2State + "#wechat_redirect";

    public static final String testOauth = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri=%s" +  "&response_type=code&scope=snsapi_userinfo&state=" + oauth2State + "#wechat_redirect";


    // 通过code换取页面授权 access_token URL
    public static final String codeGetTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    // 刷新access_token
    public static final String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    // 通过网页授权获取用户信息
    public static final String oauthGetUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";




    // ----Oauth页面授权end-----------------------------------------------------------------


    // ----模板消息start-----------------------------------------------------------------

    //设置行业
    public static final String templateIndustryUrl = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";

    //新增模板
    public static final String templateAddtemplUrl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";

    //发送模板消息
    public static final String templateSendMegUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    //三分钟扫描一次
    public static final long templateMsgScanTime = 3 * 60 * 1000;


    // ----模板消息end-------------------------------------------------------------------


    // ----客服接口start-----------------------------------------------------------------
    public static final String customMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    // ----客服接口end-------------------------------------------------------------------


    // ----客服接口start-----------------------------------------------------------------
    public static final String GET_KF_SESSION = "https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=ACCESS_TOKEN&openid=OPENID";
    public static final String CLOSE_KF_SESSION = "https://api.weixin.qq.com/customservice/kfsession/close?access_token=ACCESS_TOKEN";

    // ----客服接口end-------------------------------------------------------------------

    // ----用户认证start----------------------------------------------------------------
    // 用户认证链接的超时时间(30分钟)
    public static final long expTime = 30 * 60 * 1000;

    //用户认证URL
    public static final String bindURL = "/wechatuser/bind";

    // ----用户认证end----------------------------------------------------------------


    // ----微信支付start----------------------------------------------------------------

    //以下是几个API的路径：
    //红包发送URL
    public static final String SEND_REDPACK_API = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
    //1）被扫支付API
    public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";
    //2）被扫支付查询API
    public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";
    //3）退款API
    public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    //4）退款查询API
    public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";
    //5）撤销API
    public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";
    //6）下载对账单API
    public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";
    //7) 统计上报API
    public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

    public static String TRANSFERS_API = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    //logo地址
    public static final String logo_imgurl = "http://www.x2our.com/assets/frontend/onepage/img/logo/xylogo.png";
    // ----微信支付end----------------------------------------------------------------



    // ----用户管理start----------------------------------------------------------------
    public static final String GET_USER_INFO_API = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        //批量获取用户信息
    public static final String BATCHGET_USER_API = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

    public static final String GET_USER_LIST_API = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        //创建分组
    public static final String CREATE_GROUPS_API = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
        //查询分组
    public static final String GET_GROUPS_API = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
        //修改分组
    public static final String UPDATE_GROUPS_API = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
        //移动分组
    public static final String UPDATE_MEMBER_GROUPS_API = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";


    // ----用户管理end----------------------------------------------------------------

    // ----二维码start----------------------------------------------------------------
    public static final String QR_CODE_API = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";

    public static final String SHOW_QR_CODE_API = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";


    // ----二维码end----------------------------------------------------------------

    // ----菜单管理start----------------------------------------------------------------
        // 菜单创建（POST）
    public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        // 菜单查询（GET）
    public final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
        // 菜单删除（GET）
    public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    // ----菜单管理end----------------------------------------------------------------

    //常用的API
    //图灵机器人
    public static final String TULING_API = "http://www.tuling123.com/openapi/api";
    public static final String TULING_KEY = "28761b6fd2f1fd73b6e979e7936d5418";
    // 人脸检测
    public static final String SENDPATH9 = "http://apicn.faceplusplus.com/v2/detection/detect?url=URL&api_secret=18PekbemsZn-x954KT-bb18HKjRkSw9e&api_key=fbe9455f3e3721cd89c97f393a7fc0a7";

}
