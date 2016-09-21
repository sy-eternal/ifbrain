package com.jzeen.travel.wechat.message.resp;

import com.jzeen.travel.wechat.message.model.Music;

public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
