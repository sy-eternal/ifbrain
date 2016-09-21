package com.jzeen.travel.service.viator;

import com.jzeen.travel.common.HttpRequestUtil;
import com.jzeen.travel.service.viator.bean.ViatorSearchProductData;
import com.jzeen.travel.service.viator.bean.ViatorSearchProductReturn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description: Viator接口服务
 * Date: 2015年 09月 15日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Service
public class ViatorSearchUtilService {

    private static Logger log = LoggerFactory.getLogger(ViatorSearchUtilService.class);

    /**
     * 产品查询
     *
     * @param startDate    开始时间
     * @param endDate      截止时间
     * @param topX         条目数，例如 6-10
     * @param destId
     * @param currencyCode 货币
     * @param catId
     * @param subCatId
     * @param dealsOnly
     * @param sortOrder
     * @return
     */
    public static ViatorSearchProductReturn searchProducts(String startDate, String endDate, String topX, int destId, String currencyCode, int catId, int subCatId, boolean dealsOnly, String sortOrder) {
        String requestUrl = ViatorCommon.SEARCH_PRODUCTS_API.replace("API_KEY", ViatorCommon.API_KEY);

        String jsonData = "{\"startDate\":\"%s\",\"endDate\":\"%s\",\"topX\":\"%s\",\"destId\":%d,\"currencyCode\":\"%s\",\"catId\":%d,\"subCatId\":%d,\"dealsOnly\":%b,\"sortOrder\":\"%s\"}";

        String postEntity = String.format(jsonData, startDate, endDate, topX, destId, currencyCode, catId, subCatId, dealsOnly, sortOrder);

        log.info("产品查询，提交的数据是:{}", postEntity.toString());

        ViatorSearchProductReturn productReturn = null;

        try {
            String returnStr = HttpRequestUtil.httpPost(requestUrl, null, postEntity);
            JSONObject jsonObject = JSONObject.fromString(returnStr);

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

                        productReturn = new ViatorSearchProductReturn(dateStamp, errorMessage, errorMessageText, errorName, errorReference, errorType, success, totalCount, vmid);
                        List<ViatorSearchProductData> productData = JSONArray.toList(jsonObject.getJSONArray("data"), ViatorSearchProductData.class);
                        productReturn.setData(productData);

                        log.info("查询产品信息成功  errorReference:{} dateStamp:{} errorType:{} errorMessage:{} errorName:{} success:{} totalCount:{} errorMessageText:{} vmid ", new Object[]{errorReference, dateStamp, errorType, errorMessage, errorName, success, totalCount, errorMessageText, vmid});
                    } else {
                        productReturn = new ViatorSearchProductReturn(errorMessage, errorMessageText, errorName, errorReference, errorType);

                        log.error("查询产品信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                    }


                } catch (Exception e) {
                    String errorType = jsonObject.getString("errorType");
                    String errorReference = jsonObject.getString("errorReference");
                    String errorMessageText = jsonObject.getString("errorMessageText");
                    log.error("查询产品信息失败， errorType:{} errorMessageText:{} ", errorType, errorMessageText);
                    productReturn = new ViatorSearchProductReturn(errorMessageText, errorReference, errorType);
                }
            }
        } catch (IOException e) {
            log.error("产品查询异常信息:{}", e.toString());
        }
        return productReturn;
    }


    public static void main(String args[]) {
        ViatorSearchProductReturn locationReturn = searchProducts("2015-10-25", "2015-10-27", "1-5", 684, "EUR", 0, 0, false, "PRICE_FROM_A");
        List<ViatorSearchProductData> dataList = locationReturn.getData();
    }

}