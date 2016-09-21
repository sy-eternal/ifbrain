package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_spot_ticket_type")
public class SpotTicketType
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // 景点主键
    @ManyToOne
    @JoinColumn(name = "spots_models_id", updatable = true)
    private Spot spots;

    // 门票类型
    @NotEmpty
    @Size(max = 255)
    @Column(name = "type")
    private String type;

    // 成本价
    @NotNull
    @Min(0)
    @NumberFormat
    @Column(name = "cost")
    private BigDecimal cost;

    // 销售价
    @NotNull
    @Min(0)
    @NumberFormat
    @Column(name = "price")
    private BigDecimal price;

    // 价格系数
    @Column(name = "price_rate")
    private BigDecimal pricerate;

    // 创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    
    //包含项目
    @Column(name="including_item")
    private String includingItem;

    public String getIncludingItem() {
		return includingItem;
	}

	public void setIncludingItem(String includingItem) {
		this.includingItem = includingItem;
	}

	public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Spot getSpots()
    {
        return spots;
    }

    public void setSpots(Spot spots)
    {
        this.spots = spots;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public BigDecimal getCost()
    {
        return cost;
    }

    public void setCost(BigDecimal cost)
    {
        this.cost = cost;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPricerate()
    {
        return pricerate;
    }

    public void setPricerate(BigDecimal pricerate)
    {
        this.pricerate = pricerate;
    }
}
