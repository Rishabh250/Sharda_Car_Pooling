package com.example.shardacarpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Get_Started extends AppCompatActivity {

    ImageView arrow;
    ImageButton imageButton;
    float v =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__started);

        arrow = findViewById(R.id.arrowImg);
        imageButton = findViewById(R.id.getstartedBTN);
        arrow.setTranslationX(-800);
        arrow.setAlpha(v);
        arrow.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Get_Started.this,Login_Activity.class));
                finish();
            }
        });

    }
}