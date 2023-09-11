package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newPassword extends AppCompatActivity {
    // database object
    DatabaseHelper myDb;
    EditText username;
    EditText newPw;
    EditText cNewPw;
    Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        // database instance
        myDb = new DatabaseHelper(this);

        // define buttons, text fields
        username = findViewById(R.id.dUsername);
        newPw = findViewById(R.id.newPassword);
        cNewPw = findViewById(R.id.confirmPassword);
        resetBtn = findViewById(R.id.btnResetPw);

        // update password
        updatePassword();

    }

    // navigate to login interface
    public void backToLogin(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        finish();
    }

    // pw update method
    public void updatePassword() {
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = username.getText().toString();
                if (uName.isEmpty()) {
                    Toast.makeText(newPassword.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                String nPw = newPw.getText().toString();
                if (nPw.isEmpty()) {
                    Toast.makeText(newPassword.this, "Enter New Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isUpdate = myDb.updateDonorPw(uName, nPw);
                if (nPw.equals(cNewPw.getText().toString())) {
                    if (isUpdate) {
                        Toast.makeText(newPassword.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(newPassword.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(newPassword.this, "Enter the same password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}