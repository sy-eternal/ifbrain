package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 24日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Entity
@Table(name = "t_wechat_redpack_activity")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WeChatRedpackActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 红包活动类型
     * 1为用户关注抢红包
     */
    @Column(name = "act_type")
    private String actType;


    /**
     * 微信号
     */
    @Column(name = "openid")
    private String openid;

    /**
     * 是否抢到红包
     * 1为抢到
     * 2为没有抢到
     */
    @Column(name = "result", length = 2)
    private Integer result;


    /**
     * 红包是否已经发放
     * 0为未发放
     * 1为已经发放
     */
    @Column(name = "send_flag", length = 2)
    private Integer sendFlag;


    /**
     * 点击时间
     */
    @Column(name = "click_time", length = 11)
    private Integer clickTime;

    /**
     * 参与时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "takepartin_time")
    private Date takepartinTime;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getClickTime() {
        return clickTime;
    }

    public void setClickTime(Integer clickTime) {
        this.clickTime = clickTime;
    }

    public Date getTakepartinTime() {
        return takepartinTime;
    }

    public void setTakepartinTime(Date takepartinTime) {
        this.takepartinTime = takepartinTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
    }
}