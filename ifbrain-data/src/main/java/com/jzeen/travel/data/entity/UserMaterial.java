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


//点赞表
@Entity
@Table(name = "t_user_material")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserMaterial {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	//用户
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;	
	//素材id
	@ManyToOne(targetEntity = Material.class)
	@JoinColumn(name = "material_id")
	private Material material;	
	//点赞状态(0,未点赞 1.已点赞)
	  @Column(name="status")
	   private Integer status;
    //点赞时间
	  @Column(name="create_time")
	  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	  private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	  
	

	
	
    
	
	
	
    
    
    
    
}
