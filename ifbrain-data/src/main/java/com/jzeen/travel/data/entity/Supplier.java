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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_supplier")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Supplier
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 中文名称
     */
    @NotEmpty
    @Size(max = 100)
    @Column(name = "cn_name", length = 100)
    private String cnName;

    /**
     * 英文名称
     */
    @Size(max = 100) 
    @Column(name = "en_name", length = 100)
    private String enName;

    /**
     * 预订电话
     */
    @NotEmpty
    @Size(max = 40)
    @Column(name = "res_phone", length = 40)
    private String resPhone;

    /**
     * 预订邮件
     */
   @NotEmpty
    @Size(max = 40)
    @Column(name = "res_email", length = 40)
    private String resEmail;

    /**
     * 联系人
     */
    @NotEmpty
    @Size(max = 100)
    @Column(name = "contact", length = 100)
    private String contact;

    /**
     * 网站
     */
    @NotEmpty
    @Size(max = 200)
    @Column(name = "website", length = 200)
    private String website;

    /**
     * 特殊提醒
     */
    @NotEmpty
    @Size(max = 200)
    @Column(name = "contact_notes", length = 200)
    private String contactNotes;

    /**
     * API状态 1:有API
     * 2:无API
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "supplier_status")
    private Integer supplierStatus;
    /**
     * 供应商状态 1：激活
     * 2:冻结
     */

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    
    
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SupplierPriceRuleActive> supplierpriceruleactive;

    public List<SupplierPriceRuleActive> getSupplierpriceruleactive()
    {
        return supplierpriceruleactive;
    }

    public void setSupplierpriceruleactive(List<SupplierPriceRuleActive> supplierpriceruleactive)
    {
        this.supplierpriceruleactive = supplierpriceruleactive;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "t_supplier_price_rule", joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_type_code"))
    private List<Code> code;

    public List<Code> getCode()
    {
        return code;
    }

    public void setCode(List<Code> code)
    {
        this.code = code;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public String getCnName()
    {
        return cnName;
    }

    public void setCnName(String cnName)
    {
        this.cnName = cnName;
    }

    public String getEnName()
    {
        return enName;
    }

    public void setEnName(String enName)
    {
        this.enName = enName;
    }

    public String getResPhone()
    {
        return resPhone;
    }

    public void setResPhone(String resPhone)
    {
        this.resPhone = resPhone;
    }

    public String getResEmail()
    {
        return resEmail;
    }

    public void setResEmail(String resEmail)
    {
        this.resEmail = resEmail;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getContactNotes()
    {
        return contactNotes;
    }

    public void setContactNotes(String contactNotes)
    {
        this.contactNotes = contactNotes;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getSupplierStatus()
    {
        return supplierStatus;
    }

    public void setSupplierStatus(Integer supplierStatus)
    {
        this.supplierStatus = supplierStatus;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    // @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    // private List<Code> code;
    //
    // public List<Code> getCode()
    // {
    // return code;
    // }
    // public void setCode(List<Code> code)
    // {
    // this.code = code;
    // }
}
