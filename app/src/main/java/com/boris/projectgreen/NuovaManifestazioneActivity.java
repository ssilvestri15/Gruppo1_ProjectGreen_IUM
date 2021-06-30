package com.boris.projectgreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class NuovaManifestazioneActivity extends AppCompatActivity {

    private TextInputEditText txtNome, txtIndirizzo, txtData, txtOra;
    private MaterialButton btnAggiungiFoto, btnPubblica;
    private ImageView imgCopertina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_manifestazione);

        txtNome = findViewById(R.id.txtFieldNomeManifestazione);
        txtIndirizzo = findViewById(R.id.txtFieldIndirizzoManifestazione);
        txtData = findViewById(R.id.txtFieldDataManifestazione);
        txtOra = findViewById(R.id.txtFieldOraManifestazione);
        btnAggiungiFoto = findViewById(R.id.addFoto);
        btnPubblica = findViewById(R.id.btnPubblicaManifestazione);
        imgCopertina = findViewById(R.id.copertina);

        Manifestazione m = new Manifestazione();

        m.setTitolo(txtNome.getText().toString());
        m.setLuogo(txtIndirizzo.getText().toString());
        m.setData(txtData.getText().toString());
        m.setOra(txtOra.getText().toString());
        m.setPartecipanti(0);
        m.setLike(false);
        m.setPartecipa(false);
        m.setImg(Utils.getRandomImage());

        btnPubblica.setOnClickListener(v ->{
            Intent i = new Intent();
            i.putExtra("manifestazione", m);
            setResult(1);
            finish();
        });

    }
}