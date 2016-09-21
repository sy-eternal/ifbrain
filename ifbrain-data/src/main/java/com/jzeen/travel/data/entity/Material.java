package com.jzeen.travel.data.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_material")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Material {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	 //素材主题
    @Column(name="material_name")
    private String materialName;
    //素材内容    
    @Column(name="material_content")
    private Blob materialContent;
    //作者
    @Column(name="author")
    private String author;
    //素材创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //素材封面
	@OneToOne(targetEntity = MaterialImage.class)
	@JoinColumn(name = "material_image_id", updatable = true)
	private MaterialImage materialimage;
    //类型
	@ManyToOne(targetEntity = MaterialType.class)
	@JoinColumn(name = "material_type")
	private MaterialType materialType;	
	@Transient
	private String type;
//访问量
	@Column(name = "visitor_number")
	private Integer visitornumber;
	
	public Integer getVisitornumber() {
		return visitornumber;
	}
	public void setVisitornumber(Integer visitornumber) {
		this.visitornumber = visitornumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MaterialImage getMaterialimage() {
		return materialimage;
	}
	public void setMaterialimage(MaterialImage materialimage) {
		this.materialimage = materialimage;
	}
	public MaterialType getMaterialType() {
		return materialType;
	}
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Blob getMaterialContent() {
		return materialContent;
	}
	public void setMaterialContent(Blob materialContent) {
		this.materialContent = materialContent;
	}
	
	
	
	
    
    
    
    
}
