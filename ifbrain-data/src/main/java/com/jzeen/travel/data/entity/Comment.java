package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
@Entity
@Table(name = "t_comment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	 //评论内容
	@Column(name="content")
	private String content;
	//用户类型 家长0，老师1
	@Column(name="usrtype")
	private String usrtype;
	//两个图的类型   折线图0 ,柱状图1
	@Column(name="type")
	private String type;
	// 评论时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	
	@ManyToOne(targetEntity = IfbrainIndex.class,cascade={CascadeType.REFRESH})
	@JoinColumn(name = "ifbrain_id")
	@JsonBackReference
	private IfbrainIndex  ifbrainIndex;
	//家长
	@ManyToOne(targetEntity = User.class,cascade={CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User  user;
	//老师
	@ManyToOne(targetEntity = Member.class,cascade={CascadeType.REFRESH})
	@JoinColumn(name = "member_id")
	@JsonBackReference
	private Member  member;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public IfbrainIndex getIfbrainIndex() {
		return ifbrainIndex;
	}
	public void setIfbrainIndex(IfbrainIndex ifbrainIndex) {
		this.ifbrainIndex = ifbrainIndex;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsrtype() {
		return usrtype;
	}
	public void setUsrtype(String usrtype) {
		this.usrtype = usrtype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
