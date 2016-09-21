package com.jzeen.travel.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.QExcursionGuide;
import com.jzeen.travel.data.entity.QExcursionGuideOccupied;
import com.jzeen.travel.data.entity.QGuide;
import com.jzeen.travel.data.entity.QUser;
import com.mysema.query.jpa.impl.JPAQuery;

/**
 * 导游服务
 */
@Service
public class GuideService
{
    @PersistenceContext
    private EntityManager _entityManager;

    /**
     * 获取指定日期和城市的短途导游余量。
     * 
     * @param date
     *            日期。
     * @param cityId
     *            城市标识。
     * @return 城市的短途导游余量。
     */
    public Long getExcursionGuideRemainCount(Date date, Integer cityId)
    {
        JPAQuery query = new JPAQuery(_entityManager);
        QUser user = QUser.user;
        QGuide guide =QGuide.guide;
        QExcursionGuide execursionGuide = QExcursionGuide.excursionGuide;
        QExcursionGuideOccupied excursionGuideOccupied = QExcursionGuideOccupied.excursionGuideOccupied;
        Long count = query.from(user)
                .innerJoin(guide.excursionGuide, execursionGuide)
                .innerJoin(execursionGuide.excursionGuideOccupieds, excursionGuideOccupied)
                .where(guide.hostCity.isNotNull(), guide.hostCity.id.eq(cityId), excursionGuideOccupied.occupiedDate.ne(date))
                .count();
        return count;
    }

    /**
     * 获取指定日期和城市的标准导游余量。
     * 
     * @param date
     *            日期。
     * @param cityId
     *            城市标识。
     * @return 城市的标准导游余量。
     */
    public long getStandardGuideRemainCount(Date date, City cityId)
    {
        return 0;
    }
}
