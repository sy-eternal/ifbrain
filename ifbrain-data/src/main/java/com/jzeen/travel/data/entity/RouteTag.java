package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_route_tag")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class RouteTag {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 线路id
     */
      @ManyToOne(targetEntity = Route.class)
      @JoinColumn(name = "route_id", updatable = true)
      private Route route;
    //标签
      @Column(name="tag")
      private String tag;
      
      //创建时间
      @Column(name = "create_time")
      @DateTimeFormat(pattern="yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
      private Date createTime;


      public Integer getId() {
  		return id;
  	}

  	public void setId(Integer id) {
  		this.id = id;
  	}

  	public Route getRoute() {
  		return route;
  	}

  	public void setRoute(Route route) {
  		this.route = route;
  	}

  	public String getTag() {
  		return tag;
  	}

  	public void setTag(String tag) {
  		this.tag = tag;
  	}

  	public Date getCreateTime() {
  		return createTime;
  	}

  	public void setCreateTime(Date createTime) {
  		this.createTime = createTime;
  	}
   }
