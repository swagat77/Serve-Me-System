package com.example.homesc;

public class orderItem {
    private String orderUID;
    private String apptAddr;
    private String vendName;
    private String apptDate;
    private String apptTime;

    public orderItem(String orderUID, String apptAddr, String vendName, String apptDate, String apptTime)
    {
        this.orderUID=orderUID;
        this.apptAddr=apptAddr;
        this.vendName=vendName;
        this.apptDate=apptDate;
        this.apptTime=apptTime;
    }
    public void setOrderUID(String orderUID)
    {
        this.orderUID=orderUID;
    }
    public void setApptAddr(String apptAddr)
    {
        this.apptAddr=apptAddr;
    }
    public void setVendName(String vendName)
    {
        this.vendName=vendName;
    }
    public void setApptDate(String apptDate)
    {
        this.apptDate=apptDate;
    }
    public void setApptTime(String apptTime)
    {
        this.apptTime=apptTime;
    }

    public String getOrderUID()
    {
        return orderUID;
    }
    public String getApptAddr()
    {
        return apptAddr;
    }
    public String getVendName()
    {
        return vendName;
    }
    public String getApptDate()
    {
        return apptDate;
    }
    public String getApptTime()
    {
        return apptTime;
    }
}
