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
@Table(name="t_hotel_plan_room")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class HotelPlanRoom
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    /**
     * 酒店规划
     */
    @ManyToOne(targetEntity = HotelPlan.class)
    @JoinColumn(name = "hotel_plan_id", updatable = true)
    private HotelPlan hotelPlan;
    
    
    /**
     * 房间类型
     */
    @ManyToOne(targetEntity = HotelRoomType.class)
    @JoinColumn(name = "hotel_room_type_id", updatable = true)
    private HotelRoomType hotelRoomType;
    
    
    /**
     * 房间数量
     */
    @Column(name="room_count")
    private Integer roomCount;
    
    /**
     * 销售价
     */
    @Column(name="sale_price")
    private BigDecimal salePrice;
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;
    

    /*
     * 供应商订单号
     */
     @Column(name="supplier_num")
     private String supplierNum;



     public String getSupplierNum() {
			return supplierNum;
		}


		public void setSupplierNum(String supplierNum) {
			this.supplierNum = supplierNum;
		}
    
    

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public HotelPlan getHotelPlan()
    {
        return hotelPlan;
    }

    public void setHotelPlan(HotelPlan hotelPlan)
    {
        this.hotelPlan = hotelPlan;
    }

    public HotelRoomType getHotelRoomType()
    {
        return hotelRoomType;
    }

    public void setHotelRoomType(HotelRoomType hotelRoomType)
    {
        this.hotelRoomType = hotelRoomType;
    }

    public Integer getRoomCount()
    {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount)
    {
        this.roomCount = roomCount;
    }

    public BigDecimal getSalePrice()
    {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice)
    {
        this.salePrice = salePrice;
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
