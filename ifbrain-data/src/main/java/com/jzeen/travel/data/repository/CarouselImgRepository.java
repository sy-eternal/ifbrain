package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.CarouselImg;

public interface CarouselImgRepository extends AdvancedJpaRepository<CarouselImg, Integer> {
   
	@Query("select img from CarouselImg as img  order by img.sortnumber  asc")
	  List<CarouselImg> getAllImgOrderBySortNumber();
	
	//上移
	@Query("select max(sortnumber) from CarouselImg as img where  img.sortnumber < :sortnumber")
	Integer  findByUpSortnumber(@Param("sortnumber") Integer sortnumber);
	
	CarouselImg  findBySortnumber(@Param("sortnumber") Integer sortnumber);

	//下移
	@Query("select min(sortnumber) from CarouselImg as img where  img.sortnumber > :sortnumber")
	Integer  findByDownSortnumber(@Param("sortnumber") Integer sortnumber);
	
	@Query("select img from CarouselImg as img where img.sortnumber > :sortnumber order by img.sortnumber  asc")
	  List<CarouselImg> getByDeleteImgOrderBySortNumber(@Param("sortnumber") Integer sortnumber);
}
