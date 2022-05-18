package com.example.formelbilstvlingar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener, MainAdapter.OnButtonListner {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=c21alest";

    private static final String TAG = "==>";

    RecyclerView myRecyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager myLayoutManager;

    ArrayList<String> races;
    ArrayList<String> localTest;
    List<gp> gpinfo;

    private gp[] gpInfo;

    String sort;
    int newPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner dropdown = findViewById(R.id.sort);
        String[] dropdownList = new String[]{"Alla", "Ovalbana", "Stadsbana", "Racebana"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dropdownList);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(parent.getContext(), "Visar alla", Toast.LENGTH_SHORT).show();
                        sort = null;
                        changeReyclerView();
                        break;
                    case 1:
                        Toast.makeText(parent.getContext(), "Visar endast Ovalbanor", Toast.LENGTH_SHORT).show();
                        sort = "oval";
                        changeReyclerView();
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Visar endast Stadsbanor", Toast.LENGTH_SHORT).show();
                        sort = "stadsbana";
                        changeReyclerView();
                        break;
                    case 3:
                        Toast.makeText(parent.getContext(), "Visar endast Racebanor", Toast.LENGTH_SHORT).show();
                        sort = "racebana";
                        changeReyclerView();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });
    }

    private void changeReyclerView() {
        new JsonTask(this).execute(JSON_URL);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Visar menyalternativ i menyn
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // När Ändra text väljs i menyn skapas en ny intent
            case R.id.about:
                Intent intentWeb = new Intent(MainActivity.this, WebView.class);
                startActivity(intentWeb);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("==>", json);

        // Skapar gson instans från json fil och mountain klass
        Gson gson = new Gson();
        gpInfo = gson.fromJson(json, gp[].class);

        gpinfo = Arrays.asList(gpInfo);

        Log.d(TAG, "onPostExecute: " + sort);

        /*
        if (sort != null) {
            for (int i = 1; i < gpinfo.size(); i++) {
                if (gpinfo.get(i).getTrackType() != sort) {
                    gpinfo.remove(i);
                }
            }
        }

         */

        races = new ArrayList<>();

        /*
        // För test endast
        localTest = new ArrayList<>();
        localTest.add("Rad 1");
        localTest.add("Rad 2");
        localTest.add("Rad 3");
        localTest.add("Rad 4");
        localTest.add("Rad 5");
         */

        /*
        // Funktion som lägger till varje gson objekt i en array
        for (int i = 0; i < gpInfo.length; i++) {
            String id = gpInfo[i].getID();
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
         */

        // Skapar en recycler view instans
        myRecyclerView = findViewById(R.id.recycler_view);
        // Optimerings parameter
        myRecyclerView.setHasFixedSize(true);

        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myAdapter = new MainAdapter(gpinfo, this, sort);
        myRecyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onButtonClick(int position, ArrayList<String> howMany) {
        Log.d(TAG, "onButtonClick: " + gpinfo.get(position).getGpName());
        Log.d(TAG, "onButtonClick: " + howMany);
        Log.d(TAG, "onButtonClick: " + position);

        String Target = howMany.get(position);

        Log.d(TAG, "onButtonClick: Target: " + Target);

        for (int i = 0; i < gpinfo.size(); i++) {
            if (gpinfo.get(i).getID() == Target) {
                newPosition = i;
            }
        }

        Intent intent = new Intent(MainActivity.this, DetailedView.class);

        String driverAge = String.valueOf(gpinfo.get(newPosition).getAuxdata().getAge());
        String trackLength = String.valueOf(gpinfo.get(newPosition).getTrackLength());

        intent.putExtra("gpName", gpinfo.get(newPosition).getGpName());
        intent.putExtra("trackName", gpinfo.get(newPosition).getTrackName());
        intent.putExtra("trackType", gpinfo.get(newPosition).getTrackType());
        intent.putExtra("trackLength", trackLength);
        intent.putExtra("trackImage", gpinfo.get(newPosition).getAuxdata().getImg());
        intent.putExtra("driverName", gpinfo.get(newPosition).getAuxdata().getOw21());
        intent.putExtra("driverImage", gpinfo.get(newPosition).getAuxdata().getDimg());
        intent.putExtra("driverAge", driverAge);
        intent.putExtra("driverNat", gpinfo.get(newPosition).getAuxdata().getNat());

        startActivity(intent);
    }
}