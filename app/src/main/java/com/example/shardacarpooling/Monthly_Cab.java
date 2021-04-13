package com.example.shardacarpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Monthly_Cab extends AppCompatActivity {

    TextView dr_fullname, dr_systemID, car_model, car_number, morning_time, evening_time, dr_total_seats, dr_price;
    Button monthly_register;
    DatabaseReference databaseReference;
    Button btnEveningTimePicker, btnMorningTimePicker;
    private int eHour, eMinute, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly__cab);

        Spinner drop_city = findViewById(R.id.city_spinner);
        String[] cityItem = new String[]{"Select City", "Bulandshahr", "Khurja", "Ghaziabad", "Sikandrabad", "Noida", "Delhi"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cityItem);
        drop_city.setAdapter(adapter);


        dr_fullname = findViewById(R.id.driver_full_name);
        dr_systemID = findViewById(R.id.driver_systemID);
        car_model = findViewById(R.id.car_model);
        car_number = findViewById(R.id.car_number);
        morning_time = findViewById(R.id.morning_time);
        evening_time = findViewById(R.id.evening_time);
        dr_total_seats = findViewById(R.id.dr_seats);
        dr_price = findViewById(R.id.dr_price);
        btnMorningTimePicker = findViewById(R.id.btn_Mtime);
        btnEveningTimePicker = findViewById(R.id.btn_Etime);
        monthly_register = findViewById(R.id.car_dr_register);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Monthly Cab");


        monthly_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = dr_fullname.getText().toString().trim();
                final String sysID = dr_systemID.getText().toString().trim();
                final String carModel = car_model.getText().toString().trim();
                final String carNumber = car_number.getText().toString().trim();
                final String morningTime = morning_time.getText().toString().trim();
                final String eveningTime = evening_time.getText().toString().trim();
                final String totalSeats = dr_total_seats.getText().toString().trim();
                final String price = dr_price.getText().toString().trim();
                final String dropCity = drop_city.getSelectedItem().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(Monthly_Cab.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sysID.isEmpty()) {
                    Toast.makeText(Monthly_Cab.this, "Enter System ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dropCity == "Select City") {
                    Toast.makeText(Monthly_Cab.this, "Select City", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (carModel.isEmpty()) {
                    Toast.makeText(Monthly_Cab.this, "Enter Car Model", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (carNumber.isEmpty()) {
                    Toast.makeText(Monthly_Cab.this, "Enter Car Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (morningTime.isEmpty()) {
                    Toast.makeText(Monthly_Cab.this, "Select Morning Time", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (eveningTime.isEmpty()) {
                    Toast.makeText(Monthly_Cab.this, "Enter Evening Time", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (totalSeats.isEmpty()) {
                    Toast.makeText(Monthly_Cab.this, "Enter Total Seats Available", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (price.isEmpty()) {
                    Toast.makeText(Monthly_Cab.this, "Enter Price Per Person", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sysID.length() == 10) {
                    databaseReference.child(dropCity).child(sysID).child("Full_Name").setValue(name);
                    databaseReference.child(dropCity).child(sysID).child("System_ID").setValue(sysID);
                    databaseReference.child(dropCity).child(sysID).child("Destination").setValue(dropCity);
                    databaseReference.child(dropCity).child(sysID).child("Car_Model").setValue(carModel);
                    databaseReference.child(dropCity).child(sysID).child("Car_Number").setValue(carNumber);
                    databaseReference.child(dropCity).child(sysID).child("Morning_Time").setValue(morningTime);
                    databaseReference.child(dropCity).child(sysID).child("Evening_Time").setValue(eveningTime);
                    databaseReference.child(dropCity).child(sysID).child("Total_Seats").setValue(totalSeats);
                    databaseReference.child(dropCity).child(sysID).child("Price").setValue(price);

                    Toast.makeText(Monthly_Cab.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Monthly_Cab.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMorningTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeMorningPicker();
            }
        });
        btnEveningTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeEveningPicker();
            }
        });
    }

    public void timeMorningPicker() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(Monthly_Cab.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        morning_time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void timeEveningPicker() {
        final Calendar c = Calendar.getInstance();
        eHour = c.get(Calendar.HOUR_OF_DAY);
        eMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Monthly_Cab.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        evening_time.setText(hourOfDay + ":" + minute);
                    }
                }, eHour, eMinute, false);
        timePickerDialog.show();
    }
}