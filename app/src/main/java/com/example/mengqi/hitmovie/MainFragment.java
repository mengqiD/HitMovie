package com.example.mengqi.hitmovie;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengqi on 2/7/17.
 */

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Movie>> {

    private static final String POPULARITY_ORDER = "https://api.themoviedb.org/3/movie/popular?api_key=YOURKEY";
    private static final int MOVIE_LOADER_ID = 1;
    private GridViewAdapter mAdapter;
    private TextView mEmptyView;
    private ProgressBar mProgress;
    GridView mGridView;
    Movie mMovie;
    private FragmentActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
                args.putSerializable(Utils.KEY_MOVIE, mMovie);
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
            mEmptyView.setText(R.string.no_internet);
        }
        return view;
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieLoader(getActivity(), POPULARITY_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else {
            mEmptyView.setText(R.string.no_movies);
        }
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        mAdapter.clear();
    }
}