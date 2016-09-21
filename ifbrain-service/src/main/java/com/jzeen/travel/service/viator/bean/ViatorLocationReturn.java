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
public class ViatorLocationReturn extends ViatorBaseReturn {

    private List<ViatorLocationData> dataList;

    public ViatorLocationReturn() {

    }

    public ViatorLocationReturn(String errorMessageText, String errorReference, String errorType) {
        super(errorMessageText, errorReference, errorType);
    }

    public ViatorLocationReturn(String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType) {
        super(errorMessage, errorMessageText, errorName, errorReference, errorType);
    }

    public ViatorLocationReturn(String dateStamp, String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType, String success, int totalCount, String vmid) {
        super(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);
    }

    public List<ViatorLocationData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ViatorLocationData> dataList) {
        this.dataList = dataList;
    }


}