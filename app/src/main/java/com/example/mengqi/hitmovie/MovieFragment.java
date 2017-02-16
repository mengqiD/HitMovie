package com.example.mengqi.hitmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by Mengqi on 2/10/17.
 */

public class MovieFragment extends Fragment {
    private Movie mMovie;
    private ImageView mImageView;
    private TextView mTitle;
    private TextView mOverview;
    private TextView mReleaseDate;
    private RatingBar mRatingBar;
    private TextView mRateScore;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getArguments() != null) {
            mMovie = (Movie) getArguments().getSerializable(Utils.KEY_MOVIE);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mMovie.title);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.movie_fragment, container, false);
        mImageView = (ImageView) view.findViewById(R.id.imageView);
        mTitle = (TextView) view.findViewById(R.id.title);
        mOverview = (TextView) view.findViewById(R.id.overview);
        mReleaseDate = (TextView) view.findViewById(R.id.data_release);
        mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        mRateScore = (TextView) view.findViewById(R.id.rate_score);

        float rate = Float.parseFloat(mMovie.rate) / 2;
        setmImageView(mImageView, mMovie.poster);
        mTitle.setText(mMovie.title);
        mOverview.setText(mMovie.overview);
        mReleaseDate.setText("Release Date: " + mMovie.release);
        mRatingBar.setRating(rate);
        mRateScore.setText(mMovie.rate + "/10");
        mOverview.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setmImageView(ImageView imageView, String photoPath) {
        Picasso.with(getContext())
                .load(photoPath)
                .resize(600, 800)
                .centerCrop()
                .into(imageView);

    }
}
