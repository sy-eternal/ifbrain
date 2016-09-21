package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;








import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.CommodityMall;
import com.jzeen.travel.data.entity.CommodityType;
import com.jzeen.travel.data.entity.DemandLevel;
import com.jzeen.travel.data.entity.User;

public interface DemandLevelRepository extends AdvancedJpaRepository<DemandLevel, Integer>
{
	DemandLevel findById(Integer id);
	DemandLevel findIdByDemandName(String id);
	DemandLevel findByDemandName(String name);
}
