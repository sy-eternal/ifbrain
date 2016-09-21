package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//专车规划表
@Entity
@Table(name = "t_route_rental_car_plan")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RouteRentalPlan {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
	//线路规划主键
		@ManyToOne(targetEntity = RouteDays.class)
		@JoinColumn(name="route_day_id")
		private RouteDays routeDays;  

		public RouteDays getRouteDays() {
			return routeDays;
		}

		public void setRouteDays(RouteDays routeDays) {
			this.routeDays = routeDays;
		}
    
//专车活动id
    
    @ManyToOne(targetEntity = RentalCar.class)
    @JoinColumn(name = "special_car_id")
    private RentalCar specialcar; 
    
//专车类型
    @Column(name = "car_type", length = 10)
    private String cartype;
    
//数量
    @Column(name = "count")
    private Integer count;
    
//专车销售价
    @Column(name = "car_price")
    private BigDecimal carprice;
    
//小计
    @Column(name = "sub_total_amount", updatable = true)
    private BigDecimal subtotalamount;
    
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
   
    /*
     * 供应商订单号
     */
     @Column(name="supplier_num")
     private String supplierNum;
     

     public Integer getId() {
 		return id;
 	}

 	public void setId(Integer id) {
 		this.id = id;
 	}

 	public RentalCar getSpecialcar() {
 		return specialcar;
 	}

 	public void setSpecialcar(RentalCar specialcar) {
 		this.specialcar = specialcar;
 	}

 	public String getCartype() {
 		return cartype;
 	}

 	public void setCartype(String cartype) {
 		this.cartype = cartype;
 	}

 	public Integer getCount() {
 		return count;
 	}

 	public void setCount(Integer count) {
 		this.count = count;
 	}

 	public BigDecimal getCarprice() {
 		return carprice;
 	}

 	public void setCarprice(BigDecimal carprice) {
 		this.carprice = carprice;
 	}

 	public BigDecimal getSubtotalamount() {
 		return subtotalamount;
 	}

 	public void setSubtotalamount(BigDecimal subtotalamount) {
 		this.subtotalamount = subtotalamount;
 	}

 	public Date getCreateTime() {
 		return createTime;
 	}

 	public void setCreateTime(Date createTime) {
 		this.createTime = createTime;
 	}

 	public String getSupplierNum() {
 		return supplierNum;
 	}

 	public void setSupplierNum(String supplierNum) {
 		this.supplierNum = supplierNum;
 	}
   
}
