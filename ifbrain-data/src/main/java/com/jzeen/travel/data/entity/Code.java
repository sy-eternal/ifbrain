package com.jzeen.travel.data.entity;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "t_code")
public class Code
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "class")
    private String classs;

    @Column(name = "value")
    private Integer value;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    
    
    
    @JsonIgnore
    @ManyToMany(mappedBy = "code", cascade = CascadeType.ALL)
    private List<Supplier> supplier;
    public List<Supplier> getSupplier()
    {
        return supplier;
    }

    public void setSupplier(List<Supplier> supplier)
    {
        this.supplier = supplier;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getClasss()
    {
        return classs;
    }

    public void setClasss(String classs)
    {
        this.classs = classs;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
//  @ManyToOne(targetEntity = Supplier.class)
//  @JoinColumn(name = "supplier_type_code", updatable = false)
//  private Supplier supplier;
//  public Supplier getSupplier()
//  {
//      return supplier;
//  }
//
//  public void setSupplier(Supplier supplier)
//  {
//      this.supplier = supplier;
//  }

}
