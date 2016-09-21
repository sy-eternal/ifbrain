package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.CommodityMall;

public interface CommodityMallRepository extends AdvancedJpaRepository<CommodityMall, Integer>
{
	CommodityMall findIdByCommodityType(String commodityType);
	CommodityMall findByCommodityType(String commodityType);
}
