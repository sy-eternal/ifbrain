package com.jzeen.travel.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_material_type")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MaterialType {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	//封面
	@OneToOne(targetEntity = MaterialImage.class)
	@JoinColumn(name = "material_image_id", updatable = true)
	private MaterialImage materialimage;
	//创建时间
	 @Column(name = "create_time")
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
	 //类型
    @Column(name="type_name")
    private String materialName;
    @OneToMany(mappedBy = "materialType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<IfbrainTask> ifbraintask;
    
    //简写
    @Column(name="short_type_name")
    private String shortmaterialName;
    
	public String getShortmaterialName() {
		return shortmaterialName;
	}
	public void setShortmaterialName(String shortmaterialName) {
		this.shortmaterialName = shortmaterialName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public MaterialImage getMaterialimage() {
		return materialimage;
	}
	public void setMaterialimage(MaterialImage materialimage) {
		this.materialimage = materialimage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public List<IfbrainTask> getIfbraintask() {
		return ifbraintask;
	}
	public void setIfbraintask(List<IfbrainTask> ifbraintask) {
		this.ifbraintask = ifbraintask;
	}
 
   
   
	
	
	
	
	
    
    
    
    
}
