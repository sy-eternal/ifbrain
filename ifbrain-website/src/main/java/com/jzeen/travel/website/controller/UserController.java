package com.jzeen.travel.website.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.core.util.CipherUtil;
import com.jzeen.travel.core.util.MailContentUtil;
import com.jzeen.travel.core.util.MailUtil;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.GuideOrder;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.PlanOrder;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.WeChatUser;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.CourseOrderReponsitory;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.GuideOrderRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.PlanOrderRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.WeChatUserRepository;
import com.jzeen.travel.website.controller.trip.TripPlan;
import com.jzeen.travel.website.setting.FileUploadSetting;
import com.jzeen.travel.website.setting.MailSetting;
import com.jzeen.travel.website.setting.WebUrlSetting;
import com.jzeen.travel.wechat.utils.Sha1Util;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	CityRepository _cityRepository;
	@Autowired
	ImageRepository _imageRepository;
	@Autowired
	UserRepository _userRepository;
	@Autowired
	FileUploadSetting _fileUploadSetting;
	@Autowired
	MailSetting _mailSetting;
	@Autowired
	WebUrlSetting _webUrlSetting;
	@Autowired
	CodeRepository _codeRepository;
	@Autowired
	OrderRepository _OrderRepository;
	StringBuffer s = new StringBuffer();
	
	@Autowired
	PlanOrderRepository _planOrderRepository;

	@Autowired
	private ChildRepository _cChildRepository;
	@Autowired
	GuideOrderRepository _guideOrderRepository;

	@Autowired
	WeChatUserRepository _wChatUserRepository;
	@Autowired
    private CourseReponsitory _courseReponsitory;
    @Autowired
    private CourseOrderReponsitory _courseOrderReponsitory;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerInit(@ModelAttribute User user, Model model,HttpServletRequest request) {
	     
		List<City> citys = _cityRepository.findAll();
		model.addAttribute("citys", citys);
		// 导游支付方式
		String type = "支付方式";
		List<Code> code = _codeRepository.findByType(type);
		model.addAttribute("code", code);
		return "/user/register";
	}

	/*
	 * 游客邮箱注册
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid User user, BindingResult bindingResult,
			Model model, HttpServletRequest request) throws IOException {
		// 邮箱标志
		String emailflag = request.getParameter("emailflag");
		model.addAttribute("emailflag", emailflag);
		
		
		// 获得性别
		/*
		 * String sex = request.getParameter("sex"); Integer sexInt =
		 * Integer.parseInt(sex);
		 */

		String messageInfo = "";
		/*
		 * User userInfo =
		 * _userRepository.findByEmailAndUserType(user.getEmail(),1); if
		 * (userInfo != null) { messageInfo = "邮箱已存在，请重新输入";
		 * model.addAttribute("messageInfo", messageInfo); return
		 * "/user/register"; }
		 */
		int authCode = (int) (Math.random() * 9000 + 1000);
		String activeCode = "a" + authCode;
		user.setActivitycode(activeCode);
		// 激活码有效期至当前日期的明天
		Date date = new Date();
		user.setCreateTime(date);
		user.setActiveStatus(0);
		// user.setApprovalStatus(2);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);
		date = calendar.getTime();
		user.setActivityValidity(date);
		String passwrod = user.getPassword();
		user.setPassword(CipherUtil.generatePassword(passwrod));
		user.setLastName("");
		// 保存
		user.setUserType(1);
		_userRepository.save(user);
		messageInfo = "注册成功，请点击邮箱中的激活链接进行激活";
		String username = _mailSetting.getUsername();
		int port = _mailSetting.getPort();
		String host = _mailSetting.getHost();
		String password = _mailSetting.getPassword();
		MailUtil mailUtil = new MailUtil(_mailSetting.getHost(),
				_mailSetting.getPort(), _mailSetting.getUsername(),
				_mailSetting.getPassword());
		String to = user.getEmail();
		String webRoot = _webUrlSetting.getRootUrl();
		String url = webRoot + "/user/active?activeCode=" + activeCode
				+ "&email=" + user.getEmail() + "";
		String subject = "财脑网帐号激活，开始您的财富人生!";
		// String text =
		// MailContentUtil.buildUserRegMailContent(user.getFirstName() + " " +
		// user.getLastName(), webRoot, url);
		String text = "点击链接激活你的邮箱,就可以登陆了!" + url;
		mailUtil.sendHtmlMail(_mailSetting.getFrom(), to, subject, text);
		model.addAttribute("messageInfo", messageInfo);
		return "/user/register";

		/* } */
	}

	/**
	 * 邮箱密码重置第一步
	 * 
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/resetemailpassword", method = RequestMethod.POST)
	public String resetemailpassword(Model model, HttpServletRequest request)
			throws IOException {
		Date date = new Date();

		int authCode = (int) (Math.random() * 9000 + 1000);
		String activeCode = "a" + authCode;
		String email = request.getParameter("email");
		User user = _userRepository.findByEmail(email);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);
		date = calendar.getTime();
		user.setActivityValidity(date);
		user.setActivitycode(activeCode);
		if(user.getUserType()==1){
			user.setUserType(1);
		}else if(user.getUserType()==2){
			user.setUserType(2);
		}else{
			user.setUserType(2);
		}
		_userRepository.save(user);
		MailUtil mailUtil = new MailUtil(_mailSetting.getHost(),
				_mailSetting.getPort(), _mailSetting.getUsername(),
				_mailSetting.getPassword());
		String webRoot = _webUrlSetting.getRootUrl();
		String url = webRoot + "/user/activeA?activeCode=" + activeCode
				+ "&email=" + email + "";
		String subject = "财脑网帐号激活，开始您的财富人生!";
		// String text =
		// MailContentUtil.buildUserRegMailContent(user.getFirstName() + " " +
		// user.getLastName(), webRoot, url);
		String text = "点击链接激活你的邮箱,就可以修改密码了!" + url;
		mailUtil.sendHtmlMail(_mailSetting.getFrom(), email, subject, text);
		model.addAttribute("sendsuccess", "success");
		model.addAttribute("sendemail", email);
		return "/user/forgetemailpassword";
	}

	/**
	 * 邮箱密码重置第二步
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/resetemailpasswordsecond", method = RequestMethod.GET)
	public String resetemailpasswordsecond(Model model,
			HttpServletRequest request) throws IOException {
		String email = request.getParameter("email");
		model.addAttribute("email", email);
		model.addAttribute("resetpasswordflag", "success");
		return "/user/forgetemailpassword";
	}

	/**
	 * 邮箱密码重置第三步
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/resetemailpasswordthird", method = RequestMethod.POST)
	public String resetemailpasswordthird(Model model,
			HttpServletRequest request) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = _userRepository.findByEmail(email);
		user.setPassword(CipherUtil.generatePassword(password));
		String flag = "0";
		_userRepository.save(user);
		return "redirect:/user/login?flag=" + flag;
	}

	/**
	 * 电话密码重置第二步
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resetphonepasswordsecond", method = RequestMethod.POST)
	public String resetphonepasswordsecond(Model model,
			HttpServletRequest request) throws IOException {
		String phone = request.getParameter("phone");
		User user = _userRepository.findByMobile(phone);
		String passwrod = request.getParameter("phoneresetpassword");
		user.setPassword(CipherUtil.generatePassword(passwrod));
		// 保存
		_userRepository.save(user);
		return "redirect:/user/login";
	}

	/**
	 * 电话密码重置第一步
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resetphonepasswordfirst", method = RequestMethod.POST)
	public String resetphonepasswordfirst(Model model,
			HttpServletRequest request) throws IOException {
		String phone = request.getParameter("phonenumber");
		model.addAttribute("phone", phone);
		model.addAttribute("setphonepwdsuccess", "success");
		return "/user/forgetemailpassword";
	}

	/**
	 * 邮箱注册时,邮箱激活!
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String active(HttpServletRequest request) {
		// 通过邮箱查找对应信息
		String activeCode = request.getParameter("activeCode");
		String email = request.getParameter("email");
		User userA = _userRepository.findByEmail(email);
		if (userA != null) {
			// 判断该条信息是否已被激活
			if (userA.getActiveStatus() == 0) {
				// 判断激活码是否过期
				Date currentTime = new Date();
				currentTime.before(userA.getCreateTime());
				if (currentTime.before(userA.getActivityValidity())) {
					if (activeCode.equals(userA.getActivitycode())) {
						userA.setActiveStatus(1);
						_userRepository.save(userA);
					} else {
						_userRepository.delete(userA);
					}
				}
			} else {
				return "redirect:/user/login";
			}
		}
		return "redirect:/user/login";
	}

	/**
	 * 邮箱密码找回时,邮箱激活
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/activeA", method = RequestMethod.GET)
	public String activeA(HttpServletRequest request) {
		// 通过邮箱查找对应信息
		String email = request.getParameter("email");
		User userA = _userRepository.findByEmail(email);
		if (userA != null) {
			// 判断该条信息是否已被激活
			if (userA.getActiveStatus() == 1) {
				// 判断激活码是否过期
				Date currentTime = new Date();
				// currentTime.before(userA.getCreateTime());
				if (currentTime.before(userA.getActivityValidity())) {
					return "redirect:/user/resetemailpasswordsecond?email="
							+ email;
				} else {

				}
			} else {
				return "redirect:/user/login";
			}
		}
		return "redirect:/user/login";
	}

	// dialog
	@RequestMapping(value = "/logins", method = RequestMethod.GET)
	public String loginInit(Model model, HttpServletRequest request,
			HttpSession session) {
		String cookieDomainName = "ifbrain";
		// 读取cookies中的值
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String cookieValue = "";
			if (cookieDomainName.equals(cookies[0].getName())) {
				cookieValue = cookies[0].getValue();
			}
			String[] namepwd = cookieValue.split(":");
			if (namepwd.length == 2) {
				model.addAttribute("email", namepwd[0]);
				model.addAttribute("password", namepwd[1]);
			}
		}

		// 修改手机找回密码成功后的标志
		String flag = request.getParameter("flag");

		String returnUrlTrip = (String) session.getAttribute("returnUrlTrip");
		// 返回导游详情页的地址
		String currentUrl = request.getParameter("currentUrl");
		model.addAttribute("currentUrl", currentUrl);
		model.addAttribute("returnUrlTrip", returnUrlTrip);
		String returnUrl = request.getParameter("returnUrl");
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("flag", flag);
		return "/user/logins";
	}

	// 登录
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginInits(Model model, @ModelAttribute User user,
			HttpServletRequest request, HttpSession session) {
		
		String cookieDomainName = "ifbrain";
		// 读取cookies中的值
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String cookieValue = "";
			if (cookieDomainName.equals(cookies[0].getName())) {
				cookieValue = cookies[0].getValue();
			}
			String[] namepwd = cookieValue.split(":");
			if (namepwd.length == 2) {
				model.addAttribute("email", namepwd[0]);
				model.addAttribute("password", namepwd[1]);
			}
		}

		// 修改手机找回密码成功后的标志
		String flag = request.getParameter("flag");

		String returnUrlTrip = (String) session.getAttribute("returnUrlTrip");
		// 返回导游详情页的地址
		String currentUrl = request.getParameter("currentUrl");
		model.addAttribute("currentUrl", currentUrl);
		model.addAttribute("returnUrlTrip", returnUrlTrip);
		String returnUrl = request.getParameter("returnUrl");
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("flag", flag);
		return "/user/login";
	}

	/**
	 * 微信登录二维码界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/wechatopenplatform", method = RequestMethod.GET)
	public String WeChat() {

		return "/wechatopenplatform/login";
	}

	/**
	 * 微信登录成功后，接收code参数
	 * 
	 * @param user
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/wechatgetcode", method = RequestMethod.GET)
	public String WeChatGetCode(HttpServletRequest request, Model model) {
		String code = request.getParameter("code");
		model.addAttribute("code", code);
		return "/wechatopenplatform/getCode";
	}

	// denglu
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model,
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		String cookieDomainName = "ifbrain";
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String returnUrlTrip = request.getParameter("returnUrlTrip");
		// 导游评论地址
		String currentUrl = request.getParameter("currentUrl");

		System.out.println(returnUrlTrip);
		// 获取页面传过来的邮箱和密码的值
		model.addAttribute("email", name);
		model.addAttribute("password", password);
		/**
		 * 创建cookie
		 */
		String remembervalue = request.getParameter("remembervalue");
		if (remembervalue.equals("1")) {
			String cookievalue = name + ":" + password;
			Cookie cookie = new Cookie(cookieDomainName, cookievalue);
			cookie.setMaxAge(7 * 24 * 60 * 60 * 60);
			response.addCookie(cookie);
		}

		String randCheckCode = (String) session.getAttribute("randCheckCode");
		String validCode = request.getParameter("yanzheng");
                       String email = user.getEmail();
		// 验证码空=========================================
		if (validCode == "" || validCode == null
				|| validCode.equalsIgnoreCase(randCheckCode)) {
			user.setUserType(1);
			User userInDb;
			 
			 if(name.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"))
			 { 
				  userInDb = _userRepository.findByEmailAndPassword(name, CipherUtil.generatePassword(password));
			 }else{ 
				 if(name.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")){
					   userInDb = _userRepository.findByMobileAndPassword(name, CipherUtil.generatePassword(password));
				   }else{
					   userInDb = _userRepository.findByLastNameAndPassword(name, CipherUtil.generatePassword(password));
				   }
				}
			 
    System.out.println(CipherUtil.generatePassword(password));
			if (userInDb != null) {
				if (userInDb.getActiveStatus() == 1) {
					
					WebUtils.setSessionAttribute(request, "user", userInDb);
					WebUtils.setSessionAttribute(request, "usertyoe",
							userInDb.getUserType());
					WebUtils.setSessionAttribute(request, "usertyoes",
							userInDb.getFirstName() + userInDb.getLastName());
					/* int userimg = userInDb.getImage().getId(); */
					User img = _userRepository.findImageById(userInDb.getId());
					if (img == null) {
						WebUtils.setSessionAttribute(request, "userimg",
								userInDb.getImage().getId());
					} else {
						WebUtils.setSessionAttribute(request, "userimg", 0);
					}

					WeChatUser wechat = _wChatUserRepository
							.findByUserId(userInDb.getId());

					if (wechat != null) {
						WebUtils.setSessionAttribute(request, "userwechat",
								wechat.getHeadimgurl());

					} else {
						WebUtils.setSessionAttribute(request, "userwechat", "");
					}
					session.setMaxInactiveInterval(40 * 60);

					// 得到他之前是否有进行过快速规划
					String svaehelpplan = (String) session
							.getAttribute("svaehelpplan");
					// 有就重新走一遍svaehelpplan
					if (svaehelpplan != null) {
						if (svaehelpplan == "/svaehelpplan"
								|| svaehelpplan.equals("/svaehelpplan")) {
							Order order = (Order) session.getAttribute("order");
							order.setTraveler(userInDb);
							Integer id = (Integer) session.getAttribute("id");
							// 防止退回去在提交
							if (order.getId() == null) {
								_OrderRepository.save(order);
								PlanOrder po = new PlanOrder();
								po.setAmount(BigDecimal.valueOf(0.01));
								po.setOrder(order);
								po.setCreateTime(new Date());
								if (id == 1) {
									po.setPlanOfferName("行程规划");
								} else if (id == 2) {
									po.setPlanOfferName("行程规划+一站预定");
								} else if (id == 3) {
									po.setPlanOfferName("行程规划+一站预定+导游随行");
								}
								_planOrderRepository.save(po);
							}
							/*
							 * return
							 * "redirect:/detailtrip/tosettlement?orderid="
							 * +order.getId()+"&id="+id;
							 */
							return "redirect:/detailtrip/tosettlement?orderid="
									+ order.getId() + "&id=" + id;
						}
					}
					/*
					 * GuideOrder guideorder = (GuideOrder)
					 * session.getAttribute("guideorder");
					 * if(guideorder==null||guideorder.equals(null)){ return
					 * "redirect:/"; } else
					 * if(guideorder!=null||!guideorder.equals(null)){
					 * guideorder.setUser(userInDb); Integer id = (Integer)
					 * session.getAttribute("id");
					 * _guideOrderRepository.save(guideorder); return
					 * "redirect:/detailtrip/tosettlementguide?id="+id; }
					 */
					if (returnUrlTrip == "/trips"
							|| returnUrlTrip.equals("/trips")) {
						TripPlan tripPlan = (TripPlan) session
								.getAttribute("tripPlan");
						System.out.println(tripPlan.getId());
						user = (User) WebUtils.getSessionAttribute(request,
								"user");
						model.addAttribute("user", user.getId());
						model.addAttribute("tripPlan", tripPlan);

						return "redirect:/trips?id=" + tripPlan.getId();
					}
					// 判断导游评论地址是否为空,不为空跳转会导游详情
					else if (currentUrl != "" && currentUrl != null) {
						return "redirect:" + currentUrl;
					} else {
						Integer signupbiaoshi =(Integer)request.getSession().getAttribute("signupbiaoshi");
						String openId =(String)request.getSession().getAttribute("openId");
						String orderNo="ordernumber"+Sha1Util.getTimeStamp();
						List<Child> childlist = _cChildRepository.findByUser(userInDb);
						if (childlist.size() > 0) {
							WebUtils.setSessionAttribute(request, "childids",childlist.get(0).getId());
							/* if(signupbiaoshi!=null){
							    model.addAttribute("openId", openId);
							    model.addAttribute("orderNo",orderNo);
					            model.addAttribute("userid", "b88001");
					            
					            List<Course> course = _courseReponsitory.findAll();
						        model.addAttribute("course", course);
						        Course courseprice =_courseReponsitory.findPriceById(course.get(0).getId());
						        model.addAttribute("courseprice",courseprice.getPrice());
					            return  "/wechat/signup";
						      }else{
							    return "redirect:/";
						      }*/
						      return "redirect:/";
						} else {
							WebUtils.setSessionAttribute(request, "childids","");
							/*if(signupbiaoshi!=null){
								  List<Course> course = _courseReponsitory.findAll();
							        model.addAttribute("course", course);
							        Course courseprice =_courseReponsitory.findPriceById(course.get(0).getId());
							        model.addAttribute("courseprice",courseprice.getPrice());
								model.addAttribute("openId", openId);
							    model.addAttribute("orderNo",orderNo);
					            model.addAttribute("userid", "b88001");
							return  "/wechat/signup";
						   }else{
							return "redirect:/";
						   }*/
							return "redirect:/";
						}
						
						
						
					}
				} else {
					model.addAttribute("message", "抱歉，用户没有进行激活无法登录");
					return "/user/login";
				}
			} else {
				if (validCode == "" || validCode == null) {
					// 不输入验证码
					s.append("s");
					if (s.length() == 3) {
						model.addAttribute("visitime", s.length());
						s.delete(0, s.length());
					}
				} else {
					// 输入验证码正确
					model.addAttribute("visitime", 3);
				}

				model.addAttribute("message", "邮箱或密码错误，请重新填写。");

				return "/user/login";
			}

		} else {
			// 验证码错误
			model.addAttribute("yanzhengmessage", "验证码有误，请重新填写。");
			model.addAttribute("visitime", 3);
			return "/user/login";
		}
	}

	// dialog

	@RequestMapping(value = "/logins", method = RequestMethod.POST)
	public String logins(@ModelAttribute User user, Model model,
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String cookieDomainName = "ifbrain";
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String returnUrlTrip = request.getParameter("returnUrlTrip");
		// 导游评论地址
		String currentUrl = request.getParameter("currentUrl");
		String returnUrl = request.getParameter("returnUrl");
		System.out.println(returnUrlTrip);
		// 获取页面传过来的邮箱和密码的值
		model.addAttribute("email", name);
		model.addAttribute("password", password);
		/**
		 * 创建cookie
		 */
		String remembervalue = request.getParameter("remembervalue");
		if (remembervalue.equals("1")) {
			String cookievalue = name + ":" + password;
			Cookie cookie = new Cookie(cookieDomainName, cookievalue);
			cookie.setMaxAge(7 * 24 * 60 * 60 * 60);
			response.addCookie(cookie);
		}

		String randCheckCode = (String) session.getAttribute("randCheckCode");
		String validCode = request.getParameter("yanzheng");
                       String email = user.getEmail();
		// 验证码空=========================================
		if (validCode == "" || validCode == null
				|| validCode.equalsIgnoreCase(randCheckCode)) {
			user.setUserType(1);
			User userInDb;
			 if(name.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"))
			 { 
				  userInDb = _userRepository.findByEmailAndPassword(name, CipherUtil.generatePassword(password));
			 }else {  
				   if(name.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")){
					   userInDb = _userRepository.findByMobileAndPassword(name, CipherUtil.generatePassword(password));
				   }else{
					   userInDb = _userRepository.findByLastNameAndPassword(name, CipherUtil.generatePassword(password));
				   }
				
				 }
			
			if (userInDb != null) {
				if (userInDb.getActiveStatus() == 1) {
					WebUtils.setSessionAttribute(request, "user", userInDb);
					WebUtils.setSessionAttribute(request, "usertyoe",
							userInDb.getUserType());
					WebUtils.setSessionAttribute(request, "usertyoes",
							userInDb.getFirstName() + userInDb.getLastName());
					User img = _userRepository.findImageById(userInDb.getId());
					if (img == null) {
						WebUtils.setSessionAttribute(request, "userimg",
								userInDb.getImage().getId());
					} else {
						WebUtils.setSessionAttribute(request, "userimg", 0);
					}
					WeChatUser wechat = _wChatUserRepository
							.findByUserId(userInDb.getId());

					if (wechat != null) {
						WebUtils.setSessionAttribute(request, "userwechat",
								wechat.getHeadimgurl());

					} else {
						WebUtils.setSessionAttribute(request, "userwechat", "");
					}
					session.setMaxInactiveInterval(40 * 60);

					// 得到他之前是否有进行过快速规划
					String svaehelpplan = (String) session
							.getAttribute("svaehelpplan");
					// 有就重新走一遍svaehelpplan
					if (svaehelpplan != null) {
						if (svaehelpplan == "/svaehelpplan"
								|| svaehelpplan.equals("/svaehelpplan")) {
							Order order = (Order) session.getAttribute("order");
							order.setTraveler(userInDb);
							Integer id = (Integer) session.getAttribute("id");
							// 防止退回去在提交
							if (order.getId() == null) {
								_OrderRepository.save(order);
								PlanOrder po = new PlanOrder();
								po.setAmount(BigDecimal.valueOf(0.01));
								po.setOrder(order);
								po.setCreateTime(new Date());
								if (id == 1) {
									po.setPlanOfferName("行程规划");
								} else if (id == 2) {
									po.setPlanOfferName("行程规划+一站预定");
								} else if (id == 3) {
									po.setPlanOfferName("行程规划+一站预定+导游随行");
								}
								_planOrderRepository.save(po);
							}
							/*
							 * return
							 * "redirect:/detailtrip/tosettlement?orderid="
							 * +order.getId()+"&id="+id;
							 */
							return "redirect:/detailtrip/tosettlement?orderid="
									+ order.getId() + "&id=" + id;
						}
					}
					/*
					 * GuideOrder guideorder = (GuideOrder)
					 * session.getAttribute("guideorder");
					 * if(guideorder==null||guideorder.equals(null)){ return
					 * "redirect:/"; } else
					 * if(guideorder!=null||!guideorder.equals(null)){
					 * guideorder.setUser(userInDb); Integer id = (Integer)
					 * session.getAttribute("id");
					 * _guideOrderRepository.save(guideorder); return
					 * "redirect:/detailtrip/tosettlementguide?id="+id; }
					 */
					if (returnUrlTrip == "/trips"
							|| returnUrlTrip.equals("/trips")) {
						TripPlan tripPlan = (TripPlan) session
								.getAttribute("tripPlan");
						System.out.println(tripPlan.getId());
						user = (User) WebUtils.getSessionAttribute(request,
								"user");
						model.addAttribute("user", user.getId());
						model.addAttribute("tripPlan", tripPlan);

						return "redirect:/trips?id=" + tripPlan.getId();
					}
					// 判断导游评论地址是否为空,不为空跳转会导游详情
					else if (currentUrl != "" && currentUrl != null) {
						return "redirect:" + currentUrl;
					}else if(returnUrl=="/project/li"|| returnUrl.equals("/project/li")){
						List<Child> childlist = _cChildRepository
								.findByUser(userInDb);
						if (childlist.size() > 0) {
							WebUtils.setSessionAttribute(request, "childids",
									childlist.get(0).getId());
							return "redirect:/project/li/"+ childlist.get(0).getId();
						} else {
							return "redirect:/project/li";
						}
						
					}else if(returnUrl=="/demandcityshopping/maslowdemand"|| returnUrl.equals("/demandcityshopping/maslowdemand")){
						List<Child> childlist = _cChildRepository
								.findByUser(userInDb);
						if (childlist.size() > 0) {
							WebUtils.setSessionAttribute(request, "childids",
									childlist.get(0).getId());
							return "redirect:/demandcityshopping/maslowdemand?childid="+ childlist.get(0).getId();
						} else {
							return "redirect:/demandcityshopping/maslowdemands";
						}
						
					}else if(returnUrl=="/ifbraintask/uploadordownloadtask"|| returnUrl.equals("/ifbraintask/uploadordownloadtask")){
						List<Child> childlist = _cChildRepository.findByUser(userInDb);
						if (childlist.size() > 0) {
							WebUtils.setSessionAttribute(request, "childids",childlist.get(0).getId());
							Integer id= (Integer) request.getSession().getAttribute("todownloadtaskid");
							
							return "redirect:/ifbraintask/uploadordownloadtask?todownloadtaskid="+id+"&childidtask="+childlist.get(0).getId();
						} else {
							Integer id= (Integer) request.getSession().getAttribute("todownloadtaskid");
							return "redirect:/ifbraintask/uploadordownloadtask?todownloadtaskid="+id+"&childidtask="+0;
						}
						
					}else if(returnUrl=="/wish/addwishlist"|| returnUrl.equals("/wish/addwishlist")){
						List<Child> childlist = _cChildRepository.findByUser(userInDb);
						if (childlist.size() > 0) {
							WebUtils.setSessionAttribute(request, "childids",childlist.get(0).getId());
							Integer id= (Integer) request.getSession().getAttribute("commdityid");
							
							return "redirect:/wish/demendwishlist?shoppingmallCommodityid="+id+"&childid="+childlist.get(0).getId();
						} else {
							Integer id= (Integer) request.getSession().getAttribute("commdityid");
							return "redirect:/wish/demendwishlist?shoppingmallCommodityid="+id+"&childid="+"";
						}
						
					}
					
					else if(returnUrl=="/activity/materialdetail"|| returnUrl.equals("/activity/materialdetail")){
						Integer id= (Integer) request.getSession().getAttribute("materialid");
						return "redirect:/activity/materialdetail?id="+id;
					}
					else if(returnUrl=="/itemmanagement/toexamination"|| returnUrl.equals("/itemmanagement/toexamination")){
							return 	"redirect:/itemmanagement/toexamination";
					}
					else if(returnUrl=="/itemmanagement/myscore"|| returnUrl.equals("/itemmanagement/myscore")){		
							return 	"redirect:/itemmanagement/myscore";
					}
					else if (returnUrl != "" && returnUrl != null) {
						return "redirect:" + returnUrl;
					} else {
						List<Child> childlist = _cChildRepository
								.findByUser(userInDb);
						if (childlist.size() > 0) {
							WebUtils.setSessionAttribute(request, "childids",
									childlist.get(0).getId());
							return "redirect:/project/li/"+ childlist.get(0).getId();
						} else {
							return "redirect:/project/li";
						}

					}
				} else {
					model.addAttribute("message", "抱歉，用户没有进行激活无法登录");
					return "/user/logins";
				}
			} else {
				if (validCode == "" || validCode == null) {
					// 不输入验证码
					s.append("s");
					if (s.length() == 3) {
						model.addAttribute("visitime", s.length());
						s.delete(0, s.length());
					}
				} else {
					// 输入验证码正确
					model.addAttribute("visitime", 3);
				}

				model.addAttribute("message", "邮箱或密码错误，请重新填写。");

				return "/user/logins";
			}

		} else {
			// 验证码错误
			model.addAttribute("yanzhengmessage", "验证码有误，请重新填写。");
			model.addAttribute("visitime", 3);
			return "/user/logins";
		}

	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		model.addAttribute("message", "");
		return "/user/forgotPassword";
	}

	/**
	 * 忘记电话密码跳转页面
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/forgetphonepassword", method = RequestMethod.GET)
	public String forgetphonepassword(Model model) {
		model.addAttribute("message", "");
		return "/user/forgetphonepassword";
	}

	@ResponseBody
	@RequestMapping(value = "/getuserbyphone", method = RequestMethod.POST)
	public String getuserbyphone(HttpServletRequest request, Model model) {
		String phone = request.getParameter("phone");
		// 查询该该电话的用户是否存在
		User user = _userRepository.findByMobile(phone);
		String result = "";
		String jsonresult = "";
		if (user == null) {
			result = "0";
			jsonresult = "{\"result\":\"" + result + "\"}";
			return jsonresult;
		}
		return "";
	}

	/**
	 * 忘记邮箱密码跳转页面
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/forgetemailpassword", method = RequestMethod.GET)
	public String forgetemailpassword(Model model) {
		return "/user/forgetemailpassword";
	}

	/**
	 * 根据邮箱查看该用户是否存在(找回密码时用)
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getuserbyemail", method = RequestMethod.POST)
	public String getuserbyemail(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		// 查询该该邮箱的用户是否存在
		User user = _userRepository.findByEmail(email);
		String result = "1";
		String jsonresult = "";
		if (user == null) {
			result = "0";
			jsonresult = "{\"result\":\"" + result + "\"}";
			return jsonresult;
		}
		jsonresult = "{\"result\":\"" + result + "\"}";
		return jsonresult;
	}

	/**
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	/**
	 * 根据邮箱查看该用户是否存在(找回密码时用)
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getuserbyemailregister", method = RequestMethod.POST)
	public String getuserbyemailregister(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		// 查询该该邮箱的用户是否存在
		User user = _userRepository.findByEmail(email);
		String result = "0";
		String jsonresult = "";
		if (user != null) {
			result = "1";
			jsonresult = "{\"result\":\"" + result + "\"}";
			return jsonresult;
		}
		jsonresult = "{\"result\":\"" + result + "\"}";
		return jsonresult;
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String forgotPassword(String email, Model model) {
		// 生成随机密码
		UUID uuid = UUID.randomUUID();
		String newPassword = uuid.toString();
		System.out.println("得到的新密码:" + newPassword);
		String CipherNewPassword = CipherUtil.generatePassword(newPassword);
		System.out.println("加密之后的密码:" + CipherNewPassword);
		// 通过邮箱找到用户
		User user = _userRepository.findByEmail(email);
		if (user == null) {
			model.addAttribute("message", "不存在邮箱为" + email + "的用户！");
			return "/user/forgotPassword";
		}

		user.setPassword(CipherNewPassword);
		_userRepository.save(user);

		// 将新密码通过邮件发送给用户
		MailUtil mailUtil = new MailUtil(_mailSetting.getHost(),
				_mailSetting.getPort(), _mailSetting.getUsername(),
				_mailSetting.getPassword());
		String to = email;
		String subject = "Travel - 找回密码";
		String text = "你的密码已经被重置，新的密码为：" + newPassword + "，请使用新密码进行登录。";
		mailUtil.sendHtmlMail(_mailSetting.getFrom(), to, subject, text);

		model.addAttribute("message", "新的密码已经发到您的邮箱：" + email + "，请使用新密码进行登录。");

		return "/user/forgotPassword";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPasswordInit() {
		return "/user/resetPassword";
	}

	/**
	 * 
	 * @return 跳转到手机验证码登录页面
	 */
	@RequestMapping(value = "/phonelogin/{modell}", method = RequestMethod.GET)
	public String phoneloginInit(Model model, @PathVariable String modell) {
		int id = Integer.parseInt(modell);
		if (modell.length() > 10) {
			model.addAttribute("phone", modell);
		}

		if (modell.equals("-1")) {
			return "/user/phonelogin";
		} else {
			User user = _userRepository.findOne(id);
			if (user.getPassword() == null) {
				model.addAttribute("user", user);
				return "/user/phonepassword";
			} else {
				return "/user/phonelogin";
			}
		}
	}

	/**
	 * 
	 * @return 跳转到手机密码设定页面
	 */
	@RequestMapping(value = "/phonepassword/{id}", method = RequestMethod.GET)
	public String phonepasswordInit(HttpServletRequest request, Model model,
			@PathVariable Integer id) {
		User user = _userRepository.findOne(id);
		model.addAttribute("user", user);
		return "/user/phonepassword";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.PUT)
	public String resetPassword(HttpServletRequest request, Model model) {
		String oldPassword = CipherUtil.generatePassword(request
				.getParameter("oldPassword"));
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		User user = (User) request.getSession().getAttribute("user");
		if (user == null || !oldPassword.equals(user.getPassword())) {
			model.addAttribute("message", "旧密码错误，请重新填写！");
			return "/user/resetPassword";
		} 
		if (!confirmPassword.equals(newPassword)) {
			model.addAttribute("message", "重复密码填写不正确,请重新填写！");
			return "/user/resetPassword";
		}
		if(newPassword.length()<6){
			model.addAttribute("message", "密码长度必须大于6位!");
			return "/user/resetPassword";
		}
		user.setPassword(CipherUtil.generatePassword(newPassword));
		_userRepository.save(user);

		model.addAttribute("message", "密码修改成功！");

		return "/user/resetPassword";
	}

	@RequestMapping(value = "/phonepasswordsubmit", method = RequestMethod.PUT)
	public String phonePassword(HttpServletRequest request, Model model,
			@Valid User user) {
		User user1 = _userRepository.findOne(user.getId());
		String a = CipherUtil.generatePassword(user.getPassword());
		user1.setPassword(a);
		user1.setActiveStatus(1);
		_userRepository.save(user1);
		WebUtils.setSessionAttribute(request, "user", user1);
		return "redirect:/personalCenter/create";

	}

	// 更改密码。取消登录
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changepassword(HttpServletRequest request, Model model) {
		return "/user/changepassword";
	}
}
