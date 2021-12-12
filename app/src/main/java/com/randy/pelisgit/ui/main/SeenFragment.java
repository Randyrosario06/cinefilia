package com.randy.pelisgit.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randy.pelisgit.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeenFragment extends Fragment {
    String[] cars;
    TextView nothingText;
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
        if (cars.length <= 0){
            nothingText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_seen, container, false);
        nothingText = root.findViewById(R.id.nothingSeenText);
        cars = new String[]{};
        return root;
    }
}