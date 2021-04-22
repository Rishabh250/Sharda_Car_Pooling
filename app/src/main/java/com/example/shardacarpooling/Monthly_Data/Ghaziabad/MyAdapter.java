package com.example.shardacarpooling.Monthly_Data.Ghaziabad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shardacarpooling.Monthly_Data.Bulandshahr.bsr_show_details;
import com.example.shardacarpooling.R;
import com.example.shardacarpooling.list;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<list> myList;
    Context context;

    public MyAdapter(Context context, ArrayList<list> myList) {

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
        final list temp = myList.get(position);

        holder.name.setText(temp.getFull_Name());
        holder.sysID.setText(temp.getSystem_ID());
        holder.price.setText(temp.getPrice());

        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, gh_show_Details.class);
                intent.putExtra("seats",temp.getTotal_Seats());
                intent.putExtra("name",temp.getFull_Name());
                intent.putExtra("sysID",temp.getSystem_ID());
                intent.putExtra("MT",temp.getMorning_Time());
                intent.putExtra("ET",temp.getEvening_Time());
                intent.putExtra("model",temp.getCar_Model() +" " + temp.getCar_Number());
                intent.putExtra("number",temp.getMobile_Number());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, sysID, price;
        ImageView go;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            go = itemView.findViewById(R.id.go);
            name = itemView.findViewById(R.id.driver_Name);
            sysID = itemView.findViewById(R.id.driver_sysID);
            price = itemView.findViewById(R.id.driver_price);

        }
    }
}