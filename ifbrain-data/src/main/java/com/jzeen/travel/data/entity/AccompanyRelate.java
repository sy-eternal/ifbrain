package com.jzeen.travel.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "t_accompany_relate")
public class AccompanyRelate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "order_pk")
    private Integer orderPk;

    /**
     * 增加订单对象
     * add by limin 20150703
     */
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_pk", updatable = false, insertable = false)
    private Order order;

    @Column(name = "accompany_type_pk")
    private Integer accompanyTypePk;

    @Column(name = "create_time")
    private Date createTime;

    @NotEmpty(message = "随行人员类型不能为空。")
    @Size(max = 200)
    @Column(name = "accompany_type")
    private String accompanyType;

    /**
     * 增加随行类型人员对象
     * add by limin 20150703
     */
    @ManyToOne(targetEntity = AccompanyType.class)
    @JoinColumn(name = "accompany_type_pk", updatable = false, insertable = false)
    private AccompanyType accompany;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderPk() {
        return orderPk;
    }

    public void setOrderPk(Integer orderPk) {
        this.orderPk = orderPk;
    }

    public Integer getAccompanyTypePk() {
        return accompanyTypePk;
    }

    public void setAccompanyTypePk(Integer accompanyTypePk) {
        this.accompanyTypePk = accompanyTypePk;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccompanyType() {
        return accompanyType;
    }

    public void setAccompanyType(String accompanyType) {
        this.accompanyType = accompanyType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public AccompanyType getAccompany() {
        return accompany;
    }

    public void setAccompany(AccompanyType accompany) {
        this.accompany = accompany;
    }
}
