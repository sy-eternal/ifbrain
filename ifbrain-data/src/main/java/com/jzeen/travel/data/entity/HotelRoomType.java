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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@Entity
@Table(name = "t_hotel_room_type")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class HotelRoomType
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
     * 房间类型
     */
    @Column(name="rome_type")
    private String roomType;
    
    /**
     * 促销内容
     */
    @Column(name="promotion_content")
    private String promotionContent;
    
    /**
     * 成本价
     */
    @Column(name="per_night_cost")
    private BigDecimal perNightCost;
    
    /**
     * 销售价
     */
    @Column(name="per_night_price")
    private BigDecimal perNightPrice;
    
    /**
     * 描述
     */
    @Column(name="description")
    private String description;
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;

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

    public String getRoomType()
    {
        return roomType;
    }

    public void setRoomType(String roomType)
    {
        this.roomType = roomType;
    }

    public String getPromotionContent()
    {
        return promotionContent;
    }

    public void setPromotionContent(String promotionContent)
    {
        this.promotionContent = promotionContent;
    }

    public BigDecimal getPerNightCost()
    {
        return perNightCost;
    }

    public void setPerNightCost(BigDecimal perNightCost)
    {
        this.perNightCost = perNightCost;
    }

    public BigDecimal getPerNightPrice()
    {
        return perNightPrice;
    }

    public void setPerNightPrice(BigDecimal perNightPrice)
    {
        this.perNightPrice = perNightPrice;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
