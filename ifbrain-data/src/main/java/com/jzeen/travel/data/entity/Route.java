package com.jzeen.travel.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import java.math.BigDecimal;
/**
 * 线路规划
 */
@Entity
@Table(name = "t_route")
// 解决延迟加载所造成的代理对象无法正常序列化的问题
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
// 解决双向关联的对象生成JSON的无限循环问题
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Route
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //线路编号
	@Column(name ="route_num")
    private String routeNum;
  
	//线路名称
    @Column(name = "route_name")
    private String routeName;
    
  //线路封面照id
    @OneToOne(targetEntity = Image.class)
    @JoinColumn(name = "route_cover_pic_id")
    private Image image;

	//创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    
    //与标签一对多的关系
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteTag> routeTages;
    public List<RouteTag> getRouteTages()
    {
        return routeTages;
    }

    public void setRouteTages(List<RouteTag> routeTages)
    {
        this.routeTages = routeTages;
    }
    
    
    
    @OneToMany(mappedBy="route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteDays> routeDays;
    
    @OneToMany(mappedBy="route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteInsurancePlan> routeInsurancePlans;   

	@OneToMany(mappedBy="route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteMarginPlan> routeMarginPlans;	

    public List<RouteInsurancePlan> getRouteInsurancePlans() {
		return routeInsurancePlans;
	}

	public void setRouteInsurancePlans(List<RouteInsurancePlan> routeInsurancePlans) {
		this.routeInsurancePlans = routeInsurancePlans;
	}

	public List<RouteMarginPlan> getRouteMarginPlans() {
		return routeMarginPlans;
	}

	public void setRouteMarginPlans(List<RouteMarginPlan> routeMarginPlans) {
		this.routeMarginPlans = routeMarginPlans;
	}

   
    
    public List<RouteDays> getRouteDays() {
		return routeDays;
	}

	public void setRouteDays(List<RouteDays> routeDays) {
		this.routeDays = routeDays;
	}

	public Image getImage() {
  		return image;
  	}

  	public void setImage(Image image) {
  		this.image = image;
  	}

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRouteNum() {
		return routeNum;
	}

	public void setRouteNum(String routeNum) {
		this.routeNum = routeNum;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

  
}
