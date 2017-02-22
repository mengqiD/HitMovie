package com.example.mengqi.hitmovie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GridViewAdapter extends ArrayAdapter<Movie> {
    private final Context context;

    public GridViewAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SquaredImage view = (SquaredImage) convertView;
        if (view == null) {
            view = new SquaredImage(context);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setPadding(8, 8, 8, 8);
        }
        Movie movie = getItem(position);
        String poster = movie.poster;
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context)
                .load(poster)
                .fit()
                .tag(context)
                .into(view);
        return view;
    }
}
