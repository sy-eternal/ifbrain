package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ExamScores;

public interface ExamScoresRepository extends AdvancedJpaRepository<ExamScores, Integer>
{
	@Query("select e from ExamScores as e inner join e.itemManagementQuestion as i inner join i.question as q where q.answer =:answer and q.id =:id and i.ordernumber =:ordernumber")
	ExamScores findByAnswer(@Param("answer") String answer,@Param("id") Integer id,@Param("ordernumber") Integer ordernumber);
	
	
	
	@Query("select e from ExamScores as e inner join e.itemManagementQuestion as i inner join e.child  as c where i.id=:id and e.ordinalnumber =:ordinalnumber and c.id=:child")
	ExamScores findByExamScores(@Param("id") Integer id,@Param("ordinalnumber") Integer ordinalnumber,@Param("child") Integer child);

	
	@Query("select e from ExamScores as e inner join e.itemManagementQuestion as i  inner join i.item as it where  it.id =:itid")
	List <ExamScores> findByExamSumScores(@Param("itid") Integer itid);
	
	
	
	@Query("select e from ExamScores as e inner join e.itemManagementQuestion as i inner join i.item as it inner join e.child  as c  where c.id =:childId and it.id =:itid and e.answerStatus =:answerStatus")
	List<ExamScores> findByChildExamSumScores(@Param("childId") Integer childId,@Param("itid") Integer itid,@Param("answerStatus") Integer answerStatus);
	
	//获得作答的总人数
	@Query("select count(e) from ExamSumScores as e where e.exam.id =:examid")
	Integer  anylysisallsize(@Param("examid") Integer examid);
	
	 //获得每一道题做正确的人数
	@Query("select count(e) from ExamScores as e where e.itemManagementQuestion.item.exam.id =:examid and e.itemManagementQuestion.question.id =:questionid and e.answerStatus=1")
	Integer  anylysissuresize(@Param("examid") Integer examid,@Param("questionid") Integer questionid);
	
	
	@Query("select e from ExamScores as e inner join e.itemManagementQuestion as i inner join i.item as it inner join e.child  as c  where c.id =:childId and it.id =:itid ")
	List<ExamScores> findByChildExamSum(@Param("childId") Integer childId,@Param("itid") Integer itid);
	//根据用户节课获取相应信息
	@Query("select e from ExamScores as e inner join e.itemManagementQuestion as i inner join e.user  as u where i.id=:id and e.ordinalnumber =:ordinalnumber and u.id=:userid")
	ExamScores findByExamUserScores(@Param("id") Integer id,@Param("ordinalnumber") Integer ordinalnumber,@Param("userid") Integer userid);
}
