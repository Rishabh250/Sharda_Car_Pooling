package com.example.shardacarpooling.Single_Day.Bulandshahr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shardacarpooling.Monthly_Data.Bulandshahr.book_seat;
import com.example.shardacarpooling.R;
import com.example.shardacarpooling.Seat_Booked_Successfully;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bsr_book extends AppCompatActivity {
    TextView sysID,name,seats,pasName,passNumber;
    EditText passID;
    Button book,getDetails;
    DatabaseReference databaseReference;
    DatabaseReference ref2;
    int a = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsr_book);

        pasName = findViewById(R.id.pass_name);
        passID = findViewById(R.id.pass_sysID);
        passNumber = findViewById(R.id.pass_mN);

        book = findViewById(R.id.seat_books);
        getDetails = findViewById(R.id.seat_books2);

        sysID = findViewById(R.id.bsr_sysID3);
        name = findViewById(R.id.bsr_name2);
        seats = findViewById(R.id.bsr_total_seats);


        String drname = getIntent().getStringExtra("drName");
        String drID = getIntent().getStringExtra("drSYStemID");
        String getSeats = getIntent().getStringExtra("drSeats");
        int value = Integer. parseInt(getSeats);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Driver").child("Single Day Cab");

        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = passID.getText().toString().trim();
                ref2 = FirebaseDatabase.getInstance().getReference().child("Sharda User").child(id);
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final String full_name = snapshot.child("Name").getValue().toString();
                        final String number = snapshot.child("Mobile_Number").getValue().toString();
                        pasName.setText(full_name);
                        passNumber.setText(number);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 1;
                final String name = pasName.getText().toString().trim();
                final String PASSid = passID.getText().toString().trim();
                final String number = passNumber.getText().toString().trim();
                final String dr_sys = drID.toString().trim();


                if(name.isEmpty() && PASSid.isEmpty() && number.isEmpty()){
                    Toast.makeText(bsr_book.this, "Enter Your Full Details", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(PASSid.length() != 10 ){
                    Toast.makeText(bsr_book.this, "Invalid System ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(number.length()!=10){
                    Toast.makeText(bsr_book.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!PASSid.equals(dr_sys)){

                    String getSeats = String.valueOf(value - a);
                    databaseReference.child("Bulandshahr").child(drID).child("Total_Seats").setValue(getSeats);
                    databaseReference.child("Bulandshahr").child(drID).child("Passengers").child(PASSid).child("Passenger_Name").setValue(name);
                    databaseReference.child("Bulandshahr").child(drID).child("Passengers").child(PASSid).child("Passenger_ID").setValue(PASSid);
                    databaseReference.child("Bulandshahr").child(drID).child("Passengers").child(PASSid).child("Passenger_Number").setValue(number);

                    Toast.makeText(bsr_book.this, "Seat Booked", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(bsr_book.this, Seat_Booked_Successfully.class));
                }

                else{
                    Toast.makeText(bsr_book.this, "Driver and Passenger ID cannot be Same", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        name.setText(drname);
        sysID.setText(drID);
        seats.setText(getSeats);
    }
}