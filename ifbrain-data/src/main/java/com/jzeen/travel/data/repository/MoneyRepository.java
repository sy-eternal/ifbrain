package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;








import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.DefineTask;
import com.jzeen.travel.data.entity.Money;
import com.jzeen.travel.data.entity.User;

public interface MoneyRepository extends AdvancedJpaRepository<Money, Integer>
{
    Money findByChildId(Child child);
    @Query("select h from Money as h inner join h.childId as c where c.id = :childId")
	  public Money findByChildIds(@Param("childId")Integer childId);
    public void deleteByChildIdId(Integer id);
}
