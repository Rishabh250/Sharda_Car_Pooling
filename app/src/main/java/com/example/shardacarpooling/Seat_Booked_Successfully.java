package com.example.shardacarpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Seat_Booked_Successfully extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat__booked__successfully);
    }

    public void homePage(View view) {
        startActivity(new Intent(Seat_Booked_Successfully.this,DandPselection.class));
        finish();
    }
}