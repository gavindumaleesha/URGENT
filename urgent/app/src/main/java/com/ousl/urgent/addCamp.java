package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addCamp extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define text fields and buttons
    EditText editCampName, editCampPhone, editCampDate, editCampTime, editCampLocation, editCampDes;
    Button backHomeBtn, addCampBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camp);

        // database instance
        myDb = new DatabaseHelper(this);

        // define text fields and buttons
        editCampName = findViewById(R.id.campName);
        editCampPhone = findViewById(R.id.campPhone);
        editCampDate = findViewById(R.id.campDate);
        editCampTime = findViewById(R.id.campTime);
        editCampLocation = findViewById(R.id.campLocation);
        editCampDes = findViewById(R.id.campDescription);
        backHomeBtn = findViewById(R.id.backButtonCamp);
        addCampBtn = findViewById(R.id.addNewCamp);

        // call function
        addCamp();

        // back button navigation
        backHomeBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), campDashboard.class);
                        startActivity(intent);
                    }
                }
        );
    }

    // add blood camp
    public void addCamp(){
        addCampBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editCampName.getText().toString().trim();
                        String phone = editCampPhone.getText().toString().trim();
                        String date = editCampDate.getText().toString().trim();
                        String time = editCampTime.getText().toString().trim();
                        String location = editCampLocation.getText().toString().trim();
                        String description = editCampDes.getText().toString().trim();

                        // validate text fields
                        if (name.isEmpty() || phone.isEmpty() || date.isEmpty() || time.isEmpty() || location.isEmpty() || description.isEmpty()) {
                            Toast.makeText(addCamp.this, "Fill all the text fields", Toast.LENGTH_LONG).show();
                        }

                        // validate phone number
                        else if (phone.length() != 10) {
                            Toast.makeText(addCamp.this, "Phone number should have 10 digits", Toast.LENGTH_SHORT).show();
                        }

                        // insert data
                        else {
                            boolean isInserted = myDb.insertCamp(name, phone, date, time,location,description);

                            if(isInserted == true){
                                Toast.makeText(addCamp.this,"New Camp Added", Toast.LENGTH_LONG).show();
                                finish();
                            }
                            else
                                Toast.makeText(addCamp.this,"New Camp Not Added", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

}