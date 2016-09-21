package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelTags;


public interface HotelTagsRepository extends AdvancedJpaRepository<HotelTags, Integer>
{
	/**
	 *  根据酒店活动的对象,查询酒店标签
	 * @param hotelActivity 酒店活动对象
	 * @return 返回标签集合
	 */
    public List<HotelTags> findByhotelActivity(HotelActivity hotelActivity);
}
