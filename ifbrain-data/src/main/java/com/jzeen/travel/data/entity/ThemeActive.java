package com.jzeen.travel.data.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_theme")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ThemeActive {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @NotEmpty
    @Column(name = "theme")
    private String theme;


    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;


    @OneToOne(targetEntity = Image.class)
    @JoinColumn(name = "picture_id")
    private Image image;


    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SpotThemeRelate> spotRelates;


    public int getId() {
        return id;
    }


    public String getTheme() {
        return theme;
    }


    public void setTheme(String theme) {
        this.theme = theme;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<SpotThemeRelate> getSpotRelates() {
        return spotRelates;
    }

    public void setSpotRelates(List<SpotThemeRelate> spotRelates) {
        this.spotRelates = spotRelates;
    }
}
