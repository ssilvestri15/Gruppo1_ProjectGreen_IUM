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

            MaterialButton btnSalva = findViewById(R.id.btnSalva);
            MaterialButton btnAnnulla = findViewById(R.id.btnAnnulla);

            btnSalva.setOnClickListener( v -> {

                user.setNome(tl_name.getEditText().getText().toString());
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