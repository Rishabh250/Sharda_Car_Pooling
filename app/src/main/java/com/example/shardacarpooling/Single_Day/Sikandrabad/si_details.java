package com.example.shardacarpooling.Single_Day.Sikandrabad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shardacarpooling.R;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class si_details extends AppCompatActivity {

    TextView seats,sysID,name,Single_s_si_car,mt,et;
    Button book;
    DatabaseReference databaseReference;
    int a = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_si_details);

        seats = findViewById(R.id.Single_s_si_seats);
        sysID = findViewById(R.id.Single_s_si_sysID2);
        name = findViewById(R.id.Single_s_si_name);
        book = findViewById(R.id.book);
        Single_s_si_car = findViewById(R.id.Single_s_si_car);
        mt = findViewById(R.id.Single_s_si_MT);
        et = findViewById(R.id.Single_s_si_ET);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Single Day Cab");

        seats.setText(getIntent().getStringExtra("seats"));
        sysID.setText(getIntent().getStringExtra("sysID"));
        name.setText(getIntent().getStringExtra("name"));
        Single_s_si_car.setText(getIntent().getStringExtra("model"));
        mt.setText(getIntent().getStringExtra("Date"));
        et.setText(getIntent().getStringExtra("time"));

        String sysid = sysID.getText().toString();
        String getSeats = seats.getText().toString();
        String getName = name.getText().toString();


        int value = Integer. parseInt(getSeats);

        if(value == 0){
            book.setEnabled(false);
        }

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(si_details.this, si_book.class);
                intent.putExtra("drName",getName);
                intent.putExtra("drSYStemID",sysid);
                intent.putExtra("drSeats",getSeats);
                startActivity(intent);

            }
        });
    }
}