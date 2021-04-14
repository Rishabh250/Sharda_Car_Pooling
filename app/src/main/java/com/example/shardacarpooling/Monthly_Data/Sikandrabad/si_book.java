package com.example.shardacarpooling.Monthly_Data.Sikandrabad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.shardacarpooling.Monthly_Data.Bulandshahr.book_seat;
import com.example.shardacarpooling.Monthly_Data.Delhi.delhi_book;
import com.example.shardacarpooling.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class si_book extends AppCompatActivity {

    TextView sysID,name,seats;
    EditText pasName,passID,passNumber;
    Button book;
    DatabaseReference databaseReference;
    int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_seat);

        pasName = findViewById(R.id.pass_name);
        passID = findViewById(R.id.pass_sysID);
        passNumber = findViewById(R.id.pass_mN);

        book = findViewById(R.id.seat_books);

        sysID = findViewById(R.id.bsr_sysID3);
        name = findViewById(R.id.bsr_name2);
        seats = findViewById(R.id.bsr_total_seats);


        String drname = getIntent().getStringExtra("drName");
        String drID = getIntent().getStringExtra("drSYStemID");
        String getSeats = getIntent().getStringExtra("drSeats");
        int value = Integer. parseInt(getSeats);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Monthly Cab");


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = pasName.getText().toString().trim();
                final String id = passID.getText().toString().trim();
                final String number = passNumber.getText().toString().trim();
                final String dr_sys = drID;

                int value2 = Integer. parseInt(id);
                int getdrID = Integer. parseInt(drID);

                if(getdrID == value2){
                    Toast.makeText(si_book.this, "Driver and Passenger System ID Cannot be Same !!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(name.isEmpty() && id.isEmpty() && number.isEmpty()){
                    Toast.makeText(si_book.this, "Enter Your Full Details", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(id == dr_sys){
                    Toast.makeText(si_book.this, "You cannot be your own Passenger!!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(id.length() != 10 ){
                    Toast.makeText(si_book.this, "Invalid System ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(number.length()!=10){
                    Toast.makeText(si_book.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                String getSeats = String.valueOf(value - a);
                databaseReference.child("Sikandrabad").child(drID).child("Total_Seats").setValue(getSeats);
                databaseReference.child("Sikandrabad").child(drID).child("Passengers").child(id).child("Passenger_Name").setValue(name);
                databaseReference.child("Sikandrabad").child(drID).child("Passengers").child(id).child("Passenger_ID").setValue(id);
                databaseReference.child("Sikandrabad").child(drID).child("Passengers").child(id).child("Passenger_Number").setValue(number);
                Toast.makeText(si_book.this, "Seat Booked", Toast.LENGTH_SHORT).show();

            }
        });
        name.setText(drname);
        sysID.setText(drID);
        seats.setText(getSeats);
    }
}