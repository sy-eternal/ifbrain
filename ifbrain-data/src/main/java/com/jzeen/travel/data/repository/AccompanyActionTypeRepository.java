package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.AccompanyActionType;

public interface AccompanyActionTypeRepository extends AdvancedJpaRepository<AccompanyActionType,Integer>{

	public AccompanyActionType findByAccompanyType(String accompanyType);
}
