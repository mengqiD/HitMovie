package com.example.mengqi.hitmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Mengqi on 2/21/17.
 */

public class FavoriteViewHolder extends RecyclerView.ViewHolder {


    public CardView mCardView;
    public ImageView mImageView;
    public TextView mTextView;


    private AppCompatActivity mActivity;
    private Movie mMovie;

    public FavoriteViewHolder(View itemView, AppCompatActivity activity) {
        super(itemView);
        mActivity = activity;
        mImageView = (ImageView) itemView.findViewById(R.id.movie_photo);
        mTextView = (TextView) itemView.findViewById(R.id.movie_name);
        mCardView = (CardView) itemView.findViewById(R.id.card_view);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // item clicked
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
        mTextView.setText(movie.title);
        Picasso.with(mActivity.getApplicationContext()).load(movie.poster).
                resize(600, 800)
                .centerCrop()
                .into(mImageView);
    }


}
