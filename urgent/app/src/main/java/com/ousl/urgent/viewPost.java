package com.ousl.urgent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class viewPost extends AppCompatActivity {

    RecyclerView recyclerViewPost;
    ArrayList<String> postTopic, postDes;

    ArrayList<byte[]> postImg;
    DatabaseHelper myDb;
    postAdapter adapterPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        myDb = new DatabaseHelper(this);

        postTopic = new ArrayList<>();
        postDes = new ArrayList<>();
        postImg = new ArrayList<>();

        recyclerViewPost = findViewById(R.id.postRecyclerview);
        adapterPost = new postAdapter(this,postTopic, postDes,postImg);
        recyclerViewPost.setAdapter(adapterPost);
        recyclerViewPost.setLayoutManager(new LinearLayoutManager(this));

        // calling method
        displayPost();
    }

    private void displayPost() {
        Cursor cursor= myDb.getPost();
        if (cursor.getCount()==0){
            Toast.makeText(viewPost.this, "No Posts Exists", Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                postTopic.add(cursor.getString(0));
                postDes.add(cursor.getString(1));

                // Retrieve the image byte array and add it to the image ArrayList
                byte[] imageBytes = cursor.getBlob(2);
                postImg.add(imageBytes);
            }
            adapterPost.notifyDataSetChanged();
        }
    }
}