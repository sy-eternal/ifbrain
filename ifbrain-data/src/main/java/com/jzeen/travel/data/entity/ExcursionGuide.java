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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 短途导游
 */
@Entity
@Table(name = "t_excursion_guide")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ExcursionGuide
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "create_time", length = 100)
    private Date createTime;

    // 短途导游服务成本
    @Column(name = "excursion_guide_cost")
    private BigDecimal excursionGuideCost;

    // 短途导游服务销售价
    @Column(name = "excursion_guide_price")
    private BigDecimal excursionGuidePrice;

    // 导游用户
    @OneToOne
    @JoinColumn(name = "guide_pk")
    private Guide guide;

    /**
     * 是否有车。
     */
    @Column(name = "has_car")
    private Boolean hasCar;

    /**
     * 短途导游占用
     */
    @OneToMany(mappedBy = "excursionGuide", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ExcursionGuideOccupied> excursionGuideOccupieds;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public BigDecimal getExcursionGuideCost()
    {
        return excursionGuideCost;
    }

    public void setExcursionGuideCost(BigDecimal excursionGuideCost)
    {
        this.excursionGuideCost = excursionGuideCost;
    }

    public BigDecimal getExcursionGuidePrice()
    {
        return excursionGuidePrice;
    }

    public void setExcursionGuidePrice(BigDecimal excursionGuidePrice)
    {
        this.excursionGuidePrice = excursionGuidePrice;
    }


    public Guide getGuide()
    {
        return guide;
    }

    public void setGuide(Guide guide)
    {
        this.guide = guide;
    }

    public List<ExcursionGuideOccupied> getExcursionGuideOccupieds()
    {
        return excursionGuideOccupieds;
    }

    public void setExcursionGuideOccupieds(List<ExcursionGuideOccupied> excursionGuideOccupieds)
    {
        this.excursionGuideOccupieds = excursionGuideOccupieds;
    }

    public Boolean getHasCar()
    {
        return hasCar;
    }

    public void setHasCar(Boolean hasCar)
    {
        this.hasCar = hasCar;
    }
}
