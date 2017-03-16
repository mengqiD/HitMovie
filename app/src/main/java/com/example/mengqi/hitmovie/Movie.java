package com.example.mengqi.hitmovie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    String title;
    String poster;
    String overview;
    String rate;
    String release;
    String trailerID;
    boolean favorite = false;
    List<String> trailers = new ArrayList<>();
    List<String> reviews = new ArrayList<>();

    public Movie(String title, String poster, String overview, String rate, String release) {
        this.title = title;
        this.poster = "http://image.tmdb.org/t/p/w342/" + poster;
        this.overview = overview;
        this.rate = rate;
        this.release = release;
    }

    public void setTrailerID(String ID) {
        this.trailerID = ID;
    }

    public void isFavorite() {
        favorite = true;
    }

    public void notFavorite() {
        favorite = false;
    }
}
