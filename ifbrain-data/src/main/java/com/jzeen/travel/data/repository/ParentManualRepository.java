package com.jzeen.travel.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jzeen.travel.data.entity.ParentManual;

public interface ParentManualRepository extends AdvancedJpaRepository<ParentManual, Integer>
{
	
    List<ParentManual> findByCourseIdOrderByOrdinalNumberAsc(Integer courseId);
}
