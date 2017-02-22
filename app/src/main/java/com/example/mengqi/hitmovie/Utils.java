package com.example.mengqi.hitmovie;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengqi on 2/9/17.
 */

public class Utils {
    private static final String LOG_TAG = Utils.class.getSimpleName();
    public static final String KEY_MOVIE = "current_movie";
    public static final String KEY_SOURCE = "current_trailer";
    public static final String KEY_FAVORITE = "favorite_options";
    public static final String TMDB_API = "a2fdd315a50fdfbfd7f570c3be23e740";

    private static final String API_BASE = "https://api.themoviedb.org/3/movie/";
    private static final String TAILER_BASE = "/trailers?api_key=" + TMDB_API;
    public static final String REVIEW_BASE = "/reviews?api_key=" + TMDB_API;

    public static final Gson GSON = new Gson();

    public static MovieList sMovies = new MovieList(new ArrayList<Movie>());


    public static ArrayList<Movie> extractMovies(String movieJson) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Movie> movies = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject baseJSON = new JSONObject(movieJson);
            JSONArray movieArray = baseJSON.getJSONArray("results");
            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject currentMovie = movieArray.getJSONObject(i);
                String title = currentMovie.getString("original_title");
                String posterPath = currentMovie.getString("poster_path");
                String overView = currentMovie.getString("overview");
                String userRate = currentMovie.getString("vote_average");
                String releaseDate = currentMovie.getString("release_date");
                String trailerID = currentMovie.getString("id");
                Movie movie = new Movie(title, posterPath, overView, userRate, releaseDate);
                movie.setTrailerID(trailerID);
                movies.add(movie);
                movie.trailers = fetchTrailerData(API_BASE + movie.trailerID + TAILER_BASE);
                movie.reviews = fetchReviewData(API_BASE + movie.trailerID + REVIEW_BASE);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return movies;
    }

    public static ArrayList<String> extractTrailers(String movieJson) {

        ArrayList<String> trailers = new ArrayList<>();

        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject baseJSON = new JSONObject(movieJson);
            JSONArray trailerArray = baseJSON.getJSONArray("youtube");
            for (int i = 0; i < trailerArray.length(); i++) {
                JSONObject currentMovie = trailerArray.getJSONObject(i);
                String trailer = currentMovie.getString("source");
                trailers.add(trailer);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the trailer JSON results", e);
        }

        // Return the list of earthquakes
        return trailers;
    }

    public static List<String> extractReviews(String movieJson) {

        // Create an empty ArrayList that we can start adding earthquakes to
        List<String> reviews = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject baseJSON = new JSONObject(movieJson);
            JSONArray reviewArray = baseJSON.getJSONArray("results");
            for (int i = 0; i < reviewArray.length(); i++) {
                JSONObject currentMovie = reviewArray.getJSONObject(i);
                String author = currentMovie.getString("author");
                String review = currentMovie.getString("content");
                reviews.add(author + "[st]" + review);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the trailer JSON results", e);
        }

        // Return the list of earthquakes
        return reviews;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the move JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    public static List<Movie> fetchMovieData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        List<Movie> movies = extractMovies(jsonResponse);

        return movies;
    }

    public static List<String> fetchTrailerData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        List<String> trailers = extractTrailers(jsonResponse);

        return trailers;
    }

    public static List<String> fetchReviewData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        List<String> trailers = extractReviews(jsonResponse);

        return trailers;
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
