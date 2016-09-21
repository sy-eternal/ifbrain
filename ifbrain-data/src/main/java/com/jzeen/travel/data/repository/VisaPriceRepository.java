package com.jzeen.travel.data.repository;

import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.VisaPrice;

public interface VisaPriceRepository extends AdvancedJpaRepository<VisaPrice ,Integer>
{
     public VisaPrice  findByType(@Param("type") String type);
}
