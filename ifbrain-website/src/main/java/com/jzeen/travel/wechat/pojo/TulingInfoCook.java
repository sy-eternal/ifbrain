package com.jzeen.travel.wechat.pojo;

/**
 * Title: X2OUR_TRAVEL
 * Description: 菜谱、视频、小说等内容
 * Date: 2015年 08月 20日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class TulingInfoCook {

    private String name;

    private String info;

    private String detailurl;

    private String icon;

    public TulingInfoCook() {
        super();
    }

    public TulingInfoCook(String name, String info, String detailurl, String icon) {
        this.name = name;
        this.info = info;
        this.detailurl = detailurl;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDetailurl() {
        return detailurl;
    }

    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}