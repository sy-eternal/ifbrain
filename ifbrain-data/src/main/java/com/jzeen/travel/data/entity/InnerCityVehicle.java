package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_inner_city_vehicle")
public class InnerCityVehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="inner_city_traffic")
	private Integer innerCityTraffic;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="complete_itenary")
	private  Integer completeItenary;
	
	@Column(name="remark")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInnerCityTraffic() {
		return innerCityTraffic;
	}

	public void setInnerCityTraffic(Integer innerCityTraffic) {
		this.innerCityTraffic = innerCityTraffic;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCompleteItenary() {
		return completeItenary;
	}

	public void setCompleteItenary(Integer completeItenary) {
		this.completeItenary = completeItenary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
