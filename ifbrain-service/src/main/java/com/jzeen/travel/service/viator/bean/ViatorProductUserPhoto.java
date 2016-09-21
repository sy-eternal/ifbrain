package com.jzeen.travel.service.viator.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorProductUserPhoto {

    private int sortOrder;

    private String ownerName;

    private String ownerCountry;

    private String productTitle;

    private String ownerAvatarURL;

    private String title;

    private String thumbnailURL;

    private String productCode;

    private String caption;

    private boolean editorsPick;

    private int ownerId;

    private int photoId;

    private String photoURL;

    private String photoHiResURL;

    private String photoMediumResURL;

    private String timeUploaded;

    public ViatorProductUserPhoto() {

    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isEditorsPick() {
        return editorsPick;
    }

    public void setEditorsPick(boolean editorsPick) {
        this.editorsPick = editorsPick;
    }

    public String getOwnerAvatarURL() {
        return ownerAvatarURL;
    }

    public void setOwnerAvatarURL(String ownerAvatarURL) {
        this.ownerAvatarURL = ownerAvatarURL;
    }

    public String getOwnerCountry() {
        return ownerCountry;
    }

    public void setOwnerCountry(String ownerCountry) {
        this.ownerCountry = ownerCountry;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhotoHiResURL() {
        return photoHiResURL;
    }

    public void setPhotoHiResURL(String photoHiResURL) {
        this.photoHiResURL = photoHiResURL;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhotoMediumResURL() {
        return photoMediumResURL;
    }

    public void setPhotoMediumResURL(String photoMediumResURL) {
        this.photoMediumResURL = photoMediumResURL;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
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

    public String getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(String timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}