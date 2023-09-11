package com.ousl.urgent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class aboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    //    Navigation to bank login screen
    public void backToHome(View view) {
        Intent intent = new Intent(this, firstUI.class);
        startActivity(intent);
        finish();
    }
}

