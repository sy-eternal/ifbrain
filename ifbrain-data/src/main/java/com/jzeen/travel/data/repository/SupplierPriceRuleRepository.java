package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.SupplierPriceRule;

public interface SupplierPriceRuleRepository extends AdvancedJpaRepository<SupplierPriceRule, Integer>
{
    /**
     * 获取指定交通方式的供应商价格规则。
     * 
     * @param vehicleTypeCode
     *            交通方式的代码值。
     * @return 指定交通方式的供应商价格规则。
     */
    @Query("select spr from SupplierPriceRule as spr where spr.supplierTypeCode = (select c1.value from Code as c1 where c1.type = '供应商' and c1.classs = (select c2.classs from Code as c2 where c2.type = '交通方式' and c2.value = :vehicleTypeCode))")
    List<SupplierPriceRule> getByVehicleTypeCode(@Param("vehicleTypeCode") Integer vehicleTypeCode);

    /**
     * 获取指定类型的供应商价格规则。
     * 
     * @param supplierType
     *            供应商类型。
     * @return 指定类型的供应商价格规则。
     */
    @Query("select spr from SupplierPriceRule as spr where spr.supplierTypeCode = (select c1.value from Code as c1 where c1.type = '供应商' and c1.classs = :supplierType)")
    List<SupplierPriceRule> getBySupplierType(@Param("supplierType") String supplierType);
    
    
    
    /**
     * 根据供应商和代码表，获取中间表对象（取价格系数）
     * @param supplier 供应商对象
     * @param code  代码表ID
     * @return  返回中间表供应商价格规则 对象
     */
    @Query("select s from SupplierPriceRule as s inner join s.supplier as u where s.supplierTypeCode =:codeId and u.id=:supplier")
    public SupplierPriceRule findBySupplierAndSupplierTypeCode(@Param("supplier") Integer supplier,@Param("codeId") Integer codeId);

}
