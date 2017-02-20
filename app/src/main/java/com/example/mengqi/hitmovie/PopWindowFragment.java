package com.example.mengqi.hitmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by Mengqi on 2/16/17.
 */

public class PopWindowFragment extends Fragment {
    String trailer;
    private WebView webView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            trailer = (String) getArguments().getSerializable(Utils.KEY_SOURCE);
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Pop");
    }

    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pop_fragment, container, false);

        webView = (WebView) view.findViewById(R.id.web_view);
        int width = getResources().getConfiguration().screenWidthDp - 50;
        int height = getResources().getConfiguration().screenHeightDp / 2 - 100;
        webView.getSettings().setJavaScriptEnabled(true);
        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"" + width + "\"height=\"" + height +
                "\"src=\"https://www.youtube.com/embed/" + trailer + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.youtube.com/watch?v=" + trailer);
//        webView.loadData(frameVideo,"text/html", "utf-8");


        return view;
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

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }
}
