package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;







import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.CustomCommodity;
import com.jzeen.travel.data.entity.User;

public interface CustomCommodityRepository extends AdvancedJpaRepository<CustomCommodity, Integer>
{
	 public void deleteByChildId(Integer id);
}
