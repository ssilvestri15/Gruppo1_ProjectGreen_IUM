package com.boris.projectgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NuovaManifestazioneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_manifestazione);

        TextInputLayout tlNome = findViewById(R.id.tlNome);
        TextInputLayout tlInd = findViewById(R.id.tlInd);
        TextInputLayout tlData = findViewById(R.id.tlData);
        TextInputLayout tlOra = findViewById(R.id.tlOra);
        ImageView back = findViewById(R.id.backPress);


        MaterialButton btnPubblicaManifestazione = findViewById(R.id.btnPubblicaManifestazione);
        btnPubblicaManifestazione.setOnClickListener( v -> {

            String nome = tlNome.getEditText().getText().toString();
            String ind = tlInd.getEditText().getText().toString();
            String data = tlData.getEditText().getText().toString();
            String ora = tlOra.getEditText().getText().toString();

            Utils.listaManifestazione.add(0,new Manifestazione(nome,ind,data,ora,0,false,false, Utils.getRandomImageManifestazioni()));
            setResult(1);
            finish();
        });

        back.setOnClickListener(v ->{
            onBackPressed();
        });

    }
}