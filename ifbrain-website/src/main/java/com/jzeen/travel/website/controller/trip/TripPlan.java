package com.jzeen.travel.website.controller.trip;


public class TripPlan {

	private Integer id;
	private String tripPlan;//出行计划
	private String startDate;//出行日期
	private String NoDate;//不确定日期1
	private Integer playDay;//游玩天数1
	
	private String tripAim;//出行目的
	private String entourage;//随行人员
	private String tripObject;//旅行主题
	private String selectCountry;//目的地国家
	private Integer peopleNum;//出行人数
	private Integer plan;//规划方式
	private String address;//确定下来行程地点
	private String time;//确定下来时间
	private String isService;//是否需要导游服务
	private String beizhu;//备注
	
	private String startCity;//出发城市
	private String bCity;//必去城市 
	private Integer cityS;//城市选择 
	private String cityI;//感兴趣的地方
	private Integer tourS;//导游接送 0：部分行程 1：全部行程
	private Integer driverS;//自驾游览 0：部分行程 1：全部行程
	private Integer tourAndDriverS;//我和导游一起乘坐 0：部分行程 1：全部行程
	private String  vehicle;//城市间交通（计划安排交通工具）
	private String selfDriver;//自驾说明
	private Integer hourseS;//酒店标准
    private Integer flightS;//航班标准
    private Integer rentCar;//租车喜好
    private Integer hourseNum;//需要酒店房间数
    private String remark;//备注
    private String memberage;//年龄
    private String backCity;//返回城市
    
    
	public String getBackCity() {
		return backCity;
	}
	public void setBackCity(String backCity) {
		this.backCity = backCity;
	}
	public String getMemberage()
    {
        return memberage;
    }
    public void setMemberage(String memberage)
    {
        this.memberage = memberage;
    }
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTripPlan() {
		return tripPlan;
	}
	public void setTripPlan(String tripPlan) {
		this.tripPlan = tripPlan;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getNoDate() {
		return NoDate;
	}
	public void setNoDate(String noDate) {
		NoDate = noDate;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getPlayDay() {
		return playDay;
	}
	public void setPlayDay(Integer playDay) {
		this.playDay = playDay;
	}
	public String getTripAim() {
		return tripAim;
	}
	public void setTripAim(String tripAim) {
		this.tripAim = tripAim;
	}
	public String getEntourage() {
		return entourage;
	}
	public void setEntourage(String entourage) {
		this.entourage = entourage;
	}
	public String getTripObject() {
		return tripObject;
	}
	public void setTripObject(String tripObject) {
		this.tripObject = tripObject;
	}
	public String getSelectCountry() {
		return selectCountry;
	}
	public void setSelectCountry(String selectCountry) {
		this.selectCountry = selectCountry;
	}
	public String getSelfDriver() {
		return selfDriver;
	}
	public void setSelfDriver(String selfDriver) {
		this.selfDriver = selfDriver;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public Integer getPlan() {
		return plan;
	}
	public void setPlan(Integer plan) {
		this.plan = plan;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}
	public String getIsService() {
		return isService;
	}
	public void setIsService(String isService) {
		this.isService = isService;
	}
	public String getBeizhu()
    {
        return beizhu;
    }
    public void setBeizhu(String beizhu)
    {
        this.beizhu = beizhu;
    }
    
    public String getbCity() {
		return bCity;
	}
	public void setbCity(String bCity) {
		this.bCity = bCity;
	}

	public Integer getCityS() {
		return cityS;
	}
	public void setCityS(Integer cityS) {
		this.cityS = cityS;
	}
	public Integer getTourS() {
		return tourS;
	}
	public void setTourS(Integer tourS) {
		this.tourS = tourS;
	}
	public Integer getDriverS() {
		return driverS;
	}
	public void setDriverS(Integer driverS) {
		this.driverS = driverS;
	}
	public Integer getTourAndDriverS() {
		return tourAndDriverS;
	}
	public void setTourAndDriverS(Integer tourAndDriverS) {
		this.tourAndDriverS = tourAndDriverS;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	
	
	public Integer getHourseS() {
		return hourseS;
	}
	public void setHourseS(Integer hourseS) {
		this.hourseS = hourseS;
	}
	public Integer getFlightS() {
		return flightS;
	}
	public void setFlightS(Integer flightS) {
		this.flightS = flightS;
	}
	public Integer getRentCar() {
		return rentCar;
	}
	public void setRentCar(Integer rentCar) {
		this.rentCar = rentCar;
	}
	public Integer getHourseNum() {
		return hourseNum;
	}
	public void setHourseNum(Integer hourseNum) {
		this.hourseNum = hourseNum;
	}
	public String getCityI() {
		return cityI;
	}
	public void setCityI(String cityI) {
		this.cityI = cityI;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
