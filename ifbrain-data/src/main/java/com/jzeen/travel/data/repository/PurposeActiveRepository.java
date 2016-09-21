package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.PurposeActive;

public interface PurposeActiveRepository  extends AdvancedJpaRepository<PurposeActive,Integer>{
    public PurposeActive findByPurposeactive(String purposeactive);
}
