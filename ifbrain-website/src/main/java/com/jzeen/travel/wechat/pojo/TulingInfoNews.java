package com.jzeen.travel.wechat.pojo;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 19日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class TulingInfoNews {

    private String article;

    private String source;

    private String detailurl;

    private String icon;

    public TulingInfoNews(){
        super();
    }

    public TulingInfoNews(String article, String source, String detailurl, String icon) {
        this.article = article;
        this.source = source;
        this.detailurl = detailurl;
        this.icon = icon;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}