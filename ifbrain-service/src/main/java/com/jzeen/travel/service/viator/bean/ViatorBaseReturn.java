package com.jzeen.travel.service.viator.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorBaseReturn {



    private String errorReference;
    private String dateStamp;
    private String errorType;
    private String errorMessage;
    private String errorName;
    private String success;
    private int totalCount;
    private String errorMessageText;
    private String vmid;


    public ViatorBaseReturn() {

    }

    public ViatorBaseReturn(String errorMessageText, String errorReference, String errorType) {
        this.errorMessageText = errorMessageText;
        this.errorReference = errorReference;
        this.errorType = errorType;
    }

    public ViatorBaseReturn(String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType) {
        this.errorMessage = errorMessage;
        this.errorMessageText = errorMessageText;
        this.errorName = errorName;
        this.errorReference = errorReference;
        this.errorType = errorType;
    }

    public ViatorBaseReturn(String dateStamp, String errorMessage, String errorMessageText, String errorName, String errorReference, String errorType, String success, int totalCount, String vmid) {
        this.dateStamp = dateStamp;
        this.errorMessage = errorMessage;
        this.errorMessageText = errorMessageText;
        this.errorName = errorName;
        this.errorReference = errorReference;
        this.errorType = errorType;
        this.success = success;
        this.totalCount = totalCount;
        this.vmid = vmid;
    }



    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessageText() {
        return errorMessageText;
    }

    public void setErrorMessageText(String errorMessageText) {
        this.errorMessageText = errorMessageText;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorReference() {
        return errorReference;
    }

    public void setErrorReference(String errorReference) {
        this.errorReference = errorReference;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getVmid() {
        return vmid;
    }

    public void setVmid(String vmid) {
        this.vmid = vmid;
    }
}