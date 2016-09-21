package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.CourseInformation;

public interface CourseInformationRepository extends AdvancedJpaRepository<CourseInformation, Integer>
{
    
   
    
}
