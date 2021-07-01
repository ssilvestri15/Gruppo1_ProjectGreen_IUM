package com.boris.projectgreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class ProfileFragment extends Fragment {
    private FloatingActionButton fab;
    private Button btnDiventaVolontario;
    private ImageButton btnEdit, btnLogout;
    private TextView nomeUtente, citta, dataNascita, ruolo, livello;
    private LinearProgressIndicator pbLivello;
    private CircularProgressIndicator pbSegnalazioni, pbDonazioni, pbPartecipazioni;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).hideNavBar(true);
        fab = ((MainActivity) getActivity()).getFab();
        fab.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "TESTO 2", Toast.LENGTH_SHORT).show();
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
        btnEdit = v.findViewById(R.id.btnEditIcon);
        btnLogout = v.findViewById(R.id.btnLogout);

        updateUI(btnDiventaVolontario, nomeUtente, citta, dataNascita, ruolo, livello, pbLivello, pbSegnalazioni, pbDonazioni, pbPartecipazioni, btnEdit);

        return v;
    }

    private void updateUI(Button btnDiventaVolontario, TextView nomeUtente, TextView citta, TextView dataNascita, TextView ruolo, TextView livello, ProgressBar pbLivello, ProgressBar pbSegnalazioni, ProgressBar pbDonazioni, ProgressBar pbPartecipazioni, ImageButton btnEdit) {

        Utente c = Utente.cerca(getActivity());
        nomeUtente.setText(c.getNome() + " " + c.getCognome());
        citta.setText(c.getCitta());
        dataNascita.setText(c.getDataNascita());
        livello.setText("Livello 4");
        pbLivello.setProgress(0);
        pbSegnalazioni.setProgress(0);
        pbDonazioni.setProgress(0);
        pbPartecipazioni.setProgress(0);
        int r = c.getRuolo();



        switch (r) {

            case 0:

                ruolo.setText("Cittadino");
                btnDiventaVolontario.setVisibility(View.VISIBLE);
                btnDiventaVolontario.setOnClickListener(v1 -> {
                    BottomSheetDiventaVolontario bs = new BottomSheetDiventaVolontario();
                    bs.show(getActivity().getSupportFragmentManager(), "HI");
                });
                break;

            case 1:
                ruolo.setText("Volontario");
                break;

            case 2:
                ruolo.setText("Dip. comunale");
                btnDiventaVolontario.setVisibility(View.VISIBLE);
                btnDiventaVolontario.setOnClickListener(v1 -> {
                    BottomSheetDiventaVolontario bs = new BottomSheetDiventaVolontario();
                    bs.show(getActivity().getSupportFragmentManager(), "HI");
                });
                break;

            default:
                ruolo.setText("Dip. comunale, Volontario");
                break;

        }

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            pbLivello.setProgress(70, true);
            pbSegnalazioni.setProgress(30, true);
            pbDonazioni.setProgress(60, true);
            pbPartecipazioni.setProgress(80, true);

            btnLogout.setOnClickListener(v -> {
                startLogOut();
            });

            btnEdit.setOnClickListener(v -> {
                startActivityForResult(new Intent(getActivity(), EditProfile.class).putExtra("user", c), 1);
            });

        }, 100);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == 1){
            updateUI(btnDiventaVolontario, nomeUtente, citta, dataNascita, ruolo, livello, pbLivello, pbSegnalazioni, pbDonazioni, pbPartecipazioni, btnEdit);
        }
    }

    public void startLogOut() {
        Utente.delete(getContext());
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}