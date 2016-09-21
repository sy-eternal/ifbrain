package com.jzeen.travel.wechat.service;

import com.jzeen.travel.data.entity.WeChatRedpackActivity;
import com.jzeen.travel.data.entity.WeChatRedpackSent;
import com.jzeen.travel.data.repository.WeChatRedpackActivityRepository;
import com.jzeen.travel.data.repository.WeChatRedpackSentRepository;
import com.jzeen.travel.wechat.pojo.RedPack;
import com.jzeen.travel.wechat.pojo.RedPackSentResult;
import com.jzeen.travel.wechat.utils.RedPackUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: X2OUR_TRAVEL
 * Description: 发送红包服务类
 * Date: 2015年 08月 07日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Service
public class RedPackService {

    private static Logger log = LoggerFactory.getLogger(RedPackService.class);

    @Autowired
    WeChatRedpackSentRepository weChatRedpackSentRepository;

    @Autowired
    WeChatRedpackActivityRepository weChatRedpackActivityRepository;


    /**
     *
     * 保存红包发放结果
     *
     * @param mchBillno 商户订单ID
     * @param mchId 商户号
     * @param act_name 活动名称
     * @param reOpenid 微信号
     * @param totalAmount 金额
     * @param sendTime 发放时间
     * @param sendListid 订单号
     */
    private void saveRedPackSent(String mchBillno, String mchId, String act_name, String reOpenid, int totalAmount,
                                 int sendTime, String sendListid) {

        WeChatRedpackSent sent =  new WeChatRedpackSent();
        sent.setMchBillno(mchBillno);
        sent.setMchId(mchId);
        sent.setActName(act_name);
        sent.setReOpenid(reOpenid);
        sent.setTotalAmount(totalAmount);
        sent.setSendTime(sendTime);
        sent.setSendListid(sendListid);
        sent.setCreateTime(new Date());

        weChatRedpackSentRepository.save(sent);
    }


    /**
     * 更新状态，修改为红包已经发送
     * @param activity
     */
    private void updateRedPackRecord(WeChatRedpackActivity activity) {
        activity.setSendFlag(1);
        weChatRedpackActivityRepository.save(activity);
    }

    /**
     * 红包发送结果
     *
     * @param activity 发送列表
     * @param amount 金额
     * @param wishing 愿望语
     * @param client_ip ip地址
     * @param act_name 活动名称
     * @param remark 备注
     * @throws IllegalAccessException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws KeyManagementException
     * @throws KeyStoreException
     */
    public void sentRedPack(WeChatRedpackActivity activity, int amount, String wishing, String client_ip, String act_name, String remark) throws IllegalAccessException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        if (activity == null) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(new java.util.Date());
        str = str.replaceAll("-", "").replaceAll(":", "").replace(" ", "");

        //构造订单号 RAD + 时间（到秒） + 5位顺序号, RA2015081715311100119
        String mch_billno = "RA" + str + String.format("%05d", activity.getId());

        RedPack redPack = new RedPack(mch_billno, activity.getOpenid(), amount, wishing, client_ip, act_name, remark);
        RedPackSentResult result = RedPackUtil.sendRedPack(redPack);
//        updateRedPackRecord(activity);
        saveRedPackSent(result.getMchBillno(), result.getMchId(), "关注嘻游抢红包", result.getReOpenid(), result.getTotalAmount(),
                result.getSendTime(), result.getSendListid());

    }

}