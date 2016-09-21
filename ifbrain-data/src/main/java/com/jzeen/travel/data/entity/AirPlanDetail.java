package com.jzeen.travel.data.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AirPlanDetail
{
    
    /**
     * 机型
     */
     private String stopEquipType;
     /**
      * 航班号
      * @return
      */
    private String stopFlightNumber;
     /**
      * 飞行时间
      * @return
      */
    private String stopElapsedTime;
    /**
     * 舱位
     * @return
     */
    private String stopCabin;
    
    /**
     * 停留时间
     * @return
     */
    private String stopMin;
  
    /**
     * 出发时间
     */
   private String stopDepartureDateTime;
 
    /**
     * 到达时间
     */
   private String stopArrivalDateTime;
   
   /**
    * 出发站航楼
    * @return
    */
   private String departureTerminalId;
   /**
    * 到达站航楼
    * @return
    */
   private String   arrivalTerminalId;
   
   /**
    * 出发机场
    * @return
    */
   private String stopDepartureAirport;
     /**
      * 到达机场
      * @return
      */
 private String stopArrivalAirport;
 
    public String getDepartureTerminalId()
    {
        return departureTerminalId;
    }

    public void setDepartureTerminalId(String departureTerminalId)
    {
        this.departureTerminalId = departureTerminalId;
    }

    public String getArrivalTerminalId()
    {
        return arrivalTerminalId;
    }

    public void setArrivalTerminalId(String arrivalTerminalId)
    {
        this.arrivalTerminalId = arrivalTerminalId;
    }

    public String getStopEquipType()
    {
        return stopEquipType;
    }

    public void setStopEquipType(String stopEquipType)
    {
        this.stopEquipType = stopEquipType;
    }

    public String getStopFlightNumber()
    {
        return stopFlightNumber;
    }

    public void setStopFlightNumber(String stopFlightNumber)
    {
        this.stopFlightNumber = stopFlightNumber;
    }

    public String getStopElapsedTime()
    {
        return stopElapsedTime;
    }

    public void setStopElapsedTime(String stopElapsedTime)
    {
        this.stopElapsedTime = stopElapsedTime;
    }

    public String getStopCabin()
    {
        return stopCabin;
    }

    public void setStopCabin(String stopCabin)
    {
        this.stopCabin = stopCabin;
    }

    public String getStopMin()
    {
        return stopMin;
    }

    public void setStopMin(String stopMin)
    {
        this.stopMin = stopMin;
    }

    public String getStopDepartureDateTime()
    {
        return stopDepartureDateTime;
    }

    public void setStopDepartureDateTime(String stopDepartureDateTime)
    {
        this.stopDepartureDateTime = stopDepartureDateTime;
    }

    public String getStopArrivalDateTime()
    {
        return stopArrivalDateTime;
    }

    public void setStopArrivalDateTime(String stopArrivalDateTime)
    {
        this.stopArrivalDateTime = stopArrivalDateTime;
    }
    
    
    
    
    
    
}
