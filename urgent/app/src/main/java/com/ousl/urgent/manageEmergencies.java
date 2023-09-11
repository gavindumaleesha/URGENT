package com.ousl.urgent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class manageEmergencies extends AppCompatActivity {
    // database object
    DatabaseHelper myDb;

    // define edit text and buttons
    EditText removeId;
    Button btnViewEmergency , btnDeleteEmergency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_emergencies);
        myDb = new DatabaseHelper(this);

        removeId = findViewById(R.id.textInputManageEmergency);
        btnViewEmergency = findViewById(R.id.viewEmergencyButton);
        btnDeleteEmergency = findViewById(R.id.removeEmergencyButton);

        // calling view method
        viewAllEmergencies();
        deleteData();
    }

    // view emergency
    public void viewAllEmergencies(){
        btnViewEmergency.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = myDb.getAllData();
                        if (results.getCount()==0){
                            Toast.makeText(manageEmergencies.this, "No Emergencies Exists", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (results.moveToNext()){
                            buffer.append("ID :" + results.getString(0) + "\n");
                            buffer.append("Date :" + results.getString(1) + "\n");
                            buffer.append("Time :" + results.getString(2) + "\n");
                            buffer.append("Blood Group :" + results.getString(3) + "\n");
                            buffer.append("Reason :" + results.getString(4) + "\n\n");
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(manageEmergencies.this);
                        builder.setCancelable(true);
                        builder.setTitle("Emergencies Added Recently");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }
                }
        );
    }



    // delete emergency method
    public boolean deleteData(){
        btnDeleteEmergency.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedatarows = myDb.deleteData(removeId.getText().toString());
                        if (deletedatarows>0)
                            Toast.makeText(manageEmergencies.this, "Emergency Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(manageEmergencies.this, "Emergency Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return false;
    }

    //    Navigation to home screen
    public void goToBankDashboard(View view) {
        Intent intent = new Intent(this, bankDashboard.class);
        startActivity(intent);
        finish();
    }
}