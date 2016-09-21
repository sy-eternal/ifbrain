package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelTags;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotTags;


public interface SpotTagsRepository extends AdvancedJpaRepository<SpotTags, Integer>
{
    /**
     * 根据景点对象查询景点标签
     * @param spot
     * @return
     */
    public List<SpotTags> findBySpot(Spot spot);
}
