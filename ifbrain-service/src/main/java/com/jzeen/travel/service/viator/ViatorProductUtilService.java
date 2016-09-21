package com.jzeen.travel.service.viator;

import com.jzeen.travel.common.HttpRequestUtil;
import com.jzeen.travel.service.viator.bean.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description: Viator接口服务
 * Date: 2015年 09月 14日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Service
public class ViatorProductUtilService {

    private static Logger log = LoggerFactory.getLogger(ViatorProductUtilService.class);

    /**
     * 获取产品信息
     * @param code
     * @param currencyCode
     * @return
     */
    public static ViatorProductReturn getProduct(String code, String currencyCode) {
        // 拼接请求地址
        String requestUrl = ViatorCommon.PRODUCT_API;

        if (currencyCode != null && currencyCode != "") {
            requestUrl += "&currencyCode=" + currencyCode;
        }

        requestUrl = requestUrl.replace("API_KEY", ViatorCommon.API_KEY).replace("CODE", code);

        String jsonString = HttpRequestUtil.httpRequest(requestUrl);

        JSONObject jsonObject = JSONObject.fromString(jsonString);

        ViatorProductReturn productReturn = null;

        if (null != jsonObject) {

            try {

                String errorReference = jsonObject.getString("errorReference");
                String dateStamp = jsonObject.getString("dateStamp");
                String errorType = jsonObject.getString("errorType");
                String errorMessage = jsonObject.getString("errorMessage");
                String errorName = jsonObject.getString("errorName");
                String success = jsonObject.getString("success");
                int totalCount = jsonObject.getInt("totalCount");
                String errorMessageText = jsonObject.getString("errorMessageText");
                String vmid = jsonObject.getString("vmid");

                if (errorReference == null || "null".equals(errorReference)) {

                    productReturn = new ViatorProductReturn(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);

                    ViatorProductData productData = new ViatorProductData();

                    JSONObject dataOject = jsonObject.getJSONObject("data");

                    if (dataOject != null) {


                        productData.setSupplierName(dataOject.getString("supplierName"));
                        productData.setCurrencyCode(dataOject.getString("currencyCode"));

                        JSONArray catIdsArray = dataOject.getJSONArray("catIds");
                        if (catIdsArray != null) {
                            int[] catIds = new int[catIdsArray.length()];
                            for (int i = 0; i < catIdsArray.length(); i++) {
                                catIds[i] = catIdsArray.getInt(i);
                            }
                            productData.setCatIds(catIds);
                        }

                        JSONArray subCatIdsArray = dataOject.getJSONArray("subCatIds");
                        if (subCatIdsArray != null) {
                            int[] subCatIds = new int[subCatIdsArray.length()];
                            for (int i = 0; i < subCatIdsArray.length(); i++) {
                                subCatIds[i] = subCatIdsArray.getInt(i);
                            }
                            productData.setCatIds(subCatIds);
                        }

                        productData.setWebURL(dataOject.getString("webURL"));
                        productData.setSpecialReservationDetails(dataOject.getString("specialReservationDetails"));
                        productData.setPanoramaCount(dataOject.getInt("panoramaCount"));
                        productData.setMerchantCancellable(dataOject.getBoolean("merchantCancellable"));
                        productData.setBookingEngineId(dataOject.getString("bookingEngineId"));
                        productData.setOnRequestPeriod(dataOject.getString("onRequestPeriod"));
                        productData.setPrimaryGroupId(dataOject.getString("primaryGroupId"));
                        productData.setVoucherRequirements(dataOject.getString("voucherRequirements"));
                        productData.setTourGradesAvailable(dataOject.getBoolean("tourGradesAvailable"));
                        productData.setHotelPickup(dataOject.getBoolean("hotelPickup"));



                        List<ViatorProductUserPhoto> userPhotos = new ArrayList<ViatorProductUserPhoto>();
                        JSONArray userPhotosArray = dataOject.getJSONArray("userPhotos");
                        if (userPhotosArray != null) {
                            for (int j = 0; j < userPhotosArray.length(); j++) {
                                ViatorProductUserPhoto userPhoto = new ViatorProductUserPhoto();

                                userPhoto.setSortOrder(userPhotosArray.getJSONObject(j).getInt("sortOrder"));
                                userPhoto.setOwnerName(userPhotosArray.getJSONObject(j).getString("ownerName"));
                                userPhoto.setOwnerCountry(userPhotosArray.getJSONObject(j).getString("ownerCountry"));
                                userPhoto.setProductTitle(userPhotosArray.getJSONObject(j).getString("productTitle"));
                                userPhoto.setOwnerAvatarURL(userPhotosArray.getJSONObject(j).getString("ownerAvatarURL"));
                                userPhoto.setTitle(userPhotosArray.getJSONObject(j).getString("title"));
                                userPhoto.setThumbnailURL(userPhotosArray.getJSONObject(j).getString("thumbnailURL"));
                                userPhoto.setProductCode(userPhotosArray.getJSONObject(j).getString("productCode"));
                                userPhoto.setCaption(userPhotosArray.getJSONObject(j).getString("caption"));
                                userPhoto.setEditorsPick(userPhotosArray.getJSONObject(j).getBoolean("editorsPick"));
                                userPhoto.setOwnerId(userPhotosArray.getJSONObject(j).getInt("ownerId"));
                                userPhoto.setPhotoId(userPhotosArray.getJSONObject(j).getInt("photoId"));
                                userPhoto.setPhotoURL(userPhotosArray.getJSONObject(j).getString("photoURL"));
                                userPhoto.setPhotoHiResURL(userPhotosArray.getJSONObject(j).getString("photoHiResURL"));
                                userPhoto.setPhotoMediumResURL(userPhotosArray.getJSONObject(j).getString("photoMediumResURL"));
                                userPhoto.setTimeUploaded(userPhotosArray.getJSONObject(j).getString("timeUploaded"));

                                userPhotos.add(userPhoto);
                            }
                        }
                        productData.setUserPhotos(userPhotos);


                        List<ViatorProductReview> reviews = new ArrayList<ViatorProductReview>();
                        JSONArray reviewsArray = dataOject.getJSONArray("reviews");
                        if (reviewsArray != null) {
                            for (int j = 0; j < reviewsArray.length(); j++) {
                                ViatorProductReview review = new ViatorProductReview();

                                review.setSortOrder(reviewsArray.getJSONObject(j).getInt("sortOrder"));
                                review.setOwnerName(reviewsArray.getJSONObject(j).getString("ownerName"));
                                review.setOwnerCountry(reviewsArray.getJSONObject(j).getString("ownerCountry"));
                                review.setProductTitle(reviewsArray.getJSONObject(j).getString("productTitle"));
                                review.setOwnerAvatarURL(reviewsArray.getJSONObject(j).getString("ownerAvatarURL"));
                                review.setReview(reviewsArray.getJSONObject(j).getString("review"));
                                review.setRating(reviewsArray.getJSONObject(j).getInt("rating"));
                                review.setProductCode(reviewsArray.getJSONObject(j).getString("productCode"));
                                review.setPublishedDate(reviewsArray.getJSONObject(j).getString("publishedDate"));
                                review.setOwnerId(reviewsArray.getJSONObject(j).getInt("ownerId"));
                                review.setViatorFeedback(reviewsArray.getJSONObject(j).getString("viatorFeedback"));
                                review.setReviewId(reviewsArray.getJSONObject(j).getInt("reviewId"));
                                review.setSubmissionDate(reviewsArray.getJSONObject(j).getString("submissionDate"));
                                review.setViatorNotes(reviewsArray.getJSONObject(j).getString("viatorNotes"));

                                reviews.add(review);
                            }
                        }
                        productData.setReviews(reviews);


                        // videos 是字符串还是Array，暂时先不处理videos
                        List<ViatorProductVideo> videos = new ArrayList<ViatorProductVideo>();
//                        JSONArray videosArray = dataOject.getJSONArray("videos");
//                        if (videosArray != null) {
//                            for (int j = 0; j < videosArray.length(); j++) {
//                                ViatorProductVideo video = new ViatorProductVideo();
//
//                                video.setSortOrder(videosArray.getJSONObject(j).getInt("sortOrder"));
//                                video.set_private(videosArray.getJSONObject(j).getBoolean("private"));
//                                video.setSource(videosArray.getJSONObject(j).getString("source"));
//                                video.setDescription(videosArray.getJSONObject(j).getString("description"));
//                                video.setShortDescription(videosArray.getJSONObject(j).getString("shortDescription"));
//                                video.setProductCode(videosArray.getJSONObject(j).getString("productCode"));
//                                video.setTitle(videosArray.getJSONObject(j).getString("title"));
//                                video.setThumbnailURL(videosArray.getJSONObject(j).getString("thumbnailURL"));
//                                video.setOwnerId(videosArray.getJSONObject(j).getInt("ownerId"));
//                                video.setVideoId(videosArray.getJSONObject(j).getString("videoId"));
//                                video.setCopyright(videosArray.getJSONObject(j).getString("copyright"));
//                                video.setPhotoHiResURL(videosArray.getJSONObject(j).getString("photoHiResURL"));
//                                video.setProductTitle(videosArray.getJSONObject(j).getString("productTitle"));
//                                video.setOwnerName(videosArray.getJSONObject(j).getString("ownerName"));
//                                video.setOwnerCountry(videosArray.getJSONObject(j).getString("ownerCountry"));
//                                video.setOwnerAvatarURL(videosArray.getJSONObject(j).getString("ownerAvatarURL"));
//
//                                videos.add(video);
//                            }
//                        }
                        productData.setVideos(videos);


                        List<ViatorProductTourGrade> tourGrades = new ArrayList<ViatorProductTourGrade>();
                        JSONArray tourGradesArray = dataOject.getJSONArray("tourGrades");
                        if (tourGradesArray != null) {
                            for (int j = 0; j < tourGradesArray.length(); j++) {
                                ViatorProductTourGrade tourGrade = new ViatorProductTourGrade();

                                tourGrade.setSortOrder(tourGradesArray.getJSONObject(j).getInt("sortOrder"));
                                tourGrade.setCurrencyCode(tourGradesArray.getJSONObject(j).getString("currencyCode"));
                                tourGrade.setGradeCode(tourGradesArray.getJSONObject(j).getString("gradeCode"));
                                tourGrade.setPriceFromFormatted(tourGradesArray.getJSONObject(j).getString("priceFromFormatted"));
                                tourGrade.setPriceFrom(tourGradesArray.getJSONObject(j).getDouble("priceFrom"));
                                tourGrade.setMerchantNetPriceFrom(tourGradesArray.getJSONObject(j).getDouble("merchantNetPriceFrom"));
                                tourGrade.setMerchantNetPriceFromFormatted(tourGradesArray.getJSONObject(j).getString("merchantNetPriceFromFormatted"));
                                tourGrade.setGradeTitle(tourGradesArray.getJSONObject(j).getString("gradeTitle"));
                                tourGrade.setGradeDepartureTime(tourGradesArray.getJSONObject(j).getString("gradeDepartureTime"));
                                tourGrade.setGradeDescription(tourGradesArray.getJSONObject(j).getString("gradeDescription"));
                                tourGrade.setDefaultLanguageCode(tourGradesArray.getJSONObject(j).getString("defaultLanguageCode"));


                                JSONObject langObject = tourGradesArray.getJSONObject(j).getJSONObject("langServices");
                                String[] langStrArray = langObject.toString().replace("{", "").replace("}", "").split(",");
                                HashMap<String, String> langMap = new HashMap<String, String>();

                                if ((langStrArray != null)) {
                                    for (String item : langStrArray) {
                                        String[] itemArray = item.split(":");
                                        if ((itemArray != null) && (itemArray.length % 2 == 0)) {
                                            for (int k = 0; k * 2 < itemArray.length; k++) {
                                                langMap.put(itemArray[k].replaceAll("\"", "").trim(), itemArray[k + 1].replaceAll("\"", "").trim());
                                            }
                                        }
                                    }
                                    tourGrade.setLangServices(langMap);
                                }

                                tourGrades.add(tourGrade);
                            }
                        }
                        productData.setTourGrades(tourGrades);


                        List<ViatorProductAgeBand> ageBands = new ArrayList<ViatorProductAgeBand>();
                        JSONArray ageBandsArray = dataOject.getJSONArray("ageBands");
                        if (ageBandsArray != null) {
                            for (int j = 0; j < ageBandsArray.length(); j++) {
                                ViatorProductAgeBand ageBand = new ViatorProductAgeBand();

                                ageBand.setSortOrder(ageBandsArray.getJSONObject(j).getInt("sortOrder"));
                                ageBand.setAgeFrom(ageBandsArray.getJSONObject(j).getInt("ageFrom"));
                                ageBand.setAgeTo(ageBandsArray.getJSONObject(j).getInt("ageTo"));
                                ageBand.setBandId(ageBandsArray.getJSONObject(j).getInt("bandId"));
                                ageBand.setPluralDescription(ageBandsArray.getJSONObject(j).getString("pluralDescription"));
                                ageBand.setAdult(ageBandsArray.getJSONObject(j).getBoolean("adult"));
                                ageBand.setTreatAsAdult(ageBandsArray.getJSONObject(j).getBoolean("treatAsAdult"));
                                ageBand.setDescription(ageBandsArray.getJSONObject(j).getString("description"));
                                ageBand.setCount(ageBandsArray.getJSONObject(j).getInt("count"));

                                ageBands.add(ageBand);
                            }
                        }
                        productData.setAgeBands(ageBands);


                        List<ViatorProductBookingQuestion> bookingQuestions = new ArrayList<ViatorProductBookingQuestion>();
                        JSONArray bookingQuestionsArray = dataOject.getJSONArray("bookingQuestions");
                        if (bookingQuestionsArray != null) {
                            for (int j = 0; j < bookingQuestionsArray.length(); j++) {
                                ViatorProductBookingQuestion question = new ViatorProductBookingQuestion();

                                question.setSortOrder(bookingQuestionsArray.getJSONObject(j).getInt("sortOrder"));
                                question.setMessage(bookingQuestionsArray.getJSONObject(j).getString("message"));
                                question.setRequired(bookingQuestionsArray.getJSONObject(j).getBoolean("required"));
                                question.setTitle(bookingQuestionsArray.getJSONObject(j).getString("title"));
                                question.setQuestionId(bookingQuestionsArray.getJSONObject(j).getString("questionId"));
                                question.setSubTitle(bookingQuestionsArray.getJSONObject(j).getString("subTitle"));

                                bookingQuestions.add(question);
                            }
                        }
                        productData.setBookingQuestions(bookingQuestions);


                        productData.setHighlights(dataOject.getString("highlights"));


                        List<String> salesPoints = new ArrayList<String>();
                        JSONArray salesPointsArray = dataOject.getJSONArray("salesPoints");
                        if (salesPointsArray != null) {
                            for (int j = 0; j < salesPointsArray.length(); j++) {
                                String salesPoint = salesPointsArray.getString(j);
                                salesPoints.add(salesPoint);
                            }
                        }
                        productData.setSalesPoints(salesPoints);


                        HashMap<String, Integer> ratingCounts = new HashMap<String, Integer>();
                        JSONObject ratingCountsObject = dataOject.getJSONObject("ratingCounts");

                        if (ratingCountsObject != null) {
                            ratingCounts.put("1", ratingCountsObject.getInt("1"));
                            ratingCounts.put("2", ratingCountsObject.getInt("2"));
                            ratingCounts.put("3", ratingCountsObject.getInt("3"));
                            ratingCounts.put("4", ratingCountsObject.getInt("4"));
                            ratingCounts.put("5", ratingCountsObject.getInt("5"));
                        }
                        productData.setRatingCounts(ratingCounts);


                        productData.setTermsAndConditions(dataOject.getString("termsAndConditions"));
                        productData.setDepartureTime(dataOject.getString("departureTime"));
                        productData.setDepartureTimeComments(dataOject.getString("departureTimeComments"));
                        productData.setDeparturePoint(dataOject.getString("departurePoint"));



                        List<String> inclusions = new ArrayList<String>();
                        JSONArray inclusionsArray = dataOject.getJSONArray("inclusions");
                        if (inclusionsArray != null) {
                            for (int j = 0; j < inclusionsArray.length(); j++) {
                                String inclusion = inclusionsArray.getString(j);
                                inclusions.add(inclusion);
                            }
                        }
                        productData.setInclusions(inclusions);

                        productData.setVoucherOption(dataOject.getString("voucherOption"));
                        productData.setItinerary(dataOject.getString("itinerary"));
                        productData.setDestinationId(dataOject.getInt("destinationId"));


                        List<ViatorProductPhoto> productPhotos = new ArrayList<ViatorProductPhoto>();
                        JSONArray productPhotosArray = dataOject.getJSONArray("productPhotos");
                        if (productPhotosArray != null) {
                            for (int j = 0; j < productPhotosArray.length(); j++) {
                                ViatorProductPhoto photo = new ViatorProductPhoto();

                                photo.setPhotoURL(productPhotosArray.getJSONObject(j).getString("photoURL"));
                                photo.setCaption(productPhotosArray.getJSONObject(j).getString("caption"));
                                photo.setSupplier(productPhotosArray.getJSONObject(j).getString("supplier"));

                                productPhotos.add(photo);
                            }
                        }
                        productData.setProductPhotos(productPhotos);


                        productData.setCity(dataOject.getString("city"));

                        List<String> additionalInfo = new ArrayList<String>();
                        JSONArray additionalInfoArray = dataOject.getJSONArray("additionalInfo");
                        if (additionalInfoArray != null) {
                            for (int j = 0; j < additionalInfoArray.length(); j++) {
                                String info = additionalInfoArray.getString(j);
                                additionalInfo.add(info);
                            }
                        }
                        productData.setAdditionalInfo(additionalInfo);


                        productData.setOperates(dataOject.getString("operates"));
                        productData.setReturnDetails(dataOject.getString("returnDetails"));
                        productData.setMapURL(dataOject.getString("mapURL"));
                        productData.setSpecialOffer(dataOject.getString("specialOffer"));


                        List<String> exclusions = new ArrayList<String>();
                        JSONArray exclusionsArray = dataOject.getJSONArray("exclusions");
                        if (exclusionsArray != null) {
                            for (int j = 0; j < exclusionsArray.length(); j++) {
                                String exclusion = exclusionsArray.getString(j);
                                exclusions.add(exclusion);
                            }
                        }
                        productData.setExclusions(exclusions);


                        productData.setLocation(dataOject.getString("location"));
                        productData.setCountry(dataOject.getString("country"));
                        productData.setRegion(dataOject.getString("region"));
                        productData.setDescription(dataOject.getString("description"));
                        productData.setTitle(dataOject.getString("title"));
                        productData.setShortDescription(dataOject.getString("shortDescription"));
                        productData.setPrice(dataOject.getDouble("price"));
                        productData.setSpecialReservation(dataOject.getBoolean("specialReservation"));
                        productData.setRating(dataOject.getDouble("rating"));
                        productData.setThumbnailURL(dataOject.getString("thumbnailURL"));
                        productData.setPhotoCount(dataOject.getInt("photoCount"));
                        productData.setReviewCount(dataOject.getInt("reviewCount"));
                        productData.setSupplierCode(dataOject.getString("supplierCode"));
                        productData.setRrp(dataOject.getInt("rrp"));
                        productData.setRrpformatted(dataOject.getString("rrpformatted"));
                        productData.setSavingAmount(dataOject.getInt("savingAmount"));
                        productData.setSavingAmountFormated(dataOject.getString("savingAmountFormated"));
                        productData.setVideoCount(dataOject.getInt("videoCount"));
                        productData.setOnSale(dataOject.getBoolean("onSale"));
                        productData.setShortTitle(dataOject.getString("shortTitle"));
                        productData.setTranslationLevel(dataOject.getInt("translationLevel"));
                        productData.setPrimaryDestinationId(dataOject.getInt("primaryDestinationId"));
                        productData.setPrimaryDestinationName(dataOject.getString("primaryDestinationName"));
                        productData.setPriceFormatted(dataOject.getString("priceFormatted"));
                        productData.setMerchantNetPriceFrom(dataOject.getDouble("merchantNetPriceFrom"));
                        productData.setMerchantNetPriceFromFormatted(dataOject.getString("merchantNetPriceFromFormatted"));
                        productData.setSpecialOfferAvailable(dataOject.getBoolean("specialOfferAvailable"));
                        productData.setThumbnailHiResURL(dataOject.getString("thumbnailHiResURL"));
                        productData.setDuration(dataOject.getString("duration"));
                        productData.setCode(dataOject.getString("code"));

                        productReturn.setData(productData);
                    }

                    log.info("获取产品信息成功  errorReference:{} dateStamp:{} errorType:{} errorMessage:{} errorName:{} success:{} totalCount:{} errorMessageText:{} vmid ", new Object[]{errorReference, dateStamp, errorType, errorMessage, errorName, success, totalCount, errorMessageText, vmid});
                } else {
                    productReturn = new ViatorProductReturn(errorMessage, errorMessageText, errorName, errorReference, errorType);

                    log.error("获取产品信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                }


            } catch (Exception e) {
                String errorType = jsonObject.getString("errorType");
                String errorReference = jsonObject.getString("errorReference");
                String errorMessageText = jsonObject.getString("errorMessageText");
                log.error("获取产品信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                productReturn = new ViatorProductReturn(errorMessageText, errorReference, errorType);
            }
        }

        return productReturn;
    }



    public static ViatorProductReviewReturn getProductReviews(String code, String topX) {
        // 拼接请求地址
        String requestUrl = ViatorCommon.PRODUCT_REVIEWS_API;
        requestUrl = requestUrl.replace("API_KEY", ViatorCommon.API_KEY).replace("CODE", code).replace("TOPX", topX);

        String jsonString = HttpRequestUtil.httpRequest(requestUrl);

        JSONObject jsonObject = JSONObject.fromString(jsonString);

        ViatorProductReviewReturn productReviewReturn = null;

        if (null != jsonObject) {

            try {

                String errorReference = jsonObject.getString("errorReference");
                String dateStamp = jsonObject.getString("dateStamp");
                String errorType = jsonObject.getString("errorType");
                String errorMessage = jsonObject.getString("errorMessage");
                String errorName = jsonObject.getString("errorName");
                String success = jsonObject.getString("success");
                int totalCount = jsonObject.getInt("totalCount");
                String errorMessageText = jsonObject.getString("errorMessageText");
                String vmid = jsonObject.getString("vmid");

                if (errorReference == null || "null".equals(errorReference)) {

                    productReviewReturn = new ViatorProductReviewReturn(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);

                    List<ViatorProductReview> reviewList = JSONArray.toList(jsonObject.getJSONArray("data"), ViatorProductReview.class);
                    productReviewReturn.setData(reviewList);

                    log.info("获取review信息成功  errorReference:{} dateStamp:{} errorType:{} errorMessage:{} errorName:{} success:{} totalCount:{} errorMessageText:{} vmid ", new Object[]{errorReference, dateStamp, errorType, errorMessage, errorName, success, totalCount, errorMessageText, vmid});
                } else {
                    productReviewReturn = new ViatorProductReviewReturn(errorMessage, errorMessageText, errorName, errorReference, errorType);

                    log.error("获取review信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                }


            } catch (Exception e) {
                String errorType = jsonObject.getString("errorType");
                String errorReference = jsonObject.getString("errorReference");
                String errorMessageText = jsonObject.getString("errorMessageText");
                log.error("获取review信息失败， errorType:{} errorMessageText:{} 错误信息:{}", new String[]{errorType, errorMessageText, e.toString()});
                productReviewReturn = new ViatorProductReviewReturn(errorMessageText, errorReference, errorType);
            }
        }

        return productReviewReturn;
    }


    public static ViatorProductPhotoReturn getProductPhotos(String code, String topX) {
        // 拼接请求地址
        String requestUrl = ViatorCommon.PRODUCT_PHOTOS_API;
        requestUrl = requestUrl.replace("API_KEY", ViatorCommon.API_KEY).replace("CODE", code).replace("TOPX", topX);

        String jsonString = HttpRequestUtil.httpRequest(requestUrl);

        JSONObject jsonObject = JSONObject.fromString(jsonString);

        ViatorProductPhotoReturn productPhotoReturn = null;

        if (null != jsonObject) {

            try {

                String errorReference = jsonObject.getString("errorReference");
                String dateStamp = jsonObject.getString("dateStamp");
                String errorType = jsonObject.getString("errorType");
                String errorMessage = jsonObject.getString("errorMessage");
                String errorName = jsonObject.getString("errorName");
                String success = jsonObject.getString("success");
                int totalCount = jsonObject.getInt("totalCount");
                String errorMessageText = jsonObject.getString("errorMessageText");
                String vmid = jsonObject.getString("vmid");

                if (errorReference == null || "null".equals(errorReference)) {

                    productPhotoReturn = new ViatorProductPhotoReturn(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);

                    List<ViatorProductUserPhoto> reviewList = JSONArray.toList(jsonObject.getJSONArray("data"), ViatorProductUserPhoto.class);
                    productPhotoReturn.setData(reviewList);

                    log.info("获取review信息成功  errorReference:{} dateStamp:{} errorType:{} errorMessage:{} errorName:{} success:{} totalCount:{} errorMessageText:{} vmid ", new Object[]{errorReference, dateStamp, errorType, errorMessage, errorName, success, totalCount, errorMessageText, vmid});
                } else {
                    productPhotoReturn = new ViatorProductPhotoReturn(errorMessage, errorMessageText, errorName, errorReference, errorType);

                    log.error("获取review信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                }


            } catch (Exception e) {
                String errorType = jsonObject.getString("errorType");
                String errorReference = jsonObject.getString("errorReference");
                String errorMessageText = jsonObject.getString("errorMessageText");
                log.error("获取review信息失败， errorType:{} errorMessageText:{} 错误信息:{}", new String[]{errorType, errorMessageText, e.toString()});
                productPhotoReturn = new ViatorProductPhotoReturn(errorMessageText, errorReference, errorType);
            }
        }

        return productPhotoReturn;
    }


    public static void main(String args[]) {
        ViatorProductReturn product = getProduct("2452T7", null);
        product.getData();

//        ViatorProductReviewReturn reviewReturn = getProductReviews("2452T7", "2-9");
//        reviewReturn.getData();
//
//        ViatorProductPhotoReturn photoReturn = getProductPhotos("2452T7", "2-9");
//        photoReturn.getData();

    }

}