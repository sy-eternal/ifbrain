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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name="t_define_task")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class DefineTask {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;
	//儿童主键
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name = "child_id")
	private Child child;	
	//任务名称
	@Column(name = "task_name")
	private String taskName;
	//任务次数
		@Column(name = "task_times")
		private Integer taskTimes;
		//难易程度
		@OneToOne(targetEntity = EasyTask.class)
	    @JoinColumn(name = "easy_task_id")
	    private EasyTask easyTaskId;
		//完成次数
		@Column(name = "times_add")
		private Integer timesAdd;
		//代币数
		@Column(name = "token_numbers")
		private Integer tokennumbers;
		//任务状态
		@Column(name = "task_status")
		private Integer taskstatus;
		
		//开始时间
		@Column(name = "start_time")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date startTime;
		//修改时间		
		@Column(name = "update_time")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date updateTime;
		
		//完成次数和总次数的百分比
		@Transient
		private String rate;
		@Transient
		private String IntegerRate;
		
		public String getIntegerRate() {
			return IntegerRate;
		}
		public void setIntegerRate(String integerRate) {
			IntegerRate = integerRate;
		}
		public String getRate() {
			return rate;
		}
		public void setRate(String rate) {
			this.rate = rate;
		}
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Child getChild() {
			return child;
		}
		public void setChild(Child child) {
			this.child = child;
		}
		public String getTaskName() {
			return taskName;
		}
		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}
		public Integer getTaskTimes() {
			return taskTimes;
		}
		public void setTaskTimes(Integer taskTimes) {
			this.taskTimes = taskTimes;
		}
		public EasyTask getEasyTaskId() {
			return easyTaskId;
		}
		public void setEasyTaskId(EasyTask easyTaskId) {
			this.easyTaskId = easyTaskId;
		}
		public Integer getTimesAdd() {
			return timesAdd;
		}
		public void setTimesAdd(Integer timesAdd) {
			this.timesAdd = timesAdd;
		}
		public Integer getTokennumbers() {
			return tokennumbers;
		}
		public void setTokennumbers(Integer tokennumbers) {
			this.tokennumbers = tokennumbers;
		}
		public Integer getTaskstatus() {
			return taskstatus;
		}
		public void setTaskstatus(Integer taskstatus) {
			this.taskstatus = taskstatus;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
	
	
	
	
	
	/*@Transient
	private Integer sumTimes;
	@Transient
	private Integer sumTimesAdd;*/
    
	
	
	
}
