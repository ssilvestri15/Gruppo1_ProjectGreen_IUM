package com.boris.projectgreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utente c = Utente.cerca(getApplication());
        Log.d("LOG", c.getNome() + " " + c.getCognome());
        if(c.getEmail().equals("")) startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        replaceFragment(HomeFragment.newInstance());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.page_1:
                    replaceFragment(HomeFragment.newInstance());
                    return true;
                case R.id.page_2:
                    replaceFragment(ManifestazioniFragment.newInstance());
                    return true;
                case R.id.page_3:
                    replaceFragment(DonazioniFragment.newInstance());
                    return true;
                case R.id.page_4:
                    replaceFragment(ProfileFragment.newInstance());
                    return true;
                default:
                    break;
            }
            return false;
        });

    }




    public void startLogIn(View v){
        Intent i = new Intent(this , LoginActivity.class);
        startActivity(i);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


    public void hideNavBar(boolean hide) {
        runOnUiThread(() -> {
            FloatingActionButton fab = findViewById(R.id.floating_action_button);
            fab.animate().translationX((hide) ? 200 : 0).setDuration(300);
            fab.setVisibility((hide) ? View.GONE : View.VISIBLE);
        });
    }

    public FloatingActionButton getFab() {
        return findViewById(R.id.floating_action_button);
    }
}