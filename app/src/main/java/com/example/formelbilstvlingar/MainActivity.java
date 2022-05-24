package com.example.formelbilstvlingar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
    MainAdapter myAdapter;
    LinearLayoutManager myLayoutManager;

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

        // Sätter de olika filter som finns möjligt i dropdownen
        Spinner dropdown = findViewById(R.id.sort);
        String[] dropdownList = new String[]{"Alla", "Ovalbana", "Stadsbana", "Racebana"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dropdownList);
        dropdown.setAdapter(adapter);

        // Skapar en recycler view instans
        myRecyclerView = findViewById(R.id.recycler_view);
        // Optimerings parameter
        myRecyclerView.setHasFixedSize(true);

        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myAdapter = new MainAdapter(gpinfo, this, sort);
        myRecyclerView.setAdapter(myAdapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Olika saker pga alternativ i dropdown
                switch (position) {
                    case 0:
                        Toast.makeText(parent.getContext(), "Visar alla", Toast.LENGTH_SHORT).show();
                        sort = null;
                        break;
                    case 1:
                        Toast.makeText(parent.getContext(), "Visar endast ovalbanor", Toast.LENGTH_SHORT).show();
                        sort = "oval";
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Visar endast stadsbanor", Toast.LENGTH_SHORT).show();
                        sort = "stadsbana";
                        break;
                    case 3:
                        Toast.makeText(parent.getContext(), "Visar endast racebanor", Toast.LENGTH_SHORT).show();
                        sort = "racebana";
                        break;
                }
                saveSpinnerState();
                changeReyclerView();
            }

            // Shared preferences för att bevara det filter som valdes
            private void saveSpinnerState() {
                int Choice = dropdown.getSelectedItemPosition();
                SharedPreferences sharedPref = getSharedPreferences("dropdown",MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinnerState",Choice);
                prefEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Ställer in sparat filter i on create
        SharedPreferences sharedPref = getSharedPreferences("dropdown",MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("spinnerState",-1);
        if(spinnerValue != -1)
            dropdown.setSelection(spinnerValue);
    }

    // Startar JsonTask
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
            // När Om Appen väljs i menyn skapas en ny intent
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

        myAdapter.setRaces(gpinfo, sort);
        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onButtonClick(int position, ArrayList<String> howMany) {

        String Target = howMany.get(position);

        // Räknar ut rätt position ifall att ett filter tagit bort objekt
        for (int i = 0; i < gpinfo.size(); i++) {
            if (gpinfo.get(i).getID() == Target) {
                newPosition = i;
            }
        }

        // Skapar och skickar intent
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