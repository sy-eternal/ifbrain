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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 短途导游占用
 */
@Entity
@Table(name = "t_excursion_guide_occupied")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ExcursionGuideOccupied
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 短途导游
     */
    @ManyToOne(targetEntity = ExcursionGuide.class)
    @JoinColumn(name = "excursion_guide_id")
    @JsonManagedReference
    private ExcursionGuide excursionGuide;

    /**
     * 短途导游规划
     */
    @ManyToOne(targetEntity = ExcursionGuidePlan.class)
    @JoinColumn(name = "excursion_guide_plan_id")
    @JsonBackReference
    private ExcursionGuidePlan excursionGuidePlan;

    /**
     * 占用日期
     */
    @Column(name = "occupied_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date occupiedDate;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public ExcursionGuide getExcursionGuide()
    {
        return excursionGuide;
    }

    public void setExcursionGuide(ExcursionGuide excursionGuide)
    {
        this.excursionGuide = excursionGuide;
    }

    public ExcursionGuidePlan getExcursionGuidePlan()
    {
        return excursionGuidePlan;
    }

    public void setExcursionGuidePlan(ExcursionGuidePlan excursionGuide)
    {
        this.excursionGuidePlan = excursionGuide;
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
}
