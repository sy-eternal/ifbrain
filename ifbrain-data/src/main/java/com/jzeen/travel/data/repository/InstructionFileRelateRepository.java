package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.InnerCityVehicle;
import com.jzeen.travel.data.entity.InstructionFileRelate;
import com.jzeen.travel.data.entity.User;

public interface InstructionFileRelateRepository extends AdvancedJpaRepository<InstructionFileRelate,Integer>{
    @Query("select c from InstructionFileRelate as c inner join c.visainstruction as v where v.id =:id")
    public List<InstructionFileRelate> findByVisainstructions(@Param("id") Integer id);
    public InstructionFileRelate findByvisainstructionId(Integer id);
    
    
    //根据签证服务介绍id(VisaInstruction)，查找中间表对象信息, count (distinct i.id)
  @Query("select c from InstructionFileRelate as c inner join c.visainstruction as v where v.id =:id")
   public InstructionFileRelate findByVisaInstructionId(@Param("id") Integer id);
    
    @Query("select co.filePath from InstructionFileRelate as c inner join c.document as co where c.id = :cid")
    public String findByDocumentId(@Param("cid")Integer cid);
    
    
    @Query("select i from InstructionFileRelate as i inner join i.visainstruction as v group by v.id ")
    public List<InstructionFileRelate> findByVisainstructionrelate();
    
    
    
}
