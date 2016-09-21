package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.AccompanyMemberAge;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideImageRelate;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.VisaInstruction;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuideImageRelateRepository extends AdvancedJpaRepository<GuideImageRelate, Integer> {
   @Query("select g from GuideImageRelate as g inner join g.guide as d where d.id=:guideid and g.type=:type")
    public GuideImageRelate findByTypes(@Param("type") Integer type,@Param("guideid") Integer guideid);
   @Query("select d.id from GuideImageRelate as g inner join g.image as d inner join g.guide as i where i.id=:guideid and g.type=:type")
   public List<GuideImageRelate> findByTypelist(@Param("type") Integer type,@Param("guideid") Integer guideid);
	/*
	 * 通过导游找到关联表
	 */
List< GuideImageRelate> findByGuide(Guide guide);
/*
 * 通过type值找到关联表集合
 */
List<GuideImageRelate> findByType(Integer type);
/*
 * 通过关联表id和type值找到相对应的集合
 */
@Query("select g from GuideImageRelate g where g.guide.id=:id and g.type=:type")
List<GuideImageRelate> findByTypeimage(@Param("id") Integer id,@Param("type") Integer type);
}
