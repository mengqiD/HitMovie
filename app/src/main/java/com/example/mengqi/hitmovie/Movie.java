package com.example.mengqi.hitmovie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengqi on 2/9/17.
 */

public class Movie implements Serializable {
    String title;
    String poster;
    String overview;
    String rate;
    String release;
    String trailerID;
    List<String> trailers = new ArrayList<>();
    List<String> reviews = new ArrayList<>();



    public Movie() {

    }

    public Movie(String title, String poster, String overview, String rate, String release) {
        this.title = title;
        this.poster = "http://image.tmdb.org/t/p/w500/" + poster;
        this.overview = overview;
        this.rate = rate;
        this.release = release;
    }

    public void setTrailerID(String ID) {
        this.trailerID = ID;
    }

    public void setTrailers(ArrayList trailerList) {
        this.trailers = trailerList;
    }

    public void setReviews(ArrayList reviewList) {
        this.reviews = reviewList;
    }
}
