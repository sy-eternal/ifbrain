package com.jzeen.travel.service.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: X2OUR_TRAVEL
 * Description: 短信发送服务类
 * Date: 2015年 08月 24日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class SmsService {

    private static Logger log = LoggerFactory.getLogger(SmsService.class);

    /**
     * 发送短信消息
     *
     * @param pszMobis 手机号码
     * @param pszMsg   短信内容
     * @throws Exception
     */
    public static void smsSend(String pszMobis, String pszMsg) throws Exception {
        WmgwSoap12Stub binding = null;

        try {
            binding = (WmgwSoap12Stub)
                    new WmgwLocator().getwmgwSoap12();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
        }

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        String value = null;

        value = binding.mongateCsSpSendSmsNew("JC2513", "851202", pszMobis, pszMsg, 1, "*");
        // TBD - validate results

        log.info("短信发送返回值:{}", value);
    }
}