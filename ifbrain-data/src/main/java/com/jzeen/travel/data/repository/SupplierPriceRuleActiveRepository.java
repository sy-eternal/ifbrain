package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.SupplierPriceRuleActive;

public interface SupplierPriceRuleActiveRepository extends AdvancedJpaRepository<SupplierPriceRuleActive, Integer>
{
    List<SupplierPriceRuleActive> findById(Integer id);
    
    @Query("select t from SupplierPriceRuleActive as t inner join t.supplier as s  where s.cnName = :cnName")
    SupplierPriceRuleActive findBySupplier(@Param("cnName") String cnName);
}
