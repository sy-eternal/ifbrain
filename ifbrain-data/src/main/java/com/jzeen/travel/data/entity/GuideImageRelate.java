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
@Table(name = "t_guide_image_relate")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class GuideImageRelate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //导游
    @ManyToOne(targetEntity = Guide.class)
    @JoinColumn(name = "guide_id")
    private Guide guide;
    
    
    @ManyToOne(targetEntity = Image.class)
    @JoinColumn(name = "image_id", updatable = true)
    private Image image;
    //类型
   /* 1：身份证或护照扫描件
    2：驾照扫描件
    3：导游封面照
    4：当地风景照
    5：个人生活照*/
    @Column(name="type")
    private int type;
    //创建时间
    @Column(name = "create_time", length = 20)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createtime;
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
    public Image getImage()
    {
        return image;
    }
    public void setImage(Image image)
    {
        this.image = image;
    }
    public int getType()
    {
        return type;
    }
    public void setType(int type)
    {
        this.type = type;
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