package com.jzeen.travel.website.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件配置信息。
 */
@Component
@ConfigurationProperties(prefix = "mail")
public class MailSetting
{
    /**
     * 发邮件服务器的主机地址。
     */
    private String host;

    /**
     * 发邮件服务器的端口。
     */
    private int port;

    /**
     * 用户名。
     */
    private String username;

    /**
     * 密码。
     */
    private String password;

    /**
     * 发件邮箱。
     */
    private String from;

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }
}
