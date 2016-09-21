package com.jzeen.travel.data.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelRoomType;

public interface HotelActivityRepository extends AdvancedJpaRepository<HotelActivity, Integer>
{
 
    
    
    @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id order by ht.perNightPrice desc ")
    public List<HotelActivity> findByPrice(@Param("id") Integer id);
    
    
    
    @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass")
    public List<HotelActivity> findListHotelClass(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass );
    
    
    
    
    
    @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id order by h.hotelClass desc ")
    public List<HotelActivity> findAll(@Param("id") Integer id);
   
    //查询房间类型
    @Query("select ht from HotelActivity as h  inner join h.hotelRoomType as ht  where h.id = :id")
     public List<HotelRoomType> findHotelRoomType(@Param("id") Integer id);
    
    
    
   //最简洁的方法,当价格在某个范围之内
   
   
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass   and h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType  and EXISTS(select  ht  from h.hotelRoomType as ht where ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice)   ")
   public List<HotelActivity> findPriceAndXingjiAndFreeAll(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType);
   

   
 //最简洁的方法,当价格大于1300时
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType  and EXISTS(select  ht  from h.hotelRoomType as ht where   ht.perNightPrice > :maxPrice)  ")
   public List<HotelActivity> findPriceAndXingjiAndFreeAllOne(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("maxPrice") BigDecimal maxPrice,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType);
   
   
   
   
   
   
 //最简洁的方法,当价格小于300时
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType   and EXISTS(select  ht  from h.hotelRoomType as ht where   ht.perNightPrice < :maxPrice)  ")
   public List<HotelActivity> findPriceAndXingjiAndFreeAllTwo(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("maxPrice") BigDecimal maxPrice,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType); 
   

  
   
   
 //最简洁的方法,当星级和酒店价格为空时
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType    ")
   public List<HotelActivity> findFreeAllOne(@Param("id") Integer id,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType);
   
   
   
 //最简洁的方法,当星级和酒店为空时为空时
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id   and EXISTS(select  ht  from h.hotelRoomType as ht where  ht.perNightPrice > :minPrice and ht.perNightPrice < :maxPrice)")
   public List<HotelActivity> findFreeAllOne1(@Param("id") Integer id,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);
   
   //最简洁的方法,当星级和酒店为空时,当价格大于1300时
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and  ht.perNightPrice > :maxPrice ")
   public List<HotelActivity> findFreeAllOne2(@Param("id") Integer id,@Param("maxPrice") BigDecimal maxPrice);
   
   
 //最简洁的方法,当星级和酒店为空时,当价格小于300时
   
  
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and EXISTS(select  ht  from h.hotelRoomType as ht where   ht.perNightPrice < :maxPrice) ")
   public List<HotelActivity> findFreeAllOne3(@Param("id") Integer id,@Param("maxPrice") BigDecimal maxPrice);
   
   
   //当星级为空，其他两个不为空时
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id   and  h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType  and EXISTS(select  ht  from h.hotelRoomType as ht where ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice)  ")
   public List<HotelActivity> findPriceAndFreeAll1(@Param("id") Integer id,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType);
   
   
 //当星级为空，其他两个不为空时,当价格大于1300时
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id   and  h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType and ht.perNightPrice > :maxPrice  ")
   public List<HotelActivity> findPriceAndFreeAll2(@Param("id") Integer id,@Param("maxPrice") BigDecimal maxPrice,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType);
   
   
   
   
   
   
   
   
   
   
   
 //当星级为空，其他两个不为空时,当价格小于300时
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id   and  h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType   and EXISTS(select  ht  from h.hotelRoomType as ht where   ht.perNightPrice < :maxPrice) ")
   public List<HotelActivity> findPriceAndFreeAll3(@Param("id") Integer id,@Param("maxPrice") BigDecimal maxPrice,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType); 
   
   
   
   
   
   
   
   
   
   
   //当星级不为空，其他都为空时
   @Query("select h from HotelActivity as h   inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass ")
   public List<HotelActivity> findXingji(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass);
   
   
   @Query("select min(ht.perNightPrice) from HotelRoomType as ht  inner join ht.hotelActivity as h  where h.id = :id")
   String find(@Param("id") Integer id);
   
   
   
   
 //最简洁的方法,当价格为空时
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType    ")
   public List<HotelActivity> findXingjiAndFreeAllOne(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType);
   
   
   
   
   
   
   
   
   
   
   
   
   
   /*//最简洁的方法,当价格为空时
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeBreakfastType in :freeBreakfastType and h.freeInternetType in :freeInternetType and h.freeParkingType in :freeParkingType and h.airportShuttleType in :airportShuttleType and h.fitnessCenterType in :fitnessCenterType    ")
   public List<HotelActivity> findXingjiAndFreeAllOne(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("freeBreakfastType") Integer[] freeBreakfastType,@Param("freeInternetType") Integer[] freeInternetType,@Param("freeParkingType") Integer[] freeParkingType,@Param("airportShuttleType") Integer[] airportShuttleType,@Param("fitnessCenterType") Integer[] fitnessCenterType);*/
   
   
   
   
   /* @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id order by h.hotelClass desc ")
   public List<HotelActivity> findAll(@Param("id") Integer id);
   
   
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id order by ht.perNightPrice desc ")
   public List<HotelActivity> findByPrice(@Param("id") Integer id);
   
   
   
   
   
   
   @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass")
   public List<HotelActivity> findListHotelClass(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass );
   

   
  
  
  
   //查询所有放入条件,当价格在某个范围之内时
  
  @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice ")
  public List<HotelActivity> findPriceAndXingjiAndFree(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);
  
  
  
  
  
  
  
  
  
//查询所有放入条件，当价格在某个范围之内时,当条件为含早餐时,
  @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeBreakfastType=1 and ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice ")
  public List<HotelActivity> findPriceAndXingjiAndFreeAndfreeBreakfastType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);*/
   
   
   
   
   
   
   
   
   
   
 /*//查询所有放入条件，当价格在某个范围之内时,当条件为含无线网络时,
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeInternetType=1 and ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeAndfreeInternetType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);
   
   
 //查询所有放入条件，当价格在某个范围之内时,当条件为含停车场时,
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass  and h.freeParkingType=1 and ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeAndfreeParkingType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);
   
   
   //查询所有放入条件，当价格在某个范围之内时,当条件为含机场航班时,
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass  and h.airportShuttleType=1 and ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeAndairportShuttleType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);
  
   
   //查询所有放入条件，当价格在某个范围之内时,当条件为含健身中心时,
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.fitnessCenterType=1 and ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeAndfitnessCenterType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);*/
   
   
   
   
   
   
   
   
   
   
   
   
   /*//查询所有放入条件，当价格是一个条件时
   
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and  ht.perNightPrice > :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeByOne(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("maxPrice") BigDecimal maxPrice);
   
    
   
 //查询所有放入条件当价格是一个条件时,当条件为含早餐时,
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeBreakfastType=1 and  ht.perNightPrice > :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeByOneAndfreeBreakfastType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("maxPrice") BigDecimal maxPrice);
   
   
   //查询所有放入条件，当价格是一个条件时,当条件为含无线网络时,
   
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeInternetType=1  and  ht.perNightPrice > :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeByOneAndfreeInternetType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("maxPrice") BigDecimal maxPrice);
   
   
   
   //查询所有放入条件，当价格是一个条件时,当条件为含停车场时,
   
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.freeParkingType=1  and  ht.perNightPrice > :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeByOneAndfreeParkingType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("maxPrice") BigDecimal maxPrice);
   
   
 //查询所有放入条件，当价格是一个条件时,当条件为含机场航班时,
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.airportShuttleType=1 and  ht.perNightPrice > :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeByOneAndairportShuttleType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("maxPrice") BigDecimal maxPrice);
   
   //查询所有放入条件，当价格是一个条件时,当条件为含健身中心时,
   
   @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and h.hotelClass in :hotelClass and h.fitnessCenterType=1 and h.airportShuttleType=1 and  ht.perNightPrice > :maxPrice ")
   public List<HotelActivity> findPriceAndXingjiAndFreeByOneAndfitnessCenterType(@Param("id") Integer id,@Param("hotelClass") List<Integer> hotelClass,@Param("maxPrice") BigDecimal maxPrice);*/
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    
    
   /* //查询酒店价格在某个范围之内的
    @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and ht.perNightPrice > :minPrice and  ht.perNightPrice < :maxPrice")
    List<HotelActivity> findPrice(@Param("id") Integer id,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);
    
    
    //查询酒店价格超过1300的
    @Query("select h from HotelActivity as h inner join h.hotelRoomType as ht inner join h.city as c  where c.id = :id and  ht.perNightPrice > :maxPrice")
    List<HotelActivity> findMaxPrice(@Param("id") Integer id,@Param("maxPrice") BigDecimal maxPrice);
    
    
    //查询免费早餐
    @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and  h.freeBreakfastType  =:freeBreakfastType")
    List<HotelActivity> findFreeBreakfastTypeId(@Param("id") Integer id,@Param("freeBreakfastType") Integer freeBreakfastType);
    
    //查询免费网络
    @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and  h.freeInternetType  =:freeInternetType")
    List<HotelActivity> findfreeInternetTypeId(@Param("id") Integer id,@Param("freeInternetType") Integer freeInternetType);
    
  //查询免费停车 
    @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and  h.freeParkingType  =:freeParkingType")
    List<HotelActivity> findfreeParkingTypeId(@Param("id") Integer id,@Param("freeParkingType") Integer freeParkingType);
    
    
  //查询机场航班 
    @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and  h.airportShuttleType  =:airportShuttleType")
    List<HotelActivity> findairportShuttleTypeId(@Param("id") Integer id,@Param("airportShuttleType") Integer airportShuttleType);
    
    
    //查询健身中心
    @Query("select h from HotelActivity as h  inner join h.city as c  where c.id = :id and  h.fitnessCenterType  =:fitnessCenterType")
    List<HotelActivity> findfitnessCenterTypeId(@Param("id") Integer id,@Param("fitnessCenterType") Integer fitnessCenterType);*/
    
}
