package com.example.shardacarpooling.Single_Day.Bulandshahr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shardacarpooling.R;
import com.example.shardacarpooling.list;
import com.example.shardacarpooling.list02;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<list02> myList;
    Context context;

    public MyAdapter(Context context, ArrayList<list02> myList) {

        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardview,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final list02 temp = myList.get(position);

        holder.name.setText(temp.getFull_Name());
        holder.sysID.setText(temp.getSystem_ID());
        holder.price.setText(temp.getPrice());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, temp.getFull_Name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, sysID, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.driver_Name);
            sysID = itemView.findViewById(R.id.driver_sysID);
            price = itemView.findViewById(R.id.driver_price);
        }
    }
}