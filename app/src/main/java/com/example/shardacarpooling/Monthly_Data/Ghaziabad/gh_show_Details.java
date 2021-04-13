package com.example.shardacarpooling.Monthly_Data.Ghaziabad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shardacarpooling.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class gh_show_Details extends AppCompatActivity {

    TextView seats,sysID,name,gh_car,mt,et;
    Button book;
    DatabaseReference databaseReference;
    int a = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gh_show__details);

        seats = findViewById(R.id.gh_seats);
        sysID = findViewById(R.id.gh_sysID2);
        name = findViewById(R.id.gh_name);
        book = findViewById(R.id.book);
        gh_car = findViewById(R.id.gh_car);
        mt = findViewById(R.id.gh_MT);
        et = findViewById(R.id.gh_ET);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Monthly Cab");

        seats.setText(getIntent().getStringExtra("seats"));
        sysID.setText(getIntent().getStringExtra("sysID"));
        name.setText(getIntent().getStringExtra("name"));
        gh_car.setText(getIntent().getStringExtra("model"));
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
                Intent intent = new Intent(gh_show_Details.this, gh_book.class);
                intent.putExtra("drName",getName);
                intent.putExtra("drSYStemID",sysid);
                intent.putExtra("drSeats",getSeats);
                startActivity(intent);

            }
        });
    }
}