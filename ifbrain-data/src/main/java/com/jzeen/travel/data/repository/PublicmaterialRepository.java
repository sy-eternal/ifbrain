package com.jzeen.travel.data.repository;


import java.util.List;

import com.jzeen.travel.data.entity.Publicmaterial;

public interface PublicmaterialRepository extends AdvancedJpaRepository<Publicmaterial, Integer>
{
	List<Publicmaterial> findByNavigationbarmoduleIdOrderByCreateTimeAsc(Integer id);
    
}
