package com.jzeen.travel.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jzeen.travel.core.util.Constant;
import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.AccompanyMemberAgeRepository;
import com.jzeen.travel.data.repository.CodeOrderRelateRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.MarginPlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzeen.travel.data.repository.DatePlanRepository;

/**
 * 订单服务。
 */
@Service
public class OrderService {
    @Autowired
    private DatePlanRepository _datePlanRepository;

    @Autowired
    private CodeOrderRelateRepository _codeOrderRelateRepository;

    @Autowired
    private CodeRepository _codeRepository;

    @Autowired
    private AccompanyMemberAgeRepository _accompanyMemberAgeRepository;
    @Autowired
    private MarginPlanRepository _MarginPlanRepository;

    /**
     * 计算订单的总金额(美元)。
     *
     * @param order 订单。
     */
    /*   BigDecimal totalPrice = new BigDecimal(0);
    public BigDecimal calculateTotalPrice(Order order) {

        List<DatePlan> datePlans = _datePlanRepository.findByOrderId(order.getId());
       MarginPlan marginplan = _MarginPlanRepository.findByOrderId(order.getId());
        if (datePlans != null) {
            for (DatePlan datePlan : datePlans) {

                if (datePlan == null) {
                    continue;
                }
            
                // 交通费用
                if (datePlan.getVehiclePlan() != null && datePlan.getVehiclePlan().getSalePrice() != null) {
                    totalPrice = totalPrice.add(datePlan.getVehiclePlan().getSalePrice());
                }

                // 短途导游
                List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();
                for (ExcursionGuidePlan excursionGuidePlan : excursionGuidePlans) {
                    //短途导游费用
                    if (excursionGuidePlan != null && excursionGuidePlan.getGuidePrice() != null) {

                        totalPrice = totalPrice.add(excursionGuidePlan.getGuidePrice());
                    }

                    //短途导游车费用
                    if (excursionGuidePlan != null && excursionGuidePlan.getGuideCarPrice() != null) {

                        totalPrice = totalPrice.add(excursionGuidePlan.getGuideCarPrice());
                    }
                }*/
/*
                // 标准导游
                List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();
                for (StandardGuidePlan standardGuidePlan : standardGuidePlans) {
                    //标准导游费用
                    if (standardGuidePlan != null && standardGuidePlan.getGuidePrice() != null) {
                        totalPrice = totalPrice.add(standardGuidePlan.getGuidePrice());
                    }*/

/*芦鑫修改导游实体，暂时先注释             
                    //标准导游车费用
                    if (standardGuidePlan != null && standardGuidePlan.getGuideCarPrice() != null) {
                        totalPrice = totalPrice.add(standardGuidePlan.getGuideCarPrice());
                    }*/
                    /*
                    //人头费,小费
                    if (standardGuidePlan != null && standardGuidePlan.getCommisionPercentage() != null) {
                        totalPrice = totalPrice.add(standardGuidePlan.getCommisionPercentage());
                    }
                }

                // 景点
                if (datePlan.getSpotPlan() != null && datePlan.getSpotPlan().getSpotPlanTicket() != null && datePlan.getSpotPlan().getSpotPlanTicket().getSalePrice() != null) {
                    totalPrice = totalPrice.add(datePlan.getSpotPlan().getSpotPlanTicket().getSalePrice());
                }

                // 酒店价格
                if (datePlan.getHotelPlan() != null && datePlan.getHotelPlan().getHotelSalePrice() != null) {
                    totalPrice = totalPrice.add(datePlan.getHotelPlan().getHotelSalePrice());
                }
            }
        }
        // 保证金价格
        if (marginplan != null && marginplan.getMargintotalprice() != null) {
            totalPrice = totalPrice.add(marginplan.getMargintotalprice());
        }
        return totalPrice;
    }
    */


    /**
     * 通过orderId获取订单下的城市间交通方式，使用“,  ”分隔，没有则返回空串
     * add by limin
     * 20150630
     */
    public String getCitysTrafficByOrderId(Integer orderId) {
        return getCodes(Constant.CODE_TYPE_TRAFFIC_TYPE, getCodeIdsByOrderPk(orderId));
    }

    /**
     * 通过orderId获取订单下的航班标准，使用“,  ”分隔，没有则返回空串
     * add by limin
     * 20150630
     */
    public String getAirPlaneTypeByOrderId(Integer orderId) {

        return getCodes(Constant.CODE_TYPE_AIRPLANE_TYPE, getCodeIdsByOrderPk(orderId));
    }


    /**
     * 通过orderId获取订单下的租车喜好，使用“,  ”分隔，没有则返回空串
     * add by limin
     * 20150630
     */
    public String getCarLikeByOrderId(Integer orderId) {
        return getCodes(Constant.CODE_TYPE_CAR_LIKE, getCodeIdsByOrderPk(orderId));
    }

    /**
     * 通过orderId获取订单下的酒店类型，使用“,  ”分隔，没有则返回空串
     * add by limin
     * 20150630
     */
    public String getHotelTypeLikeByOrderId(Integer orderId) {
        return getCodes(Constant.CODE_TYPE_HOTEL, getCodeIdsByOrderPk(orderId));
    }

    /**
     * 通过Ids和类型，返回代码名称，用",  "分割
     * add by limin
     * 20150630
     */
    private String getCodes(String codeType, List<Integer> ids) {
        String codeStr = "";

        if (ids != null) {
            List<String> codeNames = _codeRepository.findByIdlist(codeType, ids);
            if (codeNames != null) {
                for (String code : codeNames) {
                    codeStr += ",  " + code;
                }
            }
        }
        return codeStr.length() > 0 ? codeStr.substring(2) : codeStr;
    }


    private List<Integer> getCodeIdsByOrderPk(Integer orderId) {
        return _codeOrderRelateRepository.findCodesByOrderPk(orderId);
    }

    /**
     * 计算订单的总金额(美元)。
     *
     * @param order 订单。
     */
    public BigDecimal calculateTotalPrices(Order order) {
        BigDecimal totalPrice = new BigDecimal(0);

        List<DatePlan> datePlans = _datePlanRepository.findByOrderId(order.getId());
        if (datePlans != null) {
            for (DatePlan datePlan : datePlans) {

                if (datePlan == null) {
                    continue;
                }

                // 交通费用
                if (datePlan.getVehiclePlan() != null && datePlan.getVehiclePlan().getSalePrice() != null) {
                    totalPrice = totalPrice.add(datePlan.getVehiclePlan().getSalePrice());
                }

                // 短途导游
               /* List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();
                for (ExcursionGuidePlan excursionGuidePlan : excursionGuidePlans) {
                    //短途导游费用
                    if (excursionGuidePlan != null && excursionGuidePlan.getGuidePrice() != null) {

                        totalPrice = totalPrice.add(excursionGuidePlan.getGuidePrice());
                    }

                    //短途导游车费用
                    if (excursionGuidePlan != null && excursionGuidePlan.getGuideCarPrice() != null) {

                        totalPrice = totalPrice.add(excursionGuidePlan.getGuideCarPrice());
                    }
                }*/

                // 标准导游
            /*    List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();
                for (StandardGuidePlan standardGuidePlan : standardGuidePlans) {
                    //标准导游费用
                    if (standardGuidePlan != null && standardGuidePlan.getGuidePrice() != null) {
                        totalPrice = totalPrice.add(standardGuidePlan.getGuidePrice());
                    }

   芦鑫修改导游实体，暂时先注释                  
                    //标准导游车费用
                    if (standardGuidePlan != null && standardGuidePlan.getGuideCarPrice() != null) {
                        totalPrice = totalPrice.add(standardGuidePlan.getGuideCarPrice());
                    }

                    //人头费
                    if (standardGuidePlan != null && standardGuidePlan.getCommisionPercentage() != null) {
                        totalPrice = totalPrice.add(standardGuidePlan.getCommisionPercentage());
                    }
                }*/

                // 景点
               /* if (datePlan.getSpotPlan() != null && datePlan.getSpotPlan().getSpotPlanTicket() != null && datePlan.getSpotPlan().getSpotPlanTicket().getSalePrice() != null) {
                    totalPrice = totalPrice.add(datePlan.getSpotPlan().getSpotPlanTicket().getSalePrice());
                }*/

          /*      // 酒店价格
                if (datePlan.getHotelPlan() != null && datePlan.getHotelPlan().getHotelSalePrice() != null) {
                    totalPrice = totalPrice.add(datePlan.getHotelPlan().getHotelSalePrice());
                }*/

            }
        }

        return totalPrice;
    }


    public String getAccompanyMemberAgeStr(Integer orderId) {
        String accoAge = "";
        if (orderId != null) {
            List<AccompanyMemberAge> list = _accompanyMemberAgeRepository.findByOrderId(orderId);
            if (list != null) {
                for (AccompanyMemberAge accompanyMemberAge : list) {
                    accoAge += "、 " + accompanyMemberAge.getAccompanymemberage();
                }
            }
        }

        return accoAge.length() > 0 ? accoAge.substring(2) : "";
    }


    public List<DatePlan> getFeedBackDatePlan(Integer orderId) {
        List<DatePlan> oriList = _datePlanRepository.findByOrderId(orderId);
        return oriList;
    }
}
