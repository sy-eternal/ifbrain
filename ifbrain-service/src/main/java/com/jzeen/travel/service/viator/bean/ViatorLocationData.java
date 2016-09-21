package com.jzeen.travel.service.viator.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorLocationData {

    private int sortOrder;

    private boolean selectable;

    private String defaultCurrencyCode;

    private String iataCode;

    private String timeZone;

    private String destinationType;

    private int destinationId;

    private String destinationName;

    private int parentId;

    private String lookupId;

    private Double latitude;

    private Double longitude;


    public ViatorLocationData() {

    }

    public ViatorLocationData(String defaultCurrencyCode, int destinationId, String destinationName, String destinationType, String iataCode, Double latitude, Double longitude, String lookupId, int parentId, boolean selectable, int sortOrder, String timeZone) {
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.destinationId = destinationId;
        this.destinationName = destinationName;
        this.destinationType = destinationType;
        this.iataCode = iataCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lookupId = lookupId;
        this.parentId = parentId;
        this.selectable = selectable;
        this.sortOrder = sortOrder;
        this.timeZone = timeZone;
    }

    public String getDefaultCurrencyCode() {
        return defaultCurrencyCode;
    }

    public void setDefaultCurrencyCode(String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLookupId() {
        return lookupId;
    }

    public void setLookupId(String lookupId) {
        this.lookupId = lookupId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}