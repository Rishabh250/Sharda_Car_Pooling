package com.example.shardacarpooling.monthly_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.shardacarpooling.R;
import com.example.shardacarpooling.passengerDetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Booking_Details extends AppCompatActivity {

    Button getDetails,deleteData;
    EditText sysID;
    DatabaseReference ref,ref3;
    Task<Void> ref2;
    private static final int Request_Call = 1;

    FirebaseRecyclerOptions<passengerDetails> options;
    FirebaseRecyclerAdapter<passengerDetails,MyViewHolder> adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking__details);

        sysID = findViewById(R.id.getSysID);

        Spinner drop_city = findViewById(R.id.getcity);
        String[] cityItem = new String[]{"Select City","Bulandshahr", "Khurja", "Ghaziabad", "Sikandrabad", "Noida", "Delhi"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cityItem);
        drop_city.setAdapter(adapter3);

        Spinner drop_select = findViewById(R.id.basis_select);
        String[] basis = new String[]{"Monthly Cab", "Single Day Cab"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, basis);
        drop_select.setAdapter(adapter2);

        deleteData = findViewById(R.id.deleteData);
        getDetails = findViewById(R.id.showDetails);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ref3 = FirebaseDatabase.getInstance().getReference().child("Driver");

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getCity = drop_city.getSelectedItem().toString().trim();
                final String getBasis = drop_select.getSelectedItem().toString().trim();
                final String getID = sysID.getText().toString();

                ref2 = FirebaseDatabase.getInstance().getReference().child("Driver").child(getBasis).child(getCity).child(getID).child("Passengers")
                        .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Booking_Details.this, "Booking Details Delete Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(Booking_Details.this, "Unable to Delete Booking Details !!", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String getCity = drop_city.getSelectedItem().toString().trim();
                final String getBasis = drop_select.getSelectedItem().toString().trim();
                final String getID = sysID.getText().toString();


                ref = FirebaseDatabase.getInstance().getReference().child("Driver").child(getBasis).child(getCity).child(getID).child("Passengers");
                Toast.makeText(Booking_Details.this, "Fetching Info....", Toast.LENGTH_SHORT).show();
                options = new FirebaseRecyclerOptions.Builder<passengerDetails>().setQuery(ref,passengerDetails.class).build();
                adapter = new FirebaseRecyclerAdapter<passengerDetails, MyViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MyViewHolder holder,int i, @NonNull passengerDetails passengerDetails) {
                        holder.name.setText(passengerDetails.getPassenger_Name());
                        holder.number.setText(passengerDetails.getPassenger_Number());
                        holder.id.setText(passengerDetails.getPassenger_ID());

                        holder.call_now.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String number = passengerDetails.getPassenger_Number().toString().trim();
                                String dial = "tel:"+number;
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse(dial));

                                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                                    Toast.makeText(Booking_Details.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
                                    ActivityCompat.requestPermissions(Booking_Details.this,new String[]{Manifest.permission.CALL_PHONE},Request_Call);
                                }
                                else{
                                    startActivity(callIntent);
                                }
                            }
                        });

                        holder.cancel_seat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ref2 = FirebaseDatabase.getInstance().getReference().child("Driver").child(getBasis)
                                        .child(getCity).child(getID).child("Passengers").child(getRef(i).getKey())
                                        .setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(Booking_Details.this,"Seat Cancel", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview2,parent,false);
                        return new MyViewHolder(v);
                    }
                };

                adapter.startListening();
                recyclerView.setAdapter(adapter);
            }
        });

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