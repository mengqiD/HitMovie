package com.example.mengqi.hitmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Mengqi on 2/7/17.
 */

public class MainFragment extends Fragment {

    //fake data
    static final String BASE = "http://i.imgur.com/";
    static final String EXT = ".jpg";
    static final String[] URLS = {
            BASE + "CqmBjo5" + EXT, BASE + "zkaAooq" + EXT, BASE + "0gqnEaY" + EXT,
            BASE + "9gbQ7YR" + EXT, BASE + "aFhEEby" + EXT, BASE + "0E2tgV7" + EXT,
            BASE + "P5JLfjk" + EXT, BASE + "nz67a4F" + EXT, BASE + "dFH34N5" + EXT,
            BASE + "FI49ftb" + EXT, BASE + "DvpvklR" + EXT, BASE + "DNKnbG8" + EXT,
            BASE + "yAdbrLp" + EXT, BASE + "55w5Km7" + EXT, BASE + "NIwNTMR" + EXT,
            BASE + "DAl0KB8" + EXT, BASE + "xZLIYFV" + EXT, BASE + "HvTyeh3" + EXT,
            BASE + "Ig9oHCM" + EXT, BASE + "7GUv9qa" + EXT, BASE + "i5vXmXp" + EXT,
            BASE + "glyvuXg" + EXT, BASE + "u6JF6JZ" + EXT, BASE + "ExwR7ap" + EXT,
            BASE + "Q54zMKT" + EXT, BASE + "9t6hLbm" + EXT, BASE + "F8n3Ic6" + EXT,
            BASE + "P5ZRSvT" + EXT, BASE + "jbemFzr" + EXT, BASE + "8B7haIK" + EXT,
            BASE + "aSeTYQr" + EXT, BASE + "OKvWoTh" + EXT, BASE + "zD3gT4Z" + EXT,
            BASE + "z77CaIt" + EXT,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.grid_view);

        gridView.setAdapter(new GridViewAdapter(getContext()));
        gridView.setOnScrollListener(new ScrollListener(getContext()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
