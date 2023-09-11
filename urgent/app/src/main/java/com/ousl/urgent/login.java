package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;
    // define text fields and buttons
    EditText logUsername, logPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // database instance
        myDb = new DatabaseHelper(this);

        // define buttons and text fields
        logUsername = findViewById(R.id.donorLoginUsername);
        logPassword = findViewById(R.id.donorLoginPassword);
        btnLogin = findViewById(R.id.donorLoginButton);

        // login button method
        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = logUsername.getText().toString();
                        String pass = logPassword.getText().toString();

                        if(user.equals("")||pass.equals(""))
                            Toast.makeText(login.this, "Please fill all the text fields", Toast.LENGTH_SHORT).show();
                        else {
                            Boolean checkuserpass = myDb.checkUsernamePassword(user,pass);
                            if (checkuserpass == true){
                                Toast.makeText(login.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),donorDashboard.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
        );

    }

    // Navigation to reset password screen
    public void goToForgotPassword(View view) {
        Intent intent = new Intent(this, resetPassword.class);
        startActivity(intent);
    }

    // Navigation to main registration screen
    public void goToDonorRegistration(View view) {
        Intent intent = new Intent(this, donorRegistration.class);
        startActivity(intent);
    }

}