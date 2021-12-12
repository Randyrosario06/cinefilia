package com.randy.pelisgit.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.randy.pelisgit.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeenFragment extends Fragment {

    public SeenFragment() {
        // Required empty public constructor
    }
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
        System.out.println("FRAGMENT 2");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seen, container, false);
    }
}