package com.jzeen.travel.data.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "t_inner_city_traffic_relate")
public class InnerCityTrafficRelate {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "order_pk")
    private Integer orderPk;


    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "remark")
    private String remark;

    @Column(name = "inner_city_traffic")
    private String innerCityTraffic;

    /**
     * 增加订单对象
     * add by limin 20150630
     */
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_pk", updatable = false, insertable = false)
    private Order order;

    @Column(name = "complete_itenary")
    private Integer completeItenary;

    public Integer getOrderPk()
    {
        return orderPk;
    }

    public void setOrderPk(Integer orderPk)
    {
        this.orderPk = orderPk;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInnerCityTraffic() {
        return innerCityTraffic;
    }

    public void setInnerCityTraffic(String innerCityTraffic) {
        this.innerCityTraffic = innerCityTraffic;
    }

    public Integer getCompleteItenary() {
        return completeItenary;
    }

    public void setCompleteItenary(Integer completeItenary) {
        this.completeItenary = completeItenary;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
