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

public class manageCamp extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define edit text and buttons
    EditText removeCamp;
    Button btnViewCamp , btnDeleteCamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_camp);
        myDb = new DatabaseHelper(this);

        removeCamp = findViewById(R.id.textInputManageCamp);
        btnViewCamp = findViewById(R.id.viewCampButton);
        btnDeleteCamp = findViewById(R.id.removeCampButton);

        // calling methods
        viewAllCamps();
        deleteCamp();
    }

    // view all camp method
    private void viewAllCamps() {

        btnViewCamp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = myDb.getAllCamp();
                        if (results.getCount()==0){
                            Toast.makeText(manageCamp.this, "No Camps Exists", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (results.moveToNext()){
                            buffer.append("ID :" + results.getString(0) + "\n");
                            buffer.append("Name :" + results.getString(1) + "\n");
                            buffer.append("Phone :" + results.getString(2) + "\n");
                            buffer.append("Date :" + results.getString(3) + "\n");
                            buffer.append("Time :" + results.getString(4) + "\n");
                            buffer.append("Location :" + results.getString(5) + "\n");
                            buffer.append("Description :" + results.getString(6) + "\n\n");
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(manageCamp.this);
                        builder.setCancelable(true);
                        builder.setTitle("Organized Camps");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }
                }
        );
    }

    // delete camp method
    public boolean deleteCamp(){
        btnDeleteCamp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedatarows = myDb.deleteCamp(removeCamp.getText().toString());
                        if (deletedatarows>0)
                            Toast.makeText(manageCamp.this, "Camp Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(manageCamp.this, "Camp Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return false;
    }

    // go to camp dashboard
    public  void goToBankDashboard(View view){
        Intent intent = new Intent(this, campDashboard.class);
        startActivity(intent);
        finish();
    }

    // go to camp dashboard
    public  void goToCampUpdate(View view){
        Intent intent = new Intent(this, updateCamp.class);
        startActivity(intent);
    }
}