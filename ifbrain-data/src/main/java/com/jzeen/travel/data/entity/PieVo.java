package com.jzeen.travel.data.entity;

import java.math.BigDecimal;

/**
 * 饼状图实体类
 * @author 孙岩
 *
 */
public class PieVo {
	private String name;
	private BigDecimal value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
  
}
