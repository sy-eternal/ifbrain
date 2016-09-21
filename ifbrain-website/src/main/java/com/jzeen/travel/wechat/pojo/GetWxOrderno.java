package com.jzeen.travel.wechat.pojo;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.jzeen.travel.openctc.emp.tool.json.JSONObject;
import com.jzeen.travel.wechat.utils.HttpClientConnectionManager;
import com.jzeen.travel.wechat.utils.RequestHandler;
import com.jzeen.travel.wechat.utils.Sha1Util;
import com.jzeen.travel.wechat.utils.TenpayUtil;

public class GetWxOrderno
{
  public static DefaultHttpClient httpclient;

  static
  {
    httpclient = new DefaultHttpClient();
    httpclient = (DefaultHttpClient)HttpClientConnectionManager.getSSLInstance(httpclient);
  }


  public static String getPayNo(String url,String xmlParam){
	  System.out.println("xml是:"+xmlParam);
	  DefaultHttpClient client = new DefaultHttpClient();
	  client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
	  HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	  String prepay_id = "";
     try {
		 httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
		 HttpResponse response = httpclient.execute(httpost);
	     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
	     Map<String, Object> dataMap = new HashMap<String, Object>();
	     System.out.println("json是:"+jsonStr);
	     
	    if(jsonStr.indexOf("FAIL")!=-1){
	    	return prepay_id;
	    }
	    Map map = doXMLParse(jsonStr);
	    String return_code  = (String) map.get("return_code");
	    prepay_id  = (String) map.get("prepay_id");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prepay_id;
  }
  
  
  
 
  /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
  public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
  
  public static void main(String[] args) {
	//网页授权后获取传递的参数
		String userId = "b88001"; 	
		String orderNo =Sha1Util.getTimeStamp(); 
		//String money = request.getParameter("&amp;money");
		String money="1";
		String openId = "omJust5qC1IbILXJn2ePyBkp_ckE";
		//金额转化为分为单位
		float sessionmoney = Float.parseFloat(money);
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
		String notify_url ="http://www.ifbrain.com/wechatpay/notify";
		//商户号
		String mch_id = partner;
		//子商户号  非必输
		//String sub_mch_id="";
		//设备号   非必输
		String device_info="WEB";
		//随机数 
		//String nonce_str = strReq;
		String nonce_str ="ibuaiVcKdpRxkhJQ";
		//商品描述根据情况修改
		String body = "rtyu";
		//附加数据
		String attach = userId;
		//商户订单号
		//String out_trade_no = orderNo;
		String out_trade_no = "1415659990";
		int intMoney = Integer.parseInt(finalmoney);
		//总金额以分为单位，不带小数点
		int total_fee = intMoney;
		//订单生成的机器 IP
		//String spbill_create_ip = "192.168.1.100";
		String spbill_create_ip = "121.42.37.146";
		String trade_type = "JSAPI";
		String openid = openId;
		//非必输
//		String product_id = "";  "<body><![CDATA["+body+"]]></body>"+
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
			prepay_id = getPayNo(createOrderURL, xml);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 }
}