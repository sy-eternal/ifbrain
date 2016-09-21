package com.jzeen.travel.wechat.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.Activity;
import com.jzeen.travel.data.entity.ActivityOrder;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseOrder;
import com.jzeen.travel.data.entity.Discount;
import com.jzeen.travel.data.entity.Money;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ActivityOrderRepository;
import com.jzeen.travel.data.repository.ActivityRepository;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CourseOrderReponsitory;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.DiscountReponsitory;
import com.jzeen.travel.data.repository.MoneyRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.WeChatUserRepository;
import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.GetWxOrderno;
import com.jzeen.travel.wechat.pojo.OAuth2Token;
import com.jzeen.travel.wechat.pojo.SNSUserInfo;
import com.jzeen.travel.wechat.pojo.WechatPay;
import com.jzeen.travel.wechat.utils.Oauth2Util;
import com.jzeen.travel.wechat.utils.RequestHandler;
import com.jzeen.travel.wechat.utils.Sha1Util;
import com.jzeen.travel.wechat.utils.TenpayUtil;


/**
 * 微信支付
 * @author  sunyan
 *
 */
@Controller
@RequestMapping("/wechatpay")
public class WechatPayController{
	    @Autowired
	    private CourseReponsitory _courseReponsitory;
	    @Autowired
	    private CourseOrderReponsitory _courseOrderReponsitory;
	    @Autowired
	    UserRepository _userRepository;
		@Autowired
		DiscountReponsitory _DiscountReponsitory;
		@Autowired
		ChildRepository _ChildRepository;
	    @Autowired
	    WeChatUserRepository  weChatUserRepository;
	    @Autowired
	    ActivityOrderRepository   _activityOrderRepository;
	    @Autowired
	    ActivityRepository  _aActivityRepository;
	    @Autowired
	    private  MoneyRepository _MoneyRepository;
	    //微信公众平台报名
		@RequestMapping(value = "/signupagain", method = RequestMethod.GET)
	    public String signupagain(Model model, HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
			String code = request.getParameter("code");
			String orderNo="ordernumber"+Sha1Util.getTimeStamp();
	            // 获取网页授权access_token
	            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
	            // 网页授权接口访问凭证
	            String accessToken = oAuth2Token.getAccessToken();
	            // 用户标识
	            String openId = oAuth2Token.getOpenId();
	            // 获取用户信息
	            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);
	            model.addAttribute("openId", snsUserInfo.getOpenId());
	            model.addAttribute("orderNo",orderNo);
	            model.addAttribute("userid", "b88001");
	            model.addAttribute("signupbiaoshi", 1);
	            WebUtils.setSessionAttribute(request, "signupbiaoshi", 1);
	            WebUtils.setSessionAttribute(request, "openId", snsUserInfo.getOpenId());
	            WebUtils.setSessionAttribute(request, "orderNo", orderNo);
	            WebUtils.setSessionAttribute(request, "userid", "b88001");
	            return "/user/register";
			
	    }
		//获得选中课程所选的价格
		   @ResponseBody
		@RequestMapping(value = "/getcourseprice", method = RequestMethod.POST)
	    public Course getcourseprice(Model model, HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
			  String classname2 = request.getParameter("classname2");
			  Course courseprice =_courseReponsitory.findPriceById(Integer.parseInt(classname2));
		        model.addAttribute("courseprice",courseprice.getPrice());
	            return courseprice;
			
	    }
		
		   
		   
		   
   /**
    * 创建订单
    * @param model
    * @param request
    * @param response
    * @return
 * @throws Exception 
    */
		   @ResponseBody
		   @RequestMapping(value = "/createorder", method = RequestMethod.POST)
		   public WechatPay createsignup( Model model, HttpServletRequest request,HttpServletResponse response) throws Exception
		   {
			   
			   //课程级别
			   String courseId = request.getParameter("classname2");
			   //孩子姓名
			   String childname = request.getParameter("amp;childname");
			   //订单总金额
			   String amount = request.getParameter("amp;price");
			   //String amount="0.01";
			   String openId = request.getParameter("amp;openId");
			   String orderNo = request.getParameter("amp;orderNo");
			   String userid = request.getParameter("amp;userid");
			   //孩子性别
			   String gender = request.getParameter("amp;gender");
			   //孩子年龄
			   String age=request.getParameter("amp;age");
			   //孩子出生年月
			   String birth = request.getParameter("amp;birth");
			   //折扣码
			 String discountcode = request.getParameter("amp;discountcode");
			 WebUtils.setSessionAttribute(request, "discountcode", discountcode);
			   Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birth); 
			
			 //金额转化为分为单位
				float sessionmoney = Float.parseFloat(amount);
				String finalmoney = String.format("%.2f", sessionmoney);
				finalmoney = finalmoney.replace(".", "");
				//商户相关资料 
				String appid = "wx376e9e0842a14b11";
				String appsecret = "";
				String partner = "1285316201";
				String partnerkey = "1234567890123456789012345ifbrain";
				//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
				String currTime = TenpayUtil.getCurrTime();
				User user = (User) WebUtils.getSessionAttribute(request,"user");
				
				//8位日期
				String strTime = currTime.substring(8, currTime.length()); 
				//四位随机数
				String strRandom = TenpayUtil.buildRandom(4) + "";
				//10位序列号,可以自行调整。
				String strReq = strTime + strRandom;
				//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
				String notify_url =WeChatConts.webURL+"/wechatpay/payreturn";
				//商户号
				String mch_id = partner;
				//子商户号  非必输
				//String sub_mch_id="";
				//设备号   非必输
				String device_info="WEB";
				//随机数 
				String nonce_str = "ibuaiVcKdpRxkhJQ";
				//商品描述根据情况修改
				String body = "课程报名";
				//附加数据
				String attach = childname+","+age+","+birth+","+gender+","+discountcode+","+user.getId()+","+courseId+","+amount+","+openId;
				//商户订单号
				String out_trade_no = orderNo;
				//String out_trade_no ="1415659991";
				int total_fee = Integer.parseInt(finalmoney);
				//订单生成的机器 IP
				String spbill_create_ip = request.getRemoteAddr();
				String trade_type = "JSAPI";
				String openid = openId;
				//非必输
//				String product_id = "";  "<body><![CDATA["+body+"]]></body>"+
				//签名 sign有疑问
				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("appid", appid);
		        packageParams.put("mch_id", mch_id);
		        packageParams.put("nonce_str", nonce_str);
		        packageParams.put("body", body);
		        packageParams.put("attach", attach);
		        packageParams.put("out_trade_no", out_trade_no);
		        packageParams.put("total_fee", Integer.toString(total_fee));
		        packageParams.put("spbill_create_ip", spbill_create_ip);
		        packageParams.put("notify_url", notify_url);
		        packageParams.put("trade_type", trade_type);
		        packageParams.put("openid", openid);
				RequestHandler reqHandler = new RequestHandler(null, null);
				String sign = reqHandler.createSign(packageParams,"UTF-8");
				String xml="<xml>"+
						"<appid>"+appid+"</appid>"+
						"<attach>"+attach+"</attach>"+
						 "<body><![CDATA["+body+"]]></body>"+
						"<mch_id>"+mch_id+"</mch_id>"+
						"<nonce_str>"+nonce_str+"</nonce_str>"+
						"<notify_url>"+notify_url+"</notify_url>"+
						"<openid>"+openid+"</openid>"+
						"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
						"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
						"<total_fee>"+total_fee+"</total_fee>"+
						"<trade_type>"+trade_type+"</trade_type>"+
						"<sign>"+sign+"</sign>"+
						"</xml>";
				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String prepay_id="";
				try {
					prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
					if(prepay_id.equals("")){
						/*request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");*/
						model.addAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		            String appid2 = appid;
		            String timestamp = Sha1Util.getTimeStamp();
		            String nonceStr2 = nonce_str;
		            String prepay_id2 = "prepay_id="+prepay_id;
		            String packages = prepay_id2;
		            finalpackage.put("appId", appid2);
		            finalpackage.put("timeStamp", timestamp);
		            finalpackage.put("nonceStr", nonceStr2);
		            finalpackage.put("package", packages);
		            finalpackage.put("signType", "MD5");
		            String finalsign = reqHandler.createSign(finalpackage,"UTF-8");
		            model.addAttribute("appId",appid2);
		            model.addAttribute("timeStamp",timestamp);
		            model.addAttribute("nonceStr",nonceStr2);
		            model.addAttribute("package",packages);
		            model.addAttribute("paySign",finalsign);
		            model.addAttribute("orderNo", orderNo);
		            WechatPay pay=new WechatPay();
		            pay.setAppId(appid2);
		            pay.setTimeStamp(timestamp);
		            pay.setNonceStr(nonceStr2);
		            pay.setPackages(packages);
		            pay.setPaySign(finalsign);
					return  pay;
		    }
	  
	    
	  
	    
	    @RequestMapping(value = "/payreturn", method = RequestMethod.POST)
	    public void notify(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
	    	/*String discountcode = request.getParameter("discountcode");*/
	    	BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        String line = null;
	        StringBuilder sb = new StringBuilder();
	        while((line = br.readLine())!=null){
	            sb.append(line);
	        }
	        String xml = sb.toString();
	        Map map = GetWxOrderno.doXMLParse(xml);
	        String ordernumber  = (String) map.get("out_trade_no");
	        String time_end  = (String) map.get("time_end");
	        String attach =(String)map.get("attach");
	        String[] split = attach.split(",");
	        String childname=split[0];
	        String age=split[1];
	        String birth=split[2];
	        String gender=split[3];
	        String discountcode = split[4];
	        String userId=split[5];
	        String courseId=split[6];
	        String amount=split[7];
	        String openid=split[8];
	      //根据课程id获得课程级别的名字。     
		    Course course = _courseReponsitory.findOne(Integer.parseInt(courseId));
	        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birth); 
	    	   //创建孩子信息
			   Child child=new Child();
			   child.setAge(Integer.parseInt(age));
			   child.setBirth(date);
			   child.setGender(Integer.parseInt(gender));
			   child.setName(childname);
			   User user = _userRepository.findOne(Integer.parseInt(userId));
			   child.setUser(user);
			   child.setCreateTime(new Date());
			   child.setBalance(new BigDecimal(0));
			  _ChildRepository.save(child);
			  Money money=new Money();
	           money.setBalance(new BigDecimal(0));
	           money.setChildId(child);
	           money.setCreateTime(new Date());
	           _MoneyRepository.save(money);
	    	   CourseOrder courseorder = new CourseOrder();
			   courseorder.setNetPrice(new BigDecimal(amount));
			   courseorder.setPayStatus(1); 
			   courseorder.setOpenid(openid);
			   courseorder.setOrderNumber(ordernumber);
			   courseorder.setCreateTime(new Date());
			   courseorder.settCId(child);
			   courseorder.setUserId(user.getId());
			   courseorder.settCId2(course);
			   courseorder.setPrice(course.getPrice());
			   courseorder.setCourseLevel(course.getCourseLevel());
			   courseorder.setPayStatus(1);
			   //paywechatid   这个做标注，尚未解决
			  _courseOrderReponsitory.save(courseorder);
	    	  //改变优惠码的状态
	    	Discount discount = _DiscountReponsitory.findDiscountPriceAndDiscountStatusByDiscountCode(discountcode);
			   if(discount!=null){
				   discount.setDiscountStatus(0);
				   _DiscountReponsitory.save(discount);
			   }
	    String xmlreturn="<xml>"+
					 "<return_code><![CDATA[SUCCESS]]></return_code>"+
	    			"  <return_msg><![CDATA[OK]]></return_msg>"+
					"</xml>";
	    	 response.getWriter().write(xmlreturn);
	    }

	    
	    @ResponseBody
	    @RequestMapping(value = "/discountcode",method = RequestMethod.POST)
	    public Discount discountcode(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String discountcodes = request.getParameter("discountcodes");
	    	Discount discount = _DiscountReponsitory.findDiscountPriceAndDiscountStatusByDiscountCode(discountcodes);
	    
	        return discount;
	    } 
	    
	    
	    
	    //微信公众平台活动报名
		@RequestMapping(value = "/activitysignupagain", method = RequestMethod.GET)
	    public String activitysignupagain(Model model, HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
			String code = request.getParameter("code");
			String orderNo="ordernumber"+Sha1Util.getTimeStamp();
	            // 获取网页授权access_token
	            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
	            // 网页授权接口访问凭证
	            String accessToken = oAuth2Token.getAccessToken();
	            // 用户标识
	            String openId = oAuth2Token.getOpenId();
	            // 获取用户信息
	            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);
	            model.addAttribute("openId", snsUserInfo.getOpenId());
	            model.addAttribute("orderNo",orderNo);
	            model.addAttribute("userid", "b88001");
	            List<Activity> activity = _aActivityRepository.findByActivityType(0);
		    	model.addAttribute("activity", activity);
		    	model.addAttribute("activityId", activity.get(0).getId());
	            return "/wechat/activity";
	    }
	    
	    //活动报名创建订单
		/**
		    * @param model
		    * @param request
		    * @param response
		    * @return
		 * @throws Exception 
		    */
				   @ResponseBody
				   @RequestMapping(value = "/activitycreateorder", method = RequestMethod.POST)
				   public WechatPay activitycreatesignup( Model model, HttpServletRequest request,HttpServletResponse response) throws Exception
				   {
					   
					   //联系人姓名
					   String contactname = request.getParameter("contactname");
					   //联系人手机
					   String contactmobile = request.getParameter("amp;contactmobile");
					   //孩子人数
					   String childNumber = request.getParameter("amp;childnumber");
					   //订单总金额
					   String amount = request.getParameter("amp;activityamount");
					   //String amount="0.01";
					   String orderNo = request.getParameter("amp;orderNo");
					   String userid = request.getParameter("amp;userid");
					   String openId = request.getParameter("amp;openId");
					   String activityId = request.getParameter("amp;activityId");
					
					 //金额转化为分为单位
						float sessionmoney = Float.parseFloat(amount);
						String finalmoney = String.format("%.2f", sessionmoney);
						finalmoney = finalmoney.replace(".", "");
						//商户相关资料 
						String appid = "wx376e9e0842a14b11";
						String appsecret = "";
						String partner = "1285316201";
						String partnerkey = "1234567890123456789012345ifbrain";
						//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
						String currTime = TenpayUtil.getCurrTime();
						//8位日期
						String strTime = currTime.substring(8, currTime.length());
						//四位随机数
						String strRandom = TenpayUtil.buildRandom(4) + "";
						//10位序列号,可以自行调整。
						String strReq = strTime + strRandom;
						//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
						String notify_url ="http://www.ifbrain.com/wechatpay/activitynotify";
						//商户号
						String mch_id = partner;
						//子商户号  非必输
						//String sub_mch_id="";
						//设备号   非必输
						String device_info="WEB";
						//随机数 
						String nonce_str = "ibuaiVcKdpRxkhJQ";
						//商品描述根据情况修改
						String body = "课程活动报名";
						//附加数据
						String attach =amount+","+childNumber+","+contactname+","+contactmobile+","+activityId;
						//商户订单号
						String out_trade_no = orderNo;
						//String out_trade_no ="1415659991";
						int total_fee = Integer.parseInt(finalmoney);
						//订单生成的机器 IP
						String spbill_create_ip = request.getRemoteAddr();
						String trade_type = "JSAPI";
						String openid = openId;
						//非必输
//						String product_id = "";  "<body><![CDATA["+body+"]]></body>"+
						//签名 sign有疑问
						SortedMap<String, String> packageParams = new TreeMap<String, String>();
						packageParams.put("appid", appid);
				        packageParams.put("mch_id", mch_id);
				        packageParams.put("nonce_str", nonce_str);
				        packageParams.put("body", body);
				        packageParams.put("attach", attach);
				        packageParams.put("out_trade_no", out_trade_no);
				        packageParams.put("total_fee", Integer.toString(total_fee));
				        packageParams.put("spbill_create_ip", spbill_create_ip);
				        packageParams.put("notify_url", notify_url);
				        packageParams.put("trade_type", trade_type);
				        packageParams.put("openid", openid);
						RequestHandler reqHandler = new RequestHandler(null, null);
						String sign = reqHandler.createSign(packageParams,"UTF-8");
						String xml="<xml>"+
								"<appid>"+appid+"</appid>"+
								"<attach>"+attach+"</attach>"+
								 "<body><![CDATA["+body+"]]></body>"+
								"<mch_id>"+mch_id+"</mch_id>"+
								"<nonce_str>"+nonce_str+"</nonce_str>"+
								"<notify_url>"+notify_url+"</notify_url>"+
								"<openid>"+openid+"</openid>"+
								"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
								"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
								"<total_fee>"+total_fee+"</total_fee>"+
								"<trade_type>"+trade_type+"</trade_type>"+
								"<sign>"+sign+"</sign>"+
								"</xml>";
						String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
						String prepay_id="";
						try {
							prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
							if(prepay_id.equals("")){
								/*request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");*/
								model.addAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						  SortedMap<String, String> finalpackage = new TreeMap<String, String>();
				            String appid2 = appid;
				            String timestamp = Sha1Util.getTimeStamp();
				            String nonceStr2 = nonce_str;
				            String prepay_id2 = "prepay_id="+prepay_id;
				            String packages = prepay_id2;
				            finalpackage.put("appId", appid2);
				            finalpackage.put("timeStamp", timestamp);
				            finalpackage.put("nonceStr", nonceStr2);
				            finalpackage.put("package", packages);
				            finalpackage.put("signType", "MD5");
				            String finalsign = reqHandler.createSign(finalpackage,"UTF-8");
				            model.addAttribute("appId",appid2);
				            model.addAttribute("timeStamp",timestamp);
				            model.addAttribute("nonceStr",nonceStr2);
				            model.addAttribute("package",packages);
				            model.addAttribute("paySign",finalsign);
				            model.addAttribute("orderNo", orderNo);
				            WechatPay pay=new WechatPay();
				            pay.setAppId(appid2);
				            pay.setTimeStamp(timestamp);
				            pay.setNonceStr(nonceStr2);
				            pay.setPackages(packages);
				            pay.setPaySign(finalsign);
							return  pay;
				    }
				   //活动订单回调
				   @RequestMapping(value = "/activitynotify", method = RequestMethod.POST)
				    public void activitynotify(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
				    	BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
				        String line = null;
				        StringBuilder sb = new StringBuilder();
				        while((line = br.readLine())!=null){
				            sb.append(line);
				        }
				        String xml = sb.toString();
				        Map map = GetWxOrderno.doXMLParse(xml);
				        String ordernumber  = (String) map.get("out_trade_no");
				        String time_end  = (String) map.get("time_end");
				        String attach =(String)map.get("attach");
				        String[] split = attach.split(",");
				        String amount=split[0];
				        String childNumber=split[1];
				        String contactname=split[2];
				        String contactmobile=split[3];
				        String activityId=split[4];
				        Activity activity = _aActivityRepository.findOne(Integer.parseInt(activityId));
				        //创建活动订单
			            ActivityOrder order=new ActivityOrder();
			            order.setActivity(activity);
			            order.setActivityAmount(new BigDecimal(amount));
			            order.setChildNumber(Integer.parseInt(childNumber));
			            order.setOrderNumber(ordernumber);
			            order.setPayStatus(1);
			            order.setContactName(contactname);
			            order.setContactMobile(contactmobile);
			            order.setCreateTime(new Date());
			            order.setPayTime(new Date());
			            _activityOrderRepository.save(order);
				    	String xmlreturn="<xml>"+
								 "<return_code><![CDATA[SUCCESS]]></return_code>"+
				    			"  <return_msg><![CDATA[OK]]></return_msg>"+
								"</xml>";
				    	 response.getWriter().write(xmlreturn);
				    }
				   
				   
				   
				   
				   
				   //会员活动页面
					@RequestMapping(value = "/studentactivitysignupagain", method = RequestMethod.GET)
				    public String studentactivitysignupagain(Model model, HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
						String code = request.getParameter("code");
						String orderNo="ordernumber"+Sha1Util.getTimeStamp();
				            // 获取网页授权access_token
				            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
				            // 网页授权接口访问凭证
				            String accessToken = oAuth2Token.getAccessToken();
				            // 用户标识
				            String openId = oAuth2Token.getOpenId();
				            // 获取用户信息
				            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);
				            model.addAttribute("openId", snsUserInfo.getOpenId());
				            model.addAttribute("orderNo",orderNo);
				            model.addAttribute("userid", "b88001");
				            List<Activity> activity = _aActivityRepository.findByActivityType(1);
					    	model.addAttribute("activity", activity);
					    	model.addAttribute("activityId", activity.get(0).getId());
				            return "/wechat/studentactivityorder";
				    }
					
					
					//会员活动报名创建订单
					/**
					    * @param model
					    * @param request
					    * @param response
					    * @return
					 * @throws Exception 
					    */
							   @ResponseBody
							   @RequestMapping(value = "/studentactivitycreateorder", method = RequestMethod.POST)
							   public WechatPay studentactivitycreatesignup( Model model, HttpServletRequest request,HttpServletResponse response) throws Exception
							   {
								   
								   //联系人姓名
								   String contactname = request.getParameter("contactname");
								   //联系人手机
								   String contactmobile = request.getParameter("amp;contactmobile");
								   //孩子人数
								   String childNumber = request.getParameter("amp;childnumber");
								   //订单总金额
								  String amount = request.getParameter("amp;activityamount");
								   //String amount="0.01";
								   String orderNo = request.getParameter("amp;orderNo");
								   String userid = request.getParameter("amp;userid");
								   String openId = request.getParameter("amp;openId");
								   String activityId = request.getParameter("amp;activityId");
								 //金额转化为分为单位
									float sessionmoney = Float.parseFloat(amount);
									String finalmoney = String.format("%.2f", sessionmoney);
									finalmoney = finalmoney.replace(".", "");
									//商户相关资料 
									String appid = "wx376e9e0842a14b11";
									String appsecret = "";
									String partner = "1285316201";
									String partnerkey = "1234567890123456789012345ifbrain";
									//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
									String currTime = TenpayUtil.getCurrTime();
									//8位日期
									String strTime = currTime.substring(8, currTime.length());
									//四位随机数
									String strRandom = TenpayUtil.buildRandom(4) + "";
									//10位序列号,可以自行调整。
									String strReq = strTime + strRandom;
									//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
									
									String notify_url ="http://www.ifbrain.com/wechatpay/studentactivitynotify";
									/*String notify_url ="http://www.ifbrain.com/wechatpay/activitynotify";*/
									//商户号
									String mch_id = partner;
									//子商户号  非必输
									//String sub_mch_id="";
									//设备号   非必输
									String device_info="WEB";
									//随机数 
									String nonce_str = "ibuaiVcKdpRxkhJQ";
									//商品描述根据情况修改
									String body = "课程活动报名";
									//附加数据
									String attach =amount+","+childNumber+","+contactname+","+contactmobile+","+activityId;
									//商户订单号
									String out_trade_no = orderNo;
									//String out_trade_no ="1415659991";
									int total_fee = Integer.parseInt(finalmoney);
									//订单生成的机器 IP
									String spbill_create_ip = request.getRemoteAddr();
									String trade_type = "JSAPI";
									String openid = openId;
									//非必输
//									String product_id = "";  "<body><![CDATA["+body+"]]></body>"+
									//签名 sign有疑问
									SortedMap<String, String> packageParams = new TreeMap<String, String>();
									packageParams.put("appid", appid);
							        packageParams.put("mch_id", mch_id);
							        packageParams.put("nonce_str", nonce_str);
							        packageParams.put("body", body);
							        packageParams.put("attach", attach);
							        packageParams.put("out_trade_no", out_trade_no);
							        packageParams.put("total_fee", Integer.toString(total_fee));
							        packageParams.put("spbill_create_ip", spbill_create_ip);
							        packageParams.put("notify_url", notify_url);
							        packageParams.put("trade_type", trade_type);
							        packageParams.put("openid", openid);
									RequestHandler reqHandler = new RequestHandler(null, null);
									String sign = reqHandler.createSign(packageParams,"UTF-8");
									String xml="<xml>"+
											"<appid>"+appid+"</appid>"+
											"<attach>"+attach+"</attach>"+
											 "<body><![CDATA["+body+"]]></body>"+
											"<mch_id>"+mch_id+"</mch_id>"+
											"<nonce_str>"+nonce_str+"</nonce_str>"+
											"<notify_url>"+notify_url+"</notify_url>"+
											"<openid>"+openid+"</openid>"+
											"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
											"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
											"<total_fee>"+total_fee+"</total_fee>"+
											"<trade_type>"+trade_type+"</trade_type>"+
											"<sign>"+sign+"</sign>"+
											"</xml>";
									String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
									String prepay_id="";
									try {
										prepay_id = GetWxOrderno.getPayNo(createOrderURL, xml);
										if(prepay_id.equals("")){
											/*request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");*/
											model.addAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
										}
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									  SortedMap<String, String> finalpackage = new TreeMap<String, String>();
							            String appid2 = appid;
							            String timestamp = Sha1Util.getTimeStamp();
							            String nonceStr2 = nonce_str;
							            String prepay_id2 = "prepay_id="+prepay_id;
							            String packages = prepay_id2;
							            finalpackage.put("appId", appid2);
							            finalpackage.put("timeStamp", timestamp);
							            finalpackage.put("nonceStr", nonceStr2);
							            finalpackage.put("package", packages);
							            finalpackage.put("signType", "MD5");
							            String finalsign = reqHandler.createSign(finalpackage,"UTF-8");
							            model.addAttribute("appId",appid2);
							            model.addAttribute("timeStamp",timestamp);
							            model.addAttribute("nonceStr",nonceStr2);
							            model.addAttribute("package",packages);
							            model.addAttribute("paySign",finalsign);
							            model.addAttribute("orderNo", orderNo);
							        
							            WechatPay pay=new WechatPay();
							            pay.setAppId(appid2);
							            pay.setTimeStamp(timestamp);
							            pay.setNonceStr(nonceStr2);
							            pay.setPackages(packages);
							            pay.setPaySign(finalsign);
										return  pay;
							    }
							   //会员活动订单回调
							   @RequestMapping(value = "/studentactivitynotify", method = RequestMethod.POST)
							    public void studentactivitynotify(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
							    	BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
							        String line = null;
							        StringBuilder sb = new StringBuilder();
							        while((line = br.readLine())!=null){
							            sb.append(line);
							        }
							        String xml = sb.toString();
							        Map map = GetWxOrderno.doXMLParse(xml);
							        String ordernumber  = (String) map.get("out_trade_no");
							        String time_end  = (String) map.get("time_end");
							        String attach =(String)map.get("attach");
							        String[] split = attach.split(",");
							        String amount=split[0];
							        String childNumber=split[1];
							        String contactname=split[2];
							        String contactmobile=split[3];
							        String activityId=split[4];
							        Activity activity = _aActivityRepository.findOne(Integer.parseInt(activityId));
							      //创建活动订单
						            ActivityOrder order=new ActivityOrder();
						            order.setActivity(activity);
						            order.setActivityAmount(new BigDecimal(amount));
						            order.setChildNumber(Integer.parseInt(childNumber));
						            order.setOrderNumber(ordernumber);
						            order.setPayStatus(1);
						            order.setContactName(contactname);
						            order.setContactMobile(contactmobile);
						            order.setCreateTime(new Date());
						            order.setPayTime(new Date());
						            _activityOrderRepository.save(order);
							    	String xmlreturn="<xml>"+
											 "<return_code><![CDATA[SUCCESS]]></return_code>"+
							    			"  <return_msg><![CDATA[OK]]></return_msg>"+
											"</xml>";
							    	 response.getWriter().write(xmlreturn);
							    }
							   
}
