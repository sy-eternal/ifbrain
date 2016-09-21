package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ExchangeRate;
import com.jzeen.travel.data.entity.School;
import com.jzeen.travel.data.entity.SchoolClass;

public interface SchoolClassRepository extends AdvancedJpaRepository<SchoolClass, Integer>
{
 public  void deleteById(Integer id);
 public void deleteBySchoolId(Integer schoolid);
 
 List <SchoolClass> findBySchoolId(Integer schoolid);
 
 List <SchoolClass> findByMemberId(Integer mamberid);
 
//判断班级名称是否重复
	 @Query("select count(*) from SchoolClass  as c inner join c.school as s where c.name = :name and s.id=:sid ")
	    public long findByClassandName(@Param("name")String name,@Param("sid")Integer sid);
}
