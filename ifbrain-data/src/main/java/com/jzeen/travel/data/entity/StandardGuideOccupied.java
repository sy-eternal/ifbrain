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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 标准导游占用
 */
@Entity
@Table(name = "t_standard_guide_occupied")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StandardGuideOccupied
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 标准导游
     */
    @ManyToOne(targetEntity = StandardGuide.class)
    @JoinColumn(name = "standard_guide_id")
    @JsonManagedReference
    private StandardGuide standardGuide;

    /**
     * 标准导游规划
     */
    @ManyToOne(targetEntity = StandardGuidePlan.class)
    @JoinColumn(name = "standard_guide_plan_id")
    @JsonBackReference
    private StandardGuidePlan standardGuidePlan;

    /**
     * 占用日期
     */
    @Column(name = "occupied_date")
    private Date occupiedDate;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 加班时间
     */
    @Column(name="ot_time")
    private Integer otTime;
    /**
     * 加班状态
     * 
     */
    @Column(name="ot_status")
    private Boolean otStatus;
    
    /**
     * 占用类型
     */
    @Column(name="occupied_type")
    private Integer occupiedType;




    public Boolean getOtStatus()
    {
        return otStatus;
    }

    public void setOtStatus(Boolean otStatus)
    {
        this.otStatus = otStatus;
    }

    public Integer getOtTime()
    {
        return otTime;
    }

    public void setOtTime(Integer otTime)
    {
        this.otTime = otTime;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public StandardGuide getStandardGuide()
    {
        return standardGuide;
    }

    public void setStandardGuide(StandardGuide standardGuide)
    {
        this.standardGuide = standardGuide;
    }

    public StandardGuidePlan getStandardGuidePlan()
    {
        return standardGuidePlan;
    }

    public void setStandardGuidePlan(StandardGuidePlan standardGuidePlan)
    {
        this.standardGuidePlan = standardGuidePlan;
    }

    public Date getOccupiedDate()
    {
        return occupiedDate;
    }

    public void setOccupiedDate(Date occupiedDate)
    {
        this.occupiedDate = occupiedDate;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Integer getOccupiedType()
    {
        return occupiedType;
    }

    public void setOccupiedType(Integer occupiedType)
    {
        this.occupiedType = occupiedType;
    }
    
    
}
