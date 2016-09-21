package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.WeChatQRCode;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Title: X2OUR_TRAVEL
 * Description: 二维码工具类
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public class QRCodeUtil {
	private static Logger log = LoggerFactory.getLogger(QRCodeUtil.class);




	/**
	 * 创建临时带参二维码
	 *
	 * @param expireSeconds
	 *            二维码有效时间，单位为秒，最大不超过1800
	 * @param sceneId
	 *            场景ID
	 * @return WeixinQRCode
	 */
	public static WeChatQRCode createTemporaryQRCode(int expireSeconds, int sceneId) {
		WeChatQRCode wechatQRCode = null;
		// 拼接请求地址
		String requestUrl = WeChatConts.QR_CODE_API.replace("ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());
		// 需要提交的json数据
		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建临时带参二维码
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, expireSeconds, sceneId));

		if (null != jsonObject) {
			try {
				wechatQRCode = new WeChatQRCode();
				wechatQRCode.setTicket(jsonObject.getString("ticket"));
				wechatQRCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
				log.info("创建临时带参二维码成功 ticket:{} expire_seconds:{}", wechatQRCode.getTicket(), wechatQRCode.getExpireSeconds());
			} catch (Exception e) {
				wechatQRCode = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建临时带参二维码失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wechatQRCode;
	}

	/**
	 * 创建永久带参二维码
	 *
	 * @param sceneId
	 *            场景ID
	 * @return ticket
	 */
	public static String createPermanentQRCode(int sceneId) {
		String ticket = null;
		// 拼接请求地址
		String requestUrl = WeChatConts.QR_CODE_API.replace("ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());
		// 需要提交的json数据
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建永久带参二维码
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, sceneId));

		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				log.info("创建永久带参二维码成功 ticket:{}", ticket);
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建永久带参二维码失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return ticket;
	}

	/**
	 * 根据ticket换取二维码
	 *
	 * @param ticket
	 *            二维码ticket
	 * @param savePath
	 *            保存路径
	 */
	public static String getQRCode(String ticket, String savePath) {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = WeChatConts.SHOW_QR_CODE_API.replace("TICKET", CommonUtil.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 将ticket作为文件名
			filePath = savePath + ticket + ".jpg";

			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();

			conn.disconnect();
			log.info("根据ticket换取二维码成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			log.error("根据ticket换取二维码失败：{}", e);
		}
		return filePath;
	}




	public static void main(String args[]) {
		/**
		 * 创建临时二维码
		*/
		WeChatQRCode wechatQRCode = createTemporaryQRCode(3*24*60*60, 1100000148);
		// 临时二维码的ticket
		System.out.println(wechatQRCode.getTicket());
		// 临时二维码的有效时间
		System.out.println(wechatQRCode.getExpireSeconds());


		/**
		 * 根据ticket换取二维码
		 */
		String ticket = "gQHW8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3JFTXhyMC1sNWpfZkNaMElRMjE0AAIEVbDNVQMEgPQDAA==";
		String savePath = "/home/limin/upload";
		// 根据ticket换取二维码
		getQRCode(ticket, savePath);

	}
}
