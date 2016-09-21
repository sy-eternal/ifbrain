package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.GuideCar;

public interface GuideCarRepository extends AdvancedJpaRepository<GuideCar, Integer>
{
	List<GuideCar> findByCarType(String carType);
}
