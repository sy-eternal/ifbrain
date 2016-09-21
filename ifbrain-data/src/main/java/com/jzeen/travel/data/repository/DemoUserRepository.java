package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.DemoUser;

public interface DemoUserRepository extends AdvancedJpaRepository<DemoUser, Integer>
{
    List<DemoUser> findByNumber(String number);
}
