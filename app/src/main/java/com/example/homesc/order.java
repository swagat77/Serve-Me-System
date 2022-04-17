package com.example.homesc;

public class order {
    int price;
    String date;
    String time;
    String vendorName;
    String address;

    public order(int price, String date, String time, String vendorName, String address)
    {
        this.price = price;
        this.date = date;
        this.time = time;
        this.vendorName = vendorName;
        this.address = address;

    }

}
