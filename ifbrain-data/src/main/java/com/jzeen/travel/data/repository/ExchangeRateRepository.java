package com.jzeen.travel.data.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ExchangeRate;

public interface ExchangeRateRepository extends AdvancedJpaRepository<ExchangeRate, Integer>
{
    /**
     * 获取指定日期范围内的美元-人民币汇率。
     * 
     * @param startDate
     *            起始日期。
     * @param endDate
     *            结束日期。
     * @return 日期范围内的美元-人民币汇率。
     */

    @Query("select er from ExchangeRate as er ")
    ExchangeRate getByExchangerate();
}
