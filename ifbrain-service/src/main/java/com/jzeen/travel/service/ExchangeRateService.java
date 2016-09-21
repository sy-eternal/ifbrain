package com.jzeen.travel.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzeen.travel.data.entity.ExchangeRate;
import com.jzeen.travel.data.repository.ExchangeRateRepository;

@Service
public class ExchangeRateService {
    @Autowired
    private ExchangeRateRepository _exchangeRateRepository;

    public BigDecimal getCurrentExchangeRate() throws RuntimeException {
       /* Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(year, month, 1, 0, 0, 0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();*/

        ExchangeRate exchangeRate = _exchangeRateRepository.getByExchangerate();
        if (exchangeRate == null || exchangeRate.getExchangerate() == null) {
            throw new RuntimeException("汇率信息不能为空！");
        }
        return exchangeRate.getExchangerate();
    }
}
