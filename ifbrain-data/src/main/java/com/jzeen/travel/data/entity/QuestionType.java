package com.jzeen.travel.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_question_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class QuestionType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	 //题型名称
    @Column(name="question_type_name")
    private String questionTypeName;
		 //序号
	    @Column(name="ordernumber")
	    private int orderNumber;
	    //模板类型
	    @ManyToOne(targetEntity = ItemTemplate.class)
		@JoinColumn(name = "itemtemplate_id")
		private ItemTemplate itemTemplate;
		  		
		  		
	    
	    @OneToMany(mappedBy = "questiontype", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	   	private List<Question> question;

	

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getQuestionTypeName() {
			return questionTypeName;
		}

		public void setQuestionTypeName(String questionTypeName) {
			this.questionTypeName = questionTypeName;
		}

		

		public int getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(int orderNumber) {
			this.orderNumber = orderNumber;
		}

		public ItemTemplate getItemTemplate() {
			return itemTemplate;
		}

		public void setItemTemplate(ItemTemplate itemTemplate) {
			this.itemTemplate = itemTemplate;
		}

		public List<Question> getQuestion() {
			return question;
		}

		public void setQuestion(List<Question> question) {
			this.question = question;
		}

		

	
	



	
	
	
}
