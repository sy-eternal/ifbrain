package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_route_comment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class RouteComment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 线路id
     */
      @ManyToOne(targetEntity = Route.class)
      @JoinColumn(name = "route_id", updatable = true)
      private Route route;
      
	  @ManyToOne(targetEntity = User.class)
      @JoinColumn(name = "user_id", updatable = true)
      private User user;
     
      @Column(name="comment")
      private String comments;
      
      @Column(name="create_time")
      private Date createtime;
      

      
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

      

   }
