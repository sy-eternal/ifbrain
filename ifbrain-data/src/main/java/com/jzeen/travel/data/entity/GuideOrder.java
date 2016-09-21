package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_guide_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//用户主键
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", updatable = true)
    private User user;
//订单号
    @Column(name="guide_order_num")
    private String guideordernum;
 //导游类型1.徒步导游2.司机兼导游
    @Column(name="guide_type")
    private String guidetype;
    
  //目的城市
    @ManyToOne(targetEntity=City.class)
    @JoinColumn(name="city")
    private City city;

    //出行日期
    @Column(name="start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startdate;
    
    //出行人数
    @Column(name="person_count")
    private Integer personcount;
    
    //出行天数
    @Column(name="duration")
    private Integer duration;
    
    //导游人数
    @Column(name="guide_count")
    private Integer guidecount;
    //导游服务费
    @Column(name="guide_service_ammount")
    private BigDecimal guideserviceammount;
    
    //导游小费
    @Column(name="guide_tips_ammount")
    private BigDecimal guidetipsammount;
    //订单状态1 未支付
//    2 已结算
    @Column(name="order_status")
    private Integer orderstatus;
    
    //派单状态0 未派单
//    1 已派单
    @Column(name="appointed_status")
    private Integer appointedstatus;
    //创建时间
    @Column(name="create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    
    //总价
    @Column(name="order_ammount")
    private BigDecimal orderamount;
    
    
    public BigDecimal getOrderamount()
    {
        return orderamount;
    }
    public void setOrderamount(BigDecimal orderamount)
    {
        this.orderamount = orderamount;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public String getGuideordernum()
    {
        return guideordernum;
    }
    public void setGuideordernum(String guideordernum)
    {
        this.guideordernum = guideordernum;
    }
    public String getGuidetype()
    {
        return guidetype;
    }
    public void setGuidetype(String guidetype)
    {
        this.guidetype = guidetype;
    }
    public City getCity()
    {
        return city;
    }
    public void setCity(City city)
    {
        this.city = city;
    }
    public Date getStartdate()
    {
        return startdate;
    }
    public void setStartdate(Date startdate)
    {
        this.startdate = startdate;
    }
    public Integer getPersoncount()
    {
        return personcount;
    }
    public void setPersoncount(Integer personcount)
    {
        this.personcount = personcount;
    }
    public Integer getDuration()
    {
        return duration;
    }
    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }
    public Integer getGuidecount()
    {
        return guidecount;
    }
    public void setGuidecount(Integer guidecount)
    {
        this.guidecount = guidecount;
    }
    public BigDecimal getGuideserviceammount()
    {
        return guideserviceammount;
    }
    public void setGuideserviceammount(BigDecimal guideserviceammount)
    {
        this.guideserviceammount = guideserviceammount;
    }
    public BigDecimal getGuidetipsammount()
    {
        return guidetipsammount;
    }
    public void setGuidetipsammount(BigDecimal guidetipsammount)
    {
        this.guidetipsammount = guidetipsammount;
    }
    public Integer getOrderstatus()
    {
        return orderstatus;
    }
    public void setOrderstatus(Integer orderstatus)
    {
        this.orderstatus = orderstatus;
    }
    public Integer getAppointedstatus()
    {
        return appointedstatus;
    }
    public void setAppointedstatus(Integer appointedstatus)
    {
        this.appointedstatus = appointedstatus;
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
