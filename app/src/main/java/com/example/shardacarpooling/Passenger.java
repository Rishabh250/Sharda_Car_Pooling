package com.example.shardacarpooling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class Passenger extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPage2;
    float v =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        tabLayout = findViewById(R.id.tablayout1);
        viewPage2 = findViewById(R.id.viewpage2);


        tabLayout.addTab(tabLayout.newTab().setText("BSR"));
        tabLayout.addTab(tabLayout.newTab().setText("Noida"));
        tabLayout.addTab(tabLayout.newTab().setText("Ghaziabad "));
        tabLayout.addTab(tabLayout.newTab().setText("Sikandrabad"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final com.example.shardacarpooling.Passenger_Adapter adapter = new com.example.shardacarpooling.Passenger_Adapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPage2.setAdapter(adapter);
        viewPage2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPage2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

    }
}