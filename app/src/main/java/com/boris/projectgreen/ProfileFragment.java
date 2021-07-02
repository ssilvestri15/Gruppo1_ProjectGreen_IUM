package com.boris.projectgreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
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
    private boolean diventaVolontario = false;

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
                diventaVolontario = false;
                btnDiventaVolontario.setVisibility(View.VISIBLE);
                btnDiventaVolontario.setOnClickListener(v1 -> {
                    makeBottomSheet(c);
                });
                break;

            case 1:
                ruolo.setText("Volontario");
                break;

            case 2:
                ruolo.setText("Dip. comunale");
                btnDiventaVolontario.setVisibility(View.VISIBLE);
                btnDiventaVolontario.setOnClickListener(v1 -> {
                    makeBottomSheet(c);
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

    private void makeBottomSheet(Utente c) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_diventavolontario, getActivity().findViewById(R.id.bottomSheetContainer));

        MaterialCheckBox ccb1, ccb2, ccb3;
        MaterialButton btnProponiti, okay, btn1;
        TextView titolo, sottotiolo;
        Dialog dialog;
        Spinner s;

        s = bottomSheetView.findViewById(R.id.spinnerAssociazioni);
        ccb1 = bottomSheetView.findViewById(R.id.ccb1);
        ccb2 = bottomSheetView.findViewById(R.id.ccb2);
        ccb3 = bottomSheetView.findViewById(R.id.ccb3);
        btnProponiti = bottomSheetView.findViewById(R.id.btnProponiti);
        btnProponiti.setClickable(false);
        btn1 = bottomSheetView.findViewById(R.id.btnDiventaVolontario);

        ccb1.setOnClickListener(v -> {
            btnProponiti.setEnabled(ccb2.isChecked() && ccb3.isChecked());
        });

        ccb2.setOnClickListener(v -> {
            btnProponiti.setEnabled(ccb1.isChecked() && ccb3.isChecked());
        });

        ccb3.setOnClickListener(v -> {
            btnProponiti.setEnabled(ccb2.isChecked() && ccb1.isChecked());
        });

        s = bottomSheetView.findViewById(R.id.spinnerAssociazioni);
        ArrayAdapter<CharSequence> a = ArrayAdapter.createFromResource(getContext(), R.array.associazioni, android.R.layout.simple_spinner_dropdown_item);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(a);

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        okay = dialog.findViewById(R.id.btnOkay);
        titolo = dialog.findViewById(R.id.txtTitolo);
        titolo.setText("Grazie per esserti unito a noi!");

        sottotiolo = dialog.findViewById(R.id.txtSottotitolo);
        sottotiolo.setText("Il mondo ha bisogno di noi! A breve riceverai una email con le istruzioni da seguire.");

        okay.setOnClickListener( v1 -> {
            c.setRuolo(1);
            Utente.salva(getContext(),c);
            dialog.dismiss();
            bottomSheetDialog.dismiss();
            btnDiventaVolontario.setVisibility(View.GONE);
            updateUI(btnDiventaVolontario, nomeUtente, citta, dataNascita, ruolo, livello, pbLivello, pbSegnalazioni, pbDonazioni, pbPartecipazioni, btnEdit);
        });


        btnProponiti.setOnClickListener(v -> {
            dialog.show();
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

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