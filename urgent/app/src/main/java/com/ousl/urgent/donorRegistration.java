package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class donorRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // database object
    DatabaseHelper myDb;

    // define text fields and buttons
    EditText textName, textAddress, textAge, textPhone, textWeight, textBloodType, textUsername, textPassword;
    Button btnRegister;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration);

        // database instance
        myDb = new DatabaseHelper(this);

        // define buttons and text fields
        textName = findViewById(R.id.textDonorName);
        textAddress = findViewById(R.id.textDonorAddress);
        textAge = findViewById(R.id.textDonorAge);
        textPhone = findViewById(R.id.textDonorPhone);
        textWeight = findViewById(R.id.textDonorWeight);
        spinner = findViewById(R.id.textDonorBloodGroup);
        textUsername = findViewById(R.id.textDonorUserName);
        textPassword = findViewById(R.id.textDonorPassword);
        btnRegister = findViewById(R.id.donorRegButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bloodGroups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // register button
        btnRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String donorName = textName.getText().toString();
                        String donorAdd = textAddress.getText().toString();
                        String donorAge = textAge.getText().toString();
                        String donorPhone = textPhone.getText().toString();
                        String donorWeight = textWeight.getText().toString();
                        String donorBloodType = spinner.getSelectedItem().toString();
                        String donorUsername = textUsername.getText().toString();
                        String donorPassword = textPassword.getText().toString();

                        // text field validation
                        if(donorName.equals("") || donorAdd.equals("") ||donorAge.equals("") ||donorPhone.equals("") ||
                                donorWeight.equals("") ||donorBloodType.equals("") ||donorUsername.equals("") ||donorPassword.equals(""))
                            Toast.makeText(donorRegistration.this, "Please fill out all required fields", Toast.LENGTH_SHORT).show();

                        // phone number validation
                        else if (donorPhone.length() != 10)
                            Toast.makeText(donorRegistration.this, "Phone number should have 10 digits", Toast.LENGTH_SHORT).show();

                        // username validation
                        else{
                           Boolean checkUser = myDb.checkUsername(donorUsername);

                           // data insertion and navigation
                           if(checkUser == false){
                               Boolean insert = myDb.insertDonor(donorName,donorAdd,donorName,donorPhone,donorWeight,donorBloodType,donorUsername,donorPassword);
                               if (insert == true){
                                   Toast.makeText(donorRegistration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(getApplicationContext(),donorDashboard.class);
                                   startActivity(intent);
                                   finish();
                               }
                               else
                                   Toast.makeText(donorRegistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                           }else
                               Toast.makeText(donorRegistration.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}