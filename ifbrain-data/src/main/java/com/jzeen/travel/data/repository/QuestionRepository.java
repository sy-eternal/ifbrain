package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Question;



public interface QuestionRepository extends AdvancedJpaRepository<Question, Integer>
{
	 
	 @Query("select max(q.ordernumber)  from Question q inner join  q.questiontype type where type.id =:id" )
	 public Integer findmaxordernumber(@Param("id")Integer id);
	 @Modifying 
	 @Query("update Question q set  q.ordernumber = q.ordernumber-1 where q.ordernumber > :ordernumber" )
	  public void  updateordernumber(@Param("ordernumber")Integer ordernumber);
	
	 List<Question>  findByQuestiontypeId(Integer id);
	 
	  //获得选中试卷中的试题
	  @Query("select q  from ItemManagementQuestion iq inner join iq.item i inner join iq.question q  where i.exam.id =:id order by iq.ordernumber" )
	  public List<Question> questionlist(@Param("id")Integer id);
	
	  
	  @Query("select q  from ItemManagementQuestion iq inner join iq.item i inner join iq.question q  where i.exam.id =:id order by iq.ordernumber asc " )
	  public List<Question> questionlist1(@Param("id")Integer id);
	 
}
