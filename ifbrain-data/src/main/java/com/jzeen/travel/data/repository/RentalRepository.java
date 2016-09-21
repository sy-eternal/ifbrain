package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.InsuranceActivity;
import com.jzeen.travel.data.entity.RentalCar;
import com.jzeen.travel.data.entity.RentalPlan;

public interface RentalRepository extends AdvancedJpaRepository<RentalCar, Integer>
{

    public RentalCar findById(Integer id);
}
