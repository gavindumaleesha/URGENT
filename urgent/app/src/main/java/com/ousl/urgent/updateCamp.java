package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateCamp extends AppCompatActivity {

    // database object
    DatabaseHelper myDb;

    // define edit text and buttons
    EditText campId, editName,editPhone,editDate,editTime,editLocation,editDes;
    Button btnViewUpdateCamp , btnUpdateCamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_camp);
        myDb = new DatabaseHelper(this);


        // define text fields and buttons
        btnViewUpdateCamp = findViewById(R.id.updateViewButton); // Corrected button reference
        btnUpdateCamp = findViewById(R.id.updateCampButton); // Button reference for update
        campId = findViewById(R.id.updateCampId);
        editName = findViewById(R.id.updateCampName);
        editPhone = findViewById(R.id.updateCampPhone);
        editDate = findViewById(R.id.updateCampDate);
        editTime = findViewById(R.id.updateCampTime);
        editLocation = findViewById(R.id.updateCampLocation);
        editDes = findViewById(R.id.updateCampDescription);

        // calling methods
        viewAllCamps();
        updateData();

    }

    // view all camp method
    private void viewAllCamps() {

        btnViewUpdateCamp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor results = myDb.getAllCamp();
                        if (results.getCount()==0){
                            Toast.makeText(updateCamp.this, "No Camps Exists", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (results.moveToNext()){
                            buffer.append("ID :" + results.getString(0) + "\n");
                            buffer.append("Name :" + results.getString(1) + "\n");
                            buffer.append("Phone :" + results.getString(2) + "\n");
                            buffer.append("Date :" + results.getString(3) + "\n");
                            buffer.append("Time :" + results.getString(4) + "\n");
                            buffer.append("Location :" + results.getString(5) + "\n");
                            buffer.append("Description :" + results.getString(6) + "\n\n");
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(updateCamp.this);
                        builder.setCancelable(true);
                        builder.setTitle("Organized Camps");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }
                }
        );
    }

    // camp update method
    public void updateData() {
        btnUpdateCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = campId.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(updateCamp.this, "Enter a valid ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isUpdate = myDb.updateCamp(
                        id,
                        editName.getText().toString(),
                        editPhone.getText().toString(),
                        editDate.getText().toString(),
                        editTime.getText().toString(),
                        editLocation.getText().toString(),
                        editDes.getText().toString()
                );

                if (isUpdate == true)
                    Toast.makeText(updateCamp.this, "Camp Updated Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(updateCamp.this, "Camp Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}