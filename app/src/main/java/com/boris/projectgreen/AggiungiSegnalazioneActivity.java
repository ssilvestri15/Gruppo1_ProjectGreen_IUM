package com.boris.projectgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AggiungiSegnalazioneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_segnalazione);

        MaterialButton btn = findViewById(R.id.btn_add);
        TextInputLayout tl1 = findViewById(R.id.outlinedTextField1);
        TextInputLayout tl2 = findViewById(R.id.outlinedTextField2);
        TextInputLayout tl3 = findViewById(R.id.outlinedTextField3);

        btn.setOnClickListener( view -> {

            String s1 = tl1.getEditText().getText().toString();
            String s2 = tl2.getEditText().getText().toString();
            String s3 = tl3.getEditText().getText().toString();

            if (!s1.trim().isEmpty() && !s2.trim().isEmpty() && !s3.trim().isEmpty()) {
                Date today = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
                s3 = formatter.format(today);
                Segnalazione s = new Segnalazione(s1, s2, s3, new Random().nextInt(98) + 1);
                Utils.listaSegnalazioni.add(0, s);
                Intent i = new Intent();
                i.putExtra("OK", "OK");
                setResult(1, i);
                finish();
            }else{
                tl1.setError("Il campo è richiesto");
                tl2.setError("Il campo è richiesto");
                tl3.setError("Il campo è richiesto");
            }
        });

    }
}