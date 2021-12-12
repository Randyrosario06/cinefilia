package com.randy.pelisgit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.randy.pelisgit.Models.Movie;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PosterListAdapter extends RecyclerView.Adapter<PosterListAdapter.ViewHolder>{
    private List<Movie> movies;

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
        viewHolder.title.setText(movies.get(position).getTitle());
        viewHolder.vote_average.setText(String.valueOf(movies.get(position).getVote_average()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        TextView vote_average;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            title = (TextView) view.findViewById(R.id.title);
            vote_average = (TextView) view.findViewById(R.id.vote_average);
        }
    }
}
