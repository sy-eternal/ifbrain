package com.jzeen.travel.website.controller.rentalCar.conf;


/**
 * 访问sabre的配置项
 *  * Date:
 * CopyRight (c) 2015 X2OUR
 * @author sunyan.sunny@x2our.com
 *
 */
public class RentalCarConfigure
{
    
     /**
      * 供应商API调用地址
      */
    private static final  String url="https://api.test.sabre.com/v1/auth/token";
      
    /**
     * 供应商API授权信息
     */
    private static final  String authorization="Basic VmpFNmRXOTRZalJ1Ym1kbWJIVnJNbXg2WWpwRVJWWkRSVTVVUlZJNlJWaFU6VkZZeU9GUnNkR1k9";
    
    /**
     * 授权
     * @return
     */
    
    private static final String ContentType="application/x-www-form-urlencoded";
    
    /**
     * 数据
     * @return
     */
    private static final String data="grant_type=client_credentials";

    
    
    public static String getData()
    {
        return data;
    }

    public static String getContenttype()
    {
        return ContentType;
    }

    public static String getUrl()
    {
        return url;
    }

    public static String getAuthorization()
    {
        return authorization;
    }


    
  
    
    
    
}
