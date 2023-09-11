package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class bankDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_dashboard);
    }

    //    Navigation to add emergency screen
    public void goToAddEmergency(View view) {
        Intent intent = new Intent(this, addEmergency.class);
        startActivity(intent);
    }

    // Navigation to manage emergency screen
    public void goToManageEmergency(View view) {
        Intent intent = new Intent(this, manageEmergencies.class);
        startActivity(intent);
    }

    // Navigation to posts screen
    public void goToPost(View view) {
        Intent intent = new Intent(this, addNotification.class);
        startActivity(intent);
    }

    // log out
    public  void goToMain(View view){
        Intent intent = new Intent(this, firstUI.class);
        startActivity(intent);
        finish();
    }

    // Navigate to camp
    public void goToCampBD(View view) {
        Intent intent = new Intent(this, viewCamp.class);
        startActivity(intent);
    }
}