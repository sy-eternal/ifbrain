package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_spot_theme_relate")
public class SpotThemeRelate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = Spot.class)
    @JoinColumn(name = "spot_pk", updatable = true)
    private Spot spot;

    @ManyToOne(targetEntity = ThemeActive.class)
    @JoinColumn(name = "theme_pk", updatable = true)
    private ThemeActive theme;

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public ThemeActive getTheme() {
        return theme;
    }

    public void setTheme(ThemeActive theme) {
        this.theme = theme;
    }

    // 创建时间
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
