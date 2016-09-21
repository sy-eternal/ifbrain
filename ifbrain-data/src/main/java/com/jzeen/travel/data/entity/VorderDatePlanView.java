package com.jzeen.travel.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "v_order_dateplan")
public class VorderDatePlanView {
	
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name="torder_id")
    private Integer tOrderId;

	@Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;
    
    @Column(name="feedback")
    private String feedback;
  
	@Transient
    private List<VcustomerReportView> vcustomerReports;
    
	  
    public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
    public Integer gettOrderId() {
		return tOrderId;
	}

	public void settOrderId(Integer tOrderId) {
		this.tOrderId = tOrderId;
	}

    
    public List<VcustomerReportView> getVcustomerReports() {
		return vcustomerReports;
	}

	public void setVcustomerReports(List<VcustomerReportView> vcustomerReports) {
		this.vcustomerReports = vcustomerReports;
	}

	public Integer getId() {
  		return id;
  	}

  	public void setId(Integer id) {
  		this.id = id;
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

}
