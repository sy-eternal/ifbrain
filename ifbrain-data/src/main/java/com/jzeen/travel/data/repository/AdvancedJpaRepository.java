package com.jzeen.travel.data.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * JPA仓储类的统一接口，支持新增、修改、批量删除、分页查询及排序等操作。
 * 
 * @author WangRui
 * @param <T>
 *            实体类型。
 * @param <ID>
 *            主键类型。
 */
@NoRepositoryBean
public interface AdvancedJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>, QueryDslPredicateExecutor<T>
{
}
