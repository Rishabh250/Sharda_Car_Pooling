package com.example.shardacarpooling;

public class list {
    String Full_Name, System_ID,Car_Model,Car_Number,Destination,Morning_Time,Evening_Time,Price,Total_Seats;

    list(){}

    public list(String full_Name, String system_ID, String car_Model, String car_Number, String destination, String morning_Time, String evening_Time, String price, String total_Seats) {
        Full_Name = full_Name;
        System_ID = system_ID;
        Car_Model = car_Model;
        Car_Number = car_Number;
        Destination = destination;
        Morning_Time = morning_Time;
        Evening_Time = evening_Time;
        Price = price;
        Total_Seats = total_Seats;
    }


    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getSystem_ID() {
        return System_ID;
    }

    public void setSystem_ID(String system_ID) {
        System_ID = system_ID;
    }

    public String getCar_Model() {
        return Car_Model;
    }

    public void setCar_Model(String car_Model) {
        Car_Model = car_Model;
    }

    public String getCar_Number() {
        return Car_Number;
    }

    public void setCar_Number(String car_Number) {
        Car_Number = car_Number;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getMorning_Time() {
        return Morning_Time;
    }

    public void setMorning_Time(String morning_Time) {
        Morning_Time = morning_Time;
    }

    public String getEvening_Time() {
        return Evening_Time;
    }

    public void setEvening_Time(String evening_Time) {
        Evening_Time = evening_Time;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotal_Seats() {
        return Total_Seats;
    }

    public void setTotal_Seats(String total_Seats) {
        Total_Seats = total_Seats;
    }
}