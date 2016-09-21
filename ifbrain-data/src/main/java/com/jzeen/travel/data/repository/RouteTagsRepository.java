package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.Route;
import com.jzeen.travel.data.entity.RouteTag;


public interface RouteTagsRepository extends AdvancedJpaRepository<RouteTag, Integer>
{
/**
 * 根据产品路线对象，查询路线标签
 */
    
    public List<RouteTag> findByroute(Route route);
    
    
}
