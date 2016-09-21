package com.jzeen.travel.wechat.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Title: X2OUR_TRAVEL
 * Description: 证书信任管理器（用于https请求）
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public class BaseX509TrustManager implements X509TrustManager {
    //检查客户端证书
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    //检查服务器端证书
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    //返回受信任的x509数组
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
