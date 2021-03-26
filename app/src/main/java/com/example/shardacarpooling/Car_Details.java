package com.example.shardacarpooling;

public class Car_Details {

    String Full_Name,Syatem_ID,Destination,PickUP_Location,Car_Model,Car_Number,Date,Time,Seats;

    public Car_Details(){}

    public Car_Details(String full_Name, String syatem_ID, String destination, String pickUP_Location, String car_Model, String car_Number, String date, String time, String seats) {
        Full_Name = full_Name;
        Syatem_ID = syatem_ID;
        Destination = destination;
        PickUP_Location = pickUP_Location;
        Car_Model = car_Model;
        Car_Number = car_Number;
        Date = date;
        Time = time;
        Seats = seats;
    }
}
