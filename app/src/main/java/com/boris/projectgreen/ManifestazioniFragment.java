package com.boris.projectgreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ManifestazioniFragment extends Fragment {

    private RecyclerView rc;
    private FloatingActionButton btnAdd;


    public ManifestazioniFragment() {
        // Required empty public constructor
    }

    public static ManifestazioniFragment newInstance() {
        return new ManifestazioniFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utente user = Utente.cerca(getActivity());
        if(user.getRuolo() == 2 || user.getRuolo() == 3){
            ((MainActivity) getActivity()).hideNavBar(false);
            btnAdd =((MainActivity) getActivity()).getFab();
            btnAdd.setOnClickListener( v -> {
                openManifestazione();
            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1){
               if(rc != null && rc.getAdapter() != null)
                   rc.getAdapter().notifyDataSetChanged();
        }
    }

   private void openManifestazione() {
        startActivityForResult(new Intent(getActivity(), NuovaManifestazioneActivity.class), 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_manifestazioni, container, false);
        rc = v.findViewById(R.id.rcManifestazioni);

        if( Utils.listaManifestazione == null) Utils.listaManifestazione = makeListManifestazione();

        rc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rc.setAdapter(new ManifestazioneAdapter(Utils.listaManifestazione, this::onManifestazioneClicked));
        return v;
    }

    private void onManifestazioneClicked(Manifestazione m){
        openDetails(m);
    }

    private void openDetails(Manifestazione m){
        BottomSheetDialog bt = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View bsv = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_manifestazione, getActivity().findViewById(R.id.bottomSheetContainerManifestazione));
        bt.setContentView(bsv);
        bt.show();
    }

    private ArrayList<Manifestazione> makeListManifestazione(){
        ArrayList<Manifestazione> listaManifestazioni = new ArrayList<>();
        listaManifestazioni.add(new Manifestazione("Sagra della Patata", "Via Piave, Avellino", "Mercoledì, 14 luglio 2021", "12:00", 45, false, false, Utils.getRandomImage()));
        listaManifestazioni.add(new Manifestazione("Sagra della Cipolla", "Piazza Cavour, Avellino", "Venerdi, 23 luglio 2021", "15:00", 5, false, false, Utils.getRandomImage()));
        listaManifestazioni.add(new Manifestazione("Sagra della Salsiccia", "Piazza Plebiscito, Picerno", "Mercoledì, 28 luglio 2021", "12:00", 30, false, false, Utils.getRandomImage()));

        return listaManifestazioni;
    }

}