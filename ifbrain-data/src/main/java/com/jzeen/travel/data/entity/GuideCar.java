package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

//导游专车
@Entity
@Table(name = "t_guide_car")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideCar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// 导游主键
	@OneToOne
	@JoinColumn(name = "guide_pk", updatable = true)
	private Guide user;

	// 车型
	@Column(name = "car_type")
	private String carType;

	// 生产年份
	@Column(name = "production_date")
	private String productionDate;

	// 审核状态
	@Column(name = "approval_status")
	private Integer approvalStatus;

	// 专车级别
	@Column(name = "class")
	private String classes;

	// 创建时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	 @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;

	// 导游专车管理主键
	@ManyToOne(targetEntity = GuideCarManage.class, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "guider_car_management_pk", updatable = true)
	private GuideCarManage guideCarManage;
	
    // 是否愿意短途接送
    @Column(name = "excursion_status")
    private Integer excursionStatus;
    
    public Integer getExcursionStatus()
    {
        return excursionStatus;
    }

    public void setExcursionStatus(Integer excursionStatus)
    {
        this.excursionStatus = excursionStatus;
    }
	// 注册卡（扫描件）id
	@OneToOne
	@JoinColumn(name = "plate_picture_id")
	@JsonIgnore
	private Image image;
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}



	public GuideCarManage getGuideCarManage() {
		return guideCarManage;
	}

	public void setGuideCarManage(GuideCarManage guideCarManage) {
		this.guideCarManage = guideCarManage;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Guide getUser()
    {
        return user;
    }

    public void setUser(Guide user)
    {
        this.user = user;
    }

    public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
