package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addEmergency extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // database object
    DatabaseHelper myDb;

    // define edit text fields and buttons
    EditText editDate, editTime, editBloodGroup, editReason;
    Button btnAddEmergency;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency);
        myDb = new DatabaseHelper(this);

        // define edit texts and buttons
        editDate = findViewById(R.id.editTextDate);
        editTime = findViewById(R.id.editTextTime);
        spinner = findViewById(R.id.textInputBloodGroup);
        editReason = findViewById(R.id.textInputReason);
        btnAddEmergency = findViewById(R.id.addEmergencyButton);

        // drop down menu
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bloodGroups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // calling add emergency method
        addData();
    }

    //    Navigation to home screen
    public void goToBankDashboard(View view) {
        Intent intent = new Intent(this, bankDashboard.class);
        startActivity(intent);
    }

    // add blood emergency
    public void addData(){
        btnAddEmergency.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String date = editDate.getText().toString().trim();
                        String time = editTime.getText().toString().trim();
                        String bloodGroup = spinner.getSelectedItem().toString().trim();
                        String reason = editReason.getText().toString().trim();

                        if (date.isEmpty() || time.isEmpty() || bloodGroup.isEmpty() || reason.isEmpty()) {
                            Toast.makeText(addEmergency.this, "Fill all the text fields", Toast.LENGTH_LONG).show();
                        } else {
                            boolean isInserted = myDb.insertData(date, time, bloodGroup, reason);

                            if(isInserted == true){
                                Toast.makeText(addEmergency.this,"Emergency Added", Toast.LENGTH_LONG).show();
                                finish();
                            } else
                                Toast.makeText(addEmergency.this,"Emergency Not Added", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}