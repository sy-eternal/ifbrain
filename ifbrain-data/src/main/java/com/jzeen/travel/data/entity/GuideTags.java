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
@Table(name = "t_guide_tags")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideTags {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 导游id
     */
      @ManyToOne(targetEntity = Guide.class)
      @JoinColumn(name = "guide_id", updatable = true)
      private Guide guide;
    //标签
      @Column(name="tag")
      private String tag;
      
      //创建时间
      @Column(name = "createtime")
      @DateTimeFormat(pattern="yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
      private Date createTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Guide getGuide()
    {
        return guide;
    }

    public void setGuide(Guide guide)
    {
        this.guide = guide;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
      
   }
