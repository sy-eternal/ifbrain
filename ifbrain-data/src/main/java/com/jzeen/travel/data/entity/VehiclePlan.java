package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 交通规划
 */
@Entity
@Table(name = "t_vehicle_plan")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehiclePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 日期规划
     */
    @OneToOne(targetEntity = DatePlan.class)
    @JoinColumn(name = "date_plan_id")
    @JsonBackReference
    private DatePlan datePlan;

    /**
     * 交通方式
     */
    @Column(name = "vehicle_type_code")
    private Integer vehicleTypeCode;

    /**
     * 出发时间
     */
    @Column(name = "departure_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date departureTime;

    /**
     * 到达时间
     */
    @Column(name = "arrival_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date arrivalTime;


    /**
     * 航空公司
     */
    @Column(name = "airline", length = 100)
    private String airline;


    /**
     * 航班号
     */
    @Size(max = 20)
    @Column(name = "vehicle_number", length = 20)
    private String vehicleNumber;

    /**
     * 级别
     */
    @Size(max = 20)
    @Column(length = 20)
    private String rank;

    /**
     * 出发航（车）站
     */
    @Size(max = 20)
    @Column(name = "departure_terminal_id", length = 20)
    private String departureTerminalId;

    /**
     * 到达航（车）站
     */
    @Size(max = 20)
    @Column(name = "arrival_terminal_id", length = 20)
    private String arrivalTerminalId;

    /**
     * 机型
     */
    @Size(max = 20)
    @Column(name = "air_equip_type", length = 20)
    private String airEquipType;

    /**
     * 人数
     */
    @Column(name = "person_count")
    private Integer personCount;

    /**
     * 供应商价格规则
     */
    @ManyToOne(targetEntity = SupplierPriceRule.class)
    @JoinColumn(name = "supplier_price_rule_id")
    private SupplierPriceRule supplierPriceRule;

    /**
     * 供应商订单号
     */
    @Size(max = 200)
    @Column(name = "supplier_order_number", length = 200)
    private String supplierOrderNumber;

    /**
     * 成本价
     */
    @Column(name = "cost_price")
    private BigDecimal costPrice;

    /**
     * 销售价
     */
    @Column(name = "sale_price")
    private BigDecimal salePrice;

    /**
     * 出发机场
     */
    @Column(name = "start_airport")
    private String startAirport;

    /**
     * 到达机场
     */
    @Column(name = "arrive_airport")
    private String arriveAirport;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public String getStartAirport() {
        return startAirport;
    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public String getArriveAirport() {
        return arriveAirport;
    }

    public void setArriveAirport(String arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DatePlan getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(DatePlan datePlan) {
        this.datePlan = datePlan;
    }

    public Integer getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(Integer vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }


    public String getDepartureTerminalId() {
        return departureTerminalId;
    }

    public void setDepartureTerminalId(String departureTerminalId) {
        this.departureTerminalId = departureTerminalId;
    }

    public String getArrivalTerminalId() {
        return arrivalTerminalId;
    }

    public void setArrivalTerminalId(String arrivalTerminalId) {
        this.arrivalTerminalId = arrivalTerminalId;
    }

    public String getAirEquipType() {
        return airEquipType;
    }

    public void setAirEquipType(String airEquipType) {
        this.airEquipType = airEquipType;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public SupplierPriceRule getSupplierPriceRule() {
        return supplierPriceRule;
    }

    public void setSupplierPriceRule(SupplierPriceRule supplierPriceRule) {
        this.supplierPriceRule = supplierPriceRule;
    }

    public String getSupplierOrderNumber() {
        return supplierOrderNumber;
    }

    public void setSupplierOrderNumber(String supplierOrderNumber) {
        this.supplierOrderNumber = supplierOrderNumber;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
