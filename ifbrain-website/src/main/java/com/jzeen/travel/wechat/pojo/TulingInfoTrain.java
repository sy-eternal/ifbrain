package com.jzeen.travel.wechat.pojo;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 19日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class TulingInfoTrain {
    private String trainnum;

    private String start;

    private String terminal;

    private String starttime;

    private String endtime;

    private String detailurl;

    private String icon;

    public TulingInfoTrain() {
        super();
    }

    public TulingInfoTrain(String trainnum, String start, String terminal, String starttime, String endtime, String detailurl, String icon) {
        this.trainnum = trainnum;
        this.start = start;
        this.terminal = terminal;
        this.starttime = starttime;
        this.endtime = endtime;
        this.detailurl = detailurl;
        this.icon = icon;
    }

    public String getTrainnum() {
        return trainnum;
    }

    public void setTrainnum(String trainnum) {
        this.trainnum = trainnum;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
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