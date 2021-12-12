package com.randy.pelisgit.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SeenMovie {
    @NonNull
    @PrimaryKey
    public int movie_id;
    public String title;
    public String rating;
    public String poster;

    public SeenMovie(int movie_id, String title, String rating, String poster) {
        this.movie_id = movie_id;
        this.title = title;
        this.rating = rating;
        this.poster = poster;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
