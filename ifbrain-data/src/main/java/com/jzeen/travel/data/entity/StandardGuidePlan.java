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
 * 标准导游规划
 */
@Entity
@Table(name = "t_standard_guide_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class StandardGuidePlan {
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
     * 主城市
     */
    @Column(name="host_city")
    private String hostCity;
    
    /**
     * 导游姓名
     */
    @Column(name="guide_name")
    private String guideName;

//    /**
//     * 出发或到达
//     */
//    @Column(name = "start_or_end")
//    private Boolean startOrEnd;

//    /**
//     * 导游数量
//     */
//    @Column(name = "guide_count")
//    private Integer guideCount;

    /**
     * 导游价格
     */
    @Column(name = "guide_price")
    private BigDecimal guidePrice;

//    /**
//     * 导游专车价格
//     */
//    @Column(name = "guide_car_price")
//    private BigDecimal guideCarPrice;

    /**
     * 导游人头费价格
     */
    @Column(name = "commision_percentage")
    private BigDecimal commisionPercentage;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 标准导游占用
     */
    @OneToMany(mappedBy = "standardGuidePlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<StandardGuideOccupied> standardGuideOccupieds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DatePlan getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(DatePlan datePlan) {
        this.datePlan = datePlan;
    }

    public BigDecimal getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(BigDecimal guidePrice) {
        this.guidePrice = guidePrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getCommisionPercentage() {
        return commisionPercentage;
    }

    public void setCommisionPercentage(BigDecimal commisionPercentage) {
        this.commisionPercentage = commisionPercentage;
    }

    public List<StandardGuideOccupied> getStandardGuideOccupieds() {
        if (standardGuideOccupieds == null) {
            standardGuideOccupieds = new ArrayList<StandardGuideOccupied>();
        }
        return standardGuideOccupieds;
    }

    public void setStandardGuideOccupieds(List<StandardGuideOccupied> standardGuideOccupieds) {
        this.standardGuideOccupieds = standardGuideOccupieds;
    }

    public String getHostCity()
    {
        return hostCity;
    }

    public void setHostCity(String hostCity)
    {
        this.hostCity = hostCity;
    }

    public String getGuideName()
    {
        return guideName;
    }

    public void setGuideName(String guideName)
    {
        this.guideName = guideName;
    }
    
    
}
