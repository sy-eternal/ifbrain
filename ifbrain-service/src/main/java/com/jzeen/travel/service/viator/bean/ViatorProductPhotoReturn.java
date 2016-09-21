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
public class ViatorProductPhotoReturn extends ViatorBaseReturn {

    private List<ViatorProductUserPhoto> data;

    public ViatorProductPhotoReturn() {

    }

    public ViatorProductPhotoReturn(String errorMessageText, String errorReference, String errorType) {
        super(errorMessageText, errorReference, errorType);
    }

    public ViatorProductPhotoReturn(String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType) {
        super(errorMessage, errorMessageText, errorName, errorReference, errorType);
    }

    public ViatorProductPhotoReturn(String dateStamp, String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType, String success, int totalCount, String vmid) {
        super(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);
    }

    public List<ViatorProductUserPhoto> getData() {
        return data;
    }

    public void setData(List<ViatorProductUserPhoto> data) {
        this.data = data;
    }
}