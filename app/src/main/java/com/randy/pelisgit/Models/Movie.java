package com.randy.pelisgit.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Comparable<Movie>{
    public boolean adult;
    public String backdrop_path;
    public List<Integer> genre_ids;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public double popularity;
    public String poster_path;
    public String release_date;
    public String title;
    public boolean video;
    public double vote_average;
    public int vote_count;
    public boolean seen;

    private List<Movie> movies;
    public Movie(JSONArray jsonArray) {
        parseJson(jsonArray);
    }

    private void parseJson(JSONArray jsonArray) {
        movies = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                movies.add(new Movie(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Movie(JSONObject json) throws JSONException {
        JSONArray jsonArray = (JSONArray) json.get("genre_ids");
        List<Integer> jsonGenre = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            Integer jsonObject = jsonArray.getInt(i);
            jsonGenre.add(jsonObject);
        }
        this.adult = (boolean) json.get("adult");
        this.backdrop_path = (String) json.get("backdrop_path");
        this.genre_ids = jsonGenre;
        this.id = (Integer) json.get("id");
        this.original_language = (String) json.get("original_language");
        this.original_title = (String) json.get("original_title");
        this.overview = (String) json.get("overview");
        this.popularity = (double) json.get("popularity");
        this.poster_path = (String) json.get("poster_path");
        this.release_date = (String) json.get("release_date");
        this.title = (String) json.get("title");
        this.video = (boolean) json.get("video");
        try {
            this.vote_average = (double) json.get("vote_average");
        }catch (Exception e){
            int avg = (Integer) json.get("vote_average");
            double parsed_avg = avg;
            this.vote_average = parsed_avg;
        }
        this.vote_count = (Integer) json.get("vote_count");
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public int compareTo(Movie o) {
        return String.valueOf(this.getVote_average()).compareTo(String.valueOf(o.getVote_average()));
    }
}
