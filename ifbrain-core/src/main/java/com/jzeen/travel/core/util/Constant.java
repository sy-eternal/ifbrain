package com.jzeen.travel.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by limin on 15-7-1.
 */
public final class Constant {


    //小数点后两位的decimal格式
    public static final String DECIMAL_FORMAT_TWO = "0.00";

    /* 代码表类型 ----begin */
    public static final String CODE_TYPE_HOTEL = "酒店";

    public static final String CODE_TYPE_AIRPLANE_TYPE = "航班标准";

    public static final String CODE_TYPE_CAR_LIKE = "租车喜好";

    public static final String CODE_TYPE_TRAFFIC_TYPE = "交通方式";
    /* 代码表类型 ----end */

    /* --交通方式代码begin--*/
    //飞机
    public static final int CODE_TYPE_TRAFFIC_AIR = 1;
    //火车
    public static final int CODE_TYPE_TRAFFIC_TRAIN = 2;
    //租车
    public static final int CODE_TYPE_TRAFFIC_RENTAL = 3;
    //标准导游专车
    public static final int CODE_TYPE_TRAFFIC_STAND_CAR = 4;
    //短途导游专车
    public static final int CODE_TYPE_TRAFFIC_SHORT_CAR = 5;
    /* --交通方式代码begin--*/

    // 性别，男
    public static final Integer USER_GANDER_MALE = 1;
    // 性别，女
    public static final Integer USER_GANDER_FEMALE = 2;

    // 性别，男
    public static final String USER_GANDER_MALE_NAME = "男";
    // 性别，女
    public static final String USER_GANDER_FEMALE_NAME = "女";


    /* --签证状态代码begin--*/
    //未支付
    public static final int CODE_TYPE_VISA_STATUS_NOT_PAY = 0;
    //已支付
    public static final int CODE_TYPE_VISA_STATUS_PAYED = 1;
    //资料已提交
    public static final int CODE_TYPE_VISA_COMMIT_FILE = 2;
    //资料审核通过
    public static final int CODE_TYPE_VISA_AUDIT_PASS = 3;
    //预约成功
    public static final int CODE_TYPE_VISA_OPPO_SUCC = 4;
    //面试成功
    public static final int CODE_TYPE_VISA_INTERVIEW_SUCC = 5;
    //签证申请成功
    public static final int CODE_TYPE_VISA_APPLY_SUCC = 6;
    //支付失败
    public static final int CODE_TYPE_VISA_PAY_FAIL = 7;
    //资料审核失败
    public static final int CODE_TYPE_VISA_AUDIT_FAIL = 8;
    //预约失败
    public static final int CODE_TYPE_VISA_OPPO_FAIL = 9;
    //签证申请失败
    public static final int CODE_TYPE_VISA_APPLY_FAIL = 10;
    // 已邮寄
    public static final int CODE_TYPE_VISA_POSTED = 11;
    /* --签证状态代码begin--*/


    //逗号
    public static final String STR_DOT = ",";

    /* 订单状态 ----begin */
    //未提交
    public static final String ORDER_TYPE_NOT_COMMIT = "0";
    //已提交
    public static final String ORDER_TYPE_COMMITED = "1";
    //已规划
    public static final String ORDER_TYPE_PLANED = "2";
    //已结算
    public static final String ORDER_TYPE_SETTLE = "3";
    //作废
    public static final String ORDER_TYPE_INAVALID = "4";
    //重新规划
    public static final String ORDER_TYPE_REPLAN = "6";


    /* 订单状态 ----end */


    /* 行程规划类型 */
    public static final String getOrderStatus(String status) {
        if ("1".equals(status)) {
            return "已提交";
        } else if ("2".equals(status)) {
            return "已规划";
        } else if ("3".equals(status)) {
            return "已结算";
        } else if ("4".equals(status)) {
            return "已作废";
        } else if ("0".equals(status)) {
            return "未提交";
        } else {
            return "未知";
        }
    }

    /* 行程规划类型 */
    public static final String getTravelPlanType(String travelPlanType) {
        if ("1".equals(travelPlanType)) {
            return "全部行程";
        } else if ("0".equals(travelPlanType)) {
            return "部分行程";
        } else {
            return "未知";
        }
    }

    /* 是否需要导游 */
    public static final String getIsNeedGuidType(String isNeedGuid) {
        if ("0".equals(isNeedGuid)) {
            return "需要导游";
        } else if ("0".equals(isNeedGuid)) {
            return "不需要导游";
        } else {
            return "未知";
        }
    }

    //返回格式化的Decimal类型，格式0.00
    public static BigDecimal decimalFormat(BigDecimal decimal) {
        return new BigDecimal(new DecimalFormat(DECIMAL_FORMAT_TWO).format(decimal));
    }

    //返回格式化的Decimal类型，格式0.00
    public static BigDecimal decimalFormat(Double doub) {
        return new BigDecimal(doub).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
