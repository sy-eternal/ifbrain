package com.jzeen.travel.wechat.pojo;

/**
 * Title: X2OUR_TRAVEL
 * Description: 临时二维码信息
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public class WeChatQRCode {
	// 获取的二维码ticket
	private String ticket;
	// 二维码的有效时间，单位为秒，最大不超过1800
	private int expireSeconds;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
