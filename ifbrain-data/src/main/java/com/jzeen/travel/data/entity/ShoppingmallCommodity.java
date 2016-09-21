package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@Entity
@Table(name = "t_shoppingmall_commodity")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShoppingmallCommodity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    //商品类型
	@ManyToOne(targetEntity = CommodityMall.class)
	@JoinColumn(name = "commoditytype_id", updatable = true)
	private CommodityMall commodityMall;
	
	//需求层次
	@ManyToOne(targetEntity = DemandLevel.class)
	@JoinColumn(name = "demand_id", updatable = true)
	private DemandLevel demandLevel;
	//商品图片
	/*@OneToOne(targetEntity = ShoppingmallCommodityImage.class)
	@JoinColumn(name = "image_id", updatable = true)
	private ShoppingmallCommodityImage image;*/
	//商品图片
	 @OneToMany(mappedBy = "shoppingmallCommodity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	  @JsonIgnore
	  private List<ShoppingmallCommodityImage> shoppingmallCommodityImage;
	
   //商品价格
	@Column(name = "price")
	private BigDecimal price;
    //商品描述
	@Column(name = "description")
	private String description;
	 //库存数量
		@Column(name = "commodity_quantity")
		private Integer commodityQuantity;
		 //商品名称
		@Column(name = "commodity_name")
		private String commodityName;
		
		// 创建时间
		@Column(name = "create_time")
		@DateTimeFormat(pattern = "yyyy-MM-dd ")
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		private Date createTime;
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	
		public List<ShoppingmallCommodityImage> getShoppingmallCommodityImage() {
			return shoppingmallCommodityImage;
		}

		public void setShoppingmallCommodityImage(
				List<ShoppingmallCommodityImage> shoppingmallCommodityImage) {
			this.shoppingmallCommodityImage = shoppingmallCommodityImage;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getCommodityName() {
			return commodityName;
		}

		public void setCommodityName(String commodityName) {
			this.commodityName = commodityName;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public CommodityMall getCommodityMall() {
			return commodityMall;
		}

		public void setCommodityMall(CommodityMall commodityMall) {
			this.commodityMall = commodityMall;
		}

		public DemandLevel getDemandLevel() {
			return demandLevel;
		}

		public void setDemandLevel(DemandLevel demandLevel) {
			this.demandLevel = demandLevel;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public Integer getCommodityQuantity() {
			return commodityQuantity;
		}

		public void setCommodityQuantity(Integer commodityQuantity) {
			this.commodityQuantity = commodityQuantity;
		}
	

	

}