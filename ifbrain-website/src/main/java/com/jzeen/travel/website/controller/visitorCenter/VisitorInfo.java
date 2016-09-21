package com.jzeen.travel.website.controller.visitorCenter;

import org.hibernate.validator.constraints.NotEmpty;

public class VisitorInfo {

	private Integer id;
	@NotEmpty(message = "姓不能为空。")
	private String firstName;
	@NotEmpty(message = "名不能为空。")
	private String lastName;
	@NotEmpty(message = "邮箱不能为空。")
	private String email;
	@NotEmpty(message = "手机号不能为空。")
	private String mobile;
	@NotEmpty(message = "支付宝账号不能为空。")
	private String alipayAccount;
	@NotEmpty(message = "身份证号码不能为空。")
	private String idcardCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getIdcardCode() {
		return idcardCode;
	}
	public void setIdcardCode(String idcardCode) {
		this.idcardCode = idcardCode;
	}
}
