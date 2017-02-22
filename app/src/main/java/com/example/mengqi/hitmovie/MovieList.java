package com.example.mengqi.hitmovie;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Mengqi on 2/21/17.
 */

public class MovieList {
    public ArrayList<Movie> movieList;

    public MovieList() {
        this(new ArrayList<Movie>());
    }

    public MovieList(@NonNull final ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public Movie get(int i) {
        return movieList.get(i);
    }


    public int size() {
        return movieList.size();
    }

    public void add(final Movie movie) {
        movieList.add(movie);
    }

    public Movie get(final Movie movie) {
        return movieList.get(movieList.indexOf(movie));
    }

    public boolean contains(final Movie movie) {
        return movieList.contains(movie);
    }
}
