package com.jzeen.travel.service;

import com.jzeen.travel.core.util.Constant;
import com.jzeen.travel.core.util.DateUtil;
import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期规划服务
 */
@Service
public class DatePlanService {
    @Autowired
    private DatePlanRepository _datePlanRepository;

    @Autowired
    private OrderRepository _orderRepository;

    @Autowired
    private ExcursionGuideRepository _excursionGuideRepository;

    @Autowired
    private ExcursionGuidePlanRepository _excursionGuidePlanRepository;

    @Autowired
    private StandardGuideRepository _standardGuideRepository;

    @Autowired
    private StandardGuidePlanRepository _standardGuidePlanRepository;


    @Autowired
    private GuideCarManageRepository _guideCarManageRepository;


    /**
     * @param orderId
     * @param datePlan
     * @param startExc   开始短途导游列表
     * @param endExc     到达短途导游列表
     * @param startStand 开始标准导游列表
     * @param endStand   到达标准导游列表
     */
    public void create(Integer orderId, DatePlan datePlan, List<Integer> startExc, List<Integer> endExc, List<Integer> startStand, List<Integer> endStand) throws ParseException {
        Order order = _orderRepository.findOne(orderId);
        Date createTime = new Date();

        // 日期规划
        datePlan.setOrder(order);
        datePlan.setConfirmStatus(0);
        datePlan.setCreateTime(createTime);

        // 城市规划
        CityPlan cityPlan = datePlan.getCityPlan();
        cityPlan.setDatePlan(datePlan);
        cityPlan.setCreateTime(createTime);

        // 交通规划
        VehiclePlan vehiclePlan = datePlan.getVehiclePlan();
        Integer vehicleTypeCode = vehiclePlan.getVehicleTypeCode();

        if (vehicleTypeCode == null) {
            datePlan.setVehiclePlan(null);
        } else {
            vehiclePlan.setDatePlan(datePlan);
            vehiclePlan.setCreateTime(createTime);

            if (vehiclePlan.getSupplierPriceRule() != null && vehiclePlan.getCostPrice() != null) {
                // 根据成本价计算销售价
                caculVehicleSalePrice(vehiclePlan);
            }

            //处理机票\到达航站楼\出发航站楼 ，号的问题
            dealwithDot(vehiclePlan);
        }

        // 短途导游规划

        ExcursionGuidePlan startExcGuidePlan = createExcGuidPlan(datePlan, true, startExc.size());

        ExcursionGuidePlan endExcGuidePlan = createExcGuidPlan(datePlan, false, endExc.size());

        // 占用指定数量的短途导游
        // 只有选择了交通工具，并且选择项为短途导游专车，返回真，其他情况返回false
        boolean hasCar = (vehicleTypeCode != null && vehicleTypeCode == 5);

        List<ExcursionGuidePlan> excursionGuidePlans = new ArrayList<ExcursionGuidePlan>();

        addExcGuidPlan(excursionGuidePlans, startExcGuidePlan, hasCar, startExc);

        addExcGuidPlan(excursionGuidePlans, endExcGuidePlan, hasCar, endExc);

       // datePlan.setExcursionGuidePlans(excursionGuidePlans);


        // 标准导游规划
        StandardGuidePlan startStandGuidePlan = createStandGuidPlan(datePlan, true, startStand.size());

        StandardGuidePlan endStandGuidePlan = createStandGuidPlan(datePlan, false, endStand.size());

        // 占用指定数量的标准导游
        // 只有选择了交通工具，并且选择项为标准导游专车，返回真，其他情况返回false
        hasCar = (vehicleTypeCode != null && vehicleTypeCode == 4);

        List<StandardGuidePlan> standGuidePlans = new ArrayList<StandardGuidePlan>();

        addStandGuidPlan(standGuidePlans, startStandGuidePlan, hasCar, startStand);

        addStandGuidPlan(standGuidePlans, endStandGuidePlan, hasCar, endStand);

        //datePlan.setStandardGuidePlans(standGuidePlans);

        _datePlanRepository.save(datePlan);
    }

    /**
     * 根据参数创建短途导游规划
     *
     * @param datePlan   日期规划
     * @param startOrEnd 出发或者到达
     * @param guideCount 导游数量
     */
    private ExcursionGuidePlan createExcGuidPlan(DatePlan datePlan, boolean startOrEnd, Integer guideCount) {
        ExcursionGuidePlan excGuidePlan = new ExcursionGuidePlan();
        excGuidePlan.setDatePlan(datePlan);
        excGuidePlan.setStartOrEnd(startOrEnd);
        excGuidePlan.setCreateTime(new Date());
        excGuidePlan.setGuideCount(guideCount);

        return excGuidePlan;
    }

    /**
     * 根据参数创建标准导游规划
     *
     * @param datePlan   日期规划
     * @param startOrEnd 出发或者到达
     * @param guideCount 导游数量
     */
    private StandardGuidePlan createStandGuidPlan(DatePlan datePlan, boolean startOrEnd, Integer guideCount) {
        StandardGuidePlan standGuidePlan = new StandardGuidePlan();
        standGuidePlan.setDatePlan(datePlan);
        
/*        芦鑫修改导游实体，暂时先注释 
        standGuidePlan.setStartOrEnd(startOrEnd);
        standGuidePlan.setCreateTime(new Date());
        standGuidePlan.setGuideCount(guideCount);*/
        return standGuidePlan;
    }

    /**
     * 如果短途导游规划的导游数量不为空，则将短途导游规划添加到短途导游规划列表中
     */
    private void addExcGuidPlan(List<ExcursionGuidePlan> guidePlans, ExcursionGuidePlan guidePlan, boolean hasCar, List<Integer> guides) throws ParseException {
        if (guidePlan.getGuideCount() > 0) {
            guidePlans.add(guidePlan);
            occupyExcGuides(guidePlan, hasCar, guides);
        }
    }

    /**
     * 如果标准导游规划的导游数量不为空，则将标准导游规划添加到短途导游规划列表中
     */
    private void addStandGuidPlan(List<StandardGuidePlan> guidePlans, StandardGuidePlan guidePlan, boolean hasCar, List<Integer> guides) throws ParseException {
      
    /*  芦鑫修改导游实体，暂时先注释   
        if (guidePlan.getGuideCount() > 0) {
            guidePlans.add(guidePlan);

            occupyStandGuides(guidePlan, hasCar, guides);

            // 处理人头费
            BigDecimal startCommision = caculTotalCommisionPercent(guidePlan.getCommisionPercentage(), guidePlan.getDatePlan().getOrder().getPersonCount());
            guidePlan.setCommisionPercentage(startCommision);
        }*/
    }


    public void occupyExcGuides(ExcursionGuidePlan excursionGuidePlan, boolean hasCar, List<Integer> excGuides) throws ParseException {
        if (excGuides == null || excGuides.size() == 0) {
            return;
        }
        //占用天数
        List<Date> occupiedDates = DateUtil.minsDates(excursionGuidePlan.getDatePlan().getStartDate(), excursionGuidePlan.getDatePlan().getEndDate());

        //获取要占用的导游
        List<ExcursionGuide> excursionGuides = _excursionGuideRepository.findByUserIds(excGuides);

        // 占用短途导游
        BigDecimal guidePrice = BigDecimal.ZERO;
        BigDecimal guideCarPrice = BigDecimal.ZERO;

        if (excursionGuides != null) {
            if (excursionGuidePlan.getExcursionGuideOccupieds() != null) {
                excursionGuidePlan.setExcursionGuideOccupieds(new ArrayList<ExcursionGuideOccupied>());
            }

            for (ExcursionGuide excursionGuide : excursionGuides) {
                for (Date dateItem : occupiedDates) {

                    ExcursionGuideOccupied occupied = new ExcursionGuideOccupied();
                    occupied.setExcursionGuide(excursionGuide);
                    occupied.setExcursionGuidePlan(excursionGuidePlan);
                    occupied.setOccupiedDate(dateItem);
                    occupied.setCreateTime(new Date());

                    excursionGuidePlan.getExcursionGuideOccupieds().add(occupied);
                    guidePrice = guidePrice.add(excursionGuide.getExcursionGuidePrice());


                    if (hasCar) {
                        // 短途导游专车的价格

                        GuideCarManage guideCarManage = _guideCarManageRepository.findByUserId(excursionGuide.getGuide().getId());

                        // 异常特殊处理，解决没有导游专车数据报NullPointer的问题，那么导游专车价格为0
                        if (guideCarManage != null) {
                            BigDecimal price = guideCarManage.getExcursionPrice();
                            guideCarPrice = guideCarPrice.add(price);
                        } else {
                            guideCarPrice = guideCarPrice.add(BigDecimal.ZERO);
                        }
                    }
                }
            }
        }

        // 设置导游价格，保存为美元，不乘以汇率
        excursionGuidePlan.setGuidePrice(guidePrice);
        // 设置导游专车价格
        excursionGuidePlan.setGuideCarPrice(guideCarPrice);

        return;
    }

    public void occupyStandGuides(StandardGuidePlan standGuidePlan, boolean hasCar, List<Integer> guideIds) throws ParseException {
        if (guideIds == null || guideIds.size() == 0) {
            return;
        }

        //占用天数
        List<Date> occupiedDates = DateUtil.minsDates(standGuidePlan.getDatePlan().getStartDate(), standGuidePlan.getDatePlan().getEndDate());

        //获取要占用的导游
        List<StandardGuide> standGuides = _standardGuideRepository.findByids(guideIds);

        BigDecimal guidePrice = BigDecimal.ZERO;
        BigDecimal guideCarPrice = BigDecimal.ZERO;
        if (standGuides != null) {
            if (standGuidePlan.getStandardGuideOccupieds() != null) {
                standGuidePlan.setStandardGuideOccupieds(new ArrayList<StandardGuideOccupied>());
            }

            for (StandardGuide standardGuide : standGuides) {
                for (Date dateItem : occupiedDates) {

                    StandardGuideOccupied standardGuideOccupied = new StandardGuideOccupied();
                    standardGuideOccupied.setStandardGuide(standardGuide);
                    standardGuideOccupied.setStandardGuidePlan(standGuidePlan);
                    standardGuideOccupied.setOccupiedDate(dateItem);
                    standardGuideOccupied.setCreateTime(new Date());

                    standGuidePlan.getStandardGuideOccupieds().add(standardGuideOccupied);
                    guidePrice = guidePrice.add(standardGuide.getGuidePrice());

                    if (hasCar) {
                        // 加上标准导游专车的价格
                        GuideCarManage guideCarManage = _guideCarManageRepository.findByUserId(standardGuide.getUser().getId());

                        // 异常特殊处理，解决没有导游专车数据报NullPointer的问题, 那么导游专车价格为0
                        if (guideCarManage != null) {
                            BigDecimal price = guideCarManage.getSalesPrice();
                            guideCarPrice = guideCarPrice.add(price);
                        } else {
                            guideCarPrice = guideCarPrice.add(BigDecimal.ZERO);
                        }
                    }
                }
            }
        }

        // 设置导游价格
        standGuidePlan.setGuidePrice(guidePrice);

        // 设置导游专车价格
        
/*    芦鑫修改导游实体，暂时先注释     
        standGuidePlan.setGuideCarPrice(guideCarPrice);*/

        //设置人头费用，此处为均价，即导游小费均价 再乘以天数。还需要乘以游客数量
        BigDecimal commison = Constant.decimalFormat(caculateCommisionPercent(standGuides).multiply(new BigDecimal(occupiedDates.size())));
        standGuidePlan.setCommisionPercentage(commison);

        return;
    }


    /**
     * 创建新的日期规划。
     */
    public void create(Integer orderId, DatePlan datePlan) {
        Order order = _orderRepository.findOne(orderId);
        Date createTime = new Date();

        // 日期规划
        datePlan.setOrder(order);
        datePlan.setConfirmStatus(0);
        datePlan.setCreateTime(createTime);

        // 城市规划
        CityPlan cityPlan = datePlan.getCityPlan();
        cityPlan.setDatePlan(datePlan);
        cityPlan.setCreateTime(createTime);

        // 交通规划
        VehiclePlan vehiclePlan = datePlan.getVehiclePlan();
        Integer vehicleTypeCode = vehiclePlan.getVehicleTypeCode();

        if (vehicleTypeCode == null) {
            datePlan.setVehiclePlan(null);
        } else {
            vehiclePlan.setDatePlan(datePlan);
            vehiclePlan.setCreateTime(createTime);

            // 按照美元保存到数据库中
            // 界面已经修改为美元展示方式，不需要再计算汇率
//            if (vehiclePlan.getCostPrice() != null) {
//                BigDecimal exchangeRate = _exchangeRateService.getCurrentExchangeRate();
//                vehiclePlan.setCostPrice(vehiclePlan.getCostPrice().divide(exchangeRate, 2, BigDecimal.ROUND_HALF_UP));
//            }

            if (vehiclePlan.getSupplierPriceRule() != null && vehiclePlan.getCostPrice() != null) {
                // 根据成本价计算销售价
                caculVehicleSalePrice(vehiclePlan);
            }

            //处理机票\到达航站楼\出发航站楼 ，号的问题
            dealwithDot(vehiclePlan);
        }

        // 短途导游规划
      /*  createExcursionGuidePlan(datePlan, vehicleTypeCode);

        // 标准导游规划
        createStandardGuidePlan(datePlan, vehicleTypeCode);*/

        _datePlanRepository.save(datePlan);
    }


    /**
     * 创建短途导游规划
     */
   /* private void createExcursionGuidePlan(DatePlan datePlan, Integer vehicleTypeCode) {
        List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();
        ExcursionGuidePlan startExcursionGuidePlan = excursionGuidePlans.get(0);
        startExcursionGuidePlan.setDatePlan(datePlan);
        startExcursionGuidePlan.setStartOrEnd(true);
        startExcursionGuidePlan.setCreateTime(new Date());

        ExcursionGuidePlan endExcursionGuidePlan = excursionGuidePlans.get(1);
        endExcursionGuidePlan.setDatePlan(datePlan);
        endExcursionGuidePlan.setStartOrEnd(false);
        endExcursionGuidePlan.setCreateTime(new Date());

        // 删除导游数量为零的记录
        excursionGuidePlans.removeIf(egp -> egp.getGuideCount() == null || egp.getGuideCount() <= 0);


        // 占用指定数量的短途导游
        // 只有选择了交通工具，并且选择项为短途导游专车，返回真，其他情况返回false
        boolean hasCar = (vehicleTypeCode != null && vehicleTypeCode == 5);


        if (excursionGuidePlans != null) {
            for (ExcursionGuidePlan excursionGuidePlan : excursionGuidePlans) {
                excursionGuidePlan = occupyExcursionGuides(excursionGuidePlan, hasCar);
            }
        }
    }
*/
    /**
     * 创建标准导游规划
     */
  /*  private void createStandardGuidePlan(DatePlan datePlan, Integer vehicleTypeCode) {
        List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();
        StandardGuidePlan startStandardGuidePlan = standardGuidePlans.get(0);
        startStandardGuidePlan.setDatePlan(datePlan);
        
        
        芦鑫修改导游实体，暂时先注释 
        startStandardGuidePlan.setStartOrEnd(true);
        startStandardGuidePlan.setCreateTime(new Date());

        StandardGuidePlan endStandardGuidePlan = standardGuidePlans.get(1);
        endStandardGuidePlan.setDatePlan(datePlan);
        endStandardGuidePlan.setStartOrEnd(false);
        endStandardGuidePlan.setCreateTime(new Date());
        // 删除导游数量为零的记录
        standardGuidePlans.removeIf(sgp -> sgp.getGuideCount() == null || sgp.getGuideCount() <= 0);

        // 占用指定数量的标准导游
        // 只有选择了交通工具，并且选择项为标准导游专车，返回真，其他情况返回false
        boolean hasCar = (vehicleTypeCode != null && vehicleTypeCode == 4);

        if (standardGuidePlans != null) {
            for (StandardGuidePlan standardGuidePlan : standardGuidePlans) {
                standardGuidePlan = occupyStandardGuides(standardGuidePlan, hasCar);

                // 处理人头费
                BigDecimal commision = caculTotalCommisionPercent(standardGuidePlan.getCommisionPercentage(), datePlan.getOrder().getPersonCount());
                standardGuidePlan.setCommisionPercentage(commision);
            }
        }
    }*/

    /**
     * 修改日期规划。
     */
    public void update(DatePlan datePlan) {
        DatePlan datePlanInDb = _datePlanRepository.findOne(datePlan.getId());

        datePlanInDb.setStartDate(datePlan.getStartDate());
        datePlanInDb.setEndDate(datePlan.getEndDate());

        CityPlan cityPlan = datePlan.getCityPlan();
        CityPlan cityPlanInDb = datePlanInDb.getCityPlan();
        cityPlanInDb.setFromCity(cityPlan.getFromCity());
        cityPlanInDb.setToCity(cityPlan.getToCity());

        VehiclePlan vehiclePlan = datePlan.getVehiclePlan();
        VehiclePlan vehiclePlanInDb = datePlanInDb.getVehiclePlan();
        Integer vehicleTypeCode = vehiclePlan.getVehicleTypeCode();

        if (vehicleTypeCode == null) {
            datePlanInDb.setVehiclePlan(null);
        } else {
            vehiclePlanInDb.setVehicleTypeCode(vehiclePlan.getVehicleTypeCode());
            vehiclePlanInDb.setSupplierPriceRule(vehiclePlan.getSupplierPriceRule());
            vehiclePlanInDb.setVehicleNumber(vehiclePlan.getVehicleNumber());
            vehiclePlanInDb.setDepartureTime(vehiclePlan.getDepartureTime());
            vehiclePlanInDb.setArrivalTime(vehiclePlan.getArrivalTime());
            vehiclePlanInDb.setRank(vehiclePlan.getRank());
            vehiclePlanInDb.setPersonCount(vehiclePlan.getPersonCount());
            vehiclePlanInDb.setStartAirport(vehiclePlan.getStartAirport());
            vehiclePlanInDb.setArriveAirport(vehiclePlan.getArriveAirport());
            vehiclePlanInDb.setDepartureTerminalId(vehiclePlan.getDepartureTerminalId());
            vehiclePlanInDb.setArrivalTerminalId(vehiclePlan.getArrivalTerminalId());
            vehiclePlanInDb.setAirEquipType(vehiclePlan.getAirEquipType());
            vehiclePlanInDb.setAirline(vehiclePlan.getAirline());

            vehiclePlanInDb.setCostPrice(vehiclePlan.getCostPrice());

            if (vehiclePlanInDb.getSupplierPriceRule() != null && vehiclePlan.getCostPrice() != null) {
                // 根据成本价计算销售价
                BigDecimal priceCoefficient = vehiclePlanInDb.getSupplierPriceRule().getPriceCoefficient();
                BigDecimal salePrice = priceCoefficient.multiply(vehiclePlan.getCostPrice());
                vehiclePlanInDb.setSalePrice(salePrice);
            }
        }

        // 短途导游规划
     /*   updateExcursionGuidePlan(datePlan, datePlanInDb, vehicleTypeCode);

        // 标准导游规划
        updateStandardGuidePlan(datePlan, datePlanInDb, vehicleTypeCode);*/

        _datePlanRepository.save(datePlanInDb);
    }


    /**
     * 修改短途导游规划
     */
   /* private void updateExcursionGuidePlan(DatePlan datePlan, DatePlan datePlanInDb, Integer vehicleTypeCode) {
        for (ExcursionGuidePlan excursionGuidePlan : datePlanInDb.getExcursionGuidePlans()) {
            _excursionGuidePlanRepository.delete(excursionGuidePlan);
        }

        List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();
        ExcursionGuidePlan startExcursionGuidePlan = excursionGuidePlans.get(0);
        startExcursionGuidePlan.setDatePlan(datePlanInDb);
        startExcursionGuidePlan.setStartOrEnd(true);
        startExcursionGuidePlan.setCreateTime(new Date());

        ExcursionGuidePlan endExcursionGuidePlan = excursionGuidePlans.get(1);
        endExcursionGuidePlan.setDatePlan(datePlanInDb);
        endExcursionGuidePlan.setStartOrEnd(false);
        endExcursionGuidePlan.setCreateTime(new Date());
        // 删除导游数量为零的记录
        excursionGuidePlans.removeIf(egp -> egp.getGuideCount() == null || egp.getGuideCount() <= 0);
        // 占用指定数量的短途导游

        boolean hasCar = (vehicleTypeCode != null && vehicleTypeCode == 5);
        if (excursionGuidePlans != null) {
            for (ExcursionGuidePlan excursionGuidePlan : excursionGuidePlans) {
                excursionGuidePlan = occupyExcursionGuides(excursionGuidePlan, hasCar);
            }
        }
    }

    *//**
     * 修改标准导游规划
     *//*
    private void updateStandardGuidePlan(DatePlan datePlan, DatePlan datePlanInDb, Integer vehicleTypeCode) {
        for (StandardGuidePlan standardGuidePlan : datePlanInDb.getStandardGuidePlans()) {
            _standardGuidePlanRepository.delete(standardGuidePlan);
        }
        List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();
        StandardGuidePlan startStandardGuidePlan = standardGuidePlans.get(0);
        startStandardGuidePlan.setDatePlan(datePlanInDb);
        
        
        芦鑫修改导游实体，暂时先注释 
        startStandardGuidePlan.setStartOrEnd(true);
        startStandardGuidePlan.setCreateTime(new Date());

        StandardGuidePlan endStandardGuidePlan = standardGuidePlans.get(1);
        endStandardGuidePlan.setDatePlan(datePlanInDb);
        endStandardGuidePlan.setStartOrEnd(false);
        endStandardGuidePlan.setCreateTime(new Date());
        // 删除导游数量为零的记录
        standardGuidePlans.removeIf(sgp -> sgp.getGuideCount() == null || sgp.getGuideCount() <= 0);

        // 占用指定数量的标准导游
        boolean hasCar = (vehicleTypeCode != null && vehicleTypeCode == 4);

        if (standardGuidePlans != null) {
            for (StandardGuidePlan standardGuidePlan : standardGuidePlans) {
                standardGuidePlan = occupyStandardGuides(standardGuidePlan, hasCar);

                // 处理人头费，乘以游客数量
                BigDecimal commision = caculTotalCommisionPercent(standardGuidePlan.getCommisionPercentage(), datePlanInDb.getOrder().getPersonCount());
                standardGuidePlan.setCommisionPercentage(commision);
            }
        }
    }*/

    /**
     * 占用指定数量的短途导游。
     *
     * @param excursionGuidePlan 短途导游规划。
     * @param hasCar             导游是否有车。
     * @return 已经分配了短途导游的短途导游规划。
     */
    public ExcursionGuidePlan occupyExcursionGuides(ExcursionGuidePlan excursionGuidePlan, boolean hasCar) {
        Date occupiedDate = excursionGuidePlan.getStartOrEnd() ? excursionGuidePlan.getDatePlan().getStartDate()
                : excursionGuidePlan.getDatePlan().getEndDate();
        Integer cityId = excursionGuidePlan.getStartOrEnd() ? excursionGuidePlan.getDatePlan().getCityPlan()
                .getFromCity().getId() : excursionGuidePlan.getDatePlan().getCityPlan().getToCity().getId();
        // 获取空闲的短途导游
        List<ExcursionGuide> excursionGuides = _excursionGuideRepository.getUnoccupied(occupiedDate, cityId);

        // 占用短途导游
        Integer guideCount = excursionGuidePlan.getGuideCount();
        BigDecimal guidePrice = BigDecimal.ZERO;
        BigDecimal guideCarPrice = BigDecimal.ZERO;

        if (excursionGuides != null) {
            int i = 0;
            for (ExcursionGuide excursionGuide : excursionGuides) {
                if (i >= guideCount) {
                    break;
                }

                ExcursionGuideOccupied excursionGuideOccupied = new ExcursionGuideOccupied();
                excursionGuideOccupied.setExcursionGuide(excursionGuide);
                excursionGuideOccupied.setExcursionGuidePlan(excursionGuidePlan);
                excursionGuideOccupied.setOccupiedDate(occupiedDate);
                excursionGuideOccupied.setCreateTime(new Date());

                excursionGuidePlan.getExcursionGuideOccupieds().add(excursionGuideOccupied);
                guidePrice = guidePrice.add(excursionGuide.getExcursionGuidePrice());

                if (hasCar) {
                    // 短途导游专车的价格

                    GuideCarManage guideCarManage = _guideCarManageRepository.findByUserId(excursionGuide.getGuide().getId());

                    // 异常特殊处理，解决没有导游专车数据报NullPointer的问题，那么导游专车价格为0
                    if (guideCarManage != null) {
                        BigDecimal price = guideCarManage.getExcursionPrice();
                        guideCarPrice = guideCarPrice.add(price);
                    } else {
                        guideCarPrice = guideCarPrice.add(BigDecimal.ZERO);
                    }
                }

                ++i;
            }
        }

        // 获取汇率
        //        BigDecimal exchangeRate = _exchangeRateService.getCurrentExchangeRate();

        // 设置导游价格，保存为美元，不乘以汇率
        excursionGuidePlan.setGuidePrice(guidePrice);
        // 设置导游专车价格
        excursionGuidePlan.setGuideCarPrice(guideCarPrice);

        return excursionGuidePlan;
    }

    /**
     * 占用指定数量的标准导游。
     *
     * @param standardGuidePlan 标准导游规划。
     * @param hasCar            导游是否有车。
     * @return 已经分配了标准导游的标准导游规划。
     */
    public StandardGuidePlan occupyStandardGuides(StandardGuidePlan standardGuidePlan, boolean hasCar) {
      
   /*     芦鑫修改导游实体，暂时先注释 
        Date occupiedDate = standardGuidePlan.getStartOrEnd() ? standardGuidePlan.getDatePlan().getStartDate()
                : standardGuidePlan.getDatePlan().getEndDate();
        Integer cityId = standardGuidePlan.getStartOrEnd() ? standardGuidePlan.getDatePlan().getCityPlan()
                .getFromCity().getId() : standardGuidePlan.getDatePlan().getCityPlan().getToCity().getId();
        // 获取空闲的标准导游
        List<StandardGuide> standardGuides = _standardGuideRepository.getUnoccupied(occupiedDate, cityId);

        // 占用标准导游
        Integer guideCount = standardGuidePlan.getGuideCount();
        BigDecimal guidePrice = BigDecimal.ZERO;
        BigDecimal guideCarPrice = BigDecimal.ZERO;
        if (standardGuides != null) {
            int i = 0;
            for (StandardGuide standardGuide : standardGuides) {
                if (i >= guideCount) {
                    break;
                }
                StandardGuideOccupied standardGuideOccupied = new StandardGuideOccupied();
                standardGuideOccupied.setStandardGuide(standardGuide);
                standardGuideOccupied.setStandardGuidePlan(standardGuidePlan);
                standardGuideOccupied.setOccupiedDate(occupiedDate);
                standardGuideOccupied.setCreateTime(new Date());

                standardGuidePlan.getStandardGuideOccupieds().add(standardGuideOccupied);
                guidePrice = guidePrice.add(standardGuide.getGuidePrice());

                if (hasCar) {
                    // 加上标准导游专车的价格
                    GuideCarManage guideCarManage = _guideCarManageRepository.findByUserId(standardGuide.getUser().getId());

                    // 异常特殊处理，解决没有导游专车数据报NullPointer的问题, 那么导游专车价格为0
                    if (guideCarManage != null) {
                        BigDecimal price = guideCarManage.getSalesPrice();
                        guideCarPrice = guideCarPrice.add(price);
                    } else {
                        guideCarPrice = guideCarPrice.add(BigDecimal.ZERO);
                    }
                }
                ++i;
            }
        }

        // 获取汇率，存储为美元，不保存为人民币
        // BigDecimal exchangeRate = _exchangeRateService.getCurrentExchangeRate();

        // 设置导游价格




        standardGuidePlan.setGuidePrice(guidePrice);

        // 设置导游专车价格
        standardGuidePlan.setGuideCarPrice(guideCarPrice);

        //设置人头费用,此处为均价,还需要乘以游客数量
        standardGuidePlan.setCommisionPercentage(caculateCommisionPercent(standardGuides));

    */
        return standardGuidePlan;
    }

    // 处理 ，号的问题
    private void dealwithDot(VehiclePlan vehiclePlan) {
        //处理机票 ，号的问题
        if (vehiclePlan.getAirEquipType() != null && vehiclePlan.getAirEquipType().trim().startsWith(Constant.STR_DOT)) {
            vehiclePlan.setAirEquipType(vehiclePlan.getAirEquipType().trim().substring(1));
        }

        //处理到达航站楼 ，号的问题
        if (vehiclePlan.getArrivalTerminalId() != null && vehiclePlan.getArrivalTerminalId().trim().startsWith(Constant.STR_DOT)) {
            vehiclePlan.setArrivalTerminalId(vehiclePlan.getArrivalTerminalId().trim().substring(1));
        }

        //处理到出发航站楼 ，号的问题
        if (vehiclePlan.getDepartureTerminalId() != null && vehiclePlan.getDepartureTerminalId().trim().startsWith(Constant.STR_DOT)) {
            vehiclePlan.setDepartureTerminalId(vehiclePlan.getDepartureTerminalId().trim().substring(1));
        }
    }

    //导游人头费均价
    private BigDecimal caculateCommisionPercent(List<StandardGuide> standardGuides) {

        BigDecimal decimal = BigDecimal.ZERO;
        if (standardGuides != null) {
            for (StandardGuide standardGuide : standardGuides) {
                decimal = decimal.add(standardGuide.getCommisionPercentage());
            }
            //避免除零问题
            if (!decimal.equals(BigDecimal.ZERO)) {
                return decimal.divide(new BigDecimal(standardGuides.size()), 2, BigDecimal.ROUND_HALF_UP);
            }
        }
        return decimal;
    }

    //计算时间规划中的导游人头费
    private BigDecimal caculTotalCommisionPercent(BigDecimal avaCommision, Integer count) {

        return Constant.decimalFormat(avaCommision.multiply(new BigDecimal(count)));
    }

    //根据交通规划成本价格计算销售价格
    private void caculVehicleSalePrice(VehiclePlan vehiclePlan) {
        BigDecimal priceCoefficient = vehiclePlan.getSupplierPriceRule().getPriceCoefficient();
        BigDecimal salePrice = priceCoefficient.multiply(vehiclePlan.getCostPrice());
        vehiclePlan.setSalePrice(Constant.decimalFormat(salePrice));
    }
}
