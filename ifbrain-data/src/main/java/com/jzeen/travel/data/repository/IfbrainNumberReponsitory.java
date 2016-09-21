package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.IfbrainNumber;


public interface IfbrainNumberReponsitory extends AdvancedJpaRepository<IfbrainNumber, Integer>
{
	
}
