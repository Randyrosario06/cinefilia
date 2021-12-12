package com.randy.pelisgit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.randy.pelisgit.Interfaces.AppDataBase;
import com.randy.pelisgit.Interfaces.ItemClickListener;
import com.randy.pelisgit.Models.Movie;
import com.randy.pelisgit.Models.SeenMovie;
import com.squareup.picasso.Picasso;
import java.util.Collections;
import java.util.List;

public class PosterListAdapter extends RecyclerView.Adapter<PosterListAdapter.ViewHolder>{
    private List<Movie> movies;
    String POSTER_BASE_URL = "https://image.tmdb.org/t/p/original";
    AppDataBase db;
    public PosterListAdapter(List<Movie> movies) {
        Collections.sort(movies, Collections.reverseOrder());
        this.movies = movies;
    }

    @NonNull
    @Override
    public PosterListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterListAdapter.ViewHolder viewHolder, int position) {
        db = Room.databaseBuilder(viewHolder.btnSeen.getContext(),
                AppDataBase.class, "database-name").allowMainThreadQueries().build();

        viewHolder.movieId.setText(String.valueOf(movies.get(position).getId()));
        viewHolder.title.setText(movies.get(position).getTitle());
        viewHolder.vote_average.setText(String.valueOf(movies.get(position).getVote_average()));
        Picasso.get().load(POSTER_BASE_URL+movies.get(position).getPoster_path()).into(viewHolder.poster);
        viewHolder.posterUrl.setText(POSTER_BASE_URL+movies.get(position).getPoster_path());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView movieId,title, posterUrl, vote_average;
        ImageView poster;
        Button btnSeen;

        public ViewHolder(View view) {
            super(view);
            movieId = view.findViewById(R.id.movieId);
            title = view.findViewById(R.id.title);
            vote_average = view.findViewById(R.id.vote_average);
            poster = view.findViewById(R.id.poster);
            posterUrl = view.findViewById(R.id.posterUrl);
            btnSeen = view.findViewById(R.id.btnSeen);
        }

    }
}
