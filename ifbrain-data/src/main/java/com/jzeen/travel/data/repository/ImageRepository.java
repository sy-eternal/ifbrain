package com.jzeen.travel.data.repository;
import java.util.List;

import com.jzeen.travel.data.entity.Image;
public interface ImageRepository extends AdvancedJpaRepository<Image, Integer>{

	public Image findByid(Integer id);
}
