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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 供应商价格规则
 */
@Entity
@Table(name = "t_supplier_price_rule")
public class SupplierPriceRule
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 供应商
     */
    @NotNull
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    /**
     * 供应商类型
     */
    @NotNull
    @Column(name = "supplier_type_code")
    private Integer supplierTypeCode;

    /**
     * 价格系数
     */
    @NotNull
    @Column(name = "price_coefficient")
    private BigDecimal priceCoefficient;

    /**
     * 创建时间
     */
    @NotNull
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

    public Supplier getSupplier()
    {
        return supplier;
    }

    public void setSupplier(Supplier supplier)
    {
        this.supplier = supplier;
    }

    public Integer getSupplierTypeCode()
    {
        return supplierTypeCode;
    }

    public void setSupplierTypeCode(Integer supplierTypeCode)
    {
        this.supplierTypeCode = supplierTypeCode;
    }

    public BigDecimal getPriceCoefficient()
    {
        return priceCoefficient;
    }

    public void setPriceCoefficient(BigDecimal priceCoefficient)
    {
        this.priceCoefficient = priceCoefficient;
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
