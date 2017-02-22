package com.example.mengqi.hitmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ReviewAdapter extends ArrayAdapter<String> {
    private List<String> reviewList;

    public ReviewAdapter(Context context, List<String> reviewList) {
        super(context, 0, reviewList);
        this.reviewList = reviewList;
    }

    public View getView(int position, View contextView, ViewGroup parent) {
        View listView = contextView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.review_list, parent, false);
        }
        TextView reviewAuthor = (TextView) listView.findViewById(R.id.review_author);
        TextView review = (TextView) listView.findViewById(R.id.review);

        String reviewItem = getItem(position);
        String[] arry = reviewItem.split("\\[st\\]");

        reviewAuthor.setText(arry[0] + ":");
        review.setText(arry[1]);
        return listView;
    }
}

