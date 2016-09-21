package com.jzeen.travel.data.repository;


import java.util.List;

import com.jzeen.travel.data.entity.Material;
import com.jzeen.travel.data.entity.MaterialType;

public interface MaterialRepository extends AdvancedJpaRepository<Material, Integer>
{
	 List<Material> findByMaterialTypeOrderByCreateTimeDesc(MaterialType type);
    
}
