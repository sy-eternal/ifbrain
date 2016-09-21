package com.jzeen.travel.service.sms;

/**
 * WmgwLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
public class WmgwLocator extends org.apache.axis.client.Service implements Wmgw {

    public WmgwLocator() {
    }


    public WmgwLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WmgwLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wmgwSoap12
    private String wmgwSoap12_address = "http://61.145.229.29:9003/MWGate/wmgw.asmx";

    public String getwmgwSoap12Address() {
        return wmgwSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private String wmgwSoap12WSDDServiceName = "wmgwSoap12";

    public String getwmgwSoap12WSDDServiceName() {
        return wmgwSoap12WSDDServiceName;
    }

    public void setwmgwSoap12WSDDServiceName(String name) {
        wmgwSoap12WSDDServiceName = name;
    }

    public WmgwSoap_PortType getwmgwSoap12() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wmgwSoap12_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwmgwSoap12(endpoint);
    }

    public WmgwSoap_PortType getwmgwSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WmgwSoap12Stub _stub = new WmgwSoap12Stub(portAddress, this);
            _stub.setPortName(getwmgwSoap12WSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwmgwSoap12EndpointAddress(String address) {
        wmgwSoap12_address = address;
    }


    // Use to get a proxy class for wmgwSoap
    private String wmgwSoap_address = "http://61.145.229.29:9003/MWGate/wmgw.asmx";

    public String getwmgwSoapAddress() {
        return wmgwSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private String wmgwSoapWSDDServiceName = "wmgwSoap";

    public String getwmgwSoapWSDDServiceName() {
        return wmgwSoapWSDDServiceName;
    }

    public void setwmgwSoapWSDDServiceName(String name) {
        wmgwSoapWSDDServiceName = name;
    }

    public WmgwSoap_PortType getwmgwSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wmgwSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwmgwSoap(endpoint);
    }

    public WmgwSoap_PortType getwmgwSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WmgwSoap_BindingStub _stub = new WmgwSoap_BindingStub(portAddress, this);
            _stub.setPortName(getwmgwSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwmgwSoapEndpointAddress(String address) {
        wmgwSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (WmgwSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                WmgwSoap12Stub _stub = new WmgwSoap12Stub(new java.net.URL(wmgwSoap12_address), this);
                _stub.setPortName(getwmgwSoap12WSDDServiceName());
                return _stub;
            }
            if (WmgwSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                WmgwSoap_BindingStub _stub = new WmgwSoap_BindingStub(new java.net.URL(wmgwSoap_address), this);
                _stub.setPortName(getwmgwSoapWSDDServiceName());
                return _stub;
            }
        } catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("wmgwSoap12".equals(inputPortName)) {
            return getwmgwSoap12();
        } else if ("wmgwSoap".equals(inputPortName)) {
            return getwmgwSoap();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "wmgw");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "wmgwSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "wmgwSoap"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

        if ("wmgwSoap12".equals(portName)) {
            setwmgwSoap12EndpointAddress(address);
        } else if ("wmgwSoap".equals(portName)) {
            setwmgwSoapEndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
