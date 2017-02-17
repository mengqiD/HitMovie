package com.example.mengqi.hitmovie;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Mengqi on 2/16/17.
 */

public class PopWindowFragment extends Fragment {
    String trailer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            trailer = (String) getArguments().getSerializable(Utils.KEY_SOURCE);
        }
    }

    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_fragment, container, false);
        PopupWindow popupWindow = new PopupWindow(view,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());


        return view;
    }
}
