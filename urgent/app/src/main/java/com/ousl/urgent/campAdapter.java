package com.ousl.urgent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class campAdapter extends RecyclerView.Adapter<campAdapter.MyCampHolder> {

    private  Context context;
    private ArrayList name_id, phone_id, date_id,time_id, location_id, description_id;

    public campAdapter(Context context, ArrayList name_id, ArrayList phone_id, ArrayList date_id, ArrayList time_id, ArrayList location_id, ArrayList description_id) {
        this.context = context;
        this.name_id = name_id;
        this.phone_id = phone_id;
        this.date_id = date_id;
        this.time_id = time_id;
        this.location_id = location_id;
        this.description_id = description_id;
    }

    @NonNull
    @Override
    public MyCampHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.camplist, parent, false);
        return new MyCampHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCampHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.phone_id.setText(String.valueOf(phone_id.get(position)));
        holder.date_id.setText(String.valueOf(date_id.get(position)));
        holder.time_id.setText(String.valueOf(time_id.get(position)));
        holder.location_id.setText(String.valueOf(location_id.get(position)));
        holder.description_id.setText(String.valueOf(description_id.get(position)));

    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyCampHolder extends RecyclerView.ViewHolder {
        TextView name_id, phone_id, date_id,time_id, location_id, description_id;
        public MyCampHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.camp_name);
            phone_id = itemView.findViewById(R.id.camp_phone);
            date_id = itemView.findViewById(R.id.camp_date);
            time_id = itemView.findViewById(R.id.camp_time);
            location_id = itemView.findViewById(R.id.camp_location);
            description_id = itemView.findViewById(R.id.camp_description);
        }
    }
}
