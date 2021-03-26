package com.example.shardacarpooling;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Passenger_Adapter extends FragmentPagerAdapter {
    private Context context;
    int TotalTabs;

    public Passenger_Adapter(FragmentManager fm, Context context, int TotalTabs) {
        super(fm);
        this.context = context;
        this.TotalTabs = TotalTabs;
    }
    @Override
    public int getCount()
    {
        return TotalTabs;
    }
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Bulandshahr_Driver  bulandshahr_driver = new Bulandshahr_Driver();
                return bulandshahr_driver;
            case 1:
                Noida_Driver noida_driver = new Noida_Driver();
                return noida_driver;

            case 2:
                Ghaziabad_Driver ghaziabad_driver = new Ghaziabad_Driver();
                return ghaziabad_driver;

            case 3:
                Sikandrabad_Driver sikandrabad_driver = new Sikandrabad_Driver();
                return sikandrabad_driver;
            default:
                return null;
        }
    }

}

