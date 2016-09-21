package com.jzeen.travel.service.viator;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 11日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class ViatorCommon {

    public static final String API_KEY = "9443181152616282";


    public static final String URL_API = "http://prelive.viatorapi.viator.com";

    public static final String TAXONOMY_LOCATIONS_API = URL_API + "/service/taxonomy/locations?apiKey=API_KEY";

    public static final String TAXONOMY_CATEGORIES_API = URL_API + "/service/taxonomy/categories?apiKey=API_KEY";

    public static final String PRODUCT_API = URL_API + "/service/product?apiKey=API_KEY&code=CODE";

    public static final String PRODUCT_REVIEWS_API = URL_API + "/service/product/reviews?apiKey=API_KEY&code=CODE&topX=TOPX&sortOrder=REVIEW_RATING_D";

    public static final String PRODUCT_PHOTOS_API = URL_API + "/service/product/photos?apiKey=API_KEY&code=CODE&topX=TOPX";


    public static final String SEARCH_PRODUCTS_API = URL_API + "/search/products?apiKey=API_KEY";

    public static final String SEARCH_PRODUCTS_CODES_API = URL_API + "/search/products/codes?apiKey=API_KEY";

    public static final String SEARCH_FREETEXT_API = URL_API + "/search/freetext?apiKey=API_KEY";



    // Traveler Rating (low->high) (not averages)
    public static final String REVIEW_RATING_A = "REVIEW_RATING_A";

    // Traveler Rating (high->low) (not averages)
    public static final String REVIEW_RATING_D = "REVIEW_RATING_D";

    // Most recent review
    public static final String REVIEW_RATING_SUBMISSION_DATE_D = "REVIEW_RATING_SUBMISSION_DATE_D";


}