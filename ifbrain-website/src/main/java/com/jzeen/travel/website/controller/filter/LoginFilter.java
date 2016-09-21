package com.jzeen.travel.website.controller.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;

@Component
public class LoginFilter implements Filter {
    /**
     * 登录地址。
     */
    private final String _loginPath = "/home/index";
    private final String _shiyong = "/project/shiyong";
    //微信首页请求
    private final String _weChatPath = "/home/wechatindex";
    //微信用户绑定
    private final String _weChatUserPath = "/wechatuser/bind";
   
    //视频
    private final String _video = "/home/ifbrain/upload";
    
    
    //微信用户授权,我的菜单
    private final String _weChatOauthPath = "/wechatoauth/trips";
    //微信用户,评估菜单
    private final String _weChatOauthPathAssement = "/wechatoauth/assementwechat";
    //微信用户,任务菜单
    private final String _weChatOauthPathWork = "/wechatoauth/projectwechat";
    //微信用户,商品菜单
    private final String _weChatOauthPathShop = "/wechatoauth/commoditywechat";
    //微信用户,课程菜单
    private final String  _weChatOauthPathCourse = "/wechatoauth/coursewechat";
    //微信用户,活动菜单
    private final String _weChatOauthPathActivity = "/wechatoauth/activitywechat";
    
    
    //微信核心处理请求
    private final String _weChatCorePath = "/wechat";
    // 微信菜单点击事件（图文消息）
    private final String _weChatMenuEvent = "/wechatmenuevent";

    private final String _wechatArtical = "/wechatartical";
    private final String _kecheng = "/course/kctable";
    private final String _mv="/course/create";
    //租车
    private final String _rentalcar="/rentalcar";
    //字体过滤
    private final String _font = "/font/font.ttf";
    
    //手机验证码回调
    private final String _openctcRecall = "/openctcsms/recall";

    //关于我们
    private final String _hometeam = "/home/team";
    //写游记
    private final String _writeTripe="/home/writetrip";

    //手机验证登录
    private final String _telephone = "/code/getsmsvalidation";
    //手机提交
    private final String _telephonesubmit = "/code/submitsmsvalidation";
    //手机验证
    private final String _telephonecodesubmit = "/code/smssubmit";
    //手机跳转
    private final String _telephonepassword = "/user/phonepassword";
    //手机登录密码保存
    private final String _telephonepasswordsubmit = "/user/phonepasswordsubmit";/*
    //跳转个人信息
    private final String _personalcenter = "/personalCenter/create";*/

    // 微信抢红包活动
    private final String _wechatRedPackAct = "/redpackactivity";
    private final String _bingtu = "/fenxi/bingtu";

    //微信开放平台
    private final String _WeChatOpenPlatform = "/user/wechatopenplatform";
    //微信开放平台code获取
    private final String _WeChatGetCode = "/openwechat/redirect";

    //导游中心
    private final String _guidecenter = "/personalCenter/doCreate1";
    
    //忘记邮箱登录密码
    private final String _forgetemailpassword="/user/forgetemailpassword";
    //忘记手机登录密码
    private final String _forgetphonepassword="/user/forgetphonepassword";

    private final String _aboutusPath = "/home/aboutus";
    //    private final String _indexPath = "";
    private final String _loginPaths = "/user/login";
    private final String _tripsPath = "/trips";
    
    //微信用户我的菜单
    private final String _ifbrainPath = "/trips/wechattrips";
    //微信用户评估菜单
    private final String _ifbrainPathAssement= "/trips/wechatassement";
    //微信用户支付
    private final  String  _wechatpay="/wechatpay/signup";
    //微信用户创建订单
    private final String _wechatpayorder="/wechatpay/createorder";
    /*//微信支付回调
    private final String  _wechatpayreturn="/wechatpay/payreturn";*/
    
    
    
    //生成二维码
    private final String _wechaterweima="/wechatpay/createerweima";
    //课程活动微信用户支付
    private final  String  _wechatpayactivity="/wechatpay/activitysignupagain";
    //课程活动微信用户创建订单
    private final String _wechatpayorderactivity="/wechatpay/activitycreateorder";
    
    
    
    
    
    //   private final String _centerPath = "/center";
    private final String _registerPath = "/user/register";
    private final String _loginsPath = "/user/logins";
    private final String _phoneloginPath = "/user/phonelogin";
    private final String _userloginPath = "/user/travelindex";
    private final String _activePath = "/user/active";
    private final String _guidePath = "/guidetaskarrangementtime/*";
    //忘记密码Controller地址
    private final String _forgotPasswordPath = "/user/forgotpassword";
    //验证码Controller地址
    private final String _imgPath = "/codeimg/img";
    private final String _AliPayPaths = "/AliPay/NotifyVisaOrderUrl";

    private final String _AliPayPath = "/AliPay/Return";

    private final String _alipayErrPath = "/order/alipayErr";

    private final String _NotifyPathr = "/Notify";

    private final String _AliPayvVisaSubmit = "/alipay/visasubmit";

    private final String _NotifyPathrs = "/ReturnVisaOrder";

    /*  private final String _ReturnPath="/Return";*/
    private final String _alipayAPIPath = "/order/alipayAPI";
    private final String _centersPath = "/center/*";
    private final String _SubmitPath = "/Submit/*";
    private final String _VisaPath = "/visa";
    private final String _visadownload = "/visa/download";
    private final String _visadownloads = "/downloads";
    private final String _visaimage = "/image";
    private final String _pageNew = "/home/tripblog";
    private final String _visaSubmit = "/VisaSubmit/*";
    //导游登录
    private final String _guidelogin = "/guide/login";
    //导游注册
    private final String _guideregister = "/guide/register";
    //私人向导
    private final String _privateguide = "/home/privateguide";
    //经典路线
    private final String _classicroute = "/home/classicroute";

    //经典路线
    private final String _spot = "/home/spots";
    //邮箱验证
    private final String _getUserByEmail="/user/getuserbyemail";
    //电话验证
    private final String _getUserByPhone="/user/getuserbyphone";
    
    //邮箱密码重置
    private final String _resetemailpwd="/user/resetemailpassword";
    //电话密码重置
    private final String _resetphonepwd="/user/resetphonepassword";
    
    //美国攻略下的美国美食
    private final String _strategyfood = "/strategyfood";
    //特色酒店
    private final String _hoteltype = "/hotel/hotelType";
    //酒店具体介绍
    private final String _hotelspecific = "/hotel/hotelSpecific";
    //私人向导列表
    private final String _guidedetail = "/home/guidedetail";
    //登录
    private final String _logins = "/user/logins";

    
    //特色酒店
    private final String _hotelsearch = "/hotel/searchhotel";

    
    //美国公司介绍
    private final String _usaincintroduction="/home/usaincintroduction";

    //飞机票
    private final String _flightPath = "/flight/list";

    //财脑
    private final String _ifbrain = "/home/ifbrain";
    
    //课程
    private final String _course = "/course/index";
    
    //活动
    private final String _activity = "/activity/index";
    //文章列表
    private final String _materiallist = "/activity/materiallist";
    //文章详细信息
    private final String _materialdetail = "/activity/materialdetail";
    
    private final String _offlinecourse = "/course/offlinecourse";
    //关于我们
    private final String _team = "/home/team";
    //进入试用
    private final String _enteruse="/home/enteruse";
    
    //免费视频
    private final String _freevideo="/moviematerial/play";
    
    //首页代币银行
    private String _daibibank="/project/daibibank";
    //进入试用
    private final String _ourproject="/home/ourproject";
    //进入试用
    private final String _ourteam="/home/ourteam";
    //进入试用
    private final String _literacy="/home/literacy";
    
    //孩子的评估
  private final String _assessment="/assessment/list";
    
    private String  _materialimage="/materialimage";
    
    private String  _carouselimage="/carouselimage";
     //线上课程
    private String  _officeonlie="/moviematerial/offlinecourse";
    //图片
    private final String _parentmanualimage="/parentmanualimage";
    
    private String  _officeonlieshowcourseinformations="/moviematerial/showcourseinformations";
    
    private String  _officeonlieshowcoursemovielist="/moviematerial/materialtypelist";
    
    private String  _officeonlieshowmovie="/moviematerial/showmovie";
    
    private String _officecoursehome ="/moviematerial/offlinecoursehome";
    //财脑课程介绍
    private String _ifbrainindexintroduction ="/course/ifbrainindexintroduction";
    private String _ifbrainindexintroductionhome ="/course/ifbrainindexintroductionhome";
    private String _clickbuttontoggle ="/course/clickbuttontoggle";
   
    
    private String _projectlist ="/project/li";
    //马斯洛
    private String _clickmaslowdemand ="/demandcityshopping/maslowdemand";
    
  //家长课堂
    private String _clickfamilycourse ="/course/familycourse";
    private String _clickfamilycourselist ="/course/familycourselist";
    private String _clickfamilycoursedetail ="/course/familycoursedetail";
    
    private String _clickcommodityindex ="/commodity/index";
    private String _clickassessmentassessmenthome ="/assessment/assessmenthome";
    
    private String _clickfamilyincomeassement ="/familyincomeassement/familyincome";
    
    private String _clickfamilyexpenses ="/familyexpenses/familyexpenses";
    //导航栏
    private String _navigationindex ="/navigation/index";
    
    private String _partialfooternew ="/partial/footer-new";
    
    private String _footermaterialindex ="/footermaterial/index";
    
    private String _ifbraintasklist="/ifbraintask/list";
    
    private String _ifbraintaskcourse="/ifbraintask/taskcourse";
    
    private String _ifbraintaskuploadordownloadtask="/ifbraintask/uploadordownloadtask";
    private String _ifbrainindexintroductionlist="/course/ifbrainindexintroductiondetail";
    
    private String _offlinecoursehome="/course/offlinecoursehome";
    private String _offlinecoursedetail="/course/offlinecoursedetail";
    private String _uploadordownloadtask="/ifbraintask/downloads";
    private String _demendwishlist="/wish/demendwishlist";
    private String _wishindex="/wish/index";
    
    private String _initwish="/wish/initwish";
    private String _initshangcheng="/wish/initshangcheng";
    
    private String _commodityclassification ="/demandcityshopping/commodityclassification";
    
    
    private String _shoppingtogo="/demandcityshopping/shoppingtogo";
    private String _mywishlist="/wish/mywhishlist";
    private String _nomywishlist="/wish/nomywhishlist";
    //考试index
    private String _itemmanagement="/itemmanagement/index";
    //选择考试
    private String _toexamination="/itemmanagement/toexamination";
  //开始考试
    private String _beginexamination="/itemmanagement/beginexamination";
    
    //开始考试
    private String _myscore="/itemmanagement/myscore";
    
    private String headernew ="/home/headernew";

    private String slide ="/home/slide";
    
    private String characteristic ="/characteristic/index";
    
    
    private String characteristicvip ="/characteristic/vipindex";
    
    //考试统计图
    
    private String _coursetest ="/itemmanagement/coursetest";
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        // 获得在下面代码中要用的request,respogetRootUrlnse,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        HttpSession session = servletRequest.getSession();
        session.setMaxInactiveInterval(40 * 60);
        // 获得用户请求的URI
        String requestPath = servletRequest.getRequestURI();
        if (!needFilter(requestPath)) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 从session里取员工工号信息
        Object user = session.getAttribute("user");


        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (user == null) {
            // 跳转到登录页面

            String queryString = servletRequest.getQueryString();
            String returnUrl = requestPath;
            if (!StringUtils.isNullOrEmpty(queryString)) {
                returnUrl = requestPath + "?" + queryString;
            }
            returnUrl = URLEncoder.encode(returnUrl, "UTF-8");

            //  servletResponse.sendRedirect(_loginPath + "?returnUrl=" + returnUrl);
            servletResponse.sendRedirect(_loginPath);
        } else {
            // 已经登录,继续此次请求
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 请求地址是否需要过滤。
     *
     * @param requestPath 请求地址。
     * @return 请求地址是否需要过滤。
     */
    private boolean needFilter(String requestPath) {
        // 登录页面无需过滤
        if (requestPath.toLowerCase().startsWith(_loginPath)) {
            return false;
        }
        //忘记手机登录密码
        if(requestPath.toLowerCase().startsWith(_forgetphonepassword)){
        	return false;
        }
        //电话密码重置
        if (requestPath.toLowerCase().startsWith(_resetphonepwd)) {
            return false;
        }
        //孩子评估
       if(requestPath.toLowerCase().startsWith(_assessment)){
        	return false;
        }
        if (requestPath.toLowerCase().startsWith(_ourteam)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_ourproject)) {
            return false;
        }
        //忘记邮箱登录密码
        if (requestPath.toLowerCase().startsWith(_forgetemailpassword)) {
            return false;
        }
        //查询邮箱是否存在
        if (requestPath.toLowerCase().startsWith(_getUserByEmail)) {
            return false;
        }if (requestPath.toLowerCase().startsWith(_kecheng)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_materialimage)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_carouselimage)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_mv)) {
            return false;
        }
        //查询电话是否存在
        if (requestPath.toLowerCase().startsWith(_getUserByPhone)) {
            return false;
        }
        //邮箱密码重置
        if (requestPath.toLowerCase().startsWith(_resetemailpwd)) {
            return false;
        }
        //免费视频
        if (requestPath.toLowerCase().startsWith(_freevideo)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_literacy)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_logins)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_spot)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_shiyong)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_guidePath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_rentalcar)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_enteruse)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_daibibank)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_userloginPath)) {
            return false;
        } if (requestPath.toLowerCase().startsWith(_hometeam)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_activePath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_loginPaths)) {
            return false;
        } if (requestPath.toLowerCase().startsWith(_bingtu)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_writeTripe)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_font)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_guidelogin)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_loginsPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_hotelsearch)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_privateguide)) {
            return false;
        }
        
        if (requestPath.toLowerCase().startsWith(_guidedetail)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_pageNew)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_classicroute)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_guideregister)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_tripsPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_guidecenter)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_AliPayvVisaSubmit)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_WeChatOpenPlatform)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_WeChatGetCode)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_registerPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_phoneloginPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_forgotPasswordPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_imgPath)) {
            return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbrainPath)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbrainPathAssement)){
        	return false;
        }
        if (requestPath.startsWith(_AliPayPath)) {
            return false;
        }
        if (requestPath.startsWith(_telephone)) {
            return false;
        }
        if (requestPath.startsWith(_wechatpay)) {
            return false;
        }
        if (requestPath.startsWith(_wechatpayorder)) {
            return false;
        }
        if (requestPath.startsWith(_wechatpayactivity)) {
            return false;
        }
        if (requestPath.startsWith(_wechatpayorderactivity)) {
            return false;
        }
        if(requestPath.toLowerCase().startsWith(_wechaterweima)){
        	return false;
        }
        if (requestPath.startsWith(_telephonesubmit)) {
            return false;
        }
        if (requestPath.startsWith(_telephonepassword)) {
            return false;
        }

        if (requestPath.startsWith(_telephonepasswordsubmit)) {
            return false;
        }
        if (requestPath.startsWith(_telephonecodesubmit)) {
            return false;
        }
        if (requestPath.startsWith(_hoteltype)) {
            return false;
        }
        if (requestPath.startsWith(_hotelspecific)) {
            return false;
        }
       /* if (requestPath.startsWith(_personalcenter)) {
            return false;
        }*/
        if (requestPath.toLowerCase().startsWith(_alipayErrPath)) {
            return false;
        }
        if (requestPath.startsWith(_NotifyPathr)) {
            return false;
        }
        if (requestPath.startsWith(_AliPayPaths)) {
            return false;
        }
        if (requestPath.startsWith(_NotifyPathrs)) {
            return false;
        }

        if (requestPath.toLowerCase().startsWith(_alipayAPIPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_centersPath)) {
            return false;
        }
        if (requestPath.startsWith(_SubmitPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_VisaPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_visadownload)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_visadownloads)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_visaimage)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_visaSubmit)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_aboutusPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_weChatPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_weChatUserPath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_video)) {
            return false;
        }
        
        if (requestPath.toLowerCase().startsWith(_weChatOauthPath)) {
            return false;
        }
        if(requestPath.toLowerCase().startsWith(_weChatOauthPathAssement)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_weChatOauthPathWork)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_weChatOauthPathShop)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_weChatOauthPathCourse)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_weChatOauthPathActivity)){
        	return false;
        }
        if (requestPath.toLowerCase().startsWith(_weChatCorePath)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_weChatMenuEvent)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_wechatRedPackAct)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_openctcRecall)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_strategyfood)) {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_wechatArtical)) {
            return false;
        }
        if(requestPath.toLowerCase().startsWith(_usaincintroduction)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_flightPath)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbrain)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_course)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_activity)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_materiallist)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_materialdetail)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_offlinecourse)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_team)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_officeonlie)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_officeonlieshowcourseinformations)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_parentmanualimage)){
        	return false;
        }
        
        if(requestPath.toLowerCase().startsWith(_officeonlieshowcoursemovielist)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_officeonlieshowmovie)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_officecoursehome)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbrainindexintroduction)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbrainindexintroductionhome)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbrainindexintroductionlist)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_clickbuttontoggle)){
        	return false;
        }    
       if(requestPath.toLowerCase().startsWith(_projectlist)){
        	return false;
        }
        /*if(requestPath.toLowerCase().startsWith(_clickmaslowdemand)){
        	return false;
        }*/
        if(requestPath.toLowerCase().startsWith(_clickfamilycourse)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_clickfamilycourselist)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_clickfamilycoursedetail)){
        	return false;
        }
        
        if(requestPath.toLowerCase().startsWith(_clickcommodityindex)){
        	return false;
        }
        
        if(requestPath.toLowerCase().startsWith(_clickassessmentassessmenthome)){
        	return false;
        }
        
        if(requestPath.toLowerCase().startsWith(_clickfamilyexpenses)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_clickfamilyincomeassement)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_navigationindex)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_partialfooternew)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_footermaterialindex)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbraintasklist)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbraintaskcourse)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_ifbraintaskuploadordownloadtask)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_officecoursehome)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_offlinecoursedetail)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_clickmaslowdemand)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_offlinecoursehome)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_uploadordownloadtask)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_demendwishlist)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_wishindex)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_initshangcheng)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_initwish)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_commodityclassification)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_shoppingtogo)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_mywishlist)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_nomywishlist)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_itemmanagement)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_toexamination)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_beginexamination)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_myscore)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(headernew)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(slide)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(characteristic)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(characteristicvip)){
        	return false;
        }
        if(requestPath.toLowerCase().startsWith(_coursetest)){
        	return false;
        }
        // 静态资源无需过滤
        if (requestPath.toLowerCase().startsWith("/assets/") || requestPath.toLowerCase().startsWith("/css/")
                || requestPath.toLowerCase().startsWith("/css/") || requestPath.toLowerCase().startsWith("/img/")
                || requestPath.toLowerCase().startsWith("/js/") || requestPath.toLowerCase().startsWith("/webjars/")
                || requestPath.toLowerCase().startsWith("/scripts/") || requestPath.toLowerCase().startsWith("/tripjs/")
                ||requestPath.toLowerCase().startsWith("/assets/index/")) {
            return false;
        }

        return true;
    }
}
