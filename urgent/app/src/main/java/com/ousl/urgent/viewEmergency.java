package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class viewEmergency extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> date, time, bloodGroup, reason;
    DatabaseHelper myDb;
    MyAdapter adapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emergency);
        myDb = new DatabaseHelper(this);

        date = new ArrayList<>();
        time = new ArrayList<>();
        bloodGroup = new ArrayList<>();
        reason = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,date,time,bloodGroup,reason);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

    }


    // view emergency method
    private void displayData() {
        Cursor result = myDb.getAllData();
        if (result.getCount() == 0){
            Toast.makeText(viewEmergency.this,"No Emergencies Exists", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            while (result.moveToNext()){
                date.add(result.getString(1));
                time.add(result.getString(2));
                bloodGroup.add(result.getString(3));
                reason.add(result.getString(4));
            }
        }

    }
}