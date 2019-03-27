package com.example.vaibhavkrishan.tubitvchallenge.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.vaibhavkrishan.tubitvchallenge.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.MoviesCustomAdapter;
import models.MovieRowModel;
import utils.MoviesQueryUtils;

public class MovieListActivity extends AppCompatActivity {

    private String TAG = MovieListActivity.class.getName();
    private ProgressDialog pDialog;
    private RecyclerView mRecyclerView;
    private MoviesCustomAdapter mCustomAdapter;
    RecyclerView.LayoutManager layoutManager;

    // URL to get contacts JSON
    private String url_moviesList = "https://us-central1-modern-venture-600.cloudfunctions.net/api/movies";
    private ArrayList<MovieRowModel> mMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // Setup RecyclerView
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mMoviesList = new ArrayList<>();

        new GetMovies().execute();
    }


    // Async task to get the movies in background thread

    private class GetMovies extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(MovieListActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            MoviesQueryUtils httpUtil = new MoviesQueryUtils();
            String jsonString = httpUtil.makeServiceCall(url_moviesList);

            Log.e(TAG, "JSON Response from url:"+jsonString);

            if(jsonString!=null){
                try {
                    JSONArray mArr = new JSONArray(jsonString);

                    for(int i=0; i<mArr.length();i++){

                        JSONObject mObject = mArr.getJSONObject(i);

                        String m_Name = mObject.getString("title");
                        String m_Image = mObject.getString("image");
                        String m_ID = mObject.getString("id");

                        // ArrayList of Movie model
                        mMoviesList.add(new MovieRowModel(m_Name, m_Image, m_ID));

                    }

                }catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }



            }else{
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            // Update the recyclerView with movies list data
            mCustomAdapter = new MoviesCustomAdapter(MovieListActivity.this, mMoviesList);
            mRecyclerView.setAdapter(mCustomAdapter);

        }

    }

}
