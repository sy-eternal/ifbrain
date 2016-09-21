package com.jzeen.travel.data.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
//题库
@Entity
@Table(name = "t_question")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	 //试题题目内容
    @Column(name="question_name_content")
    private String questionNameContent;
    //试题选项内容
    @Column(name="question_option_content")
    private String questionOptionContent;
    //试题难易度
    @Column(name="test_difficulty")
    private String testdifficulty;
    //试题答案
    @Column(name="answer")
    private String answer;
    //试题解析
    @Column(name="explaination")
    private String explaination;
    //试题序号
    @Column(name="ordernumber")
    private int ordernumber;
   
    @OneToOne(targetEntity = QuestionOptionImage.class)
	@JoinColumn(name = "question_option_image_id", updatable = true)
	private QuestionOptionImage questionOptionImage;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    
    /**
     * 正确率
     */
    @Transient
    private String rate;
    
    @ManyToOne(targetEntity = QuestionType.class)
	@JoinColumn(name = "questiontype_id", updatable = true)
	private QuestionType questiontype;

    @OneToMany(mappedBy="question",cascade=CascadeType.REMOVE)
    private List<ItemManagementQuestion> itemManagementList;
    
    
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

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

	public String getExplaination() {
		return explaination;
	}

	public void setExplaination(String explaination) {
		this.explaination = explaination;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestionNameContent() {
		return questionNameContent;
	}

	public void setQuestionNameContent(String questionNameContent) {
		this.questionNameContent = questionNameContent;
	}

	public String getTestdifficulty() {
		return testdifficulty;
	}

	public void setTestdifficulty(String testdifficulty) {
		this.testdifficulty = testdifficulty;
	}

	public QuestionType getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(QuestionType questiontype) {
		this.questiontype = questiontype;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionOptionContent() {
		return questionOptionContent;
	}

	public void setQuestionOptionContent(String questionOptionContent) {
		this.questionOptionContent = questionOptionContent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public QuestionOptionImage getQuestionOptionImage() {
		return questionOptionImage;
	}

	public void setQuestionOptionImage(QuestionOptionImage questionOptionImage) {
		this.questionOptionImage = questionOptionImage;
	}
   
}
