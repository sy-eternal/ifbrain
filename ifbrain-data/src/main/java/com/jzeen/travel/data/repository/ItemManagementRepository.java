package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ItemManagement;
import com.jzeen.travel.data.entity.Question;



public interface ItemManagementRepository extends AdvancedJpaRepository<ItemManagement, Integer>
{
	public List<ItemManagement> findByExamId(Integer id);
	
	@Query("select  q from ItemManagementQuestion iq inner join  iq.item i inner join iq.question q where i.id =:id" )
	  public List<Question> findquestion(@Param("id")Integer id);
	
	public List<ItemManagement> findByExamIdAndQuestiontypeId(Integer ExamId,Integer QuestiontypeId);

}
