package com.boris.projectgreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.boris.projectgreen.databinding.ItemCustomFixedSizeLayout3Binding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView rv;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).hideNavBar(false);
        fab = ((MainActivity) getActivity()).getFab();
        fab.setOnClickListener(view -> {
            openSegnalazioni();
        });
    }

    private void openSegnalazioni() {
        startActivityForResult(new Intent(getActivity(), AggiungiSegnalazioneActivity.class), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            if (data != null && data.getExtras().get("OK").equals("OK")) {
                if (rv != null && rv.getAdapter() != null) {
                    rv.getAdapter().notifyDataSetChanged();
                }
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rv = v.findViewById(R.id.rv_Segn);

        if (Utils.listaSegnalazioni == null)
            Utils.listaSegnalazioni = makeRandomList();

        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new SegnalazioneAdapter(Utils.listaSegnalazioni, this::onSegnalazioneClicked));

        return v;
    }

    private void onSegnalazioneClicked(Segnalazione segnalazione) {
        openDetails(segnalazione);
    }


    private void openDetails(Segnalazione segnalazione) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View bottomSheetView;
        Utente utente = Utente.cerca(getContext());

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        MaterialButton okay = dialog.findViewById(R.id.btnOkay);
        TextView titolo = dialog.findViewById(R.id.txtTitolo);
        TextView sottotiolo = dialog.findViewById(R.id.txtSottotitolo);

        if((utente.getRuolo() == 1 || utente.getRuolo() == 3) && segnalazione.getNum() == 0){
            bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_volontario, getActivity().findViewById(R.id.bottomSheetContainer));
            TextInputLayout dataP = bottomSheetView.findViewById(R.id.editData);
            TextInputLayout oraP = bottomSheetView.findViewById(R.id.editOra);
            MaterialButton btnOrganizza = bottomSheetView.findViewById(R.id.btnOrganizza);

            btnOrganizza.setOnClickListener(v -> {
                String d = dataP.getEditText().getText().toString();
                if(d != null && !d.equals("")) {
                    String o = oraP.getEditText().getText().toString();
                    if(o != null && !o.equals("")) {
                        segnalazione.setDataPulizia(d);
                        segnalazione.setOraPulizia(o);
                        segnalazione.incrementaNum();
                        segnalazione.setPartecipo(true);
                        titolo.setText("Gruppo organizzato!");
                        sottotiolo.setText("L'ambiente ha bisogno di gente come te per poter rinascere. Grazie per il tuo aiuto!");
                        dialog.show();
                        okay.setOnClickListener(v2 -> {
                            dialog.dismiss();
                            bottomSheetDialog.dismiss();
                            rv.getAdapter().notifyDataSetChanged();
                        });
                    } else oraP.setError("Il campo non può essere vuoto");
                } else dataP.setError("Il campo non può essere vuoto");
            });
        } else {

            bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_prenotazioni, getActivity().findViewById(R.id.bottomSheetContainer));
            MaterialButton btnPartecipa = bottomSheetView.findViewById(R.id.btnPartecipaGV);
            TextView rifiuti = bottomSheetView.findViewById(R.id.txt_rifiuti);
            TextView dataSegn = bottomSheetView.findViewById(R.id.txtdataSegnalazione);
            dataSegn.setText("Segnalato il " + segnalazione.getData());
            rifiuti.setText(segnalazione.getRifiuti());
            if(segnalazione.getDataPulizia() != null) {
                TextView data = bottomSheetView.findViewById(R.id.data_details);
                data.setText(segnalazione.getDataPulizia());
                TextView ora = bottomSheetView.findViewById(R.id.ora_details);
                ora.setText(segnalazione.getOraPulizia());
                TextView partecipanti = bottomSheetView.findViewById(R.id.num_details);
                partecipanti.setText(segnalazione.getNum() + "");

                MaterialButton btnNonPartecipa = bottomSheetView.findViewById(R.id.btnNonPartecipaGV);
                if(!segnalazione.isPartecipa()) {
                    btnPartecipa.setVisibility(View.VISIBLE);
                    btnNonPartecipa.setVisibility(View.GONE);
                    btnPartecipa.setOnClickListener(v2 -> {
                        segnalazione.incrementaNum();
                        titolo.setText("Grazie per esserti unito!");
                        sottotiolo.setText("L'ambiente ha bisogno di gente come te per poter rinascere. Grazie per il tuo aiuto!");
                        dialog.show();
                        okay.setOnClickListener(v3 -> {
                            dialog.dismiss();
                            bottomSheetDialog.dismiss();
                            rv.getAdapter().notifyDataSetChanged();
                        });
                    });
                } else {
                    btnPartecipa.setVisibility(View.GONE);
                    btnNonPartecipa.setVisibility(View.VISIBLE);
                    btnNonPartecipa.setOnClickListener(v4 -> {
                        segnalazione.decrementaNum();
                        if(segnalazione.getNum() == 0){
                            segnalazione.setDataPulizia(null);
                            segnalazione.setOraPulizia(null);
                        }
                        titolo.setText("Sarà per la prossima!");
                        sottotiolo.setText("Non ti preoccupare, ci sono ancora tante battaglie per cui combattere! Alla prossima!");
                        dialog.show();
                        okay.setOnClickListener(v3 -> {
                            dialog.dismiss();
                            bottomSheetDialog.dismiss();
                            rv.getAdapter().notifyDataSetChanged();
                        });
                    });
                }
            } else {
                LinearLayout ll = bottomSheetView.findViewById(R.id.llDataOraRaccolta);
                TextView txt = bottomSheetView.findViewById(R.id.txtGruppoNonOrganizzato);
                txt.setVisibility(View.VISIBLE);
                ll.setVisibility(View.GONE);
                txt.setText("Gruppo volontari non organizzato.");
                btnPartecipa.setEnabled(false);
            }
        }

        ImageCarousel carousel = bottomSheetView.findViewById(R.id.carousel);
        TextView luogo = bottomSheetView.findViewById(R.id.via_citta_details);
        luogo.setText(segnalazione.getVia() + ", " + segnalazione.getCitta());


        carousel.registerLifecycle(getViewLifecycleOwner());
        carousel.setCarouselListener(new CarouselListener() {
            @NotNull
            @Override
            public ViewBinding onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
                return ItemCustomFixedSizeLayout3Binding.inflate(layoutInflater, viewGroup, false);
            }

            @Override
            public void onBindViewHolder(@NotNull ViewBinding viewBinding, @NotNull CarouselItem carouselItem, int i) {

                ItemCustomFixedSizeLayout3Binding currentBinding = (ItemCustomFixedSizeLayout3Binding) viewBinding;
                currentBinding.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                org.imaginativeworld.whynotimagecarousel.utils.Utils.setImage(currentBinding.imageView,carouselItem,R.drawable.ic_wb_cloudy_with_padding);

            }

            @Override
            public void onClick(int i, @NotNull CarouselItem carouselItem) {

            }

            @Override
            public void onLongClick(int i, @NotNull CarouselItem carouselItem) {

            }
        });

        List<CarouselItem> list = new ArrayList<>();

        for (int img : segnalazione.getImgList()) {
            list.add(new CarouselItem(img));
        }

        carousel.setData(list);



        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private ArrayList<Segnalazione> makeRandomList() {

        ArrayList<Segnalazione> x = new ArrayList<>();
        int num = new Random().nextInt(5) + 1;
        for (int i = 0; i < num; i++) {
            x.add(new Segnalazione());
        }
        return x;

    }
}