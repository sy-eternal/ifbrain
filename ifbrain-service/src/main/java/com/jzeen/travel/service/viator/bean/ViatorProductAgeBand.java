package com.jzeen.travel.service.viator.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorProductAgeBand {

    private int sortOrder;

    private int ageFrom;

    private int ageTo;

    private int bandId;

    private String pluralDescription;

    private boolean adult;

    private boolean treatAsAdult;

    private String description;

    private int count;

    public ViatorProductAgeBand() {

    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(int ageFrom) {
        this.ageFrom = ageFrom;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPluralDescription() {
        return pluralDescription;
    }

    public void setPluralDescription(String pluralDescription) {
        this.pluralDescription = pluralDescription;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isTreatAsAdult() {
        return treatAsAdult;
    }

    public void setTreatAsAdult(boolean treatAsAdult) {
        this.treatAsAdult = treatAsAdult;
    }
}

