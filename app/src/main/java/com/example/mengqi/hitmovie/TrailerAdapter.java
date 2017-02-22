package com.example.mengqi.hitmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TrailerAdapter extends ArrayAdapter<String> {

    public TrailerAdapter(Context context, List<String> trailerSources) {
        super(context, 0, trailerSources);
    }

    public View getView(int position, View contextView, ViewGroup parent) {
        View listView = contextView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.trailer_list, parent, false);
        }
        int number = position + 1;
        TextView tailerNumber = (TextView) listView.findViewById(R.id.number);
        tailerNumber.setText("Trailers " + number);
        return listView;
    }
}

