package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_guide")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
// 标签
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuideTags> guidetag;

    /**
     * 导游评论
     */
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuideComments> guideComments;
    

	/**
     * 用户id
     */
    @OneToOne(targetEntity=User.class)
    @JoinColumn(name ="user_id")
    @JsonBackReference
    private User user;

    
  /*  @OneToOne(mappedBy = "guide")
    private GuideImageRelate guideImageRelate;
    */
    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuideImageRelate> guideImageRelate;
    /**
     * 邮箱
     */
    @Transient
    private String email;

    /**
     * 密码
     */
    @Transient
    private String password;

    /**
     * 出生日期
     */
    @Column(name = "birthday", length = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Integer sex;

   //国籍
    @Column(name = "nationality")
    private String nationality;
    
   //护照号码
    @Column(name = "passport_code")
    private String passportcode;
    
    //驾照号码
    @Column(name = "drive_license_code")
    private String drivelicensecode;
    
    //驾照扫面件id
   /* @OneToOne
    @JsonIgnore
    @JoinColumn(name = "drive_license_picture_id")
    private Image image;*/
    
    //愿意到其他地点
    @Column(name = "to_other_city")
    private Integer toothercity;
    
    //愿意临时服务
    @Column(name = "excursion_type")
    private Integer excursiontype;
    
    //支付方式
    @Column(name = "pay_method_code")
    private Integer paymethodcode;
    
    //地址
    @Column(name = "address")
    private String address;
    
    //Paypal账号
    @Column(name = "payal_account")
    private String payalaccount;
    
    //支付宝账号
    @Column(name = "alipay_account")
    private String alipayaccount;
    
    //身份证号码
    @Column(name = "id_card_number")
    private String idcardnumber;
    
    //审核状态
    @Column(name = "approval_status")
    private Integer approvalstatus;
    //导游专车
    @OneToOne(mappedBy = "user")
    private GuideCar guideCar;
    //银行名称
    @Column(name = "bank_name")
    private String bankname;
    
    //银行地址
    @Column(name = "bank_address")
    private String bankaddress;
    
    //银行电话
    @Column(name = "bank_tel")
    private String banktel;
    
    //国内电汇代码
    @Column(name = "domestic_routing_num")
    private String domesticroutingnum;
    
    //国外电汇代码
    @Column(name = "international_swift_num")
    private String internationalswiftnum;
    
    //账户名称
    @Column(name = "account_name")
    private String accountname;
    
    //账户号码
    @Column(name = "account_num")
    private String accountnum;
    
    //是否有车
    @Column(name = "has_car")
    private String has_car;
    
    //创建时间
    @Column(name = "create_time", length = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createtime;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "host_city_id")
    private City hostCity;
    
    //标准导游
    @OneToOne(mappedBy = "user")
    private StandardGuide standardGuide;
    
    //短途导游
    @OneToOne(mappedBy = "guide")
    private ExcursionGuide excursionGuide;
    
    //在该城市的时间
    @Column(name = "stay_duration")
    private Integer stayduration;
    
    //身份证或护驾照
    
   /* @OneToOne(targetEntity = Image.class)
    @JoinColumn(name = "ic_passport_pic_id")
    private Image icpassportpic;
    */
    //专长领域
  /*  @Column(name = "specialty")
    private String specialty;*/
    //座右铭介绍
    @Column(name = "motto")
    private String motto;
    //个人介绍
    @Column(name ="self_description")
    private String selfdescription;
    
    //城市游记介绍
    @Column(name ="city_description")
    private String citydescription;
    
    //导游封面照
/*    @Column(name ="cover_pic_id")
    private Image coverpic;*/
    

    public List<GuideComments> getGuideComments() {
		return guideComments;
	}

	public void setGuideComments(List<GuideComments> guideComments) {
		this.guideComments = guideComments;
	}
    

    public List<GuideTags> getGuidetag()
    {
        return guidetag;
    }

    public void setGuidetag(List<GuideTags> guidetag)
    {
        this.guidetag = guidetag;
    }

  

    public List<GuideImageRelate> getGuideImageRelate()
    {
        return guideImageRelate;
    }

    public void setGuideImageRelate(List<GuideImageRelate> guideImageRelate)
    {
        this.guideImageRelate = guideImageRelate;
    }



/*    public String getSpecialty()
    {
        return specialty;
    }

    public void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }*/

    public Integer getStayduration()
    {
        return stayduration;
    }

    public void setStayduration(Integer stayduration)
    {
        this.stayduration = stayduration;
    }

    public String getMotto()
    {
        return motto;
    }

    public void setMotto(String motto)
    {
        this.motto = motto;
    }

    public String getSelfdescription()
    {
        return selfdescription;
    }

    public void setSelfdescription(String selfdescription)
    {
        this.selfdescription = selfdescription;
    }



    public String getCitydescription()
    {
        return citydescription;
    }

    public void setCitydescription(String citydescription)
    {
        this.citydescription = citydescription;
    }

    public GuideCar getGuideCar()
    {
        return guideCar;
    }

    public void setGuideCar(GuideCar guideCar)
    {
        this.guideCar = guideCar;
    }

    public StandardGuide getStandardGuide()
    {
        return standardGuide;
    }

    public void setStandardGuide(StandardGuide standardGuide)
    {
        this.standardGuide = standardGuide;
    }

    public ExcursionGuide getExcursionGuide()
    {
        return excursionGuide;
    }

    public void setExcursionGuide(ExcursionGuide excursionGuide)
    {
        this.excursionGuide = excursionGuide;
    }

    public City getHostCity()
    {
        return hostCity;
    }

    public void setHostCity(City hostCity)
    {
        this.hostCity = hostCity;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

   /* public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }*/

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }


    public Integer getSex()
    {
        return sex;
    }

    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }


    public String getPassportcode()
    {
        return passportcode;
    }

    public void setPassportcode(String passportcode)
    {
        this.passportcode = passportcode;
    }

    public String getDrivelicensecode()
    {
        return drivelicensecode;
    }

    public void setDrivelicensecode(String drivelicensecode)
    {
        this.drivelicensecode = drivelicensecode;
    }


 /*   public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }
*/
    public Integer getToothercity()
    {
        return toothercity;
    }

    public void setToothercity(Integer toothercity)
    {
        this.toothercity = toothercity;
    }

    public Integer getExcursiontype()
    {
        return excursiontype;
    }

    public void setExcursiontype(Integer excursiontype)
    {
        this.excursiontype = excursiontype;
    }

    public Integer getPaymethodcode()
    {
        return paymethodcode;
    }

    public void setPaymethodcode(Integer paymethodcode)
    {
        this.paymethodcode = paymethodcode;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPayalaccount()
    {
        return payalaccount;
    }

    public void setPayalaccount(String payalaccount)
    {
        this.payalaccount = payalaccount;
    }

    public String getAlipayaccount()
    {
        return alipayaccount;
    }

    public void setAlipayaccount(String alipayaccount)
    {
        this.alipayaccount = alipayaccount;
    }

    public String getIdcardnumber()
    {
        return idcardnumber;
    }

    public void setIdcardnumber(String idcardnumber)
    {
        this.idcardnumber = idcardnumber;
    }

    public Integer getApprovalstatus()
    {
        return approvalstatus;
    }

    public void setApprovalstatus(Integer approvalstatus)
    {
        this.approvalstatus = approvalstatus;
    }

    public String getBankname()
    {
        return bankname;
    }

    public void setBankname(String bankname)
    {
        this.bankname = bankname;
    }

    public String getBankaddress()
    {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress)
    {
        this.bankaddress = bankaddress;
    }

    public String getBanktel()
    {
        return banktel;
    }

    public void setBanktel(String banktel)
    {
        this.banktel = banktel;
    }

    public String getDomesticroutingnum()
    {
        return domesticroutingnum;
    }

    public void setDomesticroutingnum(String domesticroutingnum)
    {
        this.domesticroutingnum = domesticroutingnum;
    }

    public String getInternationalswiftnum()
    {
        return internationalswiftnum;
    }

    public void setInternationalswiftnum(String internationalswiftnum)
    {
        this.internationalswiftnum = internationalswiftnum;
    }

    public String getAccountname()
    {
        return accountname;
    }

    public void setAccountname(String accountname)
    {
        this.accountname = accountname;
    }

    public String getAccountnum()
    {
        return accountnum;
    }

    public void setAccountnum(String accountnum)
    {
        this.accountnum = accountnum;
    }

    public String getHas_car()
    {
        return has_car;
    }

    public void setHas_car(String has_car)
    {
        this.has_car = has_car;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }
}
