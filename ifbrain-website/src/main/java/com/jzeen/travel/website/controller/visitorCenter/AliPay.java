package com.jzeen.travel.website.controller.visitorCenter;

import com.jzeen.travel.data.entity.Exam;
import com.jzeen.travel.data.entity.ExamOrder;
import com.jzeen.travel.data.entity.ExchangeRate;
import com.jzeen.travel.data.entity.GuideOrder;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.PlanOrder;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.VisaOrder;
import com.jzeen.travel.data.repository.ExamOrderRepository;
import com.jzeen.travel.data.repository.ExamRepository;
import com.jzeen.travel.data.repository.ExchangeRateRepository;
import com.jzeen.travel.data.repository.GuideOrderRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.PlanOrderRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.VisaOrderRepository;
import com.jzeen.travel.service.ExchangeRateService;
import com.jzeen.travel.service.OrderService;
import com.jzeen.travel.website.aliPay.config.AlipayConfig;
import com.jzeen.travel.website.aliPay.util.AlipayNotify;
import com.jzeen.travel.website.aliPay.util.AlipaySubmit;
import com.jzeen.travel.website.setting.WebUrlSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/AliPay")
public class AliPay {
	@Autowired
	private PlanOrderRepository _planOrderRepository;
	
    @Autowired
    private OrderRepository _orderRepository;
    @Autowired
    WebUrlSetting _webUrlSetting;
    @Autowired
    private VisaOrderRepository _visaOrderRepository;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private OrderService _orderService;

    @Autowired
    private ExchangeRateService _exchangeRateService;

    @Autowired
    private ExchangeRateRepository _exchangeRateRepository;
    @Autowired
    private GuideOrderRepository _guGuideOrderRepository;
    
    @Autowired
    private ExamRepository _ExamRepository;
    @Autowired
    private ExamOrderRepository _ExamOrderRepository;
    @RequestMapping(value = "/Submit/{orderID}")
    public String Pay(@PathVariable Integer orderID, HttpServletRequest request, Model model) {
       /* String user = (String) request.getSession().getAttribute("user");*/
        User user = (User) WebUtils.getSessionAttribute(request, "user");
        Integer id = user.getId();
        ExchangeRate exchangerate = _exchangeRateRepository.getByExchangerate();
        request.getSession().setMaxInactiveInterval(40 * 60);
        Order order = _orderRepository.findOne(orderID);
    // String cnkPrice = new DecimalFormat("#.00").format(order.getOrderAmount().multiply(exchangerate.getExchangerate().setScale(2, BigDecimal.ROUND_HALF_UP)));
      //  String cnkPrice = order.getPlanOrder().getServiceAmount().toString();
        String cnkPrice = "0.01";
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("payment_type", AlipayConfig.paymentType);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.paymentType);
        sParaTemp.put("notify_url",  _webUrlSetting.getRootUrl() + "/AliPay/Notify");
        sParaTemp.put("return_url",  _webUrlSetting.getRootUrl() + "/AliPay/Return");
        sParaTemp.put("seller_email", AlipayConfig.sellerEmail);
        sParaTemp.put("out_trade_no", order.getOrderNumber());// 订单号
        sParaTemp.put("total_fee", cnkPrice + "");// 订单价钱
    
/*        sParaTemp.put("out_trade_no","TP201234613117226678");// 订单号
            sParaTemp.put("total_fee", "0.01");// 订单价钱
*/      //           公共回传参数(订单标识号)
        sParaTemp.put("extra_common_param", id.toString());
        //         sParaTemp.put("show_url",
        //         ConfigManager.getInstance().getProperty("web.url") +
        //         "order/unpaidList");
        sParaTemp.put("subject", "旅游订单");// 订单广告名称
        sParaTemp.put("body", "旅游订单支付");

        String html = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        //request.setAttribute("returnHTML", html);
        model.addAttribute("returnHTML", html);
        return "/order/alipayAPI";
    }


    /**
     * 签证结算
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/VisaSubmit", method = RequestMethod.POST)
    public String PayVisaOrder(HttpServletRequest request, Model model) {
       /* String user = (String) request.getSession().getAttribute("user");*/
        User user = (User) WebUtils.getSessionAttribute(request, "user");

        if (user == null) {
            return "/user/login";
        }
        Integer id = user.getId();
        request.getSession().setMaxInactiveInterval(40 * 60);


        //获得人数
        String headCount = request.getParameter("headCount");
        //获得签证邮寄接收地址
        String postAddress = request.getParameter("postAddress");
        //获得签证邮寄接收人姓名
        String receiverName = request.getParameter("receiverName");
        //获得联系手机
        String teleNumber = request.getParameter("teleNumber");
        //计算总费用
        String result = request.getParameter("totalAmount");
        BigDecimal totalresult = new BigDecimal(result);
        totalresult = totalresult.setScale(2, BigDecimal.ROUND_HALF_UP);
        //微信号
        String wechatId = request.getParameter("wechatId");

        //创建新订单
        VisaOrder order = new VisaOrder();

        Date date = new Date();

        //生成订单号
        String ordernumber = "VISA" + date.getTime();

        order.setUser(user);
        order.setOrderNumber(ordernumber);
        order.setCreateTime(new Date());
        order.setPostAddress(postAddress);
        order.setHeadCount(Integer.parseInt(headCount));
        order.setReceiverName(receiverName);
        order.setTeleNumber(teleNumber);
        order.setTotalAmount(totalresult);
        order.setWechatId(wechatId);
        _visaOrderRepository.save(order);


        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("payment_type", AlipayConfig.paymentType);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.paymentType);
        sParaTemp.put("notify_url", _webUrlSetting.getRootUrl() + "/AliPay/NotifyVisaOrderUrl");
        sParaTemp.put("return_url", _webUrlSetting.getRootUrl() + "/AliPay/ReturnVisaOrder");
        sParaTemp.put("seller_email", AlipayConfig.sellerEmail);
        sParaTemp.put("out_trade_no", ordernumber);// 订单号
        sParaTemp.put("total_fee", result + "");//订单价钱
        sParaTemp.put("extra_common_param", id.toString());
        sParaTemp.put("subject", "签证订单");// 订单广告名称
        sParaTemp.put("body", "签证订单支付");
        String html = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        //request.setAttribute("returnHTML", html);
        model.addAttribute("returnHTML", html);
        return "/order/alipayAPI";
    }


    @RequestMapping(value = "/Notify")
    public void NotifyUrl(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        request.getSession().setMaxInactiveInterval(40 * 60);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            boolean isTrue = GetAliPayReturn(request);
            if (isTrue) {
                out.write("success");
            } else {
                out.write("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //签证订单
    @RequestMapping(value = "/NotifyVisaOrderUrl")
    public void NotifyVisaOrderUrl(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        request.getSession().setMaxInactiveInterval(40 * 60);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            boolean isTrue = GetAliPayVisaOrderReturn(request);
            if (isTrue) {
                out.write("success");
            } else {
                out.write("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/Return")
    public String ReturnUrl(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        boolean isTrue = GetAliPayReturn(request);
        if (isTrue) {
            return "redirect:/center"; // 我的订单
        } else {
            return "redirect: /order/alipayErr";
        }
    }


    @RequestMapping(value = "/ReturnVisaOrder")
    public String ReturnVisaOrderUrl(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        boolean isTrue = GetAliPayVisaOrderReturn(request);
        if (isTrue) {
            return "redirect:/visaOrder"; // 我的订单
        } else {
            return "redirect: /order/alipayErr";
        }
    }
    

    

    //签证订单
    private boolean GetAliPayVisaOrderReturn(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            if (valueStr != null && !"".equals(valueStr)) {
                if (!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(valueStr))) {
                    try {
                        valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            params.put(name, valueStr);
        }

        boolean verify_result = AlipayNotify.verify(params);
        if (verify_result) {
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 订单id
            String userId = params.get("extra_common_param");
            User user = _userRepository.findOne(Integer.parseInt(userId));

            WebUtils.setSessionAttribute(request, "user", user);

            // 订单编号
            String order_id = params.get("out_trade_no");

            //查询签证订单
            VisaOrder visaOrder = _visaOrderRepository.findByOrderNumber(order_id);
            visaOrder.setPaymentId(tradeNo);

            // 交易状态
            String payStatus = params.get("trade_status");


            if (payStatus.equals("TRADE_FINISHED")) {
                visaOrder.setOrderStatus(1);
            } else if (payStatus.equals("TRADE_SUCCESS")) {
                visaOrder.setOrderStatus(1);
            } else {
                visaOrder.setOrderStatus(0);
            }
            _visaOrderRepository.save(visaOrder);
        }

        return true;
    }


    //订单
   private boolean GetAliPayReturn(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            if (valueStr != null && !"".equals(valueStr)) {
                if (!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(valueStr))) {
                    try {
                        valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            params.put(name, valueStr);
        }

        boolean verify_result = AlipayNotify.verify(params);
        if (verify_result) {
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 订单id
            String userId = params.get("extra_common_param");
            User user = _userRepository.findOne(Integer.parseInt(userId));
            ExchangeRate exchangerate = _exchangeRateRepository.getByExchangerate();
            WebUtils.setSessionAttribute(request, "user", user);

            // 订单编号
            String order_id = params.get("out_trade_no");

            // 交易状态
            String payStatus = params.get("trade_status");

            Order order = _orderRepository.findByOrderNumber(order_id);

            WebUtils.setSessionAttribute(request, "order", order);
            if (payStatus.equals("TRADE_FINISHED")) {
                order.setOrderStatus(8);
               order.setExchangerate(exchangerate.getExchangerate());
            } else if (payStatus.equals("TRADE_SUCCESS")) {
                order.setOrderStatus(8);
                order.setExchangerate(exchangerate.getExchangerate());
            } else {
                order.setOrderStatus(4);
            }
            _orderRepository.save(order);
        }

        return true;
    }
    
    
    /**
     * 行程规划的帮我详细规划和帮我快速规划的支付宝去结算页面(跳转到支付宝页面)
     * @param orderID 订单ID
     * @return 支付宝的API
     */
   /* @RequestMapping(value="/submit/{orderid}")
    public String DetailPlanPay(@PathVariable Integer orderid, HttpServletRequest request, Model model){
    	
    	//获得用户对象
    	 User user = (User) WebUtils.getSessionAttribute(request, "user");
         Integer id = user.getId();
         //给Session赋最大时间
         request.getSession().setMaxInactiveInterval(40 * 60);
         //根据订单ID查询订单所有信息
         Order order = _orderRepository.findOne(orderid);
         
         //从订单规划表中得到规划费用
         String cnkPrice = order.getPlanOrder().getAmount().toString();
         
         System.out.println(cnkPrice);
         //将支付宝用的一些参数，用map的格式传入{xxx=y,xxx=u,xxx=z}
         Map<String, String> sParaTemp = new HashMap<String, String>();
         sParaTemp.put("service", "create_direct_pay_by_user");
         sParaTemp.put("payment_type", AlipayConfig.paymentType);
         sParaTemp.put("partner", AlipayConfig.partner);
         sParaTemp.put("_input_charset", AlipayConfig.input_charset);
         sParaTemp.put("payment_type", AlipayConfig.paymentType);
         sParaTemp.put("notify_url", _webUrlSetting.getRootUrl() + "/AliPay/NotifyDetailTripOrderUrl");
         sParaTemp.put("return_url", _webUrlSetting.getRootUrl() + "/AliPay/ReturnDetailTripOrder");
         
         sParaTemp.put("seller_email", AlipayConfig.sellerEmail);
         sParaTemp.put("out_trade_no", order.getOrderNumber());// 订单号
         sParaTemp.put("total_fee", cnkPrice + "");// 订单价钱
//       公共回传参数(订单标识号)
         sParaTemp.put("extra_common_param", id.toString());
         sParaTemp.put("subject", "旅游订单");// 订单广告名称
         sParaTemp.put("body", "旅游订单支付");

         String html = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
         model.addAttribute("returnHTML", html);
         return "/order/alipayAPI";
    }*/
    
    /**
     * 行程规划的帮我详细规划和帮我快速规划的支付宝的验证，判断数据是否正确(用于跳转到支付宝之后，返回到某个界面的判断)
     */
   /* private boolean GetAliPayTripReturn(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
    	//用于存储支付宝的数据
        Map<String, String> params = new HashMap<String, String>();
        //得到当前支付宝的的数据
        Map requestParams = request.getParameterMap();
        //循环遍历相关信息
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            if (valueStr != null && !"".equals(valueStr)) {
                if (!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(valueStr))) {
                    try {
                        valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
            params.put(name, valueStr);
        }

        boolean verify_result = AlipayNotify.verify(params);
        if (verify_result) {
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 订单id
            String userId = params.get("extra_common_param");
            User user = _userRepository.findOne(Integer.parseInt(userId));
//            ExchangeRate exchangerate = _exchangeRateRepository.getByExchangerate();
            WebUtils.setSessionAttribute(request, "user", user);

            // 订单编号
            String order_id = params.get("out_trade_no");

            // 交易状态
            String payStatus = params.get("trade_status");

            //得到订单对象
            Order order = _orderRepository.findByOrderNumber(order_id);
            
            //把订单对象放入Session
            WebUtils.setSessionAttribute(request, "order", order);
            //根据支付宝状态判断，如果是完成，就把订单状态变成3，3为已结算 4为已过期
            if (payStatus.equals("TRADE_FINISHED")) {
                order.setOrderStatus(3);
//               order.setExchangerate(exchangerate.getExchangerate());
            } else if (payStatus.equals("TRADE_SUCCESS")) {
                order.setOrderStatus(3);
//                order.setExchangerate(exchangerate.getExchangerate());
            } else {
                order.setOrderStatus(4);
            }
            _orderRepository.save(order);
        }

        return true;
    }*/
    
    /**
     * 帮我快速规划和帮我详细规划的(同步方式)返回
     */
   /* @RequestMapping(value="/ReturnDetailTripOrder")
    public String ReturnDetailTripOrder(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
    	//得到验证结果
    	boolean isTrue=GetAliPayTripReturn(request);
    	//如果为真跳转到订单页面，为假则跳回支付宝错误页面
    	  if (isTrue) {
    		  return "redirect:/center"; // 我的订单
          } else {
              return "redirect: /order/alipayErr";
          }
    }
    
    *//**
     * 帮我快速规划和帮我详细规划的(异步方式)返回，暂时应该用不到
     *//*
    @RequestMapping(value="/NotifyDetailTripOrderUrl")
    public void NotifyDetailTripOrderUrl(HttpServletRequest request, HttpServletResponse response) throws ParseException{
    	request.getSession().setMaxInactiveInterval(40 * 60);
    	PrintWriter out =null;
    	try {
			out=response.getWriter();
			boolean isTrue=GetAliPayTripReturn(request);
			if(isTrue){
				 out.write("success");
			}else{
			    out.write("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }*/
 
    
    
    
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    //考试付款结算
    
    @RequestMapping(value = "/SubmitExam/{orderID}")
    public String SubmitExam(@PathVariable Integer orderID, HttpServletRequest request, Model model) {
        User user = (User) WebUtils.getSessionAttribute(request, "user");
        Integer id = user.getId();
     
        request.getSession().setMaxInactiveInterval(40 * 60);
        ExamOrder order = _ExamOrderRepository.findOne(orderID);
   
        String cnkPrice =order.getOrderAmount().toString();
       /* String cnkPrice ="0.01";*/
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("payment_type", AlipayConfig.paymentType);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.paymentType);
        sParaTemp.put("notify_url",  _webUrlSetting.getRootUrl() + "/AliPay/NotifySubmitExamUrl");
        sParaTemp.put("return_url",  _webUrlSetting.getRootUrl() + "/AliPay/ReturnSubmitExam");
        sParaTemp.put("seller_email", AlipayConfig.sellerEmail);
        sParaTemp.put("out_trade_no", order.getOrderNumber());// 订单号
        sParaTemp.put("total_fee", cnkPrice + "");// 订单价钱
    
/*        sParaTemp.put("out_trade_no","TP201234613117226678");// 订单号
            sParaTemp.put("total_fee", "0.01");// 订单价钱
*/      //           公共回传参数(订单标识号)
        sParaTemp.put("extra_common_param", id.toString());
        //         sParaTemp.put("show_url",
        //         ConfigManager.getInstance().getProperty("web.url") +
        //         "order/unpaidList");
        sParaTemp.put("subject", "测评订单");// 订单广告名称
        sParaTemp.put("body", "测评订单支付");

        String html = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        //request.setAttribute("returnHTML", html);
        model.addAttribute("returnHTML", html);
        return "/order/alipayAPI";
    }
    
    
    
    
    
    /**
     * 考试订单(同步方式)返回
     */
    @RequestMapping(value="/ReturnSubmitExam")
    public String ReturnSubmitExam(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
    	//得到验证结果
    	boolean isTrue=GetAliPayExamReturn(request);
    	//如果为真跳转到订单页面，为假则跳回支付宝错误页面
    	  if (isTrue) {
    		  return "redirect:/itemmanagement/toexamination"; // 我的订单
          } else {
              return "redirect: /order/alipayErr";
          }
    }
    
    /**
     * 考试东单(异步方式)返回，暂时应该用不到
     */
    @RequestMapping(value="/NotifySubmitExamUrl")
    public void NotifySubmitExamUrl(HttpServletRequest request, HttpServletResponse response) throws ParseException{
    	request.getSession().setMaxInactiveInterval(40 * 60);
    	PrintWriter out =null;
    	try {
			out=response.getWriter();
			boolean isTrue=GetAliPayExamReturn(request);
			if(isTrue){
				 out.write("success");
			}else{
			    out.write("fail");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    
    
    //订单
    private boolean GetAliPayExamReturn(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            if (valueStr != null && !"".equals(valueStr)) {
                if (!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(valueStr))) {
                    try {
                        valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            params.put(name, valueStr);
        }

        boolean verify_result = AlipayNotify.verify(params);
        if (verify_result) {
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 订单id
            String userId = params.get("extra_common_param");
            User user = _userRepository.findOne(Integer.parseInt(userId));
            WebUtils.setSessionAttribute(request, "user", user);

            // 订单编号
            String order_id = params.get("out_trade_no");

            // 交易状态
            String payStatus = params.get("trade_status");

            ExamOrder examorder = _ExamOrderRepository.findByOrderNumber(order_id);

            WebUtils.setSessionAttribute(request, "examorder", examorder);
            if (payStatus.equals("TRADE_FINISHED")) {
            	examorder.setOrderStatus(1);
              
            } else if (payStatus.equals("TRADE_SUCCESS")) {
            	examorder.setOrderStatus(1);
             
            } else {
            	//已过期
            	examorder.setOrderStatus(2);
            }
            _ExamOrderRepository.save(examorder);
        }

        return true;
    }
    

}
