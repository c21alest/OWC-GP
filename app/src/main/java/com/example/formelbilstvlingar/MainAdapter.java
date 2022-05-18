package com.example.formelbilstvlingar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ImageView img;
    String trackInfo;
    String sortItems;
    Boolean showItem;
    private OnButtonListner myoOnButtonListner;

    private static final String TAG = "==>";

    // Skapar array som kommer från main activity
    List<gp> Races;
    List<gp> RacesSorted;
    ArrayList<String> Choosen;
    public MainAdapter(List<gp> races, OnButtonListner onButtonListner, String sort) {
        Races = races;
        sortItems = sort;
        RacesSorted = new ArrayList<>(Races);
        Choosen = new ArrayList<>();

        // Raderar de värden som inte ska visas utifrån dropdown filter
        int indexToDelete = 0;
        for (int i = 0; i < Races.size(); i++) {
            // Kollar typen på banan i varje index
            String trackType = Races.get(i).getTrackType();

            // Om bantyp ska visas i filter ökas indexToDelete för att den inte ska radera denna
            if (Objects.equals(trackType, sortItems)) {
                indexToDelete++;
                Log.d(TAG, "MainAdapter: " + trackType);
                Choosen.add(Races.get(i).getID());
                showItem = true;
            }
            // Om sortItems är null betyder det att filtret ska visa allt
            else if (sortItems == null) {
                Choosen.add(Races.get(i).getID());
                showItem = true;
            }
            // Om inte bantypen stämde överens med filter tas den bort
            else {
                // Kan inte radera i (for loop) eftersom RacesSorted alltid kommer minskas i storlek!
                RacesSorted.remove(indexToDelete);
            }
        }
        this.myoOnButtonListner = onButtonListner;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Skapar en ny view för list_items som används för att presentera innehållet i recycler viewn
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view, myoOnButtonListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        holder.title.setText(RacesSorted.get(position).getGpName());
        Picasso.get().load(RacesSorted.get(position).getAuxdata().getImg()).resize(0, 300).into(holder.trackOverview);
        holder.trackName.setText(RacesSorted.get(position).getTrackName());
        trackInfo = "Typ: " + RacesSorted.get(position).getTrackType();
        trackInfo += " Längd: " + RacesSorted.get(position).getTrackLength() + " meter";
        holder.trackInfo.setText(trackInfo);
        holder.gpWinner.setText(RacesSorted.get(position).getAuxdata().getOw21());

    }

    @Override
    public int getItemCount() {
        return RacesSorted.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public TextView trackName;
        public TextView trackInfo;
        public TextView gpWinner;
        public ImageView trackOverview;
        OnButtonListner onButtonListner;

        public ViewHolder(@NonNull View itemView, OnButtonListner onButtonListner) {
            super(itemView);

            // Kopplar variabel mot id i en layout
            title = itemView.findViewById(R.id.title);
            trackName = itemView.findViewById(R.id.track_name);
            trackInfo = itemView.findViewById(R.id.track_info);
            gpWinner = itemView.findViewById(R.id.winner_name);
            trackOverview = (ImageView) itemView.findViewById(R.id.track_overview);
            this.onButtonListner = onButtonListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onButtonListner.onButtonClick(getAbsoluteAdapterPosition(), Choosen);
        }
    }
    public interface OnButtonListner{
        void onButtonClick(int position, ArrayList<String> howMany);
    }
}
