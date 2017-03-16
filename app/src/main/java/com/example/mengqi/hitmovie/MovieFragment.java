package com.example.mengqi.hitmovie;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.mengqi.hitmovie.Utils.sMovies;

public class MovieFragment extends Fragment {
    private Movie mMovie;

    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.overview)
    TextView mOverview;
    @BindView(R.id.data_release)
    TextView mReleaseDate;
    @BindView(R.id.review_text)
    TextView mReviewText;
    @BindView(R.id.ratingBar)
    RatingBar mRatingBar;
    @BindView(R.id.rate_score)
    TextView mRateScore;
    @BindView(R.id.trailerView)
    ListView mListViewTrailer;
    @BindView(R.id.reviewView)
    ListView mListViewReview;
    @BindView(R.id.checkBox)
    CheckBox mCheckBox;


    private TrailerAdapter mAdapterT;
    private ReviewAdapter mAdapterR;
    private FragmentActivity mActivity;

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
        ButterKnife.bind(this, view);

        //set adapter
        mAdapterT = new TrailerAdapter(getContext(), mMovie.trailers);
        if (mMovie.reviews.size() == 0) {
            mReviewText.setText(getString(R.string.no_reviews));
        }
        mAdapterR = new ReviewAdapter(getContext(), mMovie.reviews);
        mListViewTrailer.setAdapter(mAdapterT);
        mListViewReview.setAdapter(mAdapterR);
        Utils.setListViewHeightBasedOnChildren(mListViewTrailer);
        Utils.setListViewHeightBasedOnChildren(mListViewReview);

        //set values
        float rate = Float.parseFloat(mMovie.rate) / 2;
        setmImageView(mImageView, mMovie.poster);
        mTitle.setText(mMovie.title);
        mOverview.setText(mMovie.overview);
        mReleaseDate.setText(getString(R.string.release_date) + mMovie.release);
        mRatingBar.setRating(rate);
        mRateScore.setText(mMovie.rate + getString(R.string.rate_out_of));
        mOverview.setMovementMethod(new ScrollingMovementMethod());

        //intent to trailer fragment
        mListViewTrailer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopWindowFragment fragment = new PopWindowFragment();
                Bundle args = new Bundle();
                String source = (String) parent.getItemAtPosition(position);
                args.putSerializable(Utils.KEY_SOURCE, source);
                fragment.setArguments(args);
                mActivity = getActivity();
                mActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });

        //checkbox
        mCheckBox.setChecked(mMovie.favorite);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie checkMovie = sMovies.findMatch(mMovie.title);
                if (((CheckBox) v).isChecked()) {
                    if (!sMovies.contains(mMovie) || checkMovie == null) {
                        sMovies.add(mMovie);
                        mMovie.isFavorite();
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        String favoriteString = Utils.GSON.toJson(sMovies);
                        preferences.edit().putString(Utils.KEY_FAVORITE, favoriteString).apply();
                    }
                } else if (!((CheckBox) v).isChecked()) {
                    if (checkMovie != null) {
                        sMovies.movieList.remove(mMovie);
                        mMovie.notFavorite();
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        String favoriteString = Utils.GSON.toJson(sMovies);
                        preferences.edit().putString(Utils.KEY_FAVORITE, favoriteString).apply();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(final View view,
                              @Nullable final Bundle savedInstanceState) {
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



