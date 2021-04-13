package com.example.shardacarpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Driver extends AppCompatActivity {


    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime, dr_fullName, dr_sysID, dr_seats, dr_carModel, dr_car_number,dr_price;
    private int mYear, mMonth, mDay, mHour, mMinute;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Spinner city = findViewById(R.id.single_city_spinner);
        String[] cityItem = new String[]{"Select City", "Bulandshahr", "Khurja", "Ghaziabad", "Sikandrabad", "Noida", "Delhi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cityItem);
        city.setAdapter(adapter);

        Spinner gate = findViewById(R.id.single_driver_pickup);
        String[] gateItem = new String[]{"Select Gate", "Gate : 1", "Gate : 2", "Gate : 3", "Gate : 4"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gateItem);
        gate.setAdapter(adapter2);


        dr_fullName = findViewById(R.id.single_driver_full_name);
        dr_sysID = findViewById(R.id.single_driver_systemID);
        dr_seats = findViewById(R.id.single_dr_seats);
        dr_carModel = findViewById(R.id.single_car_model);
        dr_car_number = findViewById(R.id.single_car_number);
        dr_price = findViewById(R.id.single_dr_price);
        btnDatePicker = findViewById(R.id.single_btn_date);
        btnTimePicker = findViewById(R.id.single_btn_time);
        txtDate = findViewById(R.id.single_in_date);
        txtTime = findViewById(R.id.single_in_time);
        Button register = findViewById(R.id.single_car_dr_register);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Single Day Cab");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = dr_fullName.getText().toString().trim();
                final String sysID = dr_sysID.getText().toString().trim();
                final String seats = dr_seats.getText().toString().trim();
                final String carModel = dr_carModel.getText().toString().trim().toUpperCase();
                final String carNumber = dr_car_number.getText().toString().trim().toUpperCase();
                final String price = dr_price.getText().toString().trim();
                final String date = txtDate.getText().toString().trim();
                final String time = txtTime.getText().toString().trim();
                final String dropCity = city.getSelectedItem().toString().trim();
                final String pickUp = gate.getSelectedItem().toString().trim();




                if (name.isEmpty()) {
                    Toast.makeText(Driver.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sysID.isEmpty()) {
                    Toast.makeText(Driver.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sysID.length() != 10) {
                    Toast.makeText(Driver.this, "Invalid System ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dropCity == "Select City") {
                    Toast.makeText(Driver.this, "Select City", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pickUp == "Select Gate") {
                    Toast.makeText(Driver.this, "Select Gate", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (carModel.isEmpty()) {
                    Toast.makeText(Driver.this, "Enter Car Model", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (carNumber.isEmpty()) {
                    Toast.makeText(Driver.this, "Enter Car Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (date.isEmpty()) {
                    Toast.makeText(Driver.this, "Select Date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (time.isEmpty()) {
                    Toast.makeText(Driver.this, "Select Time", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (seats.isEmpty()) {
                    Toast.makeText(Driver.this, "Enter Available Seats", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (price.isEmpty()) {
                    Toast.makeText(Driver.this, "Enter Price", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(sysID.length()==10)
                {
                    databaseReference.child(dropCity).child(sysID).child("Full_Name").setValue(name);
                    databaseReference.child(dropCity).child(sysID).child("System_ID").setValue(sysID);
                    databaseReference.child(dropCity).child(sysID).child("Destination").setValue(dropCity);
                    databaseReference.child(dropCity).child(sysID).child("Pick_up_Location").setValue(pickUp);
                    databaseReference.child(dropCity).child(sysID).child("Car_Model").setValue(carModel);
                    databaseReference.child(dropCity).child(sysID).child("Car_Number").setValue(carNumber);
                    databaseReference.child(dropCity).child(sysID).child("Date").setValue(date);
                    databaseReference.child(dropCity).child(sysID).child("Time").setValue(time);
                    databaseReference.child(dropCity).child(sysID).child("Total_Seats").setValue(seats);
                    databaseReference.child(dropCity).child(sysID).child("Price").setValue(price);

                    Toast.makeText(Driver.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Driver.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }



            }
        });



        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker();
            }
        });
    }


    public void datePicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(Driver.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void timePicker() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(Driver.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
}
