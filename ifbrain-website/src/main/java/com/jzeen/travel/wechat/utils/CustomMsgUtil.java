package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.message.model.Article;
import com.jzeen.travel.wechat.message.model.Music;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Title: X2OUR_TRAVEL
 * Description: 客服接口工具类
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public class CustomMsgUtil {

	private static Logger log = LoggerFactory.getLogger(CustomMsgUtil.class);

	/**
	 * 发送客服图片消息
	 * @param openId
	 * @param mediaId
	 */
	public static void sendImageMsg(String openId, String mediaId) {
		String jsonTextMsg = CustomMsgUtil.buildImageCustomMessage(openId, mediaId);
		// 发送客服消息
		CustomMsgUtil.sendCustomMessage(jsonTextMsg);
	}

	/**
	 * 发送客服文本消息
	 * @param openId
	 * @param context
	 */
	public static void sendTextMsg(String openId, String context){
		String jsonTextMsg = CustomMsgUtil.buildTextCustomMessage(openId, context);
		// 发送客服消息
		CustomMsgUtil.sendCustomMessage(jsonTextMsg);
	}


	/**
	 * 组装文本客服消息
	 *
	 * @param openId
	 *            消息发送对象
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public static String buildTextCustomMessage(String openId, String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}

	/**
	 * 组装图片客服消息
	 *
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String buildImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装语音客服消息
	 *
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String buildVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装视频客服消息
	 *
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @param thumbMediaId
	 *            视频消息缩略图的媒体id
	 * @return
	 */
	public static String buildVideoCustomMessage(String openId, String mediaId,  String thumbMediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId, thumbMediaId);
	}

	/**
	 * 组装音乐客服消息
	 *
	 * @param openId
	 *            消息发送对象
	 * @param music
	 *            音乐对象
	 * @return
	 */
	public static String buildMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music).toString());
		// 将jsonMsg中的thumbmediaid替换为thumb_media_id
		jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
		return jsonMsg;
	}

	/**
	 * 组装图文客服消息
	 *
	 * @param openId
	 *            消息发送对象
	 * @param articleList
	 *            图文消息列表
	 * @return
	 */
	public static String buildNewsCustomMessage(String openId, List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg, openId, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\""));
		// 将jsonMsg中的picUrl替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}

	/**
	 * 发送客服消息
	 *
	 * @param jsonMsg
	 *            json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public static boolean sendCustomMessage(String jsonMsg) {
		log.info("消息内容：{}", jsonMsg);

		String accessToken = WeChatConts.getTokenInst().getToken();

		// 拼接请求地址
		String requestUrl = WeChatConts.customMsgUrl.replace("ACCESS_TOKEN", accessToken);


		// 发送客服消息
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "POST", jsonMsg);

		boolean result = false;
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("客服消息发送成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("客服消息发送失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}



	public static void main(String args[]) {
		// 获取接口访问凭证

		/**
		 * 发送客服消息（文本消息）
		*/
		// 组装文本客服消息
		String jsonTextMsg = buildTextCustomMessage("ovFm4uNOKKcB01_1vkEQSe6Mfxeg", "红包大大地，介绍给更多好友哦");
		// 发送客服消息
		sendCustomMessage(jsonTextMsg);


		/**
		 * 发送客服消息（图文消息）
		*/
		Article article1 = new Article();
		article1.setTitle("微信上也能斗地主");
		article1.setDescription("");
		article1.setPicUrl("http://www.egouji.com/xiaoq/game/doudizhu_big.png");
		article1.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Doudizhu/doudizhu.htm");
		Article article2 = new Article();
		article2.setTitle("傲气雄鹰\n80后不得不玩的经典游戏");
		article2.setDescription("");
		article2.setPicUrl("http://www.egouji.com/xiaoq/game/aoqixiongying.png");
		article2.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Plane/aoqixiongying.html");
		List<Article> list = new ArrayList<Article>();
		list.add(article1);
		list.add(article2);

		// 组装图文客服消息
		String jsonNewsMsg = buildNewsCustomMessage("otWXBt3Clu58weJi01BnBNdV14C8", list);
		// 发送客服消息
//		sendCustomMessage(jsonNewsMsg);
	}
}
