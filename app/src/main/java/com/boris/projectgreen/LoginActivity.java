package com.boris.projectgreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {
    private TextView txtRegistrazione;
    private TextInputLayout email, password;
    private Button accedi;
    private Utente utente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.lime_100_alpha_100));

        email = findViewById(R.id.txtEmailLoing);
        password = findViewById(R.id.txtPasswordLogin);
        accedi = findViewById(R.id.btnAccedi);

        utente = Utente.cerca(getApplicationContext());
        accedi.setOnClickListener(v -> {
            if(email.getEditText().getText().toString().equals(utente.getEmail()) && password.getEditText().getText().toString().equals(utente.getPassword())) startActivity(new Intent(getApplicationContext(), MainActivity.class));
            else password.setError("Email o password errati. Riprova.");
        });


        txtRegistrazione = findViewById(R.id.txtRegistrazione);
        txtRegistrazione.setOnClickListener(v -> {
            BottomSheetRegistrazione bs = new BottomSheetRegistrazione();
            bs.show(getSupportFragmentManager(), "HI");
        });
    }
}