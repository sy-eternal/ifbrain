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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 规划订单表
 */
@Entity
@Table(name="t_plan_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class PlanOrder {
	 	/**
	 	 * 自增ID
	 	 */
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;
	 	
		/**
		 * 订单ID
		 */
	 	@OneToOne(targetEntity = Order.class)
	    @JoinColumn(name = "order_id")
	    private Order order;
	 	
	 	/**
	 	 * 规划主题名称
	 	 */
	 	@Column(name = "plan_offer_name")
	 	private String planOfferName;
	 	
	 	/**
	 	 * 规划费用
	 	 */
	 	@Column(name="amount")
	 	private BigDecimal amount;
	 	
	 	/**
	 	 * 创建时间
	 	 */
	 	@Column(name = "createtime")
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	    private Date createTime;

	 	
	 	
	       /**
         * 订单服务费
         * @return
         */
    @Column(name = "service_amount")
    private BigDecimal  serviceAmount;
    

    
    
    public BigDecimal getServiceAmount()
        {
            return serviceAmount;
        }


        public void setServiceAmount(BigDecimal serviceAmount)
        {
            this.serviceAmount = serviceAmount;
        }

	 	
	 	
	 	
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public String getPlanOfferName() {
			return planOfferName;
		}

		public void setPlanOfferName(String planOfferName) {
			this.planOfferName = planOfferName;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	 	
	 	
	 	
}
