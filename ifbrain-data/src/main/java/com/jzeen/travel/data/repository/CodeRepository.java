package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.Code;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodeRepository extends AdvancedJpaRepository<Code, Integer> {
    List<Code> findByTypeOrderByValueAsc(String type);

    Code findByClasss(String classs);

    Code findByValue(Integer value);

    Code findByTypeAndValue(String type, Integer value);


    /**
     * 通过代码类型、主键ID列表查询代码，返回代码名称列表
     * add by limin 20150630
     */

    @Query("select c.classs from Code as c where c.type = :codeType  and c.id in :ids")
    List<String> findByIdlist(@Param("codeType") String codeType, @Param("ids") List<Integer> ids);
    /**
     * 从代码表中获得支付方式
     */
    List<Code> findByType(String type);
    
}
