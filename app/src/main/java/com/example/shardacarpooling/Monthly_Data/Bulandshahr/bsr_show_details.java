package com.example.shardacarpooling.Monthly_Data.Bulandshahr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shardacarpooling.R;
import com.example.shardacarpooling.list;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class bsr_show_details extends AppCompatActivity {

    TextView seats,sysID,name,bsr_car,mt,et;
    Button book;
    DatabaseReference databaseReference;
    int a = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsr_show_details);

        seats = findViewById(R.id.bsr_seats);
        sysID = findViewById(R.id.bsr_sysID2);
        name = findViewById(R.id.bsr_name);
        book = findViewById(R.id.book);
        bsr_car = findViewById(R.id.bsr_car);
        mt = findViewById(R.id.bsr_MT);
        et = findViewById(R.id.bsr_ET);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Monthly Cab");

        seats.setText(getIntent().getStringExtra("seats"));
        sysID.setText(getIntent().getStringExtra("sysID"));
        name.setText(getIntent().getStringExtra("name"));
        bsr_car.setText(getIntent().getStringExtra("model"));
        mt.setText(getIntent().getStringExtra("MT"));
        et.setText(getIntent().getStringExtra("ET"));

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
                Intent intent = new Intent(bsr_show_details.this,book_seat.class);
                intent.putExtra("drName",getName);
                intent.putExtra("drSYStemID",sysid);
                intent.putExtra("drSeats",getSeats);
                startActivity(intent);

            }
        });


    }
}