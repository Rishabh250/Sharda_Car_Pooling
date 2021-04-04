package com.example.shardacarpooling.Single_Day.Nodia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import com.example.shardacarpooling.R;
import com.example.shardacarpooling.Single_Day.Khurja.Khurja_Single_driver;
import com.example.shardacarpooling.list;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Nodia_Single_driver extends AppCompatActivity {

    TextView driver_Name;
    RecyclerView driver_list;
    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    ArrayList<list> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodia__driver);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Driver").child("Single Day Cab").child("Noida");
        driver_Name = findViewById(R.id.driver_Name);
        databaseReference.keepSynced(true);

        driver_list = findViewById(R.id.recyclerView);
        driver_list.setHasFixedSize(true);

        lists = new ArrayList<>();
        myAdapter = new MyAdapter(Nodia_Single_driver.this, lists);
        driver_list.setAdapter(myAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        driver_list.setLayoutManager(layoutManager);

        ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                lists.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    list dr_lists = dataSnapshot.getValue(list.class);
                    lists.add(dr_lists);
                }

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}