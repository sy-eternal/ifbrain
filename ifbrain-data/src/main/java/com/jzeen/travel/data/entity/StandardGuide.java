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
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 标准导游
 */
@Entity
@Table(name = "t_standard_guide")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class StandardGuide
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    // 加班时间
    @Column(name = "ot_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date otTime;

    // 加班时间确认
    @Column(name = "ot_status")
    private Integer otStatus;

    //标准导游服务成本(美元/天)
    @Column(name = "guide_cost")
    @Min(0)
    private BigDecimal guideCost;

    //标准导游服务销售价(美元/天)
    @Column(name = "guide_price")
    @Min(0)
    private BigDecimal guidePrice;

    // 1+统一利润率
    @Column(name = "rate")
    @Min(0)
    private BigDecimal rate;

    // 导游主键
    @OneToOne
    @JoinColumn(name = "guide_pk")
    private Guide user;

    // 加班销售价(美元/小时)
    @Column(name = "ot_cost")
    @Min(0)
    private BigDecimal otCost;

    //加班成本(美元/小时)
    @Column(name = "ot_price")
    @Min(0)
    private BigDecimal otPrice;

    // 人头销售价
    @Column(name = "commision_percentage")
    @Min(0)
    private BigDecimal commisionPercentage;
    
     // 人头费成本
    @Column(name = "commision_cost")
    @Min(0)
    private BigDecimal  commisionCost ;
    /**
     * 是否有车。
     */
    @Column(name = "has_car")
    private Boolean hasCar;
    /**
     * 银行名称
     */
    @Column(name = "bank_name")
    private String  bankName;
    
    
    /**
     * 银行地址
     */
    @Column(name = "bank_address")
    private String  bankAddress;
    
    
    /**
     * 银行电话
     */
    @Column(name = "bank_tel")
    private String  bankTel;
    
    
    /**
     * 国内电汇代码
     */
    @Column(name = "domestic_routing_num")
    private String  domesticRoutingNum;
    
    
    
    
    /**
     * 国外电汇代码
     */
    
    @Column(name = "international_swift_num")
    private String  internationalSwiftNum;
    /**
     *账户名称
     */
    @Column(name = "account_name")
    private String  accountName;
    /**
     *账户号码
     */
    @Column(name = "account_num")
    private String  accountNum;
    
    /**
     * 临时导游成本
     */
    @Column(name="excursion_guide_cost")
    private BigDecimal excursionGuideCost;
    
    /**
     * 临时导游销售价
     */
    @Column(name="excursion_guide_price")
    private BigDecimal excursionGuidePrice;

    // 标准导游占用
    @OneToMany(mappedBy = "standardGuide", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<StandardGuideOccupied> standardGuideOccupied;

    public List<StandardGuideOccupied> getStandardGuideOccupied()
    {
        return standardGuideOccupied;
    }

    public void setStandardGuideOccupied(List<StandardGuideOccupied> standardGuideOccupied)
    {
        this.standardGuideOccupied = standardGuideOccupied;
    }

    
    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBankAddress()
    {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress)
    {
        this.bankAddress = bankAddress;
    }

    public String getBankTel()
    {
        return bankTel;
    }

    public void setBankTel(String bankTel)
    {
        this.bankTel = bankTel;
    }

    public String getDomesticRoutingNum()
    {
        return domesticRoutingNum;
    }

    public void setDomesticRoutingNum(String domesticRoutingNum)
    {
        this.domesticRoutingNum = domesticRoutingNum;
    }

    public String getInternationalSwiftNum()
    {
        return internationalSwiftNum;
    }

    public void setInternationalSwiftNum(String internationalSwiftNum)
    {
        this.internationalSwiftNum = internationalSwiftNum;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountNum()
    {
        return accountNum;
    }

    public void setAccountNum(String accountNum)
    {
        this.accountNum = accountNum;
    }


    public Guide getUser()
    {
        return user;
    }

    public void setUser(Guide user)
    {
        this.user = user;
    }

    public BigDecimal getCommisionCost()
    {
        return commisionCost;
    }

    public void setCommisionCost(BigDecimal commisionCost)
    {
        this.commisionCost = commisionCost;
    }

    public BigDecimal getOtCost()
    {
        return otCost;
    }

    public void setOtCost(BigDecimal otCost)
    {
        this.otCost = otCost;
    }

    public BigDecimal getCommisionPercentage()
    {
        return commisionPercentage;
    }

    public void setCommisionPercentage(BigDecimal commisionPercentage)
    {
        this.commisionPercentage = commisionPercentage;
    }

    public BigDecimal getOtPrice()
    {
        return otPrice;
    }

    public void setOtPrice(BigDecimal otPrice)
    {
        this.otPrice = otPrice;
    }

    public BigDecimal getRate()
    {
        return rate;
    }

    public void setRate(BigDecimal rate)
    {
        this.rate = rate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public Date getOtTime()
    {
        return otTime;
    }

    public void setOtTime(Date otTime)
    {
        this.otTime = otTime;
    }

    public Integer getOtStatus()
    {
        return otStatus;
    }

    public void setOtStatus(Integer otStatus)
    {
        this.otStatus = otStatus;
    }

    public BigDecimal getGuideCost()
    {
        return guideCost;
    }

    public void setGuideCost(BigDecimal guideCost)
    {
        this.guideCost = guideCost;
    }

    public BigDecimal getGuidePrice()
    {
        return guidePrice;
    }

    public void setGuidePrice(BigDecimal guidePrice)
    {
        this.guidePrice = guidePrice;
    }

    public Boolean getHasCar()
    {
        return hasCar;
    }

    public void setHasCar(Boolean hasCar)
    {
        this.hasCar = hasCar;
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
    
    
}
