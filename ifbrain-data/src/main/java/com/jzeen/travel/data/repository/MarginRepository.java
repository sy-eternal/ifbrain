package com.jzeen.travel.data.repository;


import com.jzeen.travel.data.entity.Margin;

public interface MarginRepository extends AdvancedJpaRepository<Margin, Integer>
{
    public Margin findByMargintype(String margintype);
  
    
}
