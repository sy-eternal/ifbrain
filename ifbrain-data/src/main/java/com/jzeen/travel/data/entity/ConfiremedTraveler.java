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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name="t_confirmed_traveler")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ConfiremedTraveler {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "order_pk", updatable = true)
	private Order order;
	

	@Column(name="xin")
	private String xin;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="ming")
	private String ming;
	
	@Column(name="second_name")
	private String secondName;
	
	@Column(name="drive_license_code")
	private String  driveLicenseCode;
	
	@Column(name="passport_number")
	private String passportNumber;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="gender")
	private Integer gender;
	
	@Column(name="birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date birthDate;
	
	@Column(name="create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;

	@Column(name="weichat")
	private String weichat;
	
	public String getWeichat() {
		return weichat;
	}
	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setWeichat(String weichat) {
		this.weichat = weichat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getXin() {
		return xin;
	}

	public void setXin(String xin) {
		this.xin = xin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMing() {
		return ming;
	}

	public void setMing(String ming) {
		this.ming = ming;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getDriveLicenseCode() {
		return driveLicenseCode;
	}

	public void setDriveLicenseCode(String driveLicenseCode) {
		this.driveLicenseCode = driveLicenseCode;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
