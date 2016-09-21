package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.data.entity.WeChatUser;
import com.jzeen.travel.service.wecaht.WeChatUserService;
import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.AccessToken;
import com.jzeen.travel.wechat.pojo.WeChatBatchUser;
import com.jzeen.travel.wechat.pojo.WeChatGroup;
import com.jzeen.travel.wechat.pojo.WeChatUserSummary;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL Description:用户工具类 Date: 2015年 08月 11日 CopyRight (c) 2015
 * X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Service
public class UserUtil {
	 @Autowired
	    private WeChatUserService weChatUserService;
	private static Logger log = LoggerFactory.getLogger(UserUtil.class);

	/**
	 * 获取用户信息
	 *
	 * @param openId
	 *            用户标识
	 * @return WeChatUserInfo
	 */
	public static WeChatUser getUserInfo(String openId) {
		WeChatUser userInfo = null;
		// 拼接请求地址
		String requestUrl = WeChatConts.GET_USER_INFO_API.replace("ACCESS_TOKEN", WeChatConts.getTokenInst().getToken()).replace("OPENID", openId);

		// 获取用户信息
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET",null);

		if (null != jsonObject) {
			try {
				 userInfo = new WeChatUser();
				// 用户的标识subscribe
				userInfo.setOpenid(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				userInfo.setSubscribe(jsonObject.getInt("subscribe"));
//0 == userInfo.getSubscribe()
				if (0 == jsonObject.getInt("subscribe")) {
					log.info("用户{}已取消关注", userInfo.getOpenid());
				} else {
					// 用户关注时间
					userInfo.setSubscribe_time(jsonObject.getInt("subscribe_time"));
					// 昵称
					// 处理包含QQ表情的昵称，将QQ表情替换为空串
					userInfo.setNickname(toValid3ByteUTF8String(jsonObject.getString("nickname")));
					// 用户的性别（1是男性，2是女性，0是未知）
					userInfo.setSex(jsonObject.getInt("sex"));
					// 用户所在国家
					userInfo.setCountry(jsonObject.getString("country"));
					// 用户所在省份
					userInfo.setProvince(jsonObject.getString("province"));
					// 用户所在城市
					userInfo.setCity(jsonObject.getString("city"));
					// 用户的语言，简体中文为zh_CN
					userInfo.setLanguage(jsonObject.getString("language"));
					// 用户头像
					userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));

					// 用户所在的分组ID
					userInfo.setGroupid(jsonObject.getInt("groupid"));
					// 备注
					userInfo.setRemark(jsonObject.getString("remark"));
					return userInfo;
					// unionid　没有unionId，暂时注释掉
					// userInfo.setUnionid(jsonObject.getString("unionid"));
				}

			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);

				// 4001错误 invalid credential, access_token is invalid or not
				// latest
				if (errorCode == 40001) {
					WeChatConts.accessToken = null;

					AccessToken token = WeChatConts.getTokenInst();
					log.info("token异常，重新获取token  token:{} expiresIn:{}",
							token.getToken(), token.getExpiresIn());
				}
			}
		}
		return userInfo;
	}

	/**
	 * 获取用户信息
	 *
	 * @return WeChatUserInfo
	 */
	public static WeChatBatchUser batchGetUser(String[] array) {
		WeChatBatchUser batchUser = null;
		// 拼接请求地址
		String requestUrl = WeChatConts.BATCHGET_USER_API.replace(
				"ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());

		StringBuilder jsonData = new StringBuilder();
		jsonData.append("{\"user_list\": [");

		for (int i = 0; i < array.length; i++) {
			jsonData.append("{\"openid\": \"");
			jsonData.append(array[i]);
			if (i == array.length - 1) {
				jsonData.append("\"}");
			} else {
				jsonData.append("\"},");
			}

		}

		jsonData.append("]}");

		System.out.println(jsonData.toString());

		// 获取用户信息
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl,
				"POST", jsonData.toString());

		if (null != jsonObject) {
			try {

				List<JSONObject> list = JSONArray.toList(
						jsonObject.getJSONArray("user_info_list"),
						JSONObject.class);

				if (list != null) {
					batchUser = new WeChatBatchUser(true);

					for (JSONObject item : list) {
						WeChatUser userInfo = new WeChatUser();
						// 用户的标识
						userInfo.setOpenid(item.getString("openid"));
						// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
						userInfo.setSubscribe(item.getInt("subscribe"));

						if (userInfo.getSubscribe() == 0) {
							log.info("用户{}已取消关注", userInfo.getOpenid());

						} else {
							// 用户关注时间
							userInfo.setSubscribe_time(item
									.getInt("subscribe_time"));
							// 昵称
							// 超长字符处理
							userInfo.setNickname(toValid3ByteUTF8String(item
									.getString("nickname")));

							// 用户的性别（1是男性，2是女性，0是未知）
							userInfo.setSex(item.getInt("sex"));
							// 用户所在国家
							userInfo.setCountry(item.getString("country"));
							// 用户所在省份
							userInfo.setProvince(item.getString("province"));
							// 用户所在城市
							userInfo.setCity(item.getString("city"));
							// 用户的语言，简体中文为zh_CN
							userInfo.setLanguage(item.getString("language"));
							// 用户头像
							userInfo.setHeadimgurl(item.getString("headimgurl"));

							// 用户所在的分组ID
							userInfo.setGroupid(item.getInt("groupid"));
							// 备注
							userInfo.setRemark(item.getString("remark"));
						}

						// 目前没有unionid，暂时注释
						// try {
						// // unionid
						// userInfo.setUnionid(item.getString("unionid"));
						// } catch (Exception e) {
						// // undo nothing
						// }

						batchUser.addUser(userInfo);
					}
				}
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户列表信息失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return batchUser;
	}

	/**
	 * 获取关注者列表
	 *
	 * @param nextOpenId
	 *            第一个拉取的openId，不填默认从头开始拉取
	 * @return WeChatUserSummary
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static WeChatUserSummary getUserList(String nextOpenId) {
		WeChatUserSummary userList = null;

		if (null == nextOpenId)
			nextOpenId = "";

		// 拼接请求地址
		String requestUrl = WeChatConts.GET_USER_LIST_API.replace(
				"ACCESS_TOKEN", WeChatConts.getTokenInst().getToken()).replace(
				"NEXT_OPENID", nextOpenId);

		// 获取关注者列表
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET",
				null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				userList = new WeChatUserSummary();
				userList.setTotal(jsonObject.getInt("total"));
				userList.setCount(jsonObject.getInt("count"));
				userList.setNextOpenId(jsonObject.getString("next_openid"));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
				userList.setOpenIdList(JSONArray.toList(
						dataObject.getJSONArray("openid"), List.class));
			} catch (JSONException e) {
				userList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取关注者列表失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return userList;
	}

	/**
	 * 查询分组
	 *
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<WeChatGroup> getGroups() {
		List<WeChatGroup> weixinGroupList = null;
		// 拼接请求地址
		String requestUrl = WeChatConts.GET_GROUPS_API.replace("ACCESS_TOKEN",
				WeChatConts.getTokenInst().getToken());
		// 查询分组
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET",
				null);

		if (null != jsonObject) {
			try {
				weixinGroupList = JSONArray.toList(
						jsonObject.getJSONArray("groups"), WeChatGroup.class);
			} catch (JSONException e) {
				weixinGroupList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("查询分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinGroupList;
	}

	/**
	 * 创建分组
	 *
	 * @param groupName
	 *            分组名称
	 * @return
	 */
	public static WeChatGroup createGroup(String groupName) {
		WeChatGroup group = null;
		// 拼接请求地址
		String requestUrl = WeChatConts.CREATE_GROUPS_API.replace(
				"ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());

		// 需要提交的json数据
		String jsonData = "{\"group\" : {\"name\" : \"%s\"}}";
		// 创建分组
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl,
				"POST", String.format(jsonData, groupName));

		if (null != jsonObject) {
			try {
				group = new WeChatGroup();
				group.setId(jsonObject.getJSONObject("group").getInt("id"));
				group.setName(jsonObject.getJSONObject("group").getString(
						"name"));
			} catch (JSONException e) {
				group = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return group;
	}

	/**
	 * 修改分组名
	 *
	 * @param groupId
	 *            分组id
	 * @param groupName
	 *            修改后的分组名
	 * @return true | false
	 */
	public static boolean updateGroup(int groupId, String groupName) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = WeChatConts.UPDATE_GROUPS_API.replace(
				"ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());
		// 需要提交的json数据
		String jsonData = "{\"group\": {\"id\": %d, \"name\": \"%s\"}}";
		// 修改分组名
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl,
				"POST", String.format(jsonData, groupId, groupName));

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("修改分组名成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("修改分组名失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 移动用户分组
	 *
	 * @param openId
	 *            用户标识
	 * @param groupId
	 *            分组id
	 * @return true | false
	 */
	public static boolean updateMemberGroup(String openId, int groupId) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = WeChatConts.UPDATE_MEMBER_GROUPS_API.replace(
				"ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\",\"to_groupid\":%d}";
		// 移动用户分组
		JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl,
				"POST", String.format(jsonData, openId, groupId));

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("移动用户分组成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("移动用户分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 超过3个字节的字符无法保存到mysql数据库中，修改修改字符集，因此 过滤掉超过3个字节的字符
	 *
	 */
	private static String toValid3ByteUTF8String(String s) {
		final int length = s.length();
		StringBuilder b = new StringBuilder(length);

		for (int offset = 0; offset < length; offset++) {
			final int codepoint = s.codePointAt(offset);

			if (Character.charCount(codepoint) == 1
					&& Character.isValidCodePoint(codepoint)) {
				b.appendCodePoint(codepoint);
			}
		}
		return b.toString();
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		/**
		 * 获取关注者列表
		 */
		/*
		 * WeChatUserSummary userSummary = getUserList("");
		 * System.out.println("总关注用户数：" + userSummary.getTotal());
		 * System.out.println("本次获取用户数：" + userSummary.getCount());
		 * System.out.println("OpenID列表：" +
		 * userSummary.getOpenIdList().toString());
		 * System.out.println("next_openid：" + userSummary.getNextOpenId());
		 * 
		 * 
		 * WeChatBatchUser batchUser = batchGetUser(new
		 * String[]{"ovFm4uGE5zanxHK_eIhFRyV39fP4"
		 * ,"ovFm4uN8nvCg9UrDlaTLPHnaHGI4",
		 * "ovFm4uJwAHrVk2t8d-DVYlKlchMI","ovFm4uJoQ4E-dBj59DICw6Wj0qJ0"
		 * ,"ovFm4uCbe0vZ2K1h0g1MuKxmjNP8","ovFm4uCB-rhugmRLYdKOVbyVHNZo",
		 * "ovFm4uPhLK1-IjX9h51FF17Y0lY0"
		 * ,"ovFm4uIKZkS_mDBENSjD3z12ZlJY","ovFm4uAqY-qCAGW4PLlV7viecpNc"
		 * ,"ovFm4uNKBQWbSwUZ5nf54TyqspFA",
		 * "ovFm4uK7ahgj6OQxYuY8Z8iAcJTI","ovFm4uDziteuooVMal7Hzukr8plQ"
		 * ,"ovFm4uEZYQA2P8v1L2S3aX0QhU4k","ovFm4uOGcqvzS3zKTU5zw2L8xu4A",
		 * "ovFm4uKrnUYlMllMuhhpo8wcTrvw"
		 * ,"ovFm4uJAn2yMa2cMV72SXl8ZnT1A","ovFm4uJvZHcIlDW9f7KS_6wgI3gk"
		 * ,"ovFm4uCP-vcOWouxjw3MHQaXQkCc",
		 * "ovFm4uJgvjc1D96vPTM9yeUr1IK4","ovFm4uEWxlAazNuDFvaj2vwoBDek"
		 * ,"ovFm4uGXNTCu7IUF4szRwwkSOt_4","ovFm4uAeu-ofAntLDiY1Ww76d9Cw",
		 * "ovFm4uKcrfUUM67poAkeTrJa4nTk"
		 * ,"ovFm4uAwamkRYryvO4iORka1IqJQ","ovFm4uCCpMlZlc_KeULYfoi7LX5A"
		 * ,"ovFm4uK9DYRtW1YOoSEO3XOeadXM",
		 * "ovFm4uIYXotwBXnSs5npHXXV8Ju4","ovFm4uCsvsGO171YBNcapr8Uyk8Q"}); if
		 * (batchUser != null && batchUser.getUserList() != null) { for
		 * (WeChatUser user : batchUser.getUserList()) {
		 * System.out.println("openid " + user.getOpenid() + "  subscribe  " +
		 * user.getSubscribe() + " subscribe_time " + user.getSubscribe_time() +
		 * " nickname " + user.getNickname() + "  sex " + user.getSex() +
		 * " country " + user.getCountry() + " province " + user.getProvince() +
		 * " city " + user.getCity() + " language " + user.getLanguage() +
		 * " groupId " + user.getGroupid() + " remark " + user.getRemark()); } }
		 */

		/**
		 * 获取用户信息
		 */
		WeChatUser user = getUserInfo("omJust5qC1IbILXJn2ePyBkp_ckE");
		
		System.out.println("OpenID：" + user.getOpenid());
		System.out.println("关注状态：" + user.getSubscribe());
		System.out.println("关注时间：" + user.getSubscribe_time());
		System.out.println("昵称：" + user.getNickname());
		System.out.println("性别：" + user.getSex());
		System.out.println("国家：" + user.getCountry());
		System.out.println("省份：" + user.getProvince());
		System.out.println("城市：" + user.getCity());
		System.out.println("语言：" + user.getLanguage());
		System.out.println("头像：" + user.getHeadimgurl());
		
		// /**
		// * 查询分组
		// */
		// List<WeChatGroup> groupList = getGroups();
		// // 循环输出各分组信息
		// for (WeChatGroup group : groupList) {
		// System.out.println(String.format("ID：%d 名称：%s 用户数：%d", group.getId(),
		// group.getName(), group.getCount()));
		// }

		/**
		 * 创建分组
		 */
		// WeChatGroup group = createGroup("同事");
		// System.out.println(String.format("成功创建分组：%s id：%d", group.getName(),
		// group.getId()));

		/**
		 * 修改分组名
		 */
		// updateGroup( 101, "公司员工");

		/**
		 * 移动用户分组
		 */
		// updateMemberGroup("ovFm4uIYXotwBXnSs5npHXXV8Ju4", 101);
	}

}