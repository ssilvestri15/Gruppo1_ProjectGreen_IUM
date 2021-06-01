package com.boris.projectgreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ManifestazioniFragment extends Fragment {

    public ManifestazioniFragment() {
        // Required empty public constructor
    }

    public static ManifestazioniFragment newInstance() {
        return new ManifestazioniFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).hideNavBar(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_manifestazioni, container, false);
        return v;
    }
}