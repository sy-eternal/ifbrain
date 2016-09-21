package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ExchangeRate;
import com.jzeen.travel.data.entity.School;

public interface SchoolRepository extends AdvancedJpaRepository<School, Integer>
{
	School  findBySchoolClassId(Integer classid);
	
	List <School>  findByMemberId(Integer memberid);
	
	//判断学校名称是否重复
	 @Query("select count(*) from School  as c inner join c.citySchool as ci where c.scName = :scName and ci.id=:ciid ")
	    public long findByCityandName(@Param("scName")String scName,@Param("ciid")Integer ciid);
}
