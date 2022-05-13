package com.example.formelbilstvlingar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    // Skapar array som kommer från main activity
    List<gp> Races;
    public MainAdapter(List<gp> races) {
        Races = races;
        Log.d("ArrayList: ", Arrays.deepToString(Races.toArray()));
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Skapar en ny view för list_items som används för att presentera innehållet i recycler viewn
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        // Hämtar varje element i array
        holder.textD.setText(Races.get(position).getID());
        /*
        String urlTrackImage = Races.get(6);
        Log.d("==>", urlTrackImage);
        Picasso.get().load(urlTrackImage).into(holder.trackOverview);

         */
    }

    @Override
    public int getItemCount() {
        return Races.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textD;
        public ImageView trackOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Kopplar variabel mot id i en layout
            textD = itemView.findViewById(R.id.display_text);
            trackOverview = (ImageView) itemView.findViewById(R.id.track_overview);
        }
    }
}
