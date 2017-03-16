package com.example.mengqi.hitmovie;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.mengqi.hitmovie.Utils.sMovies;

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Movie>> {

    private static final String POPULARITY_ORDER = "https://api.themoviedb.org/3/movie/popular?api_key="
            + Utils.TMDB_API;
    private static final String RATE_ORDER = "https://api.themoviedb.org/3/movie/top_rated?api_key="
            + Utils.TMDB_API;
    private static final int MOVIE_LOADER_ID = 1;
    private GridViewAdapter mAdapter;
    private TextView mEmptyView;
    private ProgressBar mProgress;
    private GridView mGridView;
    private Movie mMovie;
    private FragmentActivity mActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment, container, false);
        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mProgress = (ProgressBar) view.findViewById(R.id.progress);
        mEmptyView = (TextView) view.findViewById(R.id.empty_view);

        mAdapter = new GridViewAdapter(getContext(), new ArrayList<Movie>());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(new ScrollListener(getContext()));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieFragment fragment = new MovieFragment();
                Bundle args = new Bundle();
                mMovie = (Movie) parent.getItemAtPosition(position);
                Movie checkMovie = sMovies.findMatch(mMovie.title);
                if (checkMovie == null) {
                    args.putSerializable(Utils.KEY_MOVIE, mMovie);
                } else {
                    args.putSerializable(Utils.KEY_MOVIE, checkMovie);
                }
                fragment.setArguments(args);
                mActivity = getActivity();
                mActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });

        ConnectivityManager mManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        } else {
            mProgress.setVisibility(View.GONE);
            mEmptyView.setText(getString(R.string.no_internet));
        }
        return view;
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );
        String order;
        if (orderBy.equals(getString(R.string.settings_order_by_rate_label))) {
            order = RATE_ORDER;
        } else {
            order = POPULARITY_ORDER;
        }
        return new MovieLoader(getActivity(), order);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else {
            mEmptyView.setText(getString(R.string.no_movies));
        }
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getActivity().getLoaderManager().restartLoader(0, null, this);
    }
}