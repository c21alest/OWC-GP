package com.example.formelbilstvlingar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailedView extends AppCompatActivity {

    TextView gpName;
    ImageView trackImage;
    TextView trackName;
    TextView trackLength;
    TextView trackType;

    TextView driverName;
    ImageView driverImage;
    TextView driverAge;
    TextView driverNat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        gpName = findViewById(R.id.gp_name);
        trackImage = findViewById(R.id.track_overview);
        trackName = findViewById(R.id.track_name);
        trackLength = findViewById(R.id.track_length);
        trackType = findViewById(R.id.track_type);

        driverName = findViewById(R.id.driver_name);
        driverImage = findViewById(R.id.driver_image);
        driverAge = findViewById(R.id.driver_age);
        driverNat = findViewById(R.id.driver_nat);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String gpNameS = extras.getString("gpName");
            String trackNameS = extras.getString("trackName");
            String trackTypeS = extras.getString("trackType");
            String trackLengthS = extras.getString("trackLength");
            String trackImageS = extras.getString("trackImage");
            String driverNameS = extras.getString("driverName");
            String driverImageS = extras.getString("driverImage");
            String driverAgeS = extras.getString("driverAge");
            String driverNatS = extras.getString("driverNat");

            gpName.setText(gpNameS);
            Picasso.get().load(trackImageS).fit().centerInside().into(trackImage);
            trackName.setText(trackNameS);
            trackType.setText("Bantyp: " + trackTypeS);
            trackLength.setText("Banlängd: " + trackLengthS);

            driverName.setText("P1: " + driverNameS);
            Picasso.get().load(driverImageS).fit().centerInside().into(driverImage);
            driverAge.setText("Ålder: " + driverAgeS);
            driverNat.setText("Nationalitet: " + driverNatS);
        }
    }
}
