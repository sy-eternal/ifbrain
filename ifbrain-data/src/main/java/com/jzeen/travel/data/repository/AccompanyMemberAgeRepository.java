package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.AccompanyMemberAge;
import com.jzeen.travel.data.entity.Order;

import java.util.List;

public interface AccompanyMemberAgeRepository extends AdvancedJpaRepository<AccompanyMemberAge, Integer> {

    public AccompanyMemberAge findByAccompanymemberage(String accompanymemberage);


    List<AccompanyMemberAge> findByOrderId(Integer orderId);
}
