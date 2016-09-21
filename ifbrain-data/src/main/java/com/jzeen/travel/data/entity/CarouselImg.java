package com.jzeen.travel.data.entity;

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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;


@Entity
@Table(name="t_carousel_img")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CarouselImg
{
	     @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;

	    /**
	     * 文件名称
	     */
	    @Size(max = 100)
	    @Column(name = "file_name")
	    private String fileName;

	    /**
	     * 文件路径
	     */
	    @Size(max = 200)
	    @Column(name = "file_path")
	    private String filePath;

	    /**
	     * 创建时间
	     */
	    @Column(name = "create_time")
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	    private Date createTime;
	
	  //序号 
	    @Column(name = "sortnumber")
	    private Integer sortnumber;
	    //内容
	    @Column(name = "content")
	    private String content;
	    
		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Integer getSortnumber() {
			return sortnumber;
		}

		public void setSortnumber(Integer sortnumber) {
			this.sortnumber = sortnumber;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

	

		
	
    
}
