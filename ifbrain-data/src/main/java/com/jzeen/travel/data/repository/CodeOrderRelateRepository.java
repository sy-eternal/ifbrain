package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.CodeOrderRelate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeOrderRelateRepository extends AdvancedJpaRepository<CodeOrderRelate, Integer> {

    /**
     * 通过Order主键查询城市间交通方式，返回交通方式列表
     *
     * @param orderPk
     * @return List<Integer>
     * add by limin 20150630
     */
    @Query("select c.codePk from CodeOrderRelate as c where c.orderPk = :orderPk")
    public List<Integer> findCodesByOrderPk(@Param("orderPk") Integer orderPk);

}
