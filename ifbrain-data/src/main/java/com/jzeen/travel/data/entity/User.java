package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 用户类型 1:线上用户 2:学生
     */
    @Column(name = "user_type")
    private Integer userType;

    
    /**
     * 邮箱
     */
    @Size(max = 40)
    @Column(length = 40)
    private String email;

    /**
     * 密码
     */
    @Size(max = 40)
    @Column(length = 40)
    private String password;

    /**
     * 姓
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 名
     */
    @Column(name = "last_name")
    private String lastName;


    /**
     * 手机号
     */
    @Size(max = 15)
    @Column(length = 15)
    private String mobile;

    /**
     * 激活状态
     */
    @Column(name = "active_status")
    private Integer activeStatus;
    /**
     * 激活码有效期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "activity_validity")
    private Date activityValidity;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 注册邀请码
     */
    @Column(name = "activity_code")
    private String activitycode;
   
    /**
     * 手机短信验证码发送时间
     * 
     */
    @Column(name = "send_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendtime;
    /**
     * 手机短信验证码失效时间
     */
    @Column(name = "validation")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validation;

    /**
     * 手机短信验证码标识符
     */
    @Column(name = "identifier", length = 50)
    private String identifier;
    /**
     * 微信ID
     */
    @Size(max = 50)
    @Column(length = 50)
    private String wechat;
    /**
     * 手机短信验证码
     */
    @Column(name = "code", length = 255)
    private String code;
  //头像
  	@ManyToOne(targetEntity = Image.class)
  	@JoinColumn(name="image")
  	@JsonBackReference
  	private Image image;
    
    //游客
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Guide guide;
    
    //导游订单
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuideOrder> guideOrder;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkTaskRelate> workTask;
    
    public List<WorkTaskRelate> getWorkTask() {
		return workTask;
	}

	public void setWorkTask(List<WorkTaskRelate> workTask) {
		this.workTask = workTask;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	//child   家长和孩子是一对多
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Child> child;
    
    public List<Child> getChild() {
		return child;
	}

	public void setChild(List<Child> child) {
		this.child = child;
	}

	public List<GuideOrder> getGuideOrder()
    {
        return guideOrder;
    }

    public void setGuideOrder(List<GuideOrder> guideOrder)
    {
        this.guideOrder = guideOrder;
    }

    public Guide getGuide()
    {
        return guide;
    }

    public void setGuide(Guide guide)
    {
        this.guide = guide;
    }

    public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Date getActivityValidity()
    {
        return activityValidity;
    }

    public void setActivityValidity(Date activityValidity)
    {
        this.activityValidity = activityValidity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Date getValidation() {
		return validation;
	}

	public void setValidation(Date validation) {
		this.validation = validation;
	}

	public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

   /* public ExcursionGuide getExcursionGuide() {
        return excursionGuide;
    }

    public void setExcursionGuide(ExcursionGuide excursionGuide) {
        this.excursionGuide = excursionGuide;
    }*/

    public String getActivitycode() {
        return activitycode;
    }

    public void setActivitycode(String activitycode) {
        this.activitycode = activitycode;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    
    
}
