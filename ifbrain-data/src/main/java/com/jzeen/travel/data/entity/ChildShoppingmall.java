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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//l孩子商城购物表
@Entity
@Table(name = "t_child_shoppingmall")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ChildShoppingmall {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//孩子id	
		@ManyToOne(targetEntity = Child.class)
		@JoinColumn(name = "child_id", updatable = true)
		private Child child;
		//购买状态//0未购买，1已购买
		@Column(name="pay_status")
		private Integer payStatus;
		//总价
		@Column(name="sum_price")
		private BigDecimal sumPrice;
		
		@ManyToOne(targetEntity = ShoppingmallCommodity.class)
		@JoinColumn(name = "commodity_id", updatable = true)
		private ShoppingmallCommodity shoppingmallCommodity;
		
		//心愿商品名称
		@Column(name = "commodity_name")
		private String commodityName;
		 //心愿商品价格
		@Column(name = "price")
		private BigDecimal price;
		//购买商品所花代币
		@Column(name="result")
		private String result;
		//各个需求所占的百分比
		@Transient
		private String percent;
		//需求名称
		@Transient
		private String demandname;
		// 创建时间
		@Column(name = "create_time")
		@DateTimeFormat(pattern = "yyyy-MM-dd ")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		private Date createTime;
		//级别
		@Column(name="course_level")
		private String courseLevel;
		//第几节课
		@Column(name="ordinal_number")
		private Integer ordinalNumber;
		@Column(name="quantity")
		private Integer quantity;
		//类型  0心愿商品	1时购买的商品
		@Column(name="type")
		private String type;
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public String getCommodityName() {
			return commodityName;
		}
		public void setCommodityName(String commodityName) {
			this.commodityName = commodityName;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

		public String getCourseLevel() {
			return courseLevel;
		}
		public void setCourseLevel(String courseLevel) {
			this.courseLevel = courseLevel;
		}
		public Integer getOrdinalNumber() {
			return ordinalNumber;
		}
		public void setOrdinalNumber(Integer ordinalNumber) {
			this.ordinalNumber = ordinalNumber;
		}
		
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public String getDemandname() {
			return demandname;
		}
		public void setDemandname(String demandname) {
			this.demandname = demandname;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Child getChild() {
			return child;
		}
		public void setChild(Child child) {
			this.child = child;
		}
		public Integer getPayStatus() {
			return payStatus;
		}
		public void setPayStatus(Integer payStatus) {
			this.payStatus = payStatus;
		}
		public BigDecimal getSumPrice() {
			return sumPrice;
		}
		public void setSumPrice(BigDecimal sumPrice) {
			this.sumPrice = sumPrice;
		}
		public ShoppingmallCommodity getShoppingmallCommodity() {
			return shoppingmallCommodity;
		}
		public void setShoppingmallCommodity(ShoppingmallCommodity shoppingmallCommodity) {
			this.shoppingmallCommodity = shoppingmallCommodity;
		}
		public String getPercent() {
			return percent;
		}
		public void setPercent(String percent) {
			this.percent = percent;
		}
	
		
}
