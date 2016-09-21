package com.jzeen.travel.data.repository;

import org.springframework.data.jpa.repository.Query;


import com.jzeen.travel.data.entity.Roles;

public interface RolesRepository extends AdvancedJpaRepository<Roles, Integer>
{
    /**
     * 根据角色ID,查找所有用户角色
     */
//    @Query("select m from Member as m inner join m.roles  where m.roles.id=:rolesId")
//    Roles findByRole(Integer rolesId);   
}
