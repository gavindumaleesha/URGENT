package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class donorDashboard extends AppCompatActivity {
    // database object
    DatabaseHelper myDb;

    // define button
    Button btnViewEmergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_dashboard);
        myDb = new DatabaseHelper(this);
        btnViewEmergency = findViewById(R.id.viewEmergencyButton);

        // on click listener for view emergency button
        btnViewEmergency.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(donorDashboard.this,viewEmergency.class));
                    }
                }
        );

        // image slider
        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> sliderModels = new ArrayList<>();

        // sample images
        sliderModels.add(new SlideModel(R.drawable.donor_banner, ScaleTypes.FIT));
        sliderModels.add(new SlideModel(R.drawable.banner_first, ScaleTypes.FIT));
        sliderModels.add(new SlideModel(R.drawable.banner_second, ScaleTypes.FIT));
        sliderModels.add(new SlideModel(R.drawable.new_banner, ScaleTypes.FIT));
        imageSlider.setImageList(sliderModels, ScaleTypes.FIT);

    }

    //   Navigation to donor learning screen
    public void goToDonorLearning(View view) {
        Intent intent = new Intent(this, donorLearning.class);
        startActivity(intent);
    }

    //    Navigation to Google map
    public void goToMap(View view) {
        Intent intent = new Intent(this, googleMap.class);
        startActivity(intent);
    }

    // log out
    public  void goToMain(View view){
        Intent intent = new Intent(this, firstUI.class);
        startActivity(intent);
        finish();
    }

    // Navigate to camp
    public void goToCamp(View view) {
        Intent intent = new Intent(this, viewCamp.class);
        startActivity(intent);
    }

    // Navigate to post
    public void goToSocial(View view) {
        Intent intent = new Intent(this, addNotification.class);
        startActivity(intent);
    }




}