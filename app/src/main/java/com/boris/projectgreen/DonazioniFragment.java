package com.boris.projectgreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class DonazioniFragment extends Fragment {
    private MaterialButton btnCinque, btnDieci, btnQuindici, btnPiu, btnMeno, btnImportoPers, btnPaga, okay;
    private int selezione, importo;
    private Dialog dialog;
    private TextView titolo, sottotiolo;
    // 1=5, 2=10, 3=15, 4=custom
    public DonazioniFragment() {
        // Required empty public constructor
    }

    public static DonazioniFragment newInstance() {
        return new DonazioniFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).hideNavBar(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_donazioni, container, false);
        importo = 1;
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        titolo = v.findViewById(R.id.txtTitolo);
        sottotiolo = v.findViewById(R.id.txtSottotitolo);
        okay = v.findViewById(R.id.btnOkay);

        btnCinque = v.findViewById(R.id.btn_cinque);
        btnCinque.setBackgroundColor(Color.GRAY);
        btnCinque.setOnClickListener(v1 -> {
            btnCinque.setBackgroundColor(Color.parseColor("#3EA851"));
            btnDieci.setBackgroundColor(Color.GRAY);
            btnQuindici.setBackgroundColor(Color.GRAY);
            btnPiu.setBackgroundColor(Color.GRAY);
            btnMeno.setBackgroundColor(Color.GRAY);
            btnImportoPers.setBackgroundColor(Color.GRAY);
            selezione = 1;
        });

        btnDieci = v.findViewById(R.id.btn_dieci);
        btnDieci.setBackgroundColor(Color.GRAY);
        btnDieci.setOnClickListener(v1 -> {
            btnDieci.setBackgroundColor(Color.parseColor("#3EA851"));
            btnCinque.setBackgroundColor(Color.GRAY);
            btnQuindici.setBackgroundColor(Color.GRAY);
            btnPiu.setBackgroundColor(Color.GRAY);
            btnMeno.setBackgroundColor(Color.GRAY);
            btnImportoPers.setBackgroundColor(Color.GRAY);
            selezione = 2;
        });

        btnQuindici = v.findViewById(R.id.btn_quindici);
        btnQuindici.setBackgroundColor(Color.GRAY);
        btnQuindici.setOnClickListener(v1 -> {
            btnQuindici.setBackgroundColor(Color.parseColor("#3EA851"));
            btnCinque.setBackgroundColor(Color.GRAY);
            btnDieci.setBackgroundColor(Color.GRAY);
            btnPiu.setBackgroundColor(Color.GRAY);
            btnMeno.setBackgroundColor(Color.GRAY);
            btnImportoPers.setBackgroundColor(Color.GRAY);
            selezione = 3;
        });

        btnPiu = v.findViewById(R.id.btn_Piu);
        btnPiu.setBackgroundColor(Color.GRAY);
        btnPiu.setOnClickListener(v1 -> {
            btnPiu.setBackgroundColor(Color.parseColor("#3EA851"));
            btnMeno.setBackgroundColor(Color.parseColor("#3EA851"));
            btnImportoPers.setBackgroundColor(Color.parseColor("#3EA851"));
            btnCinque.setBackgroundColor(Color.GRAY);
            btnDieci.setBackgroundColor(Color.GRAY);
            btnQuindici.setBackgroundColor(Color.GRAY);
            importo++;
            btnImportoPers.setText(importo + " €");
            selezione = 4;
        });

        btnMeno = v.findViewById(R.id.btn_Meno);
        btnMeno.setBackgroundColor(Color.GRAY);
        btnMeno.setOnClickListener(v1 -> {
            btnPiu.setBackgroundColor(Color.parseColor("#3EA851"));
            btnMeno.setBackgroundColor(Color.parseColor("#3EA851"));
            btnImportoPers.setBackgroundColor(Color.parseColor("#3EA851"));
            btnCinque.setBackgroundColor(Color.GRAY);
            btnDieci.setBackgroundColor(Color.GRAY);
            btnQuindici.setBackgroundColor(Color.GRAY);
            if(importo > 1) {
                importo--;
                btnImportoPers.setText(importo + " €");
            } else btnImportoPers.setText(0 + " €");
            selezione = 4;
        });

        btnImportoPers = v.findViewById(R.id.btn_Imp);
        btnImportoPers.setBackgroundColor(Color.GRAY);
        btnPaga = v.findViewById(R.id.btnPagaConPayPal);
        btnPaga.setOnClickListener(v13 -> dialog.show());
        return v;
    }
}