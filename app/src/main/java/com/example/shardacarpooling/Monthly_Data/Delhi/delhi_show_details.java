package com.example.shardacarpooling.Monthly_Data.Delhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.shardacarpooling.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class delhi_show_details extends AppCompatActivity {

    TextView seats,sysID,name,delhi_car,mt,et;
    Button book;
    DatabaseReference databaseReference;
    int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delhi_show_details);
        seats = findViewById(R.id.delhi_seats);
        sysID = findViewById(R.id.delhi_sysID2);
        name = findViewById(R.id.delhi_name);
        book = findViewById(R.id.book);
        delhi_car = findViewById(R.id.delhi_car);
        mt = findViewById(R.id.delhi_MT);
        et = findViewById(R.id.delhi_ET);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Monthly Cab");

        seats.setText(getIntent().getStringExtra("seats"));
        sysID.setText(getIntent().getStringExtra("sysID"));
        name.setText(getIntent().getStringExtra("name"));
        delhi_car.setText(getIntent().getStringExtra("model"));
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

                Intent intent = new Intent(delhi_show_details.this, delhi_book.class);
                intent.putExtra("drName",getName);
                intent.putExtra("drSYStemID",sysid);
                intent.putExtra("drSeats",getSeats);
                startActivity(intent);

            }
        });


    }
}