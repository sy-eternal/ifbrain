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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 供应商价格规则
 */
@Entity
@Table(name = "t_supplier_price_rule")
public class SupplierPriceRuleActive
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 供应商
     */
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    /**
     * 供应商类型
     */
    @ManyToOne(targetEntity = Code.class)
    @JoinColumn(name = "supplier_type_code")
    private Code code;
    

    /**
     * 价格系数
     */
    @Column(name = "price_coefficient")
    private String priceCoefficient;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime   ;
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


    public Code getCode()
    {
        return code;
    }

    public void setCode(Code code)
    {
        this.code = code;
    }


    public String getPriceCoefficient()
    {
        return priceCoefficient;
    }

    public void setPriceCoefficient(String priceCoefficient)
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
