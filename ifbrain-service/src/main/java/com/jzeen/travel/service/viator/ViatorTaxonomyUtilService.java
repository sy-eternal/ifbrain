package com.jzeen.travel.service.viator;

import com.jzeen.travel.common.HttpRequestUtil;
import com.jzeen.travel.service.viator.bean.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description: Viator接口服务
 * Date: 2015年 09月 11日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Service
public class ViatorTaxonomyUtilService {

    private static Logger log = LoggerFactory.getLogger(ViatorTaxonomyUtilService.class);

    /**
     * 获取地址分类
     *
     * @param defaultCurrencyCode 缺省当前货币
     * @return void
     */
    public static ViatorLocationReturn getTaxonomyLocation(String defaultCurrencyCode) {
        // 拼接请求地址
        String requestUrl = ViatorCommon.TAXONOMY_LOCATIONS_API;
        requestUrl = requestUrl.replace("API_KEY", ViatorCommon.API_KEY);

        String jsonString = HttpRequestUtil.httpRequest(requestUrl);

        JSONObject jsonObject = JSONObject.fromString(jsonString);

        ViatorLocationReturn locationReturn = null;

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

                    locationReturn = new ViatorLocationReturn(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);
                    List<ViatorLocationData> locationList = JSONArray.toList(jsonObject.getJSONArray("data"), ViatorLocationData.class);
                    locationReturn.setDataList(locationList);

                    log.info("获取地区信息成功  errorReference:{} dateStamp:{} errorType:{} errorMessage:{} errorName:{} success:{} totalCount:{} errorMessageText:{} vmid ", new Object[]{errorReference, dateStamp, errorType, errorMessage, errorName, success, totalCount, errorMessageText, vmid});
                } else {
                    locationReturn = new ViatorLocationReturn(errorMessage, errorMessageText, errorName, errorReference, errorType);

                    log.error("获取地区信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                }


            } catch (Exception e) {
                String errorType = jsonObject.getString("errorType");
                String errorReference = jsonObject.getString("errorReference");
                String errorMessageText = jsonObject.getString("errorMessageText");
                log.error("获取地区信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                locationReturn = new ViatorLocationReturn(errorMessageText, errorReference, errorType);
            }
        }

        return locationReturn;
    }


    public static ViatorCategoryReturn getTaxonomyCategories() {

        return getTaxonomyCategories(-1);
    }

    /**
     * 获取分类
     *
     * @param destId
     * @return void
     */
    public static ViatorCategoryReturn getTaxonomyCategories(int destId) {
        // 拼接请求地址
        String requestUrl = ViatorCommon.TAXONOMY_CATEGORIES_API;
        if (destId != -1) {
            requestUrl += "&destId=" + destId;
        }

        requestUrl = requestUrl.replace("API_KEY", ViatorCommon.API_KEY);

        String jsonString = HttpRequestUtil.httpRequest(requestUrl);

        JSONObject jsonObject = JSONObject.fromString(jsonString);

        ViatorCategoryReturn categoryReturn = null;

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

                    categoryReturn = new ViatorCategoryReturn(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);


                    List<ViatorCategoryData> categoryList = new ArrayList<ViatorCategoryData>();
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            int sortOrder = jsonArray.getJSONObject(i).getInt("sortOrder");
                            String thumbnailURL = jsonArray.getJSONObject(i).getString("thumbnailURL");
                            String productCount = jsonArray.getJSONObject(i).getString("productCount");
                            String groupName = jsonArray.getJSONObject(i).getString("groupName");
                            int id = jsonArray.getJSONObject(i).getInt("id");

                            List<ViatorSubCategoryData> subDataList = new ArrayList<ViatorSubCategoryData>();
                            JSONArray subJsonArray = jsonArray.getJSONObject(i).getJSONArray("subcategories");
                            if (subJsonArray != null) {
                                for (int j = 0; j < subJsonArray.length(); j++) {
                                    int subSortOrder = subJsonArray.getJSONObject(j).getInt("sortOrder");
                                    String subcategoryName = subJsonArray.getJSONObject(j).getString("subcategoryName");
                                    int categoryId = subJsonArray.getJSONObject(j).getInt("categoryId");
                                    int subcategoryId = subJsonArray.getJSONObject(j).getInt("subcategoryId");

                                    ViatorSubCategoryData subCategoryData = new ViatorSubCategoryData(categoryId, subSortOrder, subcategoryId, subcategoryName);
                                    subDataList.add(subCategoryData);
                                }
                            }

                            ViatorCategoryData categoryData = new ViatorCategoryData(subDataList, groupName, id, productCount, sortOrder, thumbnailURL);
                            categoryList.add(categoryData);
                        }
                    }

                     categoryReturn.setDataList(categoryList);

                    log.info("获取目录信息成功  errorReference:{} dateStamp:{} errorType:{} errorMessage:{} errorName:{} success:{} totalCount:{} errorMessageText:{} vmid ", new Object[]{errorReference, dateStamp, errorType, errorMessage, errorName, success, totalCount, errorMessageText, vmid});
                } else {
                    categoryReturn = new ViatorCategoryReturn(errorMessage, errorMessageText, errorName, errorReference, errorType);

                    log.error("获取目录信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                }


            } catch (Exception e) {
                String errorType = jsonObject.getString("errorType");
                String errorReference = jsonObject.getString("errorReference");
                String errorMessageText = jsonObject.getString("errorMessageText");
                categoryReturn = new ViatorCategoryReturn(errorMessageText, errorReference, errorType);

                log.error("获取目录信息失败， errorType:{} errorMessageText:{} 错误信息:{}", new Object[]{errorType, errorMessageText, e.toString()});
            }
        }

        return categoryReturn;
    }

    public static void main(String args[]) {
        ViatorCategoryReturn locationReturn = getTaxonomyCategories();
        List<ViatorCategoryData> dataList = locationReturn.getDataList();
        if (dataList != null) {
            for (ViatorCategoryData dataItem : dataList) {
                System.out.println(dataItem.getGroupName());
                List<ViatorSubCategoryData> categoryDataList =  dataItem.getSubcategories();
                if(categoryDataList != null) {
                    for(ViatorSubCategoryData categoryData: categoryDataList) {
                        System.out.println("   + " + categoryData.getCategoryId() + "  " + categoryData.getSubcategoryName());
                    }
                }
            }
        }

    }

}