package com.jzeen.travel.wechat.utils;




import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.jzeen.travel.data.repository.CourseOrderReponsitory;
import com.jzeen.travel.wechat.conf.WeChatConts;



/*
 '微信支付服务器签名支付请求请求类
 '============================================================================
 'api说明：
 'init(app_id, app_secret, partner_key, app_key);
 '初始化函数，默认给一些参数赋值，如cmdno,date等。
 'setKey(key_)'设置商户密钥
 'getLasterrCode(),获取最后错误号
 'GetToken();获取Token
 'getTokenReal();Token过期后实时获取Token
 'createMd5Sign(signParams);生成Md5签名
 'genPackage(packageParams);获取package包
 'createSHA1Sign(signParams);创建签名SHA1
 'sendPrepay(packageParams);提交预支付
 'getDebugInfo(),获取debug信息
 '============================================================================
 '*/
public class RequestHandler {
	/** Token获取网关地址地址 */
	private String tokenUrl;
	/** 预支付网关url地址 */
	private String gateUrl;
	/** 查询支付通知网关URL */
	private String notifyUrl;
	/** 商户参数 */
	private String appid;
	private String appkey;
	private String partnerkey;
	private String appsecret;
	private String key;
	/** 请求的参数 */
	private SortedMap parameters;
	/** Token */
	private String Token;
	private String charset;
	/** debug信息 */
	private String debugInfo;
	private String last_errcode;

	private HttpServletRequest request;

	private HttpServletResponse response;
	@Autowired
    private CourseOrderReponsitory _courseOrderReponsitory;
	/**
	 * 初始构造函数。
	 * 
	 * @return
	 */
	public RequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		this.last_errcode = "0";
		this.request = request;
		this.response = response;
		//this.charset = "GBK";
		this.charset = "UTF-8";
		this.parameters = new TreeMap();
		// 验证notify支付订单网关
		notifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";
		
	}

	/**
	 * 初始化函数。
	 */
	public void init(String app_id, String app_secret,	String partner_key) {
		this.last_errcode = "0";
		this.Token = "token_";
		this.debugInfo = "";
		this.appid = app_id;
		this.partnerkey = partner_key;
		this.appsecret = app_secret;
		this.key = partner_key;
	}

	public void init() {
	}

	/**
	 * 获取最后错误号
	 */
	public String getLasterrCode() {
		return last_errcode;
	}

	/**
	 *获取入口地址,不包含参数值
	 */
	public String getGateUrl() {
		return gateUrl;
	}

	/**
	 * 获取参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	
	 //设置密钥
	
	public void setKey(String key) {
		this.partnerkey = key;
	}
	//设置微信密钥
	public void  setAppKey(String key){
		this.appkey = key;
	}
	
	// 特殊字符处理
	public String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, this.charset).replace("+", "%20");
	}

	/*// 获取package的签名包
	public String genPackage(SortedMap<String, String> packageParams)
			throws UnsupportedEncodingException {
		String sign = createSign(packageParams);

		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + UrlEncode(v) + "&");
		}

		// 去掉最后一个&
		String packageValue = sb.append("sign=" + sign).toString();
//		System.out.println("UrlEncode后 packageValue=" + packageValue);
		return packageValue;
	}*/

	  /**
     * 生成支付签名
     * @param packageParams
     * @param charset
     * @return
     * @author sword
     */
    public static String createSign(SortedMap<String, String> packageParams,String charset) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String key = WeChatConts.mmpayKey;
        sb.append("key=" + key);
        System.out.println("TenpayUtil.java----md5 sb === " + sb);
        String sign = MD5Util.MD5Encode(sb.toString(),charset)
                .toUpperCase();
        System.out.println("packge signature ====" + sign);
        return sign;
    }

	/**
	 * 创建package签名
	 */
	public boolean createMd5Sign(String signParams) {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		// 算出摘要
		String enc = TenpayUtil.getCharacterEncoding(this.request,
				this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		String tenpaySign = this.getParameter("sign").toLowerCase();

		// debug信息
		this.setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:"
				+ tenpaySign);

		return tenpaySign.equals(sign);
	}

	

    //输出XML
	   public String parseXML() {
		   StringBuffer sb = new StringBuffer();
	       sb.append("<xml>");
	       Set es = this.parameters.entrySet();
	       Iterator it = es.iterator();
	       while(it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				String k = (String)entry.getKey();
				String v = (String)entry.getValue();
				if(null != v && !"".equals(v) && !"appkey".equals(k)) {
					
					sb.append("<" + k +">" + getParameter(k) + "</" + k + ">\n");
				}
			}
	       sb.append("</xml>");
			return sb.toString();
		}

	/**
	 * 设置debug信息
	 */
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	public String getDebugInfo() {
		return debugInfo;
	}
	public String getKey() {
		return partnerkey;
	}

	public static void main(String[] args) throws Exception {
		
		  String requestUrl="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=AccessToken";
		  requestUrl=requestUrl.replace("AccessToken", "0Ej9nlPMNzV1qj4HQdZfTCSyZtFWg8FsW32PWa815jgNk-463JGG8bUVieVbfZTD6KYdgayUr5QZL0-HrN1lRHtNQ3YTEeo71LM8bqvlkUEl0xhZRT6V_hrKWVr1FMMmBPKjAIALZU");
          JSONObject param=new JSONObject();
          param.put("expire_seconds","1800");
          param.put("action_name","QR_SCENE");
          JSONObject param1=new JSONObject();
          JSONObject param2=new JSONObject();
          param2.put("scene_id", 100000);
          param1.put("scene", param2.toString());
          param.put("action_info", param1.toString());
          JSONObject jsonObject =HttpRequestUtil.httpsRequest(requestUrl, "POST", param.toString());
          String ticket = jsonObject.getString("ticket");
          String encodeticket = URLEncoder.encode(ticket, "utf-8"); 
          String requestUrl1="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+encodeticket;
          HttpRequestUtil.httpsRequest(requestUrl1, "POST", null);
	}
}
