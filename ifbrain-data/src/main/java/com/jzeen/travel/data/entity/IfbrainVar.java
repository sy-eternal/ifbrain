package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_ifbrain_var")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class IfbrainVar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//变量名
	@Column(name="var_name")
	private String varName;
	//变量值
	@Column(name="var_value")
	private String varValue;
	//创建时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	//类型
	@Column(name="income_type")
	private Integer incomeType;
	
	
	
 //财脑指数
	@ManyToOne(targetEntity = IfbrainIndex.class,cascade={CascadeType.REFRESH})
	@JoinColumn(name = "ifbrain_id")
	@JsonBackReference
	private IfbrainIndex  ifbrainIndex;


	 
	

	

	public Integer getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(Integer incomeType) {
		this.incomeType = incomeType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getVarValue() {
		return varValue;
	}

	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public IfbrainIndex getIfbrainIndex() {
		return ifbrainIndex;
	}

	public void setIfbrainIndex(IfbrainIndex ifbrainIndex) {
		this.ifbrainIndex = ifbrainIndex;
	}
	
	


	
}
