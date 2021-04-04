package com.example.shardacarpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.shardacarpooling.Monthly_Data.city_select;
import com.example.shardacarpooling.Single_Day.city_select_for_single_dr;

public class Passenger_SingleORmonthly extends AppCompatActivity {

    RadioButton passengerSingleDay,passengerMonthlyCab;
    Button pbtn01,pbtn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger__single_o_rmonthly);

        passengerSingleDay = findViewById(R.id.pSingle);
        passengerMonthlyCab = findViewById(R.id.pMonthly);
        pbtn01 = findViewById(R.id.pbutton1);
        pbtn02 = findViewById(R.id.pbutton2);

        passengerSingleDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce1 = AnimationUtils.loadAnimation(Passenger_SingleORmonthly.this, R.anim.bounce);
                MyBounceInterpolator myBounceInterpolator = new MyBounceInterpolator(0.2,20);
                bounce1.setInterpolator(myBounceInterpolator);
                passengerSingleDay.startAnimation(bounce1);
                passengerMonthlyCab.setChecked(false);
                pbtn01.setVisibility(View.INVISIBLE);
                pbtn02.setVisibility(View.VISIBLE);
            }
        });

        passengerMonthlyCab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce1 = AnimationUtils.loadAnimation(Passenger_SingleORmonthly.this, R.anim.bounce);
                MyBounceInterpolator myBounceInterpolator = new MyBounceInterpolator(0.2,20);
                bounce1.setInterpolator(myBounceInterpolator);
                passengerMonthlyCab.startAnimation(bounce1);
                passengerSingleDay.setChecked(false);
                pbtn02.setVisibility(View.INVISIBLE);
                pbtn01.setVisibility(View.VISIBLE);
            }
        });

        pbtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Passenger_SingleORmonthly.this, city_select.class));
            }
        });

        pbtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Passenger_SingleORmonthly.this, city_select_for_single_dr.class));
            }
        });
    }
}