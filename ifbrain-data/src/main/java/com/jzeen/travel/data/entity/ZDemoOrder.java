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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "t_demo_order")
public class ZDemoOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@ManyToOne (targetEntity = ZDemoUser.class)
	@JoinColumn(name = "user_id", updatable = true)
	private ZDemoUser zDemoUser;
	
	@NotEmpty(message = "收件人不为空")
	@Size (max = 20)
	@Column(name = "receiver_name",length = 20)
	private String reveiverName;

	@NotEmpty(message = "手机号码不能为空。")
    @Size(max = 20)
    @Column(length = 20)
	private String mobile;

	@NotEmpty(message = "详细地址不能为空。")
    @Size(max = 200)
    @Column(length = 200)
    private String address;
   
    @Size(max = 1000)
    @Column(length = 1000)
    private String remark;
    
    @Column(name = "create_time")
    private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ZDemoUser getzDemoUser() {
		return zDemoUser;
	}

	public void setzDemoUser(ZDemoUser zDemoUser) {
		this.zDemoUser = zDemoUser;
	}

	public String getReveiverName() {
		return reveiverName;
	}

	public void setReveiverName(String reveiverName) {
		this.reveiverName = reveiverName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
	 @Override
	    public String toString()
	    {
	        return String
	                .format("Order[id=%d, user='%s', reveiverName='%s', mobile='%s', address='%s', remark='%s', createTime='%tY-%tm-%td %tH:%tM:%tS']",
	                        id, zDemoUser.getNumber(), reveiverName, mobile, address, remark, createTime);
	    }
}