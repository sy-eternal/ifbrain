package com.jzeen.travel.wechat.service;

import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.GuideGuideTypePlanRateRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.wechat.utils.TemplateMsgUtil;
import com.jzeen.travel.wechat.conf.WeChatConts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description: 模板消息工具类
 * Date: 2015年 08月 05日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */

@Service
public class TemplateMsgService {

    private static Logger log = LoggerFactory.getLogger(TemplateMsgService.class);

    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    GuideGuideTypePlanRateRepository _guideTypePlanRateRepository;


    public void scanTemplateMsg() {
        try {
            //线路预约成功提醒
            sendOrderCommitMsg();
        } catch (Exception e) {
            log.error("线路预约成功提醒模板消息发送失败:{} ", e.toString());
        }

        try {
            //线路规划完成提醒
            sendPlanFinishMsg();
        } catch (Exception e) {
            log.error("线路规划成功提醒模板消息发送失败:{} ", e.toString());
        }

        try {
            //导游任务派单提醒
            sendGuideCommitMsg();
        } catch (Exception e) {
            log.error("导游派单提醒模板消息发送失败:{} ", e.toString());
        }
    }

    /**
     * 线路预约成功提醒
     */
    public void sendOrderCommitMsg() {
        log.info("扫描用户需求提交数据");

        // 未提交状态的订单, 时间为1个小时内的订单
        List<Order> orderList = orderRepository.findByOrderStatusAndCreateTimeAfter(0, new Date(System.currentTimeMillis() - 60 * 60 * 1000));

        if (orderList != null) {
            for (Order order : orderList) {
                if (new Date().getTime() - order.getCreateTime().getTime() < WeChatConts.templateMsgScanTime) {

                    User user = order.getTraveler();
                    String toUser = user.getWechat();
                    String templateId = "bGO2m1FNM_T6v6o8GtDn9d-VTkCLT_2Xg8GIHDn45QE";
                    String url = "http://www.x2our.com";
                    String first = "恭喜哦！您的定制旅游需求已经收到，我们将在24小时内提交专属于您的线路规划。同时如信息有问题我们将会联系您。";
                    String keyword1 = order.getDuration() != null ? "美国" + order.getDuration() + "日游" : "美国旅游";
                    String keyword2 = String.valueOf(order.getStartDate());
                    String keyword3 = String.valueOf(order.getPersonCount());
                    String keyword4 = order.getRemark();
                    String remark = "客服电话：18672971315";

                    // 如果用户的微信号为空，则不发送模板消息
                    if (toUser == null) {
                        continue;
                    }

                    TemplateMsgUtil.sendTemplateMsg(toUser, templateId, url, first, keyword1, keyword2, keyword3, keyword4, remark);
                }
            }
        }
    }

    /**
     * 线路规划完成提醒
     */
    public void sendPlanFinishMsg() {
        log.info("扫描规划完成数据");

        // 未提交状态的订单, 时间为1个小时内的订单
        List<Order> orderList = orderRepository.findByOrderStatusAndCommitOrderTimeAfter(2, new Date(System.currentTimeMillis() - 60 * 60 * 1000));

        if (orderList != null) {
            for (Order order : orderList) {
                if (new Date().getTime() - order.getCreateTime().getTime() < WeChatConts.templateMsgScanTime) {

                    User user = order.getTraveler();
                    String toUser = user.getWechat();
                    String templateId = "ZzBlost9o69xjGL-4TkG2POxKYzenT4pUBTcySX8nMo";
                    String url = "http://www.x2our.com";
                    String first = user.getFirstName() + user.getLastName() + "，您好！根据您提交的定制旅游需求，我们已经规划好了您的行程。";
                    String keyword1 = "详细的行程规划已经发送到您的邮箱 " + user.getEmail() + "请注意查收！";
                    String keyword2 = String.valueOf(order.getStartDate());
                    String keyword3 = String.valueOf(order.getPersonCount());
                    String remark = "请您登陆我们的官网www.x2our.com查询您的专属旅游规划内容";

                    // 如果用户的微信号为空，则不发送模板消息
                    if (toUser == null) {
                        continue;
                    }

                    TemplateMsgUtil.sendTemplateMsg(toUser, templateId, url, first, keyword1, keyword2, keyword3, remark);
                }
            }
        }
    }

    /**
     * 导游派单提醒
     */
    public void sendGuideCommitMsg() {
        log.info("扫描导游派单提交数据");
        List<GuideGuidetypePlanRelate> guideList = _guideTypePlanRateRepository.findByCreateTime(new Date(System.currentTimeMillis() - 60 * 60 * 1000));
        if (guideList != null) {
            for (GuideGuidetypePlanRelate guidetypePlanRelate : guideList) {
                if (new Date().getTime() - guidetypePlanRelate.getCreateTime().getTime() < WeChatConts.templateMsgScanTime) {

                    /*User user = order.getTraveler();*/

                    if (guidetypePlanRelate.getGuideTypePlan() != null && guidetypePlanRelate.getGuideTypePlan().getGuideActivityPlan() != null && guidetypePlanRelate.getGuideTypePlan().getAppointedStatus()==1) {
                        DatePlan datePlan = guidetypePlanRelate.getGuideTypePlan().getGuideActivityPlan().getDatePlan();
                        if (datePlan != null) {
                            Guide guide = guidetypePlanRelate.getGuide();
                            String toUser = guide.getUser().getWechat();
                            String templateId = "bGO2m1FNM_T6v6o8GtDn9d-VTkCLT_2Xg8GIHDn45QE";
                            String url = "http://www.x2our.com";
                            String first = guide.getUser().getFirstName() + guide.getUser().getLastName() + "，您好！您刚刚收到一条任务:";
                            String keyword1 = guidetypePlanRelate.getGuideTypePlan().getGuideActivityPlan().getCity().getCityName();
                            String keyword2 = String.valueOf(datePlan.getDate() != null ? datePlan.getDate() : datePlan.getOrdinatedday());
                            String keyword3 = String.valueOf(datePlan.getOrder().getPersonCount());
                            String keyword4 = datePlan.getOrder().getOrderNumber();
                            String remark = "详情";

                            // 如果用户的微信号为空，则不发送模板消息
                            if (toUser == null) {
                                continue;
                            }
                            TemplateMsgUtil.sendTemplateMsg(toUser, templateId, url, first, keyword1, keyword2, keyword3, keyword4, remark);
                        }
                    }
                }
            }
        }
    }
}