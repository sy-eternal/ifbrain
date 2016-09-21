package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Title: IFBRAIN
 * Description:
 * Date: 2015年 12月 02日
 * CopyRight (c) 2015 IFBRAIN
 *
 * @Author sunyan.sunny
 */
@Entity
@Table(name = "t_wechat_user")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WeChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id", length = 11)
    private Integer userId;

    /**
     * 用户的标识，对当前公众号唯一
     */
    @Column(name = "openid")
    private String openid;


    /**
     * 用户的昵称
     */
    @Column(name = "nickname")
    private String nickname;


    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    @Column(name = "headimgurl")
    private String headimgurl;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
     */
    @Column(name = "unionid")
    private String unionid;

    /**
     * 用户所在的分组ID
     */
    @Column(name = "groupid", length = 11)
    private Integer groupid;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @Column(name = "sex", length = 11)
    private Integer sex;

    /**
     * 用户所在国家
     */
    @Column(name = "country")
    private String country;

    /**
     * 用户所在省份
     */
    @Column(name = "province")
    private String province;


    /**
     * 用户所在城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    @Column(name = "language")
    private String language;


    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    @Column(name = "subscribe", length = 11)
    private Integer subscribe;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间。
     */
    @Column(name = "subscribe_time", length = 11)
    private Integer subscribe_time;

    /**
     * 首次关注时间。
     */
    @Column(name = "first_subscribe_time", length = 11)
    private Integer first_subscribe_time;

    /**
     * 取消时间，多次取消，取最近一次取消时间。
     */
    @Column(name = "cancel_time", length = 11)
    private Integer cancel_time;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    @Column(name = "remark")
    private String remark;


    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date create_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public Integer getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Integer subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public Integer getFirst_subscribe_time() {
        return first_subscribe_time;
    }

    public void setFirst_subscribe_time(Integer first_subscribe_time) {
        this.first_subscribe_time = first_subscribe_time;
    }

    public Integer getCancel_time() {
        return cancel_time;
    }

    public void setCancel_time(Integer cancel_time) {
        this.cancel_time = cancel_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}