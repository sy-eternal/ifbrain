package com.jzeen.travel.service.viator.bean;

import java.util.HashMap;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorProductTourGrade {

    private int sortOrder;

    private String currencyCode;

    private HashMap<String, String> langServices;

    private String gradeCode;

    private String priceFromFormatted;

    private Double priceFrom;

    private Double merchantNetPriceFrom;

    private String merchantNetPriceFromFormatted;

    private String gradeTitle;

    private String gradeDepartureTime;

    private String gradeDescription;

    private String defaultLanguageCode;

    public ViatorProductTourGrade() {

    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDefaultLanguageCode() {
        return defaultLanguageCode;
    }

    public void setDefaultLanguageCode(String defaultLanguageCode) {
        this.defaultLanguageCode = defaultLanguageCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeDepartureTime() {
        return gradeDepartureTime;
    }

    public void setGradeDepartureTime(String gradeDepartureTime) {
        this.gradeDepartureTime = gradeDepartureTime;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public String getGradeTitle() {
        return gradeTitle;
    }

    public void setGradeTitle(String gradeTitle) {
        this.gradeTitle = gradeTitle;
    }

    public HashMap<String, String> getLangServices() {
        return langServices;
    }

    public void setLangServices(HashMap<String, String> langServices) {
        this.langServices = langServices;
    }

    public Double getMerchantNetPriceFrom() {
        return merchantNetPriceFrom;
    }

    public void setMerchantNetPriceFrom(Double merchantNetPriceFrom) {
        this.merchantNetPriceFrom = merchantNetPriceFrom;
    }

    public String getMerchantNetPriceFromFormatted() {
        return merchantNetPriceFromFormatted;
    }

    public void setMerchantNetPriceFromFormatted(String merchantNetPriceFromFormatted) {
        this.merchantNetPriceFromFormatted = merchantNetPriceFromFormatted;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceFromFormatted() {
        return priceFromFormatted;
    }

    public void setPriceFromFormatted(String priceFromFormatted) {
        this.priceFromFormatted = priceFromFormatted;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}