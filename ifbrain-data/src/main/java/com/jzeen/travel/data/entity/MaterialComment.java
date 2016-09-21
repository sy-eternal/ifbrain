package com.jzeen.travel.data.entity;

import java.sql.Blob;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


//评论表
@Entity
@Table(name = "t_material_comment")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MaterialComment {
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
	
	
	
	//用户回复内容
		@Column(name="user_comment")
		private String usercomment;
    //用户评论时间
	  @Column(name="create_time")
	  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	  private Date createTime;
	  
	
	  @OneToMany(mappedBy = "materialComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<CommentReply> commentReply;
	  
	  
	public List<CommentReply> getCommentReply() {
		return commentReply;
	}
	public void setCommentReply(List<CommentReply> commentReply) {
		this.commentReply = commentReply;
	}
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
	public String getUsercomment() {
		return usercomment;
	}
	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	  
	  
	
    
	
	
	
    
    
    
    
}
