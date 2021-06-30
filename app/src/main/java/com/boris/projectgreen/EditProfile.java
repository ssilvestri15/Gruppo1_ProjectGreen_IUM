package com.boris.projectgreen;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class EditProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            Utente user = bundle.getParcelable("user");

            TextInputLayout tl_name = findViewById(R.id.tl_name);
            tl_name.getEditText().setText(user.getNome());
            TextInputLayout tl_cognome = findViewById(R.id.tl_cognome);
            tl_cognome.getEditText().setText(user.getCognome());

            TextInputLayout tl_indirizzo = findViewById(R.id.tl_indirizzo);
            tl_indirizzo.getEditText().setText(user.getIndirizzo());

            TextInputLayout tl_citta = findViewById(R.id.tl_citta);
            tl_citta.getEditText().setText(user.getCitta());

            TextInputLayout tl_data = findViewById(R.id.tl_data);
            tl_data.getEditText().setText(user.getDataNascita());

            TextInputLayout tl_email = findViewById(R.id.tl_email);
            tl_email.getEditText().setText(user.getEmail());

            MaterialButton btnSalva = findViewById(R.id.btnSalva);
            MaterialButton btnAnnulla = findViewById(R.id.btnAnnulla);

            btnSalva.setOnClickListener( v -> {

                user.setNome(tl_name.getEditText().getText().toString());
                user.setCognome(tl_cognome.getEditText().getText().toString());
                user.setIndirizzo(tl_indirizzo.getEditText().getText().toString());
                user.setCitta(tl_citta.getEditText().getText().toString());
                user.setDataNascita(tl_data.getEditText().getText().toString());
                Utente.salva(this,user);
                Toast.makeText(this, "Profilo aggiornato",Toast.LENGTH_SHORT).show();
                setResult(1);
                finish();

            });

            btnAnnulla.setOnClickListener( v -> {
                onBackPressed();
            });

        }else{
            onBackPressed();
        }

    }

    @Override
    public void onBackPressed() {
        setResult(0);
        finish();
    }


}