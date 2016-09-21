package com.jzeen.travel.service.viator.bean;

import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorSearchProductReturn extends ViatorBaseReturn {

    private List<ViatorSearchProductData> data;

    public ViatorSearchProductReturn() {

    }

    public ViatorSearchProductReturn(String errorMessageText, String errorReference, String errorType) {
        super(errorMessageText, errorReference, errorType);
    }

    public ViatorSearchProductReturn(String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType) {
        super(errorMessage, errorMessageText, errorName, errorReference, errorType);
    }

    public ViatorSearchProductReturn(String dateStamp, String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType, String success, int totalCount, String vmid) {
        super(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);
    }

    public List<ViatorSearchProductData> getData() {
        return data;
    }

    public void setData(List<ViatorSearchProductData> data) {
        this.data = data;
    }
}