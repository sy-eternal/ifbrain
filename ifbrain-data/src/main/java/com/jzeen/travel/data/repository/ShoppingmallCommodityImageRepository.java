package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ShoppingmallCommodity;
import com.jzeen.travel.data.entity.ShoppingmallCommodityImage;

public interface ShoppingmallCommodityImageRepository extends AdvancedJpaRepository<ShoppingmallCommodityImage, Integer>
{
	

	public void deleteByShoppingmallCommodityId(Integer id);
	List <ShoppingmallCommodityImage> findByShoppingmallCommodityId(Integer id);
}
