package com.example.mengqi.hitmovie;

import java.io.Serializable;

/**
 * Created by Mengqi on 2/9/17.
 */

public class Movie implements Serializable {
    String title;
    String poster;
    String overview;
    String rate;
    String release;

    public Movie() {

    }

    public Movie(String title, String poster, String overview, String rate, String release) {
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.rate = rate;
        this.release = release;
    }
}
