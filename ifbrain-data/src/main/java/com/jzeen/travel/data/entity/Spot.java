package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_spot")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Spot
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // 供应商主键
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_pk", updatable = true)
    private Supplier supplierid;

    // 城市主键
    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city_id", updatable = true)
    private City cityid;

    // 景点名称
   
    @Column(name = "spots_name")
    private String spotsname;

    // 景点英文名称
     @NotEmpty
    @Size(max = 255)
    @Column(name = "spots_English_name")
    private String spotsename;

    // 地点
    @Size(max = 255)
    @Column(name = "location")
    private String location;

    // 英文地点名
    @Size(max = 255)
    @Column(name = "location_en")
    private String enlocation;

    // 地址
    @Size(max = 100)
    @Column(length = 100)
    private String address;

    // 联系电话
    @Size(max = 20)
    @Column(length = 20)
    private String tel;

    // 图片id
    @OneToOne(targetEntity = Image.class)
    @JoinColumn(name = "pics_id", updatable = true)
    private Image image;

    // 景点介绍
    @Size(max = 10000)
    @Column(name = "spots_description")
    private String spotsdescription;

    // 景点概要
    @Size(max = 10000)
    @Column(name = "spots_summary")
    private String spotssummary;

    // 特别提醒
    @Size(max = 10000)
    @Column(name = "special_notes")
    private String specialnotes;

    // 销售公式
    @Column(name = "sales_formula")
    private String salesformula;

    // 盈利返还导游
    @Column(name = "com_guide")
    private String comguide;

    // 穿衣建议
    @Size(max = 10000)
    @Column(name = "clothing_tips")
    private String clothingtips;

    // 消费提醒
    @Size(max = 10000)
    @Column(name = "spending_tips")
    private String spendingtips;

    // 季节推荐
    @Size(max = 10000)
    @Column(name = "weather_tips")
    private String weathertips;

    // 创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    @Column(name = "cost")
    private String cost;

    //景点主题
    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpotThemeRelate> themeRelates;
    
    //门票类型
    @OneToMany(mappedBy = "spots", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpotTicketType> spotTicketTypes;
    
    //景点标签
    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpotTags> spotTags;

//    private ThemeActive theme;

    public List<SpotTags> getSpotTags() {
		return spotTags;
	}

	public void setSpotTags(List<SpotTags> spotTags) {
		this.spotTags = spotTags;
	}

	public List<SpotTicketType> getSpotTicketTypes() {
		return spotTicketTypes;
	}

	public void setSpotTicketTypes(List<SpotTicketType> spotTicketTypes) {
		this.spotTicketTypes = spotTicketTypes;
	}

	public String getCost()
    {
        return cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

//    public ThemeActive getTheme()
//    {
//        return theme;
//    }
//
//    public void setTheme(ThemeActive theme)
//    {
//        this.theme = theme;
//    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Supplier getSupplierid()
    {
        return supplierid;
    }

    public void setSupplierid(Supplier supplierid)
    {
        this.supplierid = supplierid;
    }

    public City getCityid()
    {
        return cityid;
    }

    public void setCityid(City cityid)
    {
        this.cityid = cityid;
    }

    public String getSpotsname()
    {
        return spotsname;
    }

    public void setSpotsname(String spotsname)
    {
        this.spotsname = spotsname;
    }

    public String getSpotsename()
    {
        return spotsename;
    }

    public void setSpotsename(String spotsename)
    {
        this.spotsename = spotsename;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getEnlocation()
    {
        return enlocation;
    }

    public void setEnlocation(String enlocation)
    {
        this.enlocation = enlocation;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public String getSpotsdescription()
    {
        return spotsdescription;
    }

    public void setSpotsdescription(String spotsdescription)
    {
        this.spotsdescription = spotsdescription;
    }

    public String getSpotssummary()
    {
        return spotssummary;
    }

    public void setSpotssummary(String spotssummary)
    {
        this.spotssummary = spotssummary;
    }

    public String getSpecialnotes()
    {
        return specialnotes;
    }

    public void setSpecialnotes(String specialnotes)
    {
        this.specialnotes = specialnotes;
    }

    public String getSalesformula()
    {
        return salesformula;
    }

    public void setSalesformula(String salesformula)
    {
        this.salesformula = salesformula;
    }

    public String getComguide()
    {
        return comguide;
    }

    public void setComguide(String comguide)
    {
        this.comguide = comguide;
    }

    public String getClothingtips()
    {
        return clothingtips;
    }

    public void setClothingtips(String clothingtips)
    {
        this.clothingtips = clothingtips;
    }

    public String getSpendingtips()
    {
        return spendingtips;
    }

    public void setSpendingtips(String spendingtips)
    {
        this.spendingtips = spendingtips;
    }

    public String getWeathertips()
    {
        return weathertips;
    }

    public void setWeathertips(String weathertips)
    {
        this.weathertips = weathertips;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public List<SpotThemeRelate> getThemeRelates() {
        return themeRelates;
    }

    public void setThemeRelates(List<SpotThemeRelate> themeRelates) {
        this.themeRelates = themeRelates;
    }
}
