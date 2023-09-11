package com.ousl.urgent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class resetPassword extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText donorUname;
    Button userNameValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // database instance
        myDb = new DatabaseHelper(this);

        donorUname = findViewById(R.id.resetUsername);
        userNameValidate = findViewById(R.id.userNameValidation);


        // username validation
        userNameValidate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String donorUsername = donorUname.getText().toString();

                        Boolean checkUser = myDb.checkUsername(donorUsername);

                        if (checkUser == true){
                            Toast.makeText(resetPassword.this, "Username is correct", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(resetPassword.this, newPassword.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(resetPassword.this, "Username is invalid", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }


}