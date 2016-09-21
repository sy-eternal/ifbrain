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
public class ViatorCategoryReturn extends ViatorBaseReturn {

    private List<ViatorCategoryData> dataList;

    public ViatorCategoryReturn() {

    }

    public ViatorCategoryReturn(String errorMessageText, String errorReference, String errorType) {
        super(errorMessageText, errorReference, errorType);
    }

    public ViatorCategoryReturn(String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType) {
        super(errorMessage, errorMessageText, errorName, errorReference, errorType);
    }

    public ViatorCategoryReturn(String dateStamp, String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType, String success, int totalCount, String vmid) {
        super(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);
    }

    public List<ViatorCategoryData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ViatorCategoryData> dataList) {
        this.dataList = dataList;
    }

}