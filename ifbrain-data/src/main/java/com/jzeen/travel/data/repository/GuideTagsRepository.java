package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.CityPlan;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideTags;
import com.jzeen.travel.data.entity.ThemeActive;

public interface GuideTagsRepository extends AdvancedJpaRepository<GuideTags, Integer>
{

//	@Query("select g from GuideTags as g inner join g.guide as u where u.id=:id")
	public GuideTags findBytag(String tag);
	public List<GuideTags> findByGuide(Guide guide);
}
