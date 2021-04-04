package com.example.shardacarpooling.Single_Day;

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
import com.example.shardacarpooling.Single_Day.Bulandshahr.Bulandshahr_Single_driver;
import com.example.shardacarpooling.Single_Day.Delhi.Delhi_Single_driver;
import com.example.shardacarpooling.Single_Day.Ghaziabad.Ghaziabad_Single_driver;
import com.example.shardacarpooling.Single_Day.Khurja.Khurja_Single_driver;
import com.example.shardacarpooling.Single_Day.Nodia.Nodia_Single_driver;
import com.example.shardacarpooling.Single_Day.Sikandrabad.Sikandrabad_Single_driver;

public class city_select_for_single_dr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select_for_single_dr);
    }

    public void bsr02(View view) {
        startActivity(new Intent(city_select_for_single_dr.this, Bulandshahr_Single_driver.class));
    }

    public void delhi02(View view) {
        startActivity(new Intent(city_select_for_single_dr.this, Delhi_Single_driver.class));
    }

    public void ghaziabad02(View view) {
        startActivity(new Intent(city_select_for_single_dr.this, Ghaziabad_Single_driver.class));
    }

    public void khurja02(View view) {
        startActivity(new Intent(city_select_for_single_dr.this, Khurja_Single_driver.class));
    }

    public void nodia02(View view) {
        startActivity(new Intent(city_select_for_single_dr.this, Nodia_Single_driver.class));
    }

    public void sikandra02(View view) {
        startActivity(new Intent(city_select_for_single_dr.this, Sikandrabad_Single_driver.class));
    }
}