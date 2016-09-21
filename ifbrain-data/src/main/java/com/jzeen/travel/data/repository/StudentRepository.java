package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ExchangeRate;
import com.jzeen.travel.data.entity.School;
import com.jzeen.travel.data.entity.Student;

public interface StudentRepository extends AdvancedJpaRepository<Student, Integer>
{
	 public void deleteBySchoolClassId(Integer schoolClassid);
	 
	 @Query("select s from  Student as s inner join s.schoolClass as sc inner join sc.school as school where school.id=:schoolid")
	List  <Student> findBySchoolClassId(@Param("schoolid") Integer schoolid);
	 Student findByStudentName(String name);
	 
	 @Query("select s from  Student as s inner join s.schoolClass as sc  where sc.id=:classid")
		List  <Student> findByClassId(@Param("classid") Integer classid);
	
	 Student findByStudentNameAndSchoolClassId(String name,Integer schoolClass);
}
