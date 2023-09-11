package com.ousl.urgent;

// import packages
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList date_id, time_id, bloodGroup_id, reason_id;

    public MyAdapter(Context context, ArrayList date_id, ArrayList time_id, ArrayList bloodGroup_id, ArrayList reason_id) {
        this.context = context;
        this.date_id = date_id;
        this.time_id = time_id;
        this.bloodGroup_id = bloodGroup_id;
        this.reason_id = reason_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.emergencylist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date_id.setText(String.valueOf(date_id.get(position)));
        holder.time_id.setText(String.valueOf(time_id.get(position)));
        holder.bloodGroup_id.setText(String.valueOf(bloodGroup_id.get(position)));
        holder.reason_id.setText(String.valueOf(reason_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return date_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date_id, time_id, bloodGroup_id, reason_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date_id = itemView.findViewById(R.id.textDate);
            time_id = itemView.findViewById(R.id.textTime);
            bloodGroup_id = itemView.findViewById(R.id.textBloodGroup);
            reason_id = itemView.findViewById(R.id.textReason);
        }
    }
}
