package com.example.formelbilstvlingar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Skapar array som kommer från main activity
    ArrayList<String> Races;
    public MainAdapter(ArrayList<String> races) {
        Races = races;
        Log.d("ArrayList: ", Arrays.deepToString(Races.toArray()));
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Skapar en ny view för list_items som används för att presentera innehållet i recycler viewn
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        Log.d("==>", "onCreateViewHolder");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        // Hämtar varje element i array
        Log.d("==>", "onBindViewHolder");
        holder.textD.setText(Races.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("==>", "getItemCount");
        return Races.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textD;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Kopplar variabel mot id i en layout
            textD = itemView.findViewById(R.id.display_text);
        }
    }
}
