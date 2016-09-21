package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.Purpose;

public interface PurposeRepository  extends AdvancedJpaRepository<Purpose,Integer>{

	public Purpose findByPurpose(String purpose);
}
