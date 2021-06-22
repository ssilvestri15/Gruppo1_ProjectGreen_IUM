package com.boris.projectgreen;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.boris.projectgreen.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class BottomSheetRegistrazione extends BottomSheetDialogFragment {
    public static final int CAMERA_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    private ImageButton btnGallery, btnPhoto;
    private CircleImageView imgProfilo;
    private String currentPhotoPath;
    private TextInputEditText nome, cognome, citta, indirizzo, dataDiNascita, email, password;
    private Switch sVolotario, sDipComunale;
    private Button btnRegistrazione;
    private Utente utente;
    public  ListaUtenti utenti;

    public BottomSheetRegistrazione() {
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registrazione, container, false);
        utenti = new ListaUtenti();
        btnGallery = view.findViewById(R.id.btnAddPhoto);
        btnPhoto = view.findViewById(R.id.btnTakePhoto);
        imgProfilo = view.findViewById(R.id.fotoProfilo);

        nome = view.findViewById(R.id.txtFieldNome);
        cognome = view.findViewById(R.id.txtFieldCognome);
        citta = view.findViewById(R.id.txtFieldCitta);
        indirizzo = view.findViewById(R.id.txtFieldIndirizzo);
        dataDiNascita = view.findViewById(R.id.txtFieldDaraNascita);
        email = view.findViewById(R.id.txtFieldEmail);
        password = view.findViewById(R.id.txtFieldPassword);
        sVolotario = view.findViewById(R.id.switchVolontario);
        sDipComunale = view.findViewById(R.id.switchDipComunale);
        btnRegistrazione = view.findViewById(R.id.btnRegistrazione);
        btnRegistrazione.setOnClickListener(v -> controllaCampi());

        btnPhoto.setOnClickListener(v -> askCameraPermission());
        btnGallery.setOnClickListener(v -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, GALLERY_REQUEST_CODE);
        });

        return view;
    }

    private void controllaCampi() {
        if(nome.getText().toString().equals("")) nome.setError("Campo richiesto");
        if(cognome.getText().toString().equals("")) cognome.setError("Campo richiesto");
        if(citta.getText().toString().equals("")) citta.setError("Campo richiesto");
        if(indirizzo.getText().toString().equals("")) indirizzo.setError("Campo richiesto");
        if(email.getText().toString().equals("")) email.setError("Campo richiesto");
        String pass = password.getText().toString();
        int ruolo = 0;
        if(sVolotario.isChecked()) ruolo = 1;
        if(sDipComunale.isChecked()) ruolo = 2;
        if(controlloPassword(pass)) {
            ArrayList<Integer> immagine = new ArrayList<>();
            immagine.add(R.drawable.carmine);
            utente = new Utente(nome.getText().toString(), cognome.getText().toString(), indirizzo.getText().toString(), citta.getText().toString(), dataDiNascita.getText().toString(), email.getText().toString(), pass, immagine, ruolo);
            if(utenti.aggiungiUtente(utente)) {
                Toast.makeText(getContext(), "Benvenuto su Project Green!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("utente", 0);

            }
            else Toast.makeText(getContext(), "Utente già registrato.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean controlloPassword(String p) {
        boolean caratteri = false;
        boolean numeri = false;
        if(p.length() < 8) {
            password.setError("Inserisci almeno 8 caratteri.");
            return false;
        }
        for(int i=0; i<p.length(); i++) {
            if((p.charAt(i) >= 'A' && p.charAt(i) <= 'Z') || (p.charAt(i) >= 'a' && p.charAt(i) <= 'z')) caratteri = true;
        }
        if(!caratteri) {
            password.setError("Inserisci almeno un carattere");
            return false;
        }
        for(int i=0; i<p.length(); i++) {
            if((p.charAt(i) >= '0' && p.charAt(i) <= '9')) numeri = true;
        }
        if(!numeri) {
            password.setError("Inserisci almeno un numero.");
            return false;
        }
        return true;
    }

    private void askCameraPermission() {
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{ Manifest.permission.CAMERA}, CAMERA_CODE);
        }else{
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getActivity().getPackageManager()) != null){
            File foto = null;
            try{
                foto = createImageFile();
            }catch (IOException e ){
            }
            if (foto != null){
                Uri photoURI = FileProvider.getUriForFile(getContext(),"net.smallacademy.android.fileprovider", foto);
                i.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(i, CAMERA_REQUEST_CODE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
//      File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(getContext(), "Camera Permission is Required to Use camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File f = new File(currentPhotoPath);
                imgProfilo.setImageURI(Uri.fromFile(f));
                Log.d("tag", "ABsolute Url of Image is " + Uri.fromFile(f));
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                getContext().sendBroadcast(mediaScanIntent);
            }
        }
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Gallery Image Uri: " + imageFileName);
                imgProfilo.setImageURI(contentUri);
            }
        }

    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }
}
