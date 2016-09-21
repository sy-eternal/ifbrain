package com.jzeen.travel.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.IfbrainTask;
import com.jzeen.travel.data.entity.MaterialComment;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.UserMaterial;

public interface UserMaterialRepository extends AdvancedJpaRepository<UserMaterial, Integer>
{
	//找出点赞数
  List<UserMaterial> findByMaterialIdAndStatus(Integer materialid,Integer status);
  //取消点赞
  UserMaterial findByMaterialIdAndUserId(Integer materialid,Integer status);
  UserMaterial findByUser(User user);
}
