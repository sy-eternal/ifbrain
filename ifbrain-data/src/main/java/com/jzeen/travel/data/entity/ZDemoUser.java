package com.jzeen.travel.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="t_demo_user")
public class ZDemoUser {
	//标识
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	//编号
	@NotEmpty
	@Size(max = 36)
	@Column(length = 36)
	private String number;
	//访问次数
	@Min(0)
	@Column(name = "visit_count")
	private Integer visitCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	@Override
	 public String toString()
    {
        return String.format("User[id=%d, number='%s', visitCount=%d]", id, number, visitCount);
    }
	
}