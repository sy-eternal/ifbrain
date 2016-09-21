package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;





import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.User;

public interface ChildRepository extends AdvancedJpaRepository<Child, Integer>
{
	 List<Child> findByUser(User user);
	 Child findById(Integer user);
	 Child findByName(String name);
	 Child findByImageId(Integer user);
	 Child findImageIdById(Integer id);/*
	 Child findImageIdById(Integer childid);*/
    
}
