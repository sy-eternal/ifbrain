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
public class ViatorProductReviewReturn extends ViatorBaseReturn {

    private List<ViatorProductReview> data;

    public ViatorProductReviewReturn() {

    }

    public ViatorProductReviewReturn(String errorMessageText, String errorReference, String errorType) {
        super(errorMessageText, errorReference, errorType);
    }

    public ViatorProductReviewReturn(String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType) {
        super(errorMessage, errorMessageText, errorName, errorReference, errorType);
    }

    public ViatorProductReviewReturn(String dateStamp, String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType, String success, int totalCount, String vmid) {
        super(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);
    }

    public List<ViatorProductReview> getData() {
        return data;
    }

    public void setData(List<ViatorProductReview> data) {
        this.data = data;
    }
}