package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.Exam;



public interface ExamRepository extends AdvancedJpaRepository<Exam, Integer>
{
	List<Exam> findByStatus(String status);
	
}
