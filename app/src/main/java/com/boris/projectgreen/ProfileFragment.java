package com.boris.projectgreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private FloatingActionButton fab;
    private Button btnLogout;
    private TextView nomeUtente;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).hideNavBar(false);
        fab = ((MainActivity) getActivity()).getFab();
        fab.setOnClickListener(view -> {
            Toast.makeText(getActivity(),"TESTO 2", Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        nomeUtente = v.findViewById(R.id.nomeUtente);

        Utente c = Utente.cerca(getActivity());

        return v;
    }

    public void startLogOut(View v){
        Intent i = new Intent(getContext() , LoginActivity.class);
        startActivity(i);
    }
}