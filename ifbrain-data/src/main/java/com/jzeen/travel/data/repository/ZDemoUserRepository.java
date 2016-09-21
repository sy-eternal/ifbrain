package com.jzeen.travel.data.repository;


import java.util.List;

import com.jzeen.travel.data.entity.ZDemoUser;

public interface ZDemoUserRepository extends AdvancedJpaRepository<ZDemoUser, Integer>{
	 List<ZDemoUser> findByNumber(String number);

}
