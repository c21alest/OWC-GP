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

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ImageView img;
    String trackInfo;
    private OnButtonListner myoOnButtonListner;

    // Skapar array som kommer från main activity
    List<gp> Races;
    public MainAdapter(List<gp> races, OnButtonListner onButtonListner) {
        Races = races;
        this.myoOnButtonListner = onButtonListner;
        Log.d("ArrayList: ", Arrays.deepToString(Races.toArray()));
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
        // Hämtar varje element i array
        holder.title.setText(Races.get(position).getGpName());
        Picasso.get().load(Races.get(position).getAuxdata().getImg()).resize(0, 300).into(holder.trackOverview);
        holder.trackName.setText(Races.get(position).getTrackName());
        trackInfo = "Typ: " + Races.get(position).getTrackType();
        trackInfo += " Längd: " + Races.get(position).getTrackLength() + " meter";
        holder.trackInfo.setText(trackInfo);
        holder.gpWinner.setText(Races.get(position).getAuxdata().getOw21());
    }

    @Override
    public int getItemCount() {
        return Races.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public TextView trackName;
        public TextView trackInfo;
        public TextView gpWinner;
        public ImageView trackOverview;
        private Button aboutDriver;
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

            aboutDriver = itemView.findViewById(R.id.readmore_driver);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onButtonListner.onButtonClick(getAbsoluteAdapterPosition());
        }
    }
    public interface OnButtonListner{
        void onButtonClick(int position);
    }
}
