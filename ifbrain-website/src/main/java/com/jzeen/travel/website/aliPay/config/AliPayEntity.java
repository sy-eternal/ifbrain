package com.jzeen.travel.website.aliPay.config;

public class AliPayEntity
{

    // 订单号
    private String orderCode;

    // 订单总价
    private Double totalAmount;

    // 订单佣金比例
    private Double rate;

    // 课程名称
    private String courseName;

    // 课程号
    private String courseCode;

    // 课程描述
    private String couresDescription;

    // 课程单价
    private Double pricePerDay;

    // 购买天数
    private Integer boughtDays;
    
    //购买月份
    private Integer groupMoth;

    // 订单课程总价
    private Double courseAmount;

    // 支付宝账号
    private String zhifubaoAccout;
    
    //套餐编号
    private Integer serviceId;
    
    //公司编号
    private Integer companyId;
    
    //群名称
    private String groupName;
    
    //群介绍
    private String groupIntro;
    
    
    public String getGroupIntro()
    {
        return groupIntro;
    }

    public void setGroupIntro(String groupIntro)
    {
        this.groupIntro = groupIntro;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /**
     * 获取公司编号
     * @return
     */
    public Integer getCompanyId()
    {
        return companyId;
    }

    /**
     * 设置公司编号
     * @param companyId
     */
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }

    /**
     * 获取套餐编号
     * @return
     */
    public Integer getServiceId()
    {
        return serviceId;
    }

    /**
     * 设置套餐编号
     * @param serviceId
     */
    public void setServiceId(Integer serviceId)
    {
        this.serviceId = serviceId;
    }

    /**
     * 订单号
     * 
     * @return
     */
    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    /**
     * 订单总价
     * 
     * @return
     */
    public Double getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    /**
     * 订单佣金比例
     * 
     * @return
     */
    public Double getRate()
    {
        return rate;
    }

    public void setRate(Double rate)
    {
        this.rate = rate;
    }

    public Integer getGroupMoth()
    {
        return groupMoth;
    }

    public void setGroupMoth(Integer groupMoth)
    {
        this.groupMoth = groupMoth;
    }

    /**
     * 课程名称
     * 
     * @return
     */
    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    /**
     * 课程号
     * 
     * @return
     */
    public String getCourseCode()
    {
        return courseCode;
    }

    public void setCourseCode(String courseCode)
    {
        this.courseCode = courseCode;
    }

    /**
     * 课程单价
     * 
     * @return
     */
    public Double getPricePerDay()
    {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay)
    {
        this.pricePerDay = pricePerDay;
    }

    /**
     * 购买天数
     * 
     * @return
     */
    public Integer getBoughtDays()
    {
        return boughtDays;
    }

    public void setBoughtDays(Integer boughtDays)
    {
        this.boughtDays = boughtDays;
    }

    /**
     * 订单课程总价
     * 
     * @return
     */
    public Double getCourseAmount()
    {
        return courseAmount;
    }

    public void setCourseAmount(Double courseAmount)
    {
        this.courseAmount = courseAmount;
    }

    /**
     * 支付宝账号
     * 
     * @return
     */
    public String getZhifubaoAccout()
    {
        return zhifubaoAccout;
    }

    public void setZhifubaoAccout(String zhifubaoAccout)
    {
        this.zhifubaoAccout = zhifubaoAccout;
    }

    /**
     * 课程描述
     * 
     * @return
     */
    public String getCouresDescription()
    {
        return couresDescription;
    }

    public void setCouresDescription(String couresDescription)
    {
        this.couresDescription = couresDescription;
    }
}
