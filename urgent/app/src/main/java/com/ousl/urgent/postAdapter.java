package com.ousl.urgent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class postAdapter extends RecyclerView.Adapter<postAdapter.MyPostHolder> {


    private Context context;
    private ArrayList txtTopic, txtDes;
    private ArrayList<byte[]> imgView;

    public postAdapter(Context context, ArrayList txtTopic, ArrayList txtDes, ArrayList<byte[]> imgView) {
        this.context = context;
        this.txtTopic = txtTopic;
        this.txtDes = txtDes;
        this.imgView = imgView;
    }

    @NonNull
    @Override
    public MyPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.postlist, parent, false);
        return new postAdapter.MyPostHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostHolder holder, int position) {
        holder.txtTopic.setText(String.valueOf(txtTopic.get(position)));
        holder.txtDes.setText(String.valueOf(txtDes.get(position)));

        // Set the image using the imageBytes array

        byte[] imageBytes = imgView.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.imgView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return txtTopic.size();
    }

    public class MyPostHolder extends RecyclerView.ViewHolder {
        TextView txtTopic, txtDes;
        ImageView imgView;

        public MyPostHolder(@NonNull View itemView) {
            super(itemView);
            txtTopic = itemView.findViewById(R.id.post_topic);
            txtDes = itemView.findViewById(R.id.post_des);
            imgView = itemView.findViewById(R.id.postViewImage);
        }
    }
}
