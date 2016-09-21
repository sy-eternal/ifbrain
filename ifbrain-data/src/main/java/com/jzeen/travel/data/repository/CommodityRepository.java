package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;






import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Commodity;
import com.jzeen.travel.data.entity.User;

public interface CommodityRepository extends AdvancedJpaRepository<Commodity, Integer>
{
	@Query("select c from Commodity c order by c.price asc")
	List<Commodity> findall();
	
	Commodity findByImageId(Integer id);
}
