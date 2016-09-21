package com.jzeen.travel.data.repository;

import org.springframework.data.jpa.repository.Query;

import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SupplierActive;

public interface SupplierActiveRepository extends AdvancedJpaRepository<SupplierActive, Integer>
{
    public SupplierActive findByCountry(String country);
}
