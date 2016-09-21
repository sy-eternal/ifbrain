package com.jzeen.travel.service.viator.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorProductData {

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

    private String primaryGroupId;

    private String voucherRequirements;

    private boolean tourGradesAvailable;

    private boolean hotelPickup;

    private List<ViatorProductUserPhoto> userPhotos;

    private List<ViatorProductReview> reviews;

    private List<ViatorProductVideo> videos;

    private List<ViatorProductTourGrade> tourGrades;

    private List<ViatorProductAgeBand> ageBands;

    private List<ViatorProductBookingQuestion> bookingQuestions;

    private String highlights;

    private List<String> salesPoints;

    private HashMap<String, Integer> ratingCounts;

    private String termsAndConditions;

    private String departureTime;

    private String departureTimeComments;

    private String departurePoint;

    private List<String> inclusions;

    private String voucherOption;

    private String itinerary;

    private int destinationId;

    private List<ViatorProductPhoto> productPhotos;

    private String city;

    private List<String> additionalInfo;

    private String operates;

    private String returnDetails;

    private String mapURL;

    private String specialOffer;

    private List<String> exclusions;

    private String location;

    private String country;

    private String region;

    private String description;

    private String title;

    private String shortDescription;

    private Double price;

    private boolean specialReservation;

    private Double rating;

    private String thumbnailURL;

    private int photoCount;

    private int reviewCount;

    private String supplierCode;

    private int rrp;

    private String rrpformatted;

    private int savingAmount;

    private String savingAmountFormated;

    private int videoCount;

    private boolean onSale;

    private String shortTitle;

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


    public ViatorProductData() {

    }

    public List<String> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<String> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<ViatorProductAgeBand> getAgeBands() {
        return ageBands;
    }

    public void setAgeBands(List<ViatorProductAgeBand> ageBands) {
        this.ageBands = ageBands;
    }

    public String getBookingEngineId() {
        return bookingEngineId;
    }

    public void setBookingEngineId(String bookingEngineId) {
        this.bookingEngineId = bookingEngineId;
    }

    public List<ViatorProductBookingQuestion> getBookingQuestions() {
        return bookingQuestions;
    }

    public void setBookingQuestions(List<ViatorProductBookingQuestion> bookingQuestions) {
        this.bookingQuestions = bookingQuestions;
    }

    public int[] getCatIds() {
        return catIds;
    }

    public void setCatIds(int[] catIds) {
        this.catIds = catIds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTimeComments() {
        return departureTimeComments;
    }

    public void setDepartureTimeComments(String departureTimeComments) {
        this.departureTimeComments = departureTimeComments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getExclusions() {
        return exclusions;
    }

    public void setExclusions(List<String> exclusions) {
        this.exclusions = exclusions;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public boolean isHotelPickup() {
        return hotelPickup;
    }

    public void setHotelPickup(boolean hotelPickup) {
        this.hotelPickup = hotelPickup;
    }

    public List<String> getInclusions() {
        return inclusions;
    }

    public void setInclusions(List<String> inclusions) {
        this.inclusions = inclusions;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMapURL() {
        return mapURL;
    }

    public void setMapURL(String mapURL) {
        this.mapURL = mapURL;
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

    public String getOperates() {
        return operates;
    }

    public void setOperates(String operates) {
        this.operates = operates;
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

    public String getPrimaryGroupId() {
        return primaryGroupId;
    }

    public void setPrimaryGroupId(String primaryGroupId) {
        this.primaryGroupId = primaryGroupId;
    }

    public List<ViatorProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<ViatorProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public HashMap<String, Integer> getRatingCounts() {
        return ratingCounts;
    }

    public void setRatingCounts(HashMap<String, Integer> ratingCounts) {
        this.ratingCounts = ratingCounts;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getReturnDetails() {
        return returnDetails;
    }

    public void setReturnDetails(String returnDetails) {
        this.returnDetails = returnDetails;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<ViatorProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ViatorProductReview> reviews) {
        this.reviews = reviews;
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

    public List<String> getSalesPoints() {
        return salesPoints;
    }

    public void setSalesPoints(List<String> salesPoints) {
        this.salesPoints = salesPoints;
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

    public String getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(String specialOffer) {
        this.specialOffer = specialOffer;
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

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
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

    public List<ViatorProductTourGrade> getTourGrades() {
        return tourGrades;
    }

    public void setTourGrades(List<ViatorProductTourGrade> tourGrades) {
        this.tourGrades = tourGrades;
    }

    public boolean isTourGradesAvailable() {
        return tourGradesAvailable;
    }

    public void setTourGradesAvailable(boolean tourGradesAvailable) {
        this.tourGradesAvailable = tourGradesAvailable;
    }

    public int getTranslationLevel() {
        return translationLevel;
    }

    public void setTranslationLevel(int translationLevel) {
        this.translationLevel = translationLevel;
    }

    public List<ViatorProductUserPhoto> getUserPhotos() {
        return userPhotos;
    }

    public void setUserPhotos(List<ViatorProductUserPhoto> userPhotos) {
        this.userPhotos = userPhotos;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public List<ViatorProductVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<ViatorProductVideo> videos) {
        this.videos = videos;
    }

    public String getVoucherOption() {
        return voucherOption;
    }

    public void setVoucherOption(String voucherOption) {
        this.voucherOption = voucherOption;
    }

    public String getVoucherRequirements() {
        return voucherRequirements;
    }

    public void setVoucherRequirements(String voucherRequirements) {
        this.voucherRequirements = voucherRequirements;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }
}