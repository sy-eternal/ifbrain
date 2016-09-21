package com.jzeen.travel.service.viator.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorProductReturn extends ViatorBaseReturn {

    private ViatorProductData data;

    public ViatorProductReturn() {

    }

    public ViatorProductReturn(String errorMessageText, String errorReference, String errorType) {
        super(errorMessageText, errorReference, errorType);
    }

    public ViatorProductReturn(String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType) {
        super(errorMessage, errorMessageText, errorName, errorReference, errorType);
    }

    public ViatorProductReturn(String dateStamp, String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType, String success, int totalCount, String vmid) {
        super(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);
    }

    public ViatorProductData getData() {
        return data;
    }

    public void setData(ViatorProductData data) {
        this.data = data;
    }
}