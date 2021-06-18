package com.boris.projectgreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void openFragmentRegistrazione(View view) {
        FragmentManager fm = getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction();
    }
}