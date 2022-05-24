package com.example.formelbilstvlingar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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
    List<gp> races;
    List<gp> racesSorted;
    ArrayList<String> choosen;
    public MainAdapter(List<gp> races, OnButtonListner onButtonListner, String sort) {
        this.myoOnButtonListner = onButtonListner;
    }

    public void setFilter() {
        if (races != null) {
            racesSorted = new ArrayList<>(races);
            choosen = new ArrayList<>();

            // Raderar de värden som inte ska visas utifrån dropdown filter
            int indexToDelete = 0;
            for (int i = 0; i < races.size(); i++) {
                // Kollar typen på banan i varje index
                String trackType = races.get(i).getTrackType();

                // Om bantyp ska visas i filter ökas indexToDelete för att den inte ska radera denna
                if (Objects.equals(trackType, sortItems)) {
                    indexToDelete++;
                    Log.d(TAG, "MainAdapter: " + trackType);
                    choosen.add(races.get(i).getID());
                    showItem = true;
                }
                // Om sortItems är null betyder det att filtret ska visa allt
                else if (sortItems == null) {
                    choosen.add(races.get(i).getID());
                    showItem = true;
                }
                // Om inte bantypen stämde överens med filter tas den bort
                else {
                    // Kan inte radera i (for loop) eftersom RacesSorted alltid kommer minskas i storlek!
                    racesSorted.remove(indexToDelete);
                }
            }
        }
    }

    public void setRaces(List<gp> races, String sort) {
        this.races = races;
        sortItems = sort;
        setFilter();
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
        holder.title.setText(racesSorted.get(position).getGpName());
        Picasso.get().load(racesSorted.get(position).getAuxdata().getImg()).resize(0, 300).into(holder.trackOverview);
        holder.trackName.setText(racesSorted.get(position).getTrackName());
        trackInfo = "Typ: " + racesSorted.get(position).getTrackType();
        trackInfo += " Längd: " + racesSorted.get(position).getTrackLength() + " meter";
        holder.trackInfo.setText(trackInfo);
        holder.gpWinner.setText(racesSorted.get(position).getAuxdata().getOw21());

    }

    @Override
    public int getItemCount() {
        // Om raceSorted är null returera 0 annars värdet
        return racesSorted == null ? 0 : racesSorted.size();
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
            onButtonListner.onButtonClick(getAbsoluteAdapterPosition(), choosen);
        }
    }
    public interface OnButtonListner{
        void onButtonClick(int position, ArrayList<String> howMany);
    }
}
