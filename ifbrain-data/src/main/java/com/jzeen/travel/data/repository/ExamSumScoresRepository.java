package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import com.jzeen.travel.data.entity.StandardGuide;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ExamScores;
import com.jzeen.travel.data.entity.ExamSumScores;
import com.jzeen.travel.data.entity.ExcursionGuide;
import com.jzeen.travel.data.entity.User;

public interface ExamSumScoresRepository extends AdvancedJpaRepository<ExamSumScores, Integer>
{
 
	ExamSumScores findByChildIdAndExamId(Integer childid,Integer examid);
	
	@Query("select e from ExamSumScores as e inner join e.exam as ex inner join ex.course as c  where c.id=:courseid and ex.unit =:unit  order by e.sumScore desc")
	List <ExamSumScores> findByexamSumScoresasc(@Param("courseid") Integer courseid,@Param("unit") String unit);
	
	
	public void deleteByExamId(Integer id);
	ExamSumScores findByExamId(Integer examid);
	
	ExamSumScores findByUserIdAndExamId(Integer childid,Integer examid);
	
	
	List <ExamSumScores> findByChildId(Integer childid);
	List <ExamSumScores> findByUserId(Integer userid);
	
	List <ExamSumScores> findByUserLastName(String firstName);
	
	
	List <ExamSumScores> findByUserLastNameAndUserUserType(String userid,Integer usertype);
	
}
