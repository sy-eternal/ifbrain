package com.jzeen.travel.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@Entity
@Table(name="t_airline")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Airline
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column(name="airline_code")
    private String airlineCode;//航空公司代码
    
    @Column(name="airline_china_name")
    private String airlineChinaName;//航空公司中文名称
    
    @Column(name="airline_english_name")
    private String airlineEnglishName;//航空公司英文名称
    
    
    @OneToMany(mappedBy = "airlineCodes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FilghtPlan> filghtPlan;
    
    public List<FilghtPlan> getFilghtPlan()
    {
        return filghtPlan;
    }

    public void setFilghtPlan(List<FilghtPlan> filghtPlan)
    {
        this.filghtPlan = filghtPlan;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getAirlineCode()
    {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode)
    {
        this.airlineCode = airlineCode;
    }

    public String getAirlineChinaName()
    {
        return airlineChinaName;
    }

    public void setAirlineChinaName(String airlineChinaName)
    {
        this.airlineChinaName = airlineChinaName;
    }

    public String getAirlineEnglishName()
    {
        return airlineEnglishName;
    }

    public void setAirlineEnglishName(String airlineEnglishName)
    {
        this.airlineEnglishName = airlineEnglishName;
    }
    
    
}
