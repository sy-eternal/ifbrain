package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 短途导游规划
 */
@Entity
@Table(name = "t_excursion_guide_plan")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ExcursionGuidePlan
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 日期规划
     */
    @ManyToOne(targetEntity = DatePlan.class)
    @JoinColumn(name = "date_plan_id")
    private DatePlan datePlan;

    /**
     * 出发或到达
     */
    @Column(name = "start_or_end")
    private Boolean startOrEnd;

    /**
     * 导游数量
     */
    @Column(name = "guide_count")
    private Integer guideCount;

    /**
     * 导游价格
     */
    @Column(name = "guide_price")
    private BigDecimal guidePrice;

    /**
     * 导游专车价格
     */
    @Column(name = "guide_car_price")
    private BigDecimal guideCarPrice;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 短途导游占用
     */
    @OneToMany(mappedBy = "excursionGuidePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ExcursionGuideOccupied> excursionGuideOccupieds;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public DatePlan getDatePlan()
    {
        return datePlan;
    }

    public void setDatePlan(DatePlan datePlan)
    {
        this.datePlan = datePlan;
    }

    public Boolean getStartOrEnd()
    {
        return startOrEnd;
    }

    public void setStartOrEnd(Boolean startOrEnd)
    {
        this.startOrEnd = startOrEnd;
    }

    public Integer getGuideCount()
    {
        return guideCount;
    }

    public void setGuideCount(Integer guideCount)
    {
        this.guideCount = guideCount;
    }

    public BigDecimal getGuidePrice()
    {
        return guidePrice;
    }

    public void setGuidePrice(BigDecimal guidePrice)
    {
        this.guidePrice = guidePrice;
    }

    public BigDecimal getGuideCarPrice()
    {
        return guideCarPrice;
    }

    public void setGuideCarPrice(BigDecimal guideCarPrice)
    {
        this.guideCarPrice = guideCarPrice;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public List<ExcursionGuideOccupied> getExcursionGuideOccupieds()
    {
        if (excursionGuideOccupieds == null)
        {
            excursionGuideOccupieds = new ArrayList<ExcursionGuideOccupied>();
        }
        return excursionGuideOccupieds;
    }

    public void setExcursionGuideOccupieds(List<ExcursionGuideOccupied> excursionGuideOccupieds)
    {
        this.excursionGuideOccupieds = excursionGuideOccupieds;
    }
}
