package com.jzeen.travel.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_menu")
public class Menu
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "url")
    private String url;

    @Column(name = "state")
    private String state;

    @Min(0)
    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonIgnore
    @ManyToMany(mappedBy = "menus")
    private List<Roles> roles;

   
    
    /**
     * 链接跳转属性。
     */
    @Transient
    public String getTarget()
    {
        return "_self";
    }
    
    /**
     * 树形默认全部展开
     */
   @Transient
   public String getOpen(){
       return "false";
   }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public Integer getDisplayOrder()
    {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder)
    {
        this.displayOrder = displayOrder;
    }

    @JsonFormat(pattern = "YY-MM-DD HH:mm:ss")
    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public List<Roles> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Roles> roles)
    {
        this.roles = roles;
    }

    @Override
    public String toString()
    {
        return "Menu [id=" + id + ", name=" + name + ", parentId=" + parentId + "]";
    }

}
