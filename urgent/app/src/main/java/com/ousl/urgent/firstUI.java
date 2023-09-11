package com.ousl.urgent;

// import packages
import androidx.appcompat.app.AppCompatActivity;

// Import packages
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.widget.Button;

public class firstUI extends AppCompatActivity implements SensorEventListener {

    // database object
    DatabaseHelper myDb;

    Button donorLogin;

    private SensorManager sensorManager;
    private Sensor sensorLight;
    private MediaPlayer mp;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_ui);

        // database instance
        myDb = new DatabaseHelper(this);

        // Sensor Manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // get LIGHT sensor
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // mp3 file
        mp = MediaPlayer.create(this, R.raw.record);
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);


        // donor login button
        donorLogin = findViewById(R.id.buttonDonorLogin);

        donorLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                    }
                }
        );
    }


    //    Navigation to bank login screen
    public void goToBankLogin(View view) {
        Intent intent = new Intent(this, bankRegistration.class);
        startActivity(intent);
    }

    //   Navigation to camp login screen
    public void goToCampLogin(View view) {
        Intent intent = new Intent(this, campLogin.class);
        startActivity(intent);
    }

    //   Navigation to about us screen
    public void goToAboutUs(View view) {
        Intent intent = new Intent(this, aboutUs.class);
        startActivity(intent);
    }

    // onSensorChanged method
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightIntensity = event.values[0];

            // to play mp3, lightIntensity should greater than 10000
            if (lightIntensity > 10000 && !isPlaying) {
                mp.start();
                isPlaying = true;
            }

            // lightIntensity is equal or smaller than 10000, mp3 pause
            else if (lightIntensity <= 10000 && isPlaying) {
                mp.pause();
                mp.seekTo(0);
                isPlaying = false;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }
}