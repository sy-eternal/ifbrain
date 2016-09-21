package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.ApplicationInfo;
import com.jzeen.travel.data.entity.VisaOrder;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationInfoRepository extends AdvancedJpaRepository<ApplicationInfo, Integer> {
    
    
    List<ApplicationInfo> findByVisaOrder(VisaOrder visaOrder);
    
    
    
    @Query("select co.filePath from ApplicationInfo as c inner join c.document as co where c.id = :cid")
    public String findByDocumentId(@Param("cid")Integer cid);
    
   
    
    @Query("select c from ApplicationInfo as c inner join c.visaOrder as order where order.id= :cid  order by c.id desc")
    public  List<ApplicationInfo> findByOrderId(@Param("cid") Integer cid);
}
