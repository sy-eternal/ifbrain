package com.jzeen.travel.data.entity;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 导游类型规划关联表
 */
@Entity
@Table(name ="t_guide_guidetype_plan_relate")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideGuidetypePlanRelate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * 导游类型规划id
	 */
	@ManyToOne (targetEntity=GuideTypePlan.class)
	@JoinColumn(name="guide_type_plan_id", updatable =true)
	private GuideTypePlan guideTypePlan;
	/**
	 * 
	 */
	/**
	 * 导游主键
	 */
/*	@ManyToOne (targetEntity=Guide.class)
	@JoinColumn(name="guide_id", updatable =true)
	private Guide guide;*/
	@OneToOne(targetEntity=Guide.class)
    @JoinColumn(name="guide_id")
	private Guide guide;

	/**
	 * 创建时间
	 */
	@Column(name="createtime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public GuideTypePlan getGuideTypePlan() {
		return guideTypePlan;
	}

	public void setGuideTypePlan(GuideTypePlan guideTypePlan) {
		this.guideTypePlan = guideTypePlan;
	}

	
}
