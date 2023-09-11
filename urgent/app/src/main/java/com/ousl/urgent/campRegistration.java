package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class campRegistration extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define buttons and text fields
    EditText txtName,txtAddress,txtPhone,txtDist,txtUsername,txtPassword;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_registration);

        // database instance
        myDb = new DatabaseHelper(this);

        // define button and text fields
        txtName = findViewById(R.id.orgName);
        txtAddress = findViewById(R.id.orgAddress);
        txtPhone = findViewById(R.id.orgPhoneNumber);
        txtDist = findViewById(R.id.orgDistrict);
        txtUsername = findViewById(R.id.orgUsername);
        txtPassword = findViewById(R.id.orgPassword);
        btnReg = findViewById(R.id.orgRegButton);

        // register button - add new camp
        btnReg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String orgName = txtName.getText().toString();
                        String orgAdd = txtAddress.getText().toString();
                        String orgPhone = txtPhone.getText().toString();
                        String orgDist = txtDist.getText().toString();
                        String orgUsername = txtUsername.getText().toString();
                        String orgPassword = txtPassword.getText().toString();

                        // text field validation
                        if(orgName.equals("") || orgAdd.equals("") ||orgPhone.equals("") ||orgDist.equals("") ||
                                orgUsername.equals("") ||orgPassword.equals(""))
                            Toast.makeText(campRegistration.this, "Please fill out all required fields", Toast.LENGTH_SHORT).show();

                        // phone number validation
                        else if (orgPhone.length() != 10)
                            Toast.makeText(campRegistration.this, "Phone number should have 10 digits", Toast.LENGTH_SHORT).show();

                        // data insertion and navigation
                        else{
                            Boolean checkOrgUser = myDb.checkOrgUsername(orgUsername);
                            if(checkOrgUser == false){
                                Boolean insert = myDb.insertOrg(orgName,orgAdd,orgPhone,orgDist,orgUsername,orgPassword);
                                if (insert == true){
                                    Toast.makeText(campRegistration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),campDashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                    Toast.makeText(campRegistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(campRegistration.this, "Organization already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}