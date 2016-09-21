package com.jzeen.travel.openctc.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 01日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class RandcodeIdentifier {


    private String resCode;
    private String identifier;
    private String createAt;

    public RandcodeIdentifier() {

    }

    public RandcodeIdentifier(String resCode, String identifier, String createAt) {
        this.resCode = resCode;
        this.identifier = identifier;
        this.createAt = createAt;

    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}