package com.jzeen.travel.data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
//题型内容
@Entity
@Table(name = "t_item_management")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ItemManagement {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	 //题型标题
    @Column(name="title")
    private String title;
    //每题的分数
    @Column(name="perscore")
    private String perscore;
    //题型序号
    @Column(name="ordernumber")
    private int ordernumber;
    //题型说明
    @Column(name="item_description")
    private String itemdescription;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    @ManyToOne(targetEntity = QuestionType.class)
	@JoinColumn(name = "questiontype_id", updatable = true)
	private QuestionType questiontype;
    @ManyToOne(targetEntity = Exam.class)
	@JoinColumn(name = "exam_id", updatable = true)
	private Exam exam;
    
    @OneToMany(mappedBy="item",cascade=CascadeType.REMOVE)
    private List<ItemManagementQuestion> itemManagementList;

	public List<ItemManagementQuestion> getItemManagementList() {
		return itemManagementList;
	}
	public void setItemManagementList(
			List<ItemManagementQuestion> itemManagementList) {
		this.itemManagementList = itemManagementList;
	}
	public int getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public QuestionType getQuestiontype() {
		return questiontype;
	}
	public void setQuestiontype(QuestionType questiontype) {
		this.questiontype = questiontype;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPerscore() {
		return perscore;
	}
	public void setPerscore(String perscore) {
		this.perscore = perscore;
	}
	public String getItemdescription() {
		return itemdescription;
	}
	public void setItemdescription(String itemdescription) {
		this.itemdescription = itemdescription;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
}
