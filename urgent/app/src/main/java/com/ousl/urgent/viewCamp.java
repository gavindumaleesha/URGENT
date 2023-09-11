package com.ousl.urgent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class viewCamp extends AppCompatActivity {
    RecyclerView recyclerViewCamp;
    ArrayList<String> campName, campPhone,campDate,campTime,campLocation,campDescription;
    DatabaseHelper myDb;
    campAdapter adapterCamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_camp);
        myDb = new DatabaseHelper(this);

        campName = new ArrayList<>();
        campPhone = new ArrayList<>();
        campDate = new ArrayList<>();
        campTime = new ArrayList<>();
        campLocation = new ArrayList<>();
        campDescription = new ArrayList<>();
        recyclerViewCamp = findViewById(R.id.campRecyclerview);
        adapterCamp = new campAdapter(this,campName, campPhone,campDate,campTime,campLocation, campDescription);
        recyclerViewCamp.setAdapter(adapterCamp);
        recyclerViewCamp.setLayoutManager(new LinearLayoutManager(this));
        displayCamp();        

    }

    private void displayCamp() {
        Cursor cursor= myDb.getAllCamp();
        if (cursor.getCount()==0){
            Toast.makeText(viewCamp.this, "No Organized Camps Exists", Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                campName.add(cursor.getString(1));
                campPhone.add(cursor.getString(2));
                campDate.add(cursor.getString(3));
                campTime.add(cursor.getString(4));
                campLocation.add(cursor.getString(5));
                campDescription.add(cursor.getString(6));
            }
        }
    }

    //    Navigation to Google map
    public void goToLocation(View view) {
        Intent intent = new Intent(this, googleMap.class);
        startActivity(intent);
    }
}