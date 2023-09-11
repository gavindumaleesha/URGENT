package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class campDashboard extends AppCompatActivity {
    // database object
    DatabaseHelper myDb;

    // define buttons
    Button btnViewEmergencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_dashboard);
        myDb = new DatabaseHelper(this);

        btnViewEmergencies = findViewById(R.id.emergenciesButton);

        // calling view methods
        viewAllEmergencies();
    }

    //    Navigation to donor add camp screen
    public void goToAddCamp(View view) {
        Intent intent = new Intent(this, addCamp.class);
        startActivity(intent);
    }

    // view emergency method
    public void viewAllEmergencies(){
        btnViewEmergencies.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),viewEmergency.class);
                        startActivity(intent);
                    }
                }
        );
    }

    // log out
    public  void goToMain(View view){
        Intent intent = new Intent(this, firstUI.class);
        startActivity(intent);
        finish();
    }

    // go to camp management
    public  void goToCampManage(View view){
        Intent intent = new Intent(this, manageCamp.class);
        startActivity(intent);
    }
}