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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_ifbrain_index")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class IfbrainIndex {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	//课程级别
	@Column(name="course_level")
	private String courseLevel;
	//班级
	@Column(name="class_id")
	private Integer classId;
	//课程序数
	@Column(name="ordinal_number")
	private Integer ordinalNumber;
	
	//课序名称
	@Column(name="lesson_name")
	private String lessonName;
	
	//孩子id	
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name = "child_id", updatable = true)
	private Child child;
	
	
	@OneToMany(mappedBy = "ifbrainIndex",cascade={CascadeType.REMOVE})
	private List<IfbrainVar> ifbrainVar;
	
	@OneToMany(mappedBy = "ifbrainIndex",cascade={CascadeType.REMOVE})
	private List<Comment> comment;
	// 创建时间
	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;
/*	
	   //课堂训练收入 inclass_income
		@Column(name="inclass_income")
		private String inclassIncome;*/
/*		
		//收入类型 income_type 0,知识 1,应用
		@Column(name="income_type")
		private Integer incomeType;*/
		//期初财脑指数 primarys_ifbrainindex
		@Column(name="primarys_ifbrainindex")
		private Integer primarysIfbrainindex;
		//课后训练收入 afterclass_income
		@Column(name="afterclass_income")
		private Integer afterclassIncome;
		//消费支出  expense
		@Column(name="expense")
		private BigDecimal expense;
		//财脑指数 ifbrain_index
		@Column(name="ifbrain_index")
		private Integer ifbrainIndex;
		//收入
		@Column(name="income")
		private BigDecimal income;
		
		//知识总分
		@Column(name="var_knowledgetotalscore")
		 private String knowledgetotalScore;
		//应用总分
		@Column(name="var_applicationtotalscore")
		private String applicationtotalScore;
			 
			 
		//代币余额
		@Column(name="balance")
		private BigDecimal balance;
	  
		
		
		//课后代币收入
		@Column(name="bank_afterclass_income")
		private Integer bankAfterclassIncome;
		
		//补充代币
		@Column(name="supplementary_tokens")
		private Integer supplementaryTokens;
		//代币初期
	/*	@Column(name="bank_primarys_ifbrainindex")
		private Integer bankPrimarysIfbrainindex;*/
	
	/*public Integer getBankPrimarysIfbrainindex() {
			return bankPrimarysIfbrainindex;
		}
		public void setBankPrimarysIfbrainindex(Integer bankPrimarysIfbrainindex) {
			this.bankPrimarysIfbrainindex = bankPrimarysIfbrainindex;
		}*/
		
		public Integer getBankAfterclassIncome() {
			return bankAfterclassIncome;
		}
	
		public List<Comment> getComment() {
			return comment;
		}
	
		public void setComment(List<Comment> comment) {
			this.comment = comment;
		}
		public void setBankAfterclassIncome(Integer bankAfterclassIncome) {
			this.bankAfterclassIncome = bankAfterclassIncome;
		}
		public Integer getSupplementaryTokens() {
			return supplementaryTokens;
		}
		public void setSupplementaryTokens(Integer supplementaryTokens) {
			this.supplementaryTokens = supplementaryTokens;
		}
	public BigDecimal getBalance() {
			return balance;
		}
		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}
	public Integer getClassId() {
			return classId;
		}
		public void setClassId(Integer classId) {
			this.classId = classId;
		}
	public String getKnowledgetotalScore() {
				return knowledgetotalScore;
			}
			public void setKnowledgetotalScore(String knowledgetotalScore) {
				this.knowledgetotalScore = knowledgetotalScore;
			}
			public String getApplicationtotalScore() {
				return applicationtotalScore;
			}
			public void setApplicationtotalScore(String applicationtotalScore) {
				this.applicationtotalScore = applicationtotalScore;
			}
	public List<IfbrainVar> getIfbrainVar() {
			return ifbrainVar;
		}
		public void setIfbrainVar(List<IfbrainVar> ifbrainVar) {
			this.ifbrainVar = ifbrainVar;
		}
	public BigDecimal getIncome() {
			return income;
		}
		public void setIncome(BigDecimal income) {
			this.income = income;
		}
	
		public Integer getPrimarysIfbrainindex() {
			return primarysIfbrainindex;
		}
		public void setPrimarysIfbrainindex(Integer primarysIfbrainindex) {
			this.primarysIfbrainindex = primarysIfbrainindex;
		}
		public Integer getAfterclassIncome() {
			return afterclassIncome;
		}
		public void setAfterclassIncome(Integer afterclassIncome) {
			this.afterclassIncome = afterclassIncome;
		}
		
		public BigDecimal getExpense() {
			return expense;
		}
		public void setExpense(BigDecimal expense) {
			this.expense = expense;
		}
		public Integer getIfbrainIndex() {
			return ifbrainIndex;
		}
		public void setIfbrainIndex(Integer ifbrainIndex) {
			this.ifbrainIndex = ifbrainIndex;
		}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
