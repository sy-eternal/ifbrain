package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ItemManagementQuestion;



public interface ItemManagementQuestionRepository extends AdvancedJpaRepository<ItemManagementQuestion, Integer>
{
	
	@Query("select  iq from ItemManagementQuestion iq inner join  iq.item i  where i.id =:id" )
	  public List<ItemManagementQuestion> findquestionbyitemid(@Param("id")Integer id);
	
	@Query("select  iq from ItemManagementQuestion iq  inner join  iq.item i  inner join i.exam as e where e.id =:id order by iq.ordernumber asc " )
	  public List<ItemManagementQuestion> findByOrdernumberAsc(@Param("id")Integer id);

	
	 @Modifying 
	 @Query("update ItemManagementQuestion iq set  iq.ordernumber = iq.ordernumber-1 where iq.ordernumber > :ordernumber and iq.item.id =:itemmanagementid" )
	  public void  updateordernumber(@Param("ordernumber")Integer ordernumber,@Param("itemmanagementid")Integer itemmanagementid);

	
	@Query("select  iq from ItemManagementQuestion iq  inner join  iq.item i  inner join i.exam as e where e.status =:status order by iq.ordernumber asc " )
	  public List<ItemManagementQuestion> findByexamStatues(@Param("status")String status);

	
	
	@Query("select  iq from ItemManagementQuestion iq  inner join  iq.item i  inner join i.exam as e where e.id =:id and iq.ordernumber=:ordernumber " )
	  public ItemManagementQuestion findByOrdernumber(@Param("id")Integer id,@Param("ordernumber")Integer ordernumber);
	
	

}
