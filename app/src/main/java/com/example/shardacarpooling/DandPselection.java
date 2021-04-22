package com.example.shardacarpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DandPselection extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    FirebaseAuth auth;
    TextView name_driver,name;
    RadioButton driver,passenger;
    float v = 0;
    Button buttonDP,buttonDP2,selectONE,logout;
    SharedPreferences sharedPreferences;

    private static final String Shared_Pref_Name = "mypref";
    private static final String Key_Email = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dand_pselection);

        auth = FirebaseAuth.getInstance();
        driver = findViewById(R.id.driver);
        passenger= findViewById(R.id.passenger);
        buttonDP = findViewById(R.id.dandpbutton);
        buttonDP2 = findViewById(R.id.dandpbutton2);
        name_driver = findViewById(R.id.textView10);
        selectONE = findViewById(R.id.select_one);
        logout = findViewById(R.id.logout);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Members");
        userID = user.getUid();
        sharedPreferences = getSharedPreferences(Shared_Pref_Name,MODE_PRIVATE);

        String email = sharedPreferences.getString(Key_Email,null);

        if(email!=null){
            name_driver.setText(email);
        }

        selectONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DandPselection.this, "Select One Option Driver or Passenger ", Toast.LENGTH_SHORT).show();
                return;
            }
        });


        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce1 = AnimationUtils.loadAnimation(DandPselection.this, R.anim.bounce);
                MyBounceInterpolator myBounceInterpolator = new MyBounceInterpolator(0.2,20);
                bounce1.setInterpolator(myBounceInterpolator);
                driver.startAnimation(bounce1);
                passenger.setChecked(false);
                buttonDP.setVisibility(View.INVISIBLE);
                buttonDP2.setVisibility(View.VISIBLE);
                selectONE.setVisibility(View.INVISIBLE);
            }
        });

        passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce2 = AnimationUtils.loadAnimation(DandPselection.this, R.anim.bounce);
                MyBounceInterpolator myBounceInterpolator = new MyBounceInterpolator(0.2,20);
                bounce2.setInterpolator(myBounceInterpolator);
                passenger.startAnimation(bounce2);
                driver.setChecked(false);
                buttonDP2.setVisibility(View.INVISIBLE);
                buttonDP.setVisibility(View.VISIBLE);
                selectONE.setVisibility(View.INVISIBLE);

            }
        });
        buttonDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DandPselection.this,Passenger_SingleORmonthly.class));
            }
        });

        buttonDP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DandPselection.this,Driver_Selection_Page.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.clear();
               editor.apply();
               startActivity(new Intent(DandPselection.this,Login_Activity.class));
               finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}