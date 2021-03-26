package com.example.shardacarpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;

public class Driver_Selection_Page extends AppCompatActivity {

    RadioButton monthlyCab,singleDayCab;
    Button driverbtn01,driverbtn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__selection__page);

        monthlyCab = findViewById(R.id.monthly);
        singleDayCab = findViewById(R.id.singleDay);
        driverbtn01 = findViewById(R.id.driverbutton01);
        driverbtn02 = findViewById(R.id.driverbutton02);

        monthlyCab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce1 = AnimationUtils.loadAnimation(Driver_Selection_Page.this, R.anim.bounce);
                MyBounceInterpolator myBounceInterpolator = new MyBounceInterpolator(0.2,20);
                bounce1.setInterpolator(myBounceInterpolator);
                monthlyCab.startAnimation(bounce1);
                singleDayCab.setChecked(false);
                driverbtn02.setVisibility(View.INVISIBLE);
                driverbtn01.setVisibility(View.VISIBLE);

            }
        });

        singleDayCab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce1 = AnimationUtils.loadAnimation(Driver_Selection_Page.this, R.anim.bounce);
                MyBounceInterpolator myBounceInterpolator = new MyBounceInterpolator(0.2,20);
                bounce1.setInterpolator(myBounceInterpolator);
                singleDayCab.startAnimation(bounce1);
                monthlyCab.setChecked(false);
                driverbtn01.setVisibility(View.INVISIBLE);
                driverbtn02.setVisibility(View.VISIBLE);

            }
        });

        driverbtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Driver_Selection_Page.this,Monthly_Cab.class));
            }
        });

        driverbtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Driver_Selection_Page.this,Driver.class));
            }
        });
    }
}