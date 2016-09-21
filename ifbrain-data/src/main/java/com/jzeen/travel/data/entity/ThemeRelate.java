package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_theme_relate")
public class ThemeRelate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_pk", updatable = false)
    private Order order;

    @ManyToOne(targetEntity = Theme.class)
    @JoinColumn(name = "theme_pk", updatable = false)
    private Theme theme;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "define_theme")
    private String defineTheme;
    
    @Column(name="theme_name")
    private String themeName;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Theme getTheme()
    {
        return theme;
    }

    public void setTheme(Theme theme)
    {
        this.theme = theme;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getDefineTheme()
    {
        return defineTheme;
    }

    public void setDefineTheme(String defineTheme)
    {
        this.defineTheme = defineTheme;
    }

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
    
}
