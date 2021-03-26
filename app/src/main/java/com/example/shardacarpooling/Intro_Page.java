package com.example.shardacarpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Intro_Page extends AppCompatActivity {

    ImageView shardaLogo;
    TextView textView01, textView02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__page);

        if(!isNetworkAvailable(Intro_Page.this)) {
            Toast.makeText(this, "Internet Connection is not Avialable", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                public void run() {
                    startActivity(new Intent(Intro_Page.this, Intro_Page.class));
                    finish();
                }
            }, 3000);
        }else{
            new Timer().schedule(new TimerTask() {
                public void run() {
                    startActivity(new Intent(Intro_Page.this, Get_Started.class));
                    finish();
                }
            }, 2000);
        }

        shardaLogo = findViewById(R.id.imageView);
        textView01 = findViewById(R.id.textView);
        textView02 = findViewById(R.id.textView2);


    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}