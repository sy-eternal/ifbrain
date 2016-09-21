package com.jzeen.travel.data.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_guide_activity")
@JsonIgnoreProperties ({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideActivity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * 供应商领域类
	 */
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name ="supplier_id", updatable =true)
	private Supplier supplier;
	
	/**
	 * 导游活动类型
	 */
	@Column(name= "guide_activity_type")
	private String guideActivityType;
	
	/**
	 * 主城市id
	 */
	@ManyToOne(targetEntity =City.class)
	@JoinColumn(name="host_city_id", updatable=true)
	private City city;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time")
	private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getGuideActivityType() {
		return guideActivityType;
	}

	public void setGuideActivityType(String guideActivityType) {
		this.guideActivityType = guideActivityType;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}
