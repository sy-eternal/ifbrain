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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_course_order")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CourseOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    //儿童id
    @OneToOne(targetEntity=Child.class)
   	@JoinColumn(name ="t_c_id")
   	private Child tCId;
  
   /* @OneToMany(mappedBy = "courseOrderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Child> childId;
    */
    
  
   /* @ManyToOne(targetEntity = Child.class)
    @JoinColumn(name="t_c_id")
    private Child tCId;  */
    
    
    //课程信id
   /* @Column(name="t_c_id2")
    private Integer tCId2;*/  
    @OneToOne(targetEntity=Course.class)
	@JoinColumn(name ="t_c_id2")
	private Course tCId2;
    
    
    //订单编号
    @Column(name="order_number")
    private String orderNumber;
    //微信id
    @Column(name = "openid")
    private String openid;
    
    //家长id
    @Column(name = "user_id")
    private Integer userId;
    
    
    //课程级别
    @Column(name="course_level")
    private String courseLevel;
     
    //列表价
    @Column(name="price")
    private BigDecimal price;
  
    //折扣价
    @Column(name="net_price")
    private BigDecimal netPrice;
    //支付状态
    @Column(name="pay_status")
    private  Integer payStatus;
    
    //微信支付id
    @Column(name="pay_wechatid")
    private  String payWechatid;

    //订单创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
    private Date createTime;
    
    
    /**
     * 订单支付时间
     * @return
     */
    
    @Column(name = "pay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
    private Date payTime;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	

	

	

	
	public Child gettCId() {
		return tCId;
	}

	public void settCId(Child tCId) {
		this.tCId = tCId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCourseLevel() {
		return courseLevel;
	}

	public Course gettCId2() {
		return tCId2;
	}

	public void settCId2(Course tCId2) {
		this.tCId2 = tCId2;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayWechatid() {
		return payWechatid;
	}

	public void setPayWechatid(String payWechatid) {
		this.payWechatid = payWechatid;
	}
    
  /*  //用户id
    @Column(name = "openid")
    private String openid;
   //总价
    @Column(name="amount")
    private BigDecimal amount;
    //支付状态
    @Column(name="pay_status")
    private  Integer payStatus;
    //微信支付id
    @Column(name="pay_wechatid")
    private  String payWechatid;
    
    //创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
    private Date createTime;
    //支付时间
    @Column(name = "pay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm ")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm",timezone = "GMT+8")
    private Date payTime;
    //订单号
    @Column(name = "order_number")
    private String orderNumber;
    */
    
    
    /*@OneToMany(mappedBy = "courseOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Course> course;*/
    
    
    
	/*public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}*/
    
	
    
    
    
}
