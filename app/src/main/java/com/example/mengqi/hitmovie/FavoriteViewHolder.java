package com.example.mengqi.hitmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FavoriteViewHolder extends RecyclerView.ViewHolder {

    public CardView mCardView;
    public ImageView mImageView;
    private AppCompatActivity mActivity;
    private Movie mMovie;

    public FavoriteViewHolder(View itemView, AppCompatActivity activity) {
        super(itemView);
        mActivity = activity;
        mImageView = (ImageView) itemView.findViewById(R.id.movie_photo);
        mCardView = (CardView) itemView.findViewById(R.id.card_view);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieFragment fragment = new MovieFragment();
                Bundle args = new Bundle();
                args.putSerializable(Utils.KEY_MOVIE, mMovie);
                fragment.setArguments(args);
                mActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });
    }

    public void bind(Movie movie) {
        mMovie = movie;
        Picasso.with(mActivity.getApplicationContext()).load(movie.poster).
                resize(500, 600)
                .centerCrop()
                .into(mImageView);
    }
}
