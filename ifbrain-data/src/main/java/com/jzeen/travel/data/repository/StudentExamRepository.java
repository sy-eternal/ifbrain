package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.StudentExam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentExamRepository extends AdvancedJpaRepository<StudentExam, Integer> {
    
	StudentExam findByExamIdAndStudentId(Integer examid,Integer studentid);
	@Query("select s from StudentExam as s inner join s.student as u inner join u.schoolClass as cl inner join cl.school  as sc where sc.id =:scid")
	List <StudentExam> findByStudentSchoolClassId(@Param("scid")Integer scid);
	
	
	@Query("select s from StudentExam as s inner join s.student as u inner join u.schoolClass as cl where cl.id =:scid")
	List <StudentExam> findByStudentClassId(@Param("scid")Integer scid);
}
