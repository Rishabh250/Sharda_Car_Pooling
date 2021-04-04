package com.example.shardacarpooling.Monthly_Data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shardacarpooling.Monthly_Data.Bulandshahr.Bulandshahr_Driver;

import com.example.shardacarpooling.Monthly_Data.Delhi.Delhi_Driver;
import com.example.shardacarpooling.Monthly_Data.Ghaziabad.Ghaziabad_Driver;
import com.example.shardacarpooling.Monthly_Data.Khurja.Khurja_Driver;
import com.example.shardacarpooling.Monthly_Data.Nodia.Nodia_Driver;
import com.example.shardacarpooling.Monthly_Data.Sikandrabad.Sikandrabad_Driver;
import com.example.shardacarpooling.R;

public class city_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
    }

    public void bsr(View view) {
        startActivity(new Intent(city_select.this, Bulandshahr_Driver.class));
    }

    public void delhi(View view) {
        startActivity(new Intent(city_select.this, Delhi_Driver.class));
    }

    public void ghaziabad(View view) {
        startActivity(new Intent(city_select.this, Ghaziabad_Driver.class));
    }

    public void khurja(View view) {
        startActivity(new Intent(city_select.this, Khurja_Driver.class));
    }

    public void nodia(View view) {
        startActivity(new Intent(city_select.this, Nodia_Driver.class));
    }

    public void sikandra(View view) {
        startActivity(new Intent(city_select.this, Sikandrabad_Driver.class));
    }
}