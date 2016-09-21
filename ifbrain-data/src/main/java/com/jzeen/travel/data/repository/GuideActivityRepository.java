package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.GuideActivity;

public interface GuideActivityRepository extends AdvancedJpaRepository<GuideActivity, Integer> {

    public GuideActivity findByCityAndGuideActivityType(City city,String guideActivityType);
}
