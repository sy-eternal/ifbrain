package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_ifbrain_number")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class IfbrainNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	//财脑编号
	@Column(name="brainnumber")
	private String brainnumber;
	// 创建时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
	
	//孩子id
	
	/*@Column(name="childid")
	private Integer childid;*/

	 @OneToOne(targetEntity=Child.class)
	@JoinColumn(name ="child_id")
	private Child child;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrainnumber() {
		return brainnumber;
	}

	public void setBrainnumber(String brainnumber) {
		this.brainnumber = brainnumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}
}
