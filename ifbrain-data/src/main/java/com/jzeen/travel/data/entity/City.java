package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_city")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(name = "cn_name", length = 255)
    private String cityName;

    @Column(name = "airport_code", length = 10)
    private String airportCode;

    @OneToOne(targetEntity = Image.class)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id", updatable = true)
    private Country country;
    
    @NotEmpty
    @Column(name = "en_name", length = 255)
    private String enName;

   /* @OneToMany(mappedBy = "arrivalCityId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private FlightDetails arrivalCityId;
    
    
    
    @OneToMany(mappedBy = "departureCityId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private FlightDetails departureCityId;
*/
    
    
    
    
    /*public FlightDetails getArrivalCityId()
    {
        return arrivalCityId;
    }

    public void setArrivalCityId(FlightDetails arrivalCityId)
    {
        this.arrivalCityId = arrivalCityId;
    }

    public FlightDetails getDepartureCityId()
    {
        return departureCityId;
    }

    public void setDepartureCityId(FlightDetails departureCityId)
    {
        this.departureCityId = departureCityId;
    }*/

    public String getEnName()
    {
        return enName;
    }

    public void setEnName(String enName)
    {
        this.enName = enName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }
}
