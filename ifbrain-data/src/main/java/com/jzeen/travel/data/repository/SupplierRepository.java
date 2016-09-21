package com.jzeen.travel.data.repository;


import java.util.List;

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.Supplier;
public interface SupplierRepository extends AdvancedJpaRepository<Supplier, Integer>
{
    public Supplier findBySupplierpriceruleactive(String supplierpriceruleactive);
    public Supplier  findByEnNameAndCnName(String enName,String cnName);
//    List<Supplier> findByEnNameOrderById(String enName);
    public Supplier findByCnName(String cnName);
    List<Supplier> findBySupplierStatus(Integer status);
    
    
    /**
     * 根据代码表（保险ID）查询所有关于保险的供应商
     */
    public List<Supplier> findByCodeId(Integer codeId);
}
