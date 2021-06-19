package com.boris.projectgreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RegistrazioneFragment extends Fragment {

    public RegistrazioneFragment() {
    }

    public static RegistrazioneFragment newInstance() {
        RegistrazioneFragment fragment = new RegistrazioneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registrazione, container, false);
    }
}