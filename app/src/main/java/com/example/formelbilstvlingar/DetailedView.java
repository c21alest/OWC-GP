package com.example.formelbilstvlingar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedView extends AppCompatActivity {

    TextView gpName;
    ImageView trackImage;
    TextView trackName;
    TextView trackLength;
    TextView trackType;
    TextView maxCrowd;

    TextView driverName;
    ImageView driverImage;
    TextView driverAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        gpName = findViewById(R.id.gp_name);
        trackImage = findViewById(R.id.track_overview);
        trackName = findViewById(R.id.track_name);
        trackLength = findViewById(R.id.track_length);
        trackType = findViewById(R.id.track_type);
        maxCrowd = findViewById(R.id.max_crowd);

        driverName = findViewById(R.id.driver_name);
        driverImage = findViewById(R.id.driver_image);
        driverAge = findViewById(R.id.driver_age);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String gpNameS = extras.getString("gpName");
            String trackNameS = extras.getString("trackName");
            String trackTypeS = extras.getString("trackType");
            String trackLengthS = extras.getString("trackLength");
            String trackImageS = extras.getString("trackImage");
            String driverNameS = extras.getString("driverName");

            gpName.setText(gpNameS);

            trackType.setText(trackTypeS);
            trackLength.setText(trackLengthS);

            driverName.setText(driverNameS);
        }
    }
}
