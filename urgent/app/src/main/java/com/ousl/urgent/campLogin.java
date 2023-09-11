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

public class campLogin extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define text fields and buttons
    EditText orgUer, orgPass;
    Button orgLog, orgReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_login);

        // database instance
        myDb = new DatabaseHelper(this);

        // define buttons and text fields
        orgUer = findViewById(R.id.campLoginUsername);
        orgPass = findViewById(R.id.campLoginPassword);
        orgLog = findViewById(R.id.campLoginButton);
        orgReg = findViewById(R.id.campRegNavButton);

        // login button method
        orgLog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = orgUer.getText().toString();
                        String pass = orgPass.getText().toString();

                        // text fields validation
                        if(user.equals("")||pass.equals(""))
                            Toast.makeText(campLogin.this, "Please fill all the text fields", Toast.LENGTH_SHORT).show();

                        // check username and password
                        else {
                            Boolean checkCredentials = myDb.checkOrgUsernamePassword(user,pass);
                            if (checkCredentials == true){
                                Toast.makeText(campLogin.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),campDashboard.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(campLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
        );

        // register button method
        orgReg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), campRegistration.class);
                        startActivity(intent);
                    }
                }
        );
    }

}