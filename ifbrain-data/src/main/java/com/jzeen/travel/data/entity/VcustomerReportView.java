package com.jzeen.travel.data.entity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "v_customer_report")
public class VcustomerReportView {
 
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "seq")
    private Integer seq;
    
    @Column(name = "type_code_id")
    private Integer typeCodeId;

	@Column(name = "date_plan_id")
    private Integer datePlanId;

	@Column(name = "order_id")
    private Integer orderId;
	
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;
    
    @Column(name = "from_city_id")
    private Integer fromCtiyId;
    
    @Column(name = "from_city_name")
    private String fromCityName;
    
    @Column(name = "to_city_id")
    private Integer toCtiyId;
    
    @Column(name = "to_city_name")
    private String toCityName;

    @Column(name = "type_code")
    private String typeCode;
    
    @Column(name = "type_name")
    private String typeName;
    
    @Column(name = "person_count")
    private Integer personCount;
    
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    
	public Integer getTypeCodeId() {
		return typeCodeId;
	}

	public void setTypeCodeId(Integer typeCodeId) {
		this.typeCodeId = typeCodeId;
	}
    
	public Integer getDatePlanId() {
		return datePlanId;
	}

	public void setDatePlanId(Integer datePlanId) {
		this.datePlanId = datePlanId;
	}
	  
    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    
   	public Integer getOrderId() {
   		return orderId;
   	}

   	public void setOrderId(Integer orderId) {
   		this.orderId = orderId;
   	}

   	public Date getStartDate() {
   		return startDate;
   	}

   	public void setStartDate(Date startDate) {
   		this.startDate = startDate;
   	}

   	public Date getEndDate() {
   		return endDate;
   	}

   	public void setEndDate(Date endDate) {
   		this.endDate = endDate;
   	}

   	public Integer getFromCtiyId() {
   		return fromCtiyId;
   	}

   	public void setFromCtiyId(Integer fromCtiyId) {
   		this.fromCtiyId = fromCtiyId;
   	}

   	public String getFromCityName() {
   		return fromCityName;
   	}

   	public void setFromCityName(String fromCityName) {
   		this.fromCityName = fromCityName;
   	}

   	public Integer getToCtiyId() {
   		return toCtiyId;
   	}

   	public void setToCtiyId(Integer toCtiyId) {
   		this.toCtiyId = toCtiyId;
   	}

   	public String getToCityName() {
   		return toCityName;
   	}

   	public void setToCityName(String toCityName) {
   		this.toCityName = toCityName;
   	}

   	public String getTypeCode() {
   		return typeCode;
   	}

   	public void setTypeCode(String typeCode) {
   		this.typeCode = typeCode;
   	}

   	public String getTypeName() {
   		return typeName;
   	}

   	public void setTypeName(String typeName) {
   		this.typeName = typeName;
   	}

   	public Integer getPersonCount() {
   		return personCount;
   	}

   	public void setPersonCount(Integer personCount) {
   		this.personCount = personCount;
   	}

   	public BigDecimal getSalePrice() {
   		return salePrice;
   	}

   	public void setSalePrice(BigDecimal salePrice) {
   		this.salePrice = salePrice;
   	}

}
