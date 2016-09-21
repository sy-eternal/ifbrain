package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_hotel_activity")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class HotelActivity
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    /**
     * 供应商
     */
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id", updatable = true)
    private Supplier supplier;
    
    /**
     * 城市
     */
    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city_id", updatable = true)
    private City city;
    
    /**
     * 酒店英文名称
     */
    @Column(name="hotel_eng_name")
    private String hotelEngName;
    
    /**
     * 酒店中文名称
     */
    @Column(name="hotel_ch_name")
    private String hotelChName;
    
    /**
     * 星级
     */
    @Column(name="hotel_class")
    private Integer hotelClass;
    
    /**
     * 地址
     */
    @Column(name="address")
    private String address;
    
    /**
     * 酒店介绍
     */
    @Column(name="description")
    private String description;
    
    /**
     * 图片
     */
    @OneToOne(targetEntity = Image.class)
    @JoinColumn(name = "pic_id")
    private Image image;
    
    /**
     * 是否能取消
     */
    @Column(name="free_cancellation")
    private Integer freeCancellation;
    
    /**
     * 免费早餐
     */
    @Column(name="free_breakfast_type")
    private Integer freeBreakfastType;
    
    /**
     * 免费网络
     */
    @Column(name="free_internet_type")
    private Integer freeInternetType;
    
    /**
     * 免费停车
     */
    @Column(name="free_parking_type")
    private Integer freeParkingType;
    
    /**
     * 机场班车
     */
    @Column(name="airport_shuttle_type")
    private Integer airportShuttleType;
    
    /**
     * 健身中心
     */
    @Column(name="fitness_center_type")
    private Integer fitnessCenterType;
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;
    
    
    @OneToMany(mappedBy = "hotelActivity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HotelRoomType> hotelRoomType;
    
    
    
    
    @OneToMany(mappedBy = "hotelActivity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HotelTags> hotelTags;
    /**
     * 销售价
     * @return
     */
    @Transient
    private BigDecimal perNightPrice;
    
    

    public List<HotelTags> getHotelTags()
    {
        return hotelTags;
    }

    public void setHotelTags(List<HotelTags> hotelTags)
    {
        this.hotelTags = hotelTags;
    }

    public BigDecimal getPerNightPrice()
    {
        return perNightPrice;
    }

    public void setPerNightPrice(BigDecimal perNightPrice)
    {
        this.perNightPrice = perNightPrice;
    }

    public List<HotelRoomType> getHotelRoomType()
    {
        return hotelRoomType;
    }

    public void setHotelRoomType(List<HotelRoomType> hotelRoomType)
    {
        this.hotelRoomType = hotelRoomType;
    }

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

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public String getHotelEngName()
    {
        return hotelEngName;
    }

    public void setHotelEngName(String hotelEngName)
    {
        this.hotelEngName = hotelEngName;
    }

    public String getHotelChName()
    {
        return hotelChName;
    }

    public void setHotelChName(String hotelChName)
    {
        this.hotelChName = hotelChName;
    }

    public Integer getHotelClass()
    {
        return hotelClass;
    }

    public void setHotelClass(Integer hotelClass)
    {
        this.hotelClass = hotelClass;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public Integer getFreeCancellation()
    {
        return freeCancellation;
    }

    public void setFreeCancellation(Integer freeCancellation)
    {
        this.freeCancellation = freeCancellation;
    }

    public Integer getFreeBreakfastType()
    {
        return freeBreakfastType;
    }

    public void setFreeBreakfastType(Integer freeBreakfastType)
    {
        this.freeBreakfastType = freeBreakfastType;
    }

    public Integer getFreeInternetType()
    {
        return freeInternetType;
    }

    public void setFreeInternetType(Integer freeInternetType)
    {
        this.freeInternetType = freeInternetType;
    }

    public Integer getFreeParkingType()
    {
        return freeParkingType;
    }

    public void setFreeParkingType(Integer freeParkingType)
    {
        this.freeParkingType = freeParkingType;
    }

    public Integer getAirportShuttleType()
    {
        return airportShuttleType;
    }

    public void setAirportShuttleType(Integer airportShuttleType)
    {
        this.airportShuttleType = airportShuttleType;
    }

    public Integer getFitnessCenterType()
    {
        return fitnessCenterType;
    }

    public void setFitnessCenterType(Integer fitnessCenterType)
    {
        this.fitnessCenterType = fitnessCenterType;
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
