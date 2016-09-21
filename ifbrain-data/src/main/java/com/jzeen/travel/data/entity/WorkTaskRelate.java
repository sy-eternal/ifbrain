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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_work_task_relate")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WorkTaskRelate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	@ManyToOne(targetEntity = EasyTask.class)
    @JoinColumn(name = "easy_task_id")
    private EasyTask easyTask;
	
	@Column(name = "times_add")
    private Integer timeAdd;
	public Integer getTimeAdd() {
		return timeAdd;
	}

	public void setTimeAdd(Integer timeAdd) {
		this.timeAdd = timeAdd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public EasyTask getEasyTask() {
		return easyTask;
	}

	public void setEasyTask(EasyTask easyTask) {
		this.easyTask = easyTask;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	@Column(name = "times")
	private Integer times;
/*	@Column(name = "time_add")
	private Integer timeAdd;*/
	@Column(name = "status")
	private Integer status;
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "start_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date createTime;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(targetEntity = Task.class)
    @JoinColumn(name = "task_id")
    private Task task;
	
	@ManyToOne(targetEntity = Child.class)
	@JoinColumn(name = "child_id")
	private Child child;
	//每次完成的金额
	@Column(name = "every_time_money")
	private BigDecimal everytimesmoney;
	public BigDecimal getEverytimesmoney() {
		return everytimesmoney;
	}

	public void setEverytimesmoney(BigDecimal everytimesmoney) {
		this.everytimesmoney = everytimesmoney;
	}
	
	
/*	
	//0未分配 1已分配
		 @Transient
		 private Integer workstatus;

		public Integer getWorkstatus() {
			return workstatus;
		}

		public void setWorkstatus(Integer workstatus) {
			this.workstatus = workstatus;
		}*/
		 
}
