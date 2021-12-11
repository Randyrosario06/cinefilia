package com.randy.pelisgit.ui.main;

import android.widget.Toast;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.randy.pelisgit.Models.Movie;
import com.randy.pelisgit.R;
import com.randy.pelisgit.RequestController;

import org.json.JSONArray;

public class PageViewModel extends ViewModel {
    public static final String REQUEST_TAG = "JSON_ARRAY_REQUEST_TAG";
    public static final String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key="+ R.string.TMDB_API_KEY+"&language=en-US&page=1";
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    private void sendRequest() {
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(JSON_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        showResponse(new Movie(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(PageViewModel.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // Increase Timeout to 15 secs.
        jsonArrayReq.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding JsonObject request to request queue
        RequestController.getInstance().addToRequestQueue(jsonArrayReq, REQUEST_TAG);
    }
    private void showResponse(Movie movie) {
        String a = movie.title;
        boolean b = movie.adult;
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.request_response);
//        if (recyclerView != null) {
//            PostAdapter postAdapter = new PostAdapter(posts, R.layout.single_post);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(postAdapter);
        }
    }