package com.jzeen.travel.service.viator.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorProductReview {

    private int sortOrder;

    private String ownerName;

    private String ownerCountry;

    private String productTitle;

    private String ownerAvatarURL;

    private String review;

    private int rating;

    private String productCode;

    private String publishedDate;

    private int ownerId;

    private String viatorFeedback;

    private int reviewId;

    private String submissionDate;

    private String viatorNotes;

    public ViatorProductReview() {

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

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getViatorFeedback() {
        return viatorFeedback;
    }

    public void setViatorFeedback(String viatorFeedback) {
        this.viatorFeedback = viatorFeedback;
    }

    public String getViatorNotes() {
        return viatorNotes;
    }

    public void setViatorNotes(String viatorNotes) {
        this.viatorNotes = viatorNotes;
    }
}