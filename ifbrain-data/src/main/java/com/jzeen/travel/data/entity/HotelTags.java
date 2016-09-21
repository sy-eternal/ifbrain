package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@Entity
@Table(name = "t_hotel_tags")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class HotelTags
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    /**
     * 酒店活动
     */
    @ManyToOne(targetEntity = HotelActivity.class)
    @JoinColumn(name = "hotel_id", updatable = true)
    private HotelActivity hotelActivity;
    
    /**
     * 标签
     */
    @Column(name="tag")
    private String tag;
    
    /**
     * 创建时间
     */
    @Column(name="createtime")
    private Date createtime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public HotelActivity getHotelActivity()
    {
        return hotelActivity;
    }

    public void setHotelActivity(HotelActivity hotelActivity)
    {
        this.hotelActivity = hotelActivity;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }

  
    
    

}
