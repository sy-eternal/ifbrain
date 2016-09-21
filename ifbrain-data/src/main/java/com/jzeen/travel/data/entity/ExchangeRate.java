package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_exchange_rate")
public class ExchangeRate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 卖出货币类型
    @NotEmpty
    @Column(name = "selling_currency")
    private String sellingcurrency;

    // 买入货币类型
    @NotEmpty
    @Column(name = "buying_currency")
    private String buyingcurrency;

    // 汇率
    @NotNull
    @Column(name = "exchange_rate")
    private BigDecimal exchangerate;

    // 创建时间
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
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

    public String getSellingcurrency()
    {
        return sellingcurrency;
    }

    public void setSellingcurrency(String sellingcurrency)
    {
        this.sellingcurrency = sellingcurrency;
    }

    public String getBuyingcurrency()
    {
        return buyingcurrency;
    }

    public void setBuyingcurrency(String buyingcurrency)
    {
        this.buyingcurrency = buyingcurrency;
    }

    public BigDecimal getExchangerate()
    {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate)
    {
        this.exchangerate = exchangerate;
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
