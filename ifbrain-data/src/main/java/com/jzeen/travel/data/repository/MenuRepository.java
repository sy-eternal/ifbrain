package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Menu;

public interface MenuRepository extends AdvancedJpaRepository<Menu, Integer>
{
    Menu findByUrl(String url);

    /**
     * 根据用户ID,查询用户的所有菜单
     * 
     * @param memberId
     * @return
     */
    @Query("select m from Menu as m inner join m.roles as r inner join r.members as u where u.id = :memberId order by m.displayOrder asc")
    List<Menu> getByMemberId(@Param("memberId") Integer memberId);

    /**
     * 根据用户和页面地址获取菜单数量。
     * 
     * @param memberId
     * @param url
     * @return
     */
    @Query("select count(m) from Menu as m inner join m.roles as r inner join r.members as u where u.id = :memberId and m.url = :url")
    long countByMemberIdAndUrl(@Param("memberId") Integer memberId, @Param("url") String url);
}
