package com.example.mengqi.hitmovie;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengqi on 2/7/17.
 */

public class MainFragment extends Fragment {

    private static final String POPULARITY_ORDER = "https://api.themoviedb.org/3/movie/popular?api_key=a2fdd315a50fdfbfd7f570c3be23e740";

    private GridViewAdapter mAdapter;
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

        mAdapter = new GridViewAdapter(getContext(), new ArrayList<Movie>());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(new ScrollListener(getContext()));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieFragment fragment = new MovieFragment();
                Bundle args = new Bundle();
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

        new MyAsyncTask(getActivity(), mGridView).execute(POPULARITY_ORDER);
        return view;
    }

    private class MyAsyncTask extends AsyncTask<String, Void, List<Movie>> {
        GridView mGridView;
        Activity mContex;

        public MyAsyncTask(Activity contex, GridView gview) {
            this.mGridView = gview;
            this.mContex = contex;
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            if (params.length < 1 || params[0] == null) {
                return null;
            }
            List<Movie> fetchMovies = Utils.fetchMovieData(params[0]);
            return fetchMovies;
        }

        @Override
        protected void onPostExecute(List<Movie> data) {
            mAdapter.clear();
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }

//    @Override
//    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
//        return new MovieLoader(getContext(),POPULARITY_ORDER);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
//        mAdapter.clear();
//    }
//
//    @Override
//    public void onLoaderReset(Loader<List<Movie>> loader) {
//        mAdapter.clear();
//    }
//}
}