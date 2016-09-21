package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotThemeRelate;

public interface SpotThemeRelateRepository extends AdvancedJpaRepository<SpotThemeRelate,Integer>{
	public void deleteBySpotId(Integer id);

	//    @Query("select co from SpotThemeRelate as c  inner join c.theme as co inner join c.spot  as sp where sp.id=:id")
	//    public List<ThemeActive> list(@Param("id") Integer id);

	public List<SpotThemeRelate> findBySpot(Spot spot);

	@Query("select c from SpotThemeRelate as c  inner join c.spot as cs inner join c.theme as ct  where cs.id=:spotId order by ct.id")
	public List<SpotThemeRelate> findBySpot(@Param("spotId") Integer spotId);
	@Query("select c from SpotThemeRelate as c  inner join c.spot as cs inner join c.theme as ct  where ct.id=:themeId order by ct.id")
	public List<SpotThemeRelate> findByTheme(@Param("themeId") Integer themeId);
}
