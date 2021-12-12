package com.randy.pelisgit.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.randy.pelisgit.Interfaces.ItemClickListener;
import com.randy.pelisgit.Interfaces.RecyclerItemClickListener;
import com.randy.pelisgit.Models.Movie;
import com.randy.pelisgit.MovieActivity;
import com.randy.pelisgit.PosterListAdapter;
import com.randy.pelisgit.R;
import com.randy.pelisgit.RequestController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    String API_KEY="";
    public String JSON_URL = "";
    RecyclerView recyclerView;
    PosterListAdapter posterListAdapter;
    TextView connectionText;
    public PlaceholderFragment(){ }

    public static PlaceholderFragment newInstance() {
        PlaceholderFragment fragment = new PlaceholderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        API_KEY = getString(R.string.TMDB_API_KEY);
        JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key="+ API_KEY +"&language=en-US&page=1";
    }

    @Override
    public void onResume() {
        super.onResume();
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){
            connectionText.setVisibility(View.INVISIBLE);
            sendRequest();
            recyclerView.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setVisibility(View.INVISIBLE);
            connectionText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        connectionText = root.findViewById(R.id.connectionText);
        recyclerView = root.findViewById(R.id.posterList);
        recyclerView.setHasFixedSize(true);
        return root;
    }

    public void sendRequest() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,JSON_URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Movie m = null;
                        try {
                            JSONArray arr = (JSONArray) response.get("results");
                            m = new Movie(arr);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            posterListAdapter = new PosterListAdapter(m.getMovies());
                            recyclerView.setAdapter(posterListAdapter);
                            Movie finalM = m;
                            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener(){
                                @Override public void onItemClick(View view, int position) {
                                    Movie movie = finalM.getMovies().get(position);
                                    Intent i = new Intent(getActivity(), MovieActivity.class);
                                    i.putExtra("id", movie.getId());
                                    i.putExtra("title", movie.getTitle());
                                    i.putExtra("descp", movie.getOverview());
                                    i.putExtra("vote_average", movie.getVote_average());
                                    i.putExtra("poster", movie.getPoster_path());
                                    startActivity(i);
                                }

                                @Override public void onLongItemClick(View view, int position) {
                                    // do whatever
                                }
                              })
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // Increase Timeout to 15 secs.
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(jsonObjectRequest);
    }
}