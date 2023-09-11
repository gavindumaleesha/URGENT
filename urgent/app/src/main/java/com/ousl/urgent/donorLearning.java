package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class donorLearning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_learning);
    }

//    Navigation to main dashboard
    public void backToDonorDashboard(View view) {
        Intent intent = new Intent(this, donorDashboard.class);
        startActivity(intent);
        finish();
    }
}