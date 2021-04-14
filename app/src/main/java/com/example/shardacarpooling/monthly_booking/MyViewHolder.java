package com.example.shardacarpooling.monthly_booking;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shardacarpooling.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name,number,id;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.passenger_Name);
        number = itemView.findViewById(R.id.passenger_number);
        id = itemView.findViewById(R.id.passenger_sysID);
    }
}
