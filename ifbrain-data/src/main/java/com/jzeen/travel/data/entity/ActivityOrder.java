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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@Entity
@Table(name = "t_activity_order")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ActivityOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	 //联系人名称
    @Column(name="contact_name")
    private String contactName;
    //联系人电话
    @Column(name="contact_mobile")
    private String contactMobile;
    //订单号
    @Column(name="order_number")
    private String orderNumber;
    //孩子人数
    @Column(name="child_number")
    private Integer childNumber;
    //总价格
    @Column(name="activity_amount")
    private BigDecimal activityAmount;
    //订单创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
    private Date createTime;
    //支付状态
    @Column(name="pay_status")
    private  Integer payStatus;
    /**
     * 订单支付时间
     * @return
     */
    @Column(name = "pay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
    private Date payTime;
    
    
  
    @ManyToOne(targetEntity = Activity.class)
	@JoinColumn(name = "activity_id", updatable = true)
	private Activity activity;
    
    
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getChildNumber() {
		return childNumber;
	}
	public void setChildNumber(Integer childNumber) {
		this.childNumber = childNumber;
	}
	public BigDecimal getActivityAmount() {
		return activityAmount;
	}
	public void setActivityAmount(BigDecimal activityAmount) {
		this.activityAmount = activityAmount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
    
}
