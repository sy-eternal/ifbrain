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
public class ViatorCategoryData {

    private int sortOrder;

    private String thumbnailURL;

    private String productCount;

    private String groupName;

    private int id;

    private List<ViatorSubCategoryData> subcategories;

    public ViatorCategoryData() {
    }

    public ViatorCategoryData(String groupName, int id, String productCount, int sortOrder, String thumbnailURL) {
        this.groupName = groupName;
        this.id = id;
        this.productCount = productCount;
        this.sortOrder = sortOrder;
        this.thumbnailURL = thumbnailURL;
    }

    public ViatorCategoryData(List<ViatorSubCategoryData> subcategories, String groupName, int id, String productCount, int sortOrder, String thumbnailURL) {
        this.subcategories = subcategories;
        this.groupName = groupName;
        this.id = id;
        this.productCount = productCount;
        this.sortOrder = sortOrder;
        this.thumbnailURL = thumbnailURL;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public List<ViatorSubCategoryData> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<ViatorSubCategoryData> subcategories) {
        this.subcategories = subcategories;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}