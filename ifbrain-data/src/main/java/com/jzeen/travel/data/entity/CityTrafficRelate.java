package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_city_traffic_relate")
public class CityTrafficRelate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="city_traffic_pk")
	private Integer CityTrafficPk;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="order_pk")
	private Integer orderPk;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCityTrafficPk() {
		return CityTrafficPk;
	}

	public void setCityTrafficPk(Integer cityTrafficPk) {
		CityTrafficPk = cityTrafficPk;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderPk() {
		return orderPk;
	}

	public void setOrderPk(Integer orderPk) {
		this.orderPk = orderPk;
	}
	
}
