package com.boris.projectgreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {
    private FloatingActionButton fab;
    private Button btnLogout, btnDiventaVolontario;
    private TextView nomeUtente, citta, dataNascita, ruolo, livello;
    private ProgressBar pbLivello, pbSegnalazioni, pbDonazioni, pbPartecipazioni;

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
        btnDiventaVolontario = v.findViewById(R.id.btnDiventaVolontario);
        nomeUtente = v.findViewById(R.id.nomeUtenteFP);
        citta = v.findViewById(R.id.txtResidenzaFP);
        dataNascita = v.findViewById(R.id.txtAnniFP);
        ruolo = v.findViewById(R.id.txtCategoriaFP);
        livello = v.findViewById(R.id.txtLivelloFP);
        pbLivello = v.findViewById(R.id.progressBarLevel);
        pbSegnalazioni = v.findViewById(R.id.progressSegnalazione);
        pbDonazioni = v.findViewById(R.id.progressDonazione);
        pbPartecipazioni = v.findViewById(R.id.progressPartecipazione);

        Utente c = Utente.cerca(getActivity());
        nomeUtente.setText(c.getNome() + " " + c.getCognome());
        citta.setText(c.getCitta());
        dataNascita.setText(c.getDataNascita());
        livello.setText("Livello " + c.getLivello());
        pbLivello.setMax(c.getLivello() * 2);
        pbLivello.setProgress(c.getProgressiLivello(), true);
        pbSegnalazioni.setMax(c.getSegnalazione() * 2);
        pbSegnalazioni.setProgress(c.getProgressiSegnalazione(), true);
        pbDonazioni.setMax(c.getDonazione() * 2);
        pbDonazioni.setProgress(c.getProgressiDonazione(), true);
        pbPartecipazioni.setMax(c.getPartecipazioni() * 2);
        pbPartecipazioni.setProgress(c.getProgressiPartecipazioni(), true);
        int r = c.getRuolo();
        if(r == 0) {
            ruolo.setText("Cittadino");
            btnDiventaVolontario.setVisibility(View.VISIBLE);
            btnDiventaVolontario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BottomSheetDiventaVolontario bs = new BottomSheetDiventaVolontario();
                    bs.show(getActivity().getSupportFragmentManager(), "HI");
                }
            });
        }
        else if(r == 1) ruolo.setText("Volontario");
        else ruolo.setText("Dip. comunale");
        return v;
    }

    public void startLogOut(View v){
        Intent i = new Intent(getContext() , LoginActivity.class);
        startActivity(i);
    }
}