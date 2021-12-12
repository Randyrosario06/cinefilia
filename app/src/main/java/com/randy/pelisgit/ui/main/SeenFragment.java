package com.randy.pelisgit.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randy.pelisgit.Interfaces.AppDataBase;
import com.randy.pelisgit.Interfaces.MovieDAO;
import com.randy.pelisgit.Models.SeenMovie;
import com.randy.pelisgit.PosterListAdapter;
import com.randy.pelisgit.R;
import com.randy.pelisgit.SeenMoviesAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeenFragment extends Fragment {
    AppDataBase db;
    MovieDAO movieDAO;
    List<SeenMovie> movies;
    TextView nothingText;
    RecyclerView recyclerView;
    SeenMoviesAdapter seenMoviesAdapter;
    public SeenFragment() {}

    public static SeenFragment newInstance() {
        SeenFragment fragment = new SeenFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        movieDAO = db.movieDAO();
        movies = movieDAO.getAll();

        if (movies.size() <= 0){
            nothingText.setVisibility(View.VISIBLE);
        }else{
            nothingText.setVisibility(View.INVISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            seenMoviesAdapter = new SeenMoviesAdapter(movies);
            recyclerView.setAdapter(seenMoviesAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_seen, container, false);
        recyclerView = root.findViewById(R.id.seenList);
        recyclerView.setHasFixedSize(true);
        db = Room.databaseBuilder(getActivity(),
                AppDataBase.class, "database-name").allowMainThreadQueries().build();
        nothingText = root.findViewById(R.id.nothingSeenText);
        return root;
    }
}