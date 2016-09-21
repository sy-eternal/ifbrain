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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//题库
@Entity
@Table(name = "t_exam")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Exam {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	 //试卷名称
    @Column(name="exam_name")
    private String examName;
    //学分要求
    @Column(name="credits_required")
    private String creditsRequired;
    //考试时间
    @Column(name="exam_time")
    private String examTime;
    //试卷说明
    @Column(name="exam_description")
    private String examDescription;
    //试卷状态  0是未发布,1是已发布,2是撤销.
    @Column(name="status")
    private String status;
    //哪一个单元的考试
    @Column(name="unit")
    private String unit;
    //试卷难易度
    @Column(name="test_difficulty")
    private String testdifficulty;
    
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    
    @ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "course_id", updatable = true)
	private Course course;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   	private List<ItemManagement> itemManagement;
    //试卷价格
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    
    
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<ItemManagement> getItemManagement() {
		return itemManagement;
	}

	public void setItemManagement(List<ItemManagement> itemManagement) {
		this.itemManagement = itemManagement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTestdifficulty() {
		return testdifficulty;
	}

	public void setTestdifficulty(String testdifficulty) {
		this.testdifficulty = testdifficulty;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getCreditsRequired() {
		return creditsRequired;
	}

	public void setCreditsRequired(String creditsRequired) {
		this.creditsRequired = creditsRequired;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	

	

	


   
}
