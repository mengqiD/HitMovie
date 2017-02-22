package com.example.mengqi.hitmovie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.mengqi.hitmovie.Utils.sMovies;

/**
 * Created by Mengqi on 2/21/17.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {
    private AppCompatActivity mActivity;
    private Context mContext;

    public FavoriteAdapter(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_favorite, parent, false);
        return new FavoriteViewHolder(view, mActivity);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        Movie movie = sMovies.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return sMovies.size();
    }
}


