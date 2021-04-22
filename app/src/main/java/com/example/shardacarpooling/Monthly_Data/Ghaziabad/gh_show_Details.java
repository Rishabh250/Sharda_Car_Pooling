package com.example.shardacarpooling.Monthly_Data.Ghaziabad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shardacarpooling.Monthly_Data.Bulandshahr.bsr_show_details;
import com.example.shardacarpooling.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class gh_show_Details extends AppCompatActivity {

    TextView seats,sysID,name,gh_car,mt,et;
    Button book;
    DatabaseReference databaseReference;
    int a = 1;
    String number;
    public static final int Request_Call = 1;


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

        number  = getIntent().getStringExtra("number");

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

    public void gh_call_now(View view) {
        String dial = "tel:"+number;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(dial));


        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(gh_show_Details.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(gh_show_Details.this,new String[]{Manifest.permission.CALL_PHONE},Request_Call);
        }
        else{
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == Request_Call){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}