package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.ButtCap;

public class bankRegistration extends AppCompatActivity {

    // define buttons and text fields
    EditText textKey;
    Button btnBankLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_registration);

        // define text fields and buttons
        textKey = findViewById(R.id.bankLogKey);
        btnBankLog = findViewById(R.id.bankLoginButton);

        // bank login button
        btnBankLog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key = textKey.getText().toString();

                        if (key.equals(""))
                            Toast.makeText(bankRegistration.this, "Fill The Text Field", Toast.LENGTH_SHORT).show();
                        else{
                            if(key.equals("bank123")){
                                Intent intent = new Intent(getApplicationContext(), bankDashboard.class);
                                startActivity(intent);
                                finish();
                            }else
                                Toast.makeText(bankRegistration.this, "Key Invalid", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

}