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
@Table(name = "t_wechat_redpack_sent")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WeChatRedpackSent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 商户订单号
     */
    @Column(name = "mch_billno")
    private String mchBillno;

    /**
     * 商户ID
     */
    @Column(name = "mch_id")
    private String mchId;

    /**
     * 活动名称
     */
    @Column(name = "act_name")
    private String actName;

    /**
     * 用户微信号
     */
    @Column(name = "re_openid")
    private String reOpenid;


    /**
     * 红包金额
     */
    @Column(name = "total_amount")
    private Integer totalAmount;


    /**
     * 发送时间
     */
    @Column(name = "send_time")
    private Integer sendTime;

    /**
     * 订单号
     */
    @Column(name = "send_listid")
    private String sendListid;

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

    public String getMchBillno() {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getReOpenid() {
        return reOpenid;
    }

    public void setReOpenid(String reOpenid) {
        this.reOpenid = reOpenid;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getSendTime() {
        return sendTime;
    }

    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendListid() {
        return sendListid;
    }

    public void setSendListid(String sendListid) {
        this.sendListid = sendListid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}