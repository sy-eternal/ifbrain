package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.SpotTicketType;

public interface SpotTicketTypeRepository extends AdvancedJpaRepository<SpotTicketType, Integer>
{
    List<SpotTicketType> findById(Integer id);

    List<SpotTicketType> findBySpotsId(Integer spotId);
    public SpotTicketType findByCostAndPrice(double cost, double price);
    public SpotTicketType findByType(String type);
}
