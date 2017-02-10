package com.example.mengqi.hitmovie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mengqi on 2/7/17.
 */

public class GridViewAdapter extends ArrayAdapter<Movie> {
    private final Context context;
    private final List<String> urls = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();


    public GridViewAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        this.context = context;
        this.movies = movies;
        for (Movie movie : movies) {
            String poster = "http://image.tmdb.org/t/p/w500/" + movie.poster;
            urls.add(poster);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SquaredImage view = (SquaredImage) convertView;
        if (view == null) {
            view = new SquaredImage(context);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setPadding(8, 8, 8, 8);
        }
        String poster = urls.get(position);

        // Get the image URL for the current position.
//        Movie currentMovie = getItem(position);
//        String poster = "http://image.tmdb.org/t/p/w500/" + currentMovie.poster;

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(poster) //
                .fit() //
                .tag(context) //
                .into(view);

        return view;
    }

    @Override
    public int getCount() {
        return urls.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }
}
