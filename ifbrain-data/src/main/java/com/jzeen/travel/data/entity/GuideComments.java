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
@Table(name = "t_guide_comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideComments {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 导游id
     */
      @ManyToOne(targetEntity = Guide.class)
      @JoinColumn(name = "guide_id", updatable = true)
      private Guide guide;
      
      @ManyToOne(targetEntity = User.class)
      @JoinColumn(name = "user_id", updatable = true)
      private User user;
     
      @Column(name="comments")
      private String comments;
      
      @Column(name="createtime")
      private Date createtime;
      

      public Integer getId() {
  		return id;
  	}

  	public void setId(Integer id) {
  		this.id = id;
  	}

  	public Guide getGuide() {
  		return guide;
  	}

  	public void setGuide(Guide guide) {
  		this.guide = guide;
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
