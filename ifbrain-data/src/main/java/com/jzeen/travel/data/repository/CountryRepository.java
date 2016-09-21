package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.Country;

public interface CountryRepository extends AdvancedJpaRepository<Country, Integer>
{
    public Country findByName(String name);
}
