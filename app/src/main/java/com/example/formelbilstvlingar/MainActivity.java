package com.example.formelbilstvlingar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=c21alest";

    RecyclerView myRecyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager myLayoutManager;
    ArrayList<String> races;

    private gp[] gpInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask(this).execute(JSON_URL);

    }

    @Override
    public void onPostExecute(String json) {
        Log.d("==>", json);

        // Skapar gson instans från json fil och mountain klass
        Gson gson = new Gson();
        gpInfo = gson.fromJson(json, gp[].class);

        races = new ArrayList<>();

        // Funktion som lägger till varje gson objekt i en array
        for (int i = 0; i < gpInfo.length; i++) {
            String id = gpInfo[i].getId();
            races.add(id);
            String name = gpInfo[i].getTrackName();
            races.add(name);
            String company = gpInfo[i].getGpName();
            races.add(company);
            String location = gpInfo[i].getLocation();
            races.add(location);
            String category = gpInfo[i].getTrackType();
            races.add(category);
            int TrackLength = gpInfo[i].getTrackLength();
            races.add(String.valueOf(TrackLength));
            String img = gpInfo[i].getAuxdata().getImg();
            races.add(img);
            String ow21 = gpInfo[i].getAuxdata().getOw21();
            races.add(ow21);
            races.add("\n");
        }

        /*
        // Skapar en recycler view instans
        myRecyclerView = findViewById(R.id.recycler_view);
        // Optimerings parameter
        myRecyclerView.setHasFixedSize(true);

        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myAdapter = new MainAdapter();
        myRecyclerView.setAdapter(myAdapter);

         */
    }
}