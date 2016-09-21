package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.NavigationbarModule;

public interface NavigationbarModuleRepository extends AdvancedJpaRepository<NavigationbarModule, Integer>
{
   List<NavigationbarModule> findByNavigationBarTypeOrderByNavigationBarTypeAsc(Integer type);
   
   List<NavigationbarModule> findByNavigationBarTypeOrderByTypeAsc(Integer type);
   
   NavigationbarModule findByTitle(String materialName);
   
   NavigationbarModule findByNavigationBarTypeAndType(Integer NavigationBarType,Integer type);
   
   NavigationbarModule findByType(Integer type);
   
}
