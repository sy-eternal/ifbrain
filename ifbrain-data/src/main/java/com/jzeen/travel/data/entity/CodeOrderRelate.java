package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_code_order_relate")
public class CodeOrderRelate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="create_time")
	private Date createTime;

	@Column(name="order_pk")
	private Integer orderPk;
	
	@Column(name="code_pk")
	private Integer codePk;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderPk() {
		return orderPk;
	}

	public void setOrderPk(Integer orderPk) {
		this.orderPk = orderPk;
	}

	public Integer getCodePk() {
		return codePk;
	}

	public void setCodePk(Integer codePk) {
		this.codePk = codePk;
	}

	
}
