package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
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

@Entity
@Table(name = "t_child")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Child {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//宝贝姓名
	@Column(name="name")
	private String name;
	//需求者类型
	@Column(name="type")
	private String type;
//昵称
	@Column(name="nick_name")
	private String nickName;
	//班级id
	@ManyToOne(targetEntity = CourseClass.class)
	@JoinColumn(name = "class_id", updatable = true)
	private CourseClass courseClass;
	//
	@OneToMany(mappedBy = "child")
	private List<IfbrainIndex> ifbrainIndex;
//代币余额
	@Column(name="balance")
	private BigDecimal balance;
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public CourseClass getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(CourseClass courseClass) {
		this.courseClass = courseClass;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	//年龄
	@Column(name="age")
	private Integer age;

	//头像
	@ManyToOne(targetEntity = Image.class)
	@JoinColumn(name="head")
	@JsonBackReference
	private Image image;

	//生日
	@Column(name = "birth")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date birth;
	//创建日期
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
	//修改日期
	@Column(name = "edit_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date editTime;

	//用户
	@JsonBackReference
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="user_id")
	private User user;

	@OneToOne(mappedBy = "childId")
	private Money money;
	
	/*@OneToOne(mappedBy = "child",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private IfbrainIndex ifbrainIndex;*/
	//0男
	//1女
	@Column(name="gender")
	private Integer gender;
	/**
	 * 孩子和任务是一对多, cascade = CascadeType.ALL, fetch = FetchType.LAZY
	 */
	@OneToMany(mappedBy = "child")
	private List<DefineTask> defineTask;

	@OneToMany(mappedBy = "child")
	private List<Video> video;

	@OneToMany(mappedBy = "child")
	private List<ChildShoppingmall> childShoppingmall;
	
	
	public List<ChildShoppingmall> getChildShoppingmall() {
		return childShoppingmall;
	}

	public void setChildShoppingmall(List<ChildShoppingmall> childShoppingmall) {
		this.childShoppingmall = childShoppingmall;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<IfbrainIndex> getIfbrainIndex() {
		return ifbrainIndex;
	}

	public void setIfbrainIndex(List<IfbrainIndex> ifbrainIndex) {
		this.ifbrainIndex = ifbrainIndex;
	}

	public List<Video> getVideo() {
		return video;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	

	public List<DefineTask> getDefineTask() {
		return defineTask;
	}

	public void setDefineTask(List<DefineTask> defineTask) {
		this.defineTask = defineTask;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
