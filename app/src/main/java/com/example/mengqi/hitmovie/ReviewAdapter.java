package com.example.mengqi.hitmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewAdapter extends ArrayAdapter<String> {
    @BindView(R.id.review_author)
    TextView reviewAuthor;
    @BindView(R.id.review)
    TextView review;

    public ReviewAdapter(Context context, List<String> reviewList) {
        super(context, 0, reviewList);
    }

    public View getView(int position, View contextView, ViewGroup parent) {
        View listView = contextView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.review_list, parent, false);
        }
        ButterKnife.bind(this, listView);


        String reviewItem = getItem(position);
        String[] arry = reviewItem.split("\\[st\\]");

        reviewAuthor.setText(arry[0] + ":");
        review.setText(arry[1]);
        return listView;
    }
}

