package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.InsuranceRate;
import com.jzeen.travel.data.entity.RentalCar;
import com.jzeen.travel.data.entity.RentalRate;

public interface RentalRateRepository extends AdvancedJpaRepository<RentalRate, Integer>
{
    public RentalRate findById(Integer id);
    //查询有多个租车
    public List<RentalRate> findBySpecialcar(RentalCar specialCar);
}
