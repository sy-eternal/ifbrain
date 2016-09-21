package com.jzeen.travel.website.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 * 获取项目路径
 * @author Administrator
 *
 */

@Component
@ConfigurationProperties(prefix = "web-url")
public class WebUrlSetting
{
    /**
     * 项目根路径
     */
    
    private String rootUrl;

    public String getRootUrl()
    {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl)
    {
        this.rootUrl = rootUrl;
    }
    
    

}
