package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;


/**
 * 签证订单申请人
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_application_info")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicationInfo {


    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 签证订单
     */
    @ManyToOne(targetEntity = VisaOrder.class)
    @JoinColumn(name = "order_pk", updatable = true)
    private VisaOrder visaOrder;

  

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 申请人材料
     */
    @ManyToOne(targetEntity = Document.class)
    @JoinColumn(name = "file_info")
    private Document document;


    /**
     * 创建时间
     */

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Document getDocument()
    {
        return document;
    }

    public void setDocument(Document document)
    {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public VisaOrder getVisaOrder() {
        return visaOrder;
    }

    public void setVisaOrder(VisaOrder visaOrder) {
        this.visaOrder = visaOrder;
    }
}
