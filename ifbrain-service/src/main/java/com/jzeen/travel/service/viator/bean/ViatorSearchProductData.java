package com.jzeen.travel.service.viator.bean;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorSearchProductData {

    private int sortOrder;

    private String supplierName;

    private String currencyCode;

    private int[] catIds;

    private int[] subCatIds;

    private String webURL;

    private String specialReservationDetails;

    private int panoramaCount;

    private boolean merchantCancellable;

    private String bookingEngineId;

    private String onRequestPeriod;

    private int primaryGroupId;

    private String shortDescription;

    private Double price;

    private int rrp;

    private boolean specialReservation;

    private Double rating;

    private String thumbnailURL;

    private int photoCount;

    private int reviewCount;

    private String supplierCode;

    private String rrpformatted;

    private boolean onSale;

    private int savingAmount;

    private String savingAmountFormated;

    private int translationLevel;

    private int primaryDestinationId;

    private String primaryDestinationName;

    private String priceFormatted;

    private Double merchantNetPriceFrom;

    private String merchantNetPriceFromFormatted;

    private boolean specialOfferAvailable;

    private String thumbnailHiResURL;

    private String duration;

    private String code;

    private String title;

    private String shortTitle;

    private int videoCount;

    public ViatorSearchProductData() {

    }

    public String getBookingEngineId() {
        return bookingEngineId;
    }

    public void setBookingEngineId(String bookingEngineId) {
        this.bookingEngineId = bookingEngineId;
    }

    public int[] getCatIds() {
        return catIds;
    }

    public void setCatIds(int[] catIds) {
        this.catIds = catIds;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isMerchantCancellable() {
        return merchantCancellable;
    }

    public void setMerchantCancellable(boolean merchantCancellable) {
        this.merchantCancellable = merchantCancellable;
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

    public String getOnRequestPeriod() {
        return onRequestPeriod;
    }

    public void setOnRequestPeriod(String onRequestPeriod) {
        this.onRequestPeriod = onRequestPeriod;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public int getPanoramaCount() {
        return panoramaCount;
    }

    public void setPanoramaCount(int panoramaCount) {
        this.panoramaCount = panoramaCount;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPriceFormatted() {
        return priceFormatted;
    }

    public void setPriceFormatted(String priceFormatted) {
        this.priceFormatted = priceFormatted;
    }

    public int getPrimaryDestinationId() {
        return primaryDestinationId;
    }

    public void setPrimaryDestinationId(int primaryDestinationId) {
        this.primaryDestinationId = primaryDestinationId;
    }

    public String getPrimaryDestinationName() {
        return primaryDestinationName;
    }

    public void setPrimaryDestinationName(String primaryDestinationName) {
        this.primaryDestinationName = primaryDestinationName;
    }

    public int getPrimaryGroupId() {
        return primaryGroupId;
    }

    public void setPrimaryGroupId(int primaryGroupId) {
        this.primaryGroupId = primaryGroupId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getRrp() {
        return rrp;
    }

    public void setRrp(int rrp) {
        this.rrp = rrp;
    }

    public String getRrpformatted() {
        return rrpformatted;
    }

    public void setRrpformatted(String rrpformatted) {
        this.rrpformatted = rrpformatted;
    }

    public int getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(int savingAmount) {
        this.savingAmount = savingAmount;
    }

    public String getSavingAmountFormated() {
        return savingAmountFormated;
    }

    public void setSavingAmountFormated(String savingAmountFormated) {
        this.savingAmountFormated = savingAmountFormated;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isSpecialOfferAvailable() {
        return specialOfferAvailable;
    }

    public void setSpecialOfferAvailable(boolean specialOfferAvailable) {
        this.specialOfferAvailable = specialOfferAvailable;
    }

    public boolean isSpecialReservation() {
        return specialReservation;
    }

    public void setSpecialReservation(boolean specialReservation) {
        this.specialReservation = specialReservation;
    }

    public String getSpecialReservationDetails() {
        return specialReservationDetails;
    }

    public void setSpecialReservationDetails(String specialReservationDetails) {
        this.specialReservationDetails = specialReservationDetails;
    }

    public int[] getSubCatIds() {
        return subCatIds;
    }

    public void setSubCatIds(int[] subCatIds) {
        this.subCatIds = subCatIds;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getThumbnailHiResURL() {
        return thumbnailHiResURL;
    }

    public void setThumbnailHiResURL(String thumbnailHiResURL) {
        this.thumbnailHiResURL = thumbnailHiResURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTranslationLevel() {
        return translationLevel;
    }

    public void setTranslationLevel(int translationLevel) {
        this.translationLevel = translationLevel;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }
}