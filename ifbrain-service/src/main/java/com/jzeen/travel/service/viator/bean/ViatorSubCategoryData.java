package com.jzeen.travel.service.viator.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorSubCategoryData {

    private int sortOrder;

    private String subcategoryName;

    private int categoryId;

    private int subcategoryId;


    public ViatorSubCategoryData() {
    }

    public ViatorSubCategoryData(int categoryId, int sortOrder, int subcategoryId, String subcategoryName) {
        this.categoryId = categoryId;
        this.sortOrder = sortOrder;
        this.subcategoryId = subcategoryId;
        this.subcategoryName = subcategoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}