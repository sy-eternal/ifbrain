package com.jzeen.travel.website.controller.rentalCar.pojo;
/**
 * Title: X2OUR_TRAVEL
 * Description: 网页授权Token信息，获取页面授权Access_token
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author sunyan.sunny@x2our.com
 */
public class RentalCarToken
{
    // 网页授权接口调用凭证
    private String accessToken;

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
    
}
