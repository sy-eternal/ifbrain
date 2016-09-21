package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jzeen.travel.core.util.DateUtil;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 旅行订单
 */
@Entity
@Table(name = "t_exam_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ExamOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 试卷id
     */
   /* @ManyToOne(targetEntity = Exam.class)
    @JoinColumn(name = "exam_id", updatable = true)
    private Exam exam;*/

    /**
     * 订单号
     */
    @Size(max = 40)
    @Column(name = "order_number", length = 40)
    private String orderNumber;
    /**
     * 用户id
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", updatable = true)
    private User user;
    //
    @ManyToOne(targetEntity = Child.class)
    @JoinColumn(name = "child_id", updatable = true)
    private Child child;
    /**
     * 订单状态0,未付款  1，已付款 2.已过期
     */
    @Column(name = "order_status")
    private Integer orderStatus;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm", timezone = "GMT+8")
    private Date createTime;
    
    public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	/**
     * 订单总金额
     */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
    
   
}