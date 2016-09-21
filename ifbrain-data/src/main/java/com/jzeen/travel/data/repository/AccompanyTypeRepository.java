package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.AccompanyType;

public interface AccompanyTypeRepository extends AdvancedJpaRepository<AccompanyType,Integer>{

	public AccompanyType findByAccompanyType(String accompanyType);
}
