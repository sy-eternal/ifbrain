package com.jzeen.travel.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 保险活动
 */
@Entity
@Table(name = "t_insurance_activity")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class InsuranceActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	// 供应商主键，一对多关系
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id",updatable = true)
    private Supplier supplier;

	@OneToMany(mappedBy = "insuranceActivity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InsuranceRate> insuranceRate;
    
    //保险类型
    @Column(name="insurance_type")
    private String insuranceType;
    
    //险种
    @Column(name="insurance_name")
    private String insuranceName;
    
    //保险范围
    @Column(name="insurance_coverage")
    private String insuranceCove;
    
    //创建时间
    @Column(name="update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public List<InsuranceRate> getInsuranceRate() {
		return insuranceRate;
	}

	public void setInsuranceRate(List<InsuranceRate> insuranceRate) {
		this.insuranceRate = insuranceRate;
	}    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getInsuranceCove() {
		return insuranceCove;
	}

	public void setInsuranceCove(String insuranceCove) {
		this.insuranceCove = insuranceCove;
	}

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

 
	

}
