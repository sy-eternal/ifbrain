package com.jzeen.travel.website.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 上传文件配置信息。
 */
@Component
@ConfigurationProperties(prefix = "file-upload")
public class FileUploadSetting
{
    /**
     * 根路径。
     */
    private String rootPath;
    
    /**
     * 签证路径
     * @return
     */
   private String visaPath;
   /**
    * 锦囊封面图路径
    * @return
    */
   private String blogPath;
   /**
    * 上传孩子头像的路径
    * @return
    */
   
   private String childPath;
   
   /**
    * 上传商品的图片路径
    * @return
    */
   private String basicCommodityPath;
   /**
    * 用户上传头像的图片
    * @return
    */
   private String userPath;
   
   /**
    * 上传商品类别图片
    * @return
    */
   private String commodityTypePath;
   
   /**
	 * 财脑任务路径
	 */
	private String ifbraintaskPath;
	
	public String getIfbraintaskPath() {
		return ifbraintaskPath;
	}

	public void setIfbraintaskPath(String ifbraintaskPath) {
		this.ifbraintaskPath = ifbraintaskPath;
	}
	 private String freevideoPath;

		public String getFreevideoPath() {
			return freevideoPath;
		}

		public void setFreevideoPath(String freevideoPath) {
			this.freevideoPath = freevideoPath;
		}
	public String getCommodityTypePath() {
		return commodityTypePath;
	}

	public void setCommodityTypePath(String commodityTypePath) {
		this.commodityTypePath = commodityTypePath;
	}

	public String getUserPath() {
		return userPath;
	}

	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}

	public String getChildPath() {
		return childPath;
	}

	public void setChildPath(String childPath) {
		this.childPath = childPath;
	}

	public String getBasicCommodityPath() {
		return basicCommodityPath;
	}

	public void setBasicCommodityPath(String basicCommodityPath) {
		this.basicCommodityPath = basicCommodityPath;
	}

	public String getVisaPath()
    {
        return visaPath;
    }

    public void setVisaPath(String visaPath)
    {
        this.visaPath = visaPath;
    }

    public String getRootPath()
    {
        return rootPath;
    }

    public void setRootPath(String rootPath)
    {
        this.rootPath = rootPath;
    }

	public String getBlogPath() {
		return blogPath;
	}

	public void setBlogPath(String blogPath) {
		this.blogPath = blogPath;
	}
}
