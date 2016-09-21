package com.jzeen.travel.data.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


//评论表
@Entity
@Table(name = "t_comment_reply")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CommentReply {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	
	//作者
	@ManyToOne(targetEntity = Member.class)
	@JoinColumn(name = "member_id")
	@JsonBackReference
	private Member  member;
	//回复内容
	@Column(name="member_comment")
	private String membercomment;
	
	
   
	//老师评论时间
	  @Column(name="membercomment_time")
	  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	  private Date membercommentTime;

	  //用户评论id
	   @ManyToOne(targetEntity = MaterialComment.class)
		@JoinColumn(name = "material_comment_id")
		@JsonBackReference
		private MaterialComment  materialComment;
	 
	

	public MaterialComment getMaterialComment() {
		return materialComment;
	}

	public void setMaterialComment(MaterialComment materialComment) {
		this.materialComment = materialComment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getMembercomment() {
		return membercomment;
	}

	public void setMembercomment(String membercomment) {
		this.membercomment = membercomment;
	}

	

	public Date getMembercommentTime() {
		return membercommentTime;
	}

	public void setMembercommentTime(Date membercommentTime) {
		this.membercommentTime = membercommentTime;
	}
	  
	  
	
	
	
    
	
	
	
    
    
    
    
}
