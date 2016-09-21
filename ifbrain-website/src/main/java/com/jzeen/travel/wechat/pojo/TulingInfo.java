package com.jzeen.travel.wechat.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 19日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class TulingInfo {

    private int code;
    private String text;
    private String url;

    private List<TulingInfoNews> newsList;

    private List<TulingInfoTrain> trainList;

    private List<TulingInfoCook> cookList;


    public TulingInfo() {
        super();
    }

    /**
     * 文字类消息
     *
     * @param code
     * @param text
     */
    public TulingInfo(int code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 链接类
     *
     * @param code
     * @param text
     * @param url
     */
    public TulingInfo(int code, String text, String url) {
        this.code = code;
        this.text = text;
        this.url = url;
    }

    /**
     * 初始化新闻列表
     */
    public void initNewsList() {
        if (this.newsList == null) {
            this.newsList = new ArrayList<TulingInfoNews>();
        }
    }


    /**
     * 增加新闻到新闻列表
     *
     * @param news
     */
    public void addNews(TulingInfoNews news) {
        if (this.newsList == null) {
            this.initNewsList();
        }
        this.getNewsList().add(news);
    }

    /**
     * 初始化列车列表
     */
    public void initTrainList() {
        if (this.trainList == null) {
            this.trainList = new ArrayList<TulingInfoTrain>();
        }
    }

    /**
     * 增加新闻到新闻列表
     *
     * @param train
     */
    public void addTrain(TulingInfoTrain train) {
        if (this.trainList == null) {
            this.initTrainList();
        }
        this.getTrainList().add(train);
    }

    /**
     * 初始化菜谱列表
     */
    public void initCookList() {
        if (this.cookList == null) {
            this.cookList = new ArrayList<TulingInfoCook>();
        }
    }

    /**
     * 增加菜谱到菜谱列表
     *
     * @param cook
     */
    public void addCook(TulingInfoCook cook) {
        if (this.cookList == null) {
            this.initCookList();
        }
        this.getCookList().add(cook);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TulingInfoNews> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<TulingInfoNews> newsList) {
        this.newsList = newsList;
    }

    public List<TulingInfoTrain> getTrainList() {
        return trainList;
    }

    public void setTrainList(List<TulingInfoTrain> trainList) {
        this.trainList = trainList;
    }

    public List<TulingInfoCook> getCookList() {
        return cookList;
    }

    public void setCookList(List<TulingInfoCook> cookList) {
        this.cookList = cookList;
    }
}