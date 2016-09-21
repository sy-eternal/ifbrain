package com.jzeen.travel.admin.setting;

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

    public String getRootPath()
    {
        return rootPath;
    }

    public void setRootPath(String rootPath)
    {
        this.rootPath = rootPath;
    }
    /**
     * 视频
     */
    private String videoPath;
    
    
    public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	/**
	 * 免费视频路径
	 */
	  
   private String freevideoPath;
   /**
    * 上传商品的图片路径
    * @return
    */
   private String basicCommodityPath;
   
   
   
   
	public String getBasicCommodityPath() {
	return basicCommodityPath;
}

public void setBasicCommodityPath(String basicCommodityPath) {
	this.basicCommodityPath = basicCommodityPath;
}

	public String getFreevideoPath() {
		return freevideoPath;
	}

	public void setFreevideoPath(String freevideoPath) {
		this.freevideoPath = freevideoPath;
	}
	//免费视频资料
	private String freevideomaterialPath;
	
	
	public String getFreevideomaterialPath() {
		return freevideomaterialPath;
	}

	public void setFreevideomaterialPath(String freevideomaterialPath) {
		this.freevideomaterialPath = freevideomaterialPath;
	}
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
	/**
     * 素材图片
     */
    private String materialPath;
   
	public String getMaterialPath() {
		return materialPath;
	}

	public void setMaterialPath(String materialPath) {
		this.materialPath = materialPath;
	}
	
	/**
     * 首页轮播图片
     */
    private String carouselPath;
   /**
    * 家长手册图片
    * @return
    */
	private String parentmanualPath;
	/**
	 * 导航栏下的模块的头像
	 * @return
	 */
	private String headportraitPath;
	
	private String questionoptionPath;
	
	public String getQuestionoptionPath() {
		return questionoptionPath;
	}

	public void setQuestionoptionPath(String questionoptionPath) {
		this.questionoptionPath = questionoptionPath;
	}

	public String getHeadportraitPath() {
		return headportraitPath;
	}

	public void setHeadportraitPath(String headportraitPath) {
		this.headportraitPath = headportraitPath;
	}

	public String getParentmanualPath() {
		return parentmanualPath;
	}

	public void setParentmanualPath(String parentmanualPath) {
		this.parentmanualPath = parentmanualPath;
	}

	public String getCarouselPath() {
		return carouselPath;
	}

	public void setCarouselPath(String carouselPath) {
		this.carouselPath = carouselPath;
	}
	/**
     * 签证路径
     * @return
     */
   private String visaPath;

    public String getVisaPath()
    {
        return visaPath;
    }

    public void setVisaPath(String visaPath)
    {
        this.visaPath = visaPath;
    }
    
    /**
     * 酒店路径
     */
    private String hotelPath;

    public String getHotelPath()
    {
        return hotelPath;
    }

    public void setHotelPath(String hotelPath)
    {
        this.hotelPath = hotelPath;
    }
    
   /**
    * 线路产品路径
    */
    
    private String routePath;

    public String getRoutePath()
    {
        return routePath;
    }

    public void setRoutePath(String routePath)
    {
        this.routePath = routePath;
    }
    
}
