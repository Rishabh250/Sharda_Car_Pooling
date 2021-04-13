package com.example.shardacarpooling.Single_Day.Delhi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shardacarpooling.R;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dl_book extends AppCompatActivity {

    TextView sysID,name,seats;
    EditText pasName,passID,passNumber;
    Button book;
    DatabaseReference databaseReference;
    int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dl_book);

        pasName = findViewById(R.id.pass_name);
        passID = findViewById(R.id.pass_sysID);
        passNumber = findViewById(R.id.pass_mN);

        book = findViewById(R.id.seat_books);

        sysID = findViewById(R.id.dl_sysID3);
        name = findViewById(R.id.dl_name2);
        seats = findViewById(R.id.dl_total_seats);


        String drname = getIntent().getStringExtra("drName");
        String drID = getIntent().getStringExtra("drSYStemID");
        String getSeats = getIntent().getStringExtra("drSeats");
        int value = Integer. parseInt(getSeats);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Single Day Cab");


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = pasName.getText().toString().trim();
                final String id = passID.getText().toString().trim();
                final String number = passNumber.getText().toString().trim();
                final String dr_sys = drID;

                if(name.isEmpty() && id.isEmpty() && number.isEmpty()){
                    Toast.makeText(dl_book.this, "Enter Your Full Details", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(id == dr_sys){
                    Toast.makeText(dl_book.this, "You cannot be your own Passenger!!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(id.length() != 10 ){
                    Toast.makeText(dl_book.this, "Invalid System ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(number.length()!=10){
                    Toast.makeText(dl_book.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                String getSeats = String.valueOf(value - a);
                databaseReference.child("Delhi").child(drID).child("Total_Seats").setValue(getSeats);
                databaseReference.child("Delhi").child(drID).child("Passengers").child(id).child("Passenger_Name").setValue(name);
                databaseReference.child("Delhi").child(drID).child("Passengers").child(id).child("Passenger_ID").setValue(id);
                databaseReference.child("Delhi").child(drID).child("Passengers").child(id).child("Passenger_Number").setValue(number);

                Toast.makeText(dl_book.this, "Seat Booked", Toast.LENGTH_SHORT).show();
            }
        });
        name.setText(drname);
        sysID.setText(drID);
        seats.setText(getSeats);
    }
}