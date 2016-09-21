package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelRoomType;

public interface HotelRoomTypeRepository extends AdvancedJpaRepository<HotelRoomType, Integer>
{
    /**
     * 根据酒店活动对象，查找多个酒店房间类型
     * @param hotelActivity
     * @return
     */
 public List<HotelRoomType> findByHotelActivity(HotelActivity hotelActivity);
 
 
 
      @Query("select ht from HotelRoomType as ht inner join ht.hotelActivity as h where h.id= :activityId order by ht.perNightPrice desc")
    public   List<HotelRoomType> findByHotelIdOrderbyPerNightPrice(Integer activityId);
}
