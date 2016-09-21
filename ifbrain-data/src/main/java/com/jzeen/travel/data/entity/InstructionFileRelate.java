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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@Table(name = "t_visa_process_doc_relate")
@JsonIdentityInfo(generator = JSOGGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InstructionFileRelate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //文件主键
    @ManyToOne(targetEntity = Document.class)
    @JoinColumn(name = "document_id", updatable = true)
    private Document document;
    
    //流程
    @ManyToOne(targetEntity = VisaInstruction.class)
    @JoinColumn(name = "process_id", updatable = true)
    private VisaInstruction visainstruction;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    

    public Document getDocument()
    {
        return document;
    }

    public void setDocument(Document document)
    {
        this.document = document;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public VisaInstruction getVisainstruction()
    {
        return visainstruction;
    }

    public void setVisainstruction(VisaInstruction visainstruction)
    {
        this.visainstruction = visainstruction;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }

    
    
}
