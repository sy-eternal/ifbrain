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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;



/**
 * 签证订单
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_visa_order")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class VisaOrder
{
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * 游客主键
	 */
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "traveler_id", updatable = true)
	private User user;

	/**
	 * 订单号
	 */
	@Column(name="order_number") 
	private String orderNumber;

	/**
	 * 淘宝订单号
	 */
	@Column(name="taobao_order_number") 
	private String taobaoOrderNumber;

	/**
	 * 数量
	 */
	@Column(name="head_count") 
	private Integer headCount;

	/**
	 * 金额
	 */
	@Column(name="total_amount") 
	private BigDecimal totalAmount;

	/**
	 * 订单状态
	 */

	@Column(name="order_status") 
	private Integer orderStatus;

	/**
	 * 创建时间
	 */

	@Column(name="create_time")  
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 收件人
	 */
	@Column(name="receiver_name") 
	private String receiverName;
	/**
	 * 收件地址
	 */
	@Column(name="post_address") 
	private String postAddress;

	/**
	 * 收件人电话
	 */
	@Column(name="tele_number") 
	private String teleNumber;

	/**
	 * 支付号
	 */
	@Column(name="payment_id") 
	private String paymentId;

	/**
	 * 快递公司
	 */
	@Column(name="express_name") 
	private String expressName;
	/**
	 * 快递号
	 */
	@Column(name="express_number") 
	private String expressNumber;

	/**
	 * 预约日期
	 */
	@Column(name="interview_date") 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date interviewDate;

	/**
	 * 预约时间段
	 */
	@Column(name="interview_time") 
	private String interviewTime;

	/**
	 * 预约地点
	 */
	@Column(name="interview_place") 
	private String interviewPlace;

	/**
	 * 预约备注
	 */
	@Column(name="interview_memo") 
	private String interviewMemo;
	/**
	 * 审核意见
	 */
	@Column(name="comment") 
	private String comment;
	
	/**
	 * 微信号
	 */
	@Column(name="wechat_id") 
    private String wechatId;
	
	
	
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}
	
	
	public String getWechatId()
    {
        return wechatId;
    }
    public void setWechatId(String wechatId)
    {
        this.wechatId = wechatId;
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
    public String getOrderNumber()
	{
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}
	public String getTaobaoOrderNumber()
	{
		return taobaoOrderNumber;
	}
	public void setTaobaoOrderNumber(String taobaoOrderNumber)
	{
		this.taobaoOrderNumber = taobaoOrderNumber;
	}
	public Integer getHeadCount()
	{
		return headCount;
	}
	public void setHeadCount(Integer headCount)
	{
		this.headCount = headCount;
	}
	public BigDecimal getTotalAmount()
	{
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount)
	{
		this.totalAmount = totalAmount;
	}
	public Integer getOrderStatus()
	{
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus)
	{
		this.orderStatus = orderStatus;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public String getReceiverName()
	{
		return receiverName;
	}
	public void setReceiverName(String receiverName)
	{
		this.receiverName = receiverName;
	}
	public String getPostAddress()
	{
		return postAddress;
	}
	public void setPostAddress(String postAddress)
	{
		this.postAddress = postAddress;
	}
	public String getTeleNumber()
	{
		return teleNumber;
	}
	public void setTeleNumber(String teleNumber)
	{
		this.teleNumber = teleNumber;
	}
	public String getPaymentId()
	{
		return paymentId;
	}
	public void setPaymentId(String paymentId)
	{
		this.paymentId = paymentId;
	}
	public String getExpressName()
	{
		return expressName;
	}
	public void setExpressName(String expressName)
	{
		this.expressName = expressName;
	}
	public String getExpressNumber()
	{
		return expressNumber;
	}
	public void setExpressNumber(String expressNumber)
	{
		this.expressNumber = expressNumber;
	}
	public Date getInterviewDate()
	{
		return interviewDate;
	}
	public void setInterviewDate(Date interviewDate)
	{
		this.interviewDate = interviewDate;
	}
	public String getInterviewPlace()
	{
		return interviewPlace;
	}
	public void setInterviewPlace(String interviewPlace)
	{
		this.interviewPlace = interviewPlace;
	}
	public String getInterviewMemo()
	{
		return interviewMemo;
	}
	public void setInterviewMemo(String interviewMemo)
	{
		this.interviewMemo = interviewMemo;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}






}
