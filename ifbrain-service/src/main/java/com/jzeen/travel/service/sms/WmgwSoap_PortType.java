package com.jzeen.travel.service.sms;

/**
 * WmgwSoap_PortType.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
public interface WmgwSoap_PortType extends java.rmi.Remote {

    /**
     * 短信息发送接口
     */
    public String mongateCsSpSendSmsNew(String userId, String password, String pszMobis, String pszMsg, int iMobiCount, String pszSubPort) throws java.rmi.RemoteException;

    /**
     * 短信息发送接口
     */
    public String mongateSendSubmit(String userId, String password, String pszMobis, String pszMsg, int iMobiCount, String pszSubPort, String msgId) throws java.rmi.RemoteException;

    /**
     * 短信息发送接口
     */
    public String mongateMULTIXSend(String userId, String password, String multixmt) throws java.rmi.RemoteException;

    /**
     * 接收上行信息接口
     */
    public ArrayOfString mongateCsGetSmsExEx(String userId, String password) throws java.rmi.RemoteException;

    /**
     * 获取状态报告接口
     */
    public ArrayOfString mongateCsGetStatusReportExEx(String userId, String password) throws java.rmi.RemoteException;

    /**
     * 查询余额接口
     */
    public int mongateQueryBalance(String userId, String password) throws java.rmi.RemoteException;

    /**
     * 获取上行/状态报告接口
     */
    public ArrayOfString mongateGetDeliver(String userId, String password, int iReqType) throws java.rmi.RemoteException;
}
