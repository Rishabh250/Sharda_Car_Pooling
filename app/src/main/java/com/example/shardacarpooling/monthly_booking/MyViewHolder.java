package com.example.shardacarpooling.monthly_booking;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shardacarpooling.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name,number,id;
    ImageView cancel_seat;
    Button call_now;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.passenger_Name);
        number = itemView.findViewById(R.id.passenger_number);
        id = itemView.findViewById(R.id.passenger_sysID);
        call_now = itemView.findViewById(R.id.call_now);
        cancel_seat = itemView.findViewById(R.id.cancel_seat);
    }
}
