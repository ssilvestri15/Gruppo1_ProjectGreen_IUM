package com.boris.projectgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class AggiungiSegnalazioneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_segnalazione);

        MaterialButton btn = findViewById(R.id.btn_add);

        btn.setOnClickListener( view -> {
            Segnalazione s = new Segnalazione("TEST","TEST","TEST",34);
            Utils.listaSegnalazioni.add(0,s);
            Intent i = new Intent();
            i.putExtra("OK","OK");
            setResult(1,i);
            finish();
        });

    }
}