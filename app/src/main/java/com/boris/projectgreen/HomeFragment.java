package com.boris.projectgreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.boris.projectgreen.databinding.ItemCustomFixedSizeLayout3Binding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        Utente utente = Utente.cerca(getContext());

        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_prenotazioni, getActivity().findViewById(R.id.bottomSheetContainer));

        if((utente.getRuolo() == 1 || utente.getRuolo() == 3) && segnalazione.getNum() == 0){
            bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_volontario, getActivity().findViewById(R.id.bottomSheetContainer));
        }

        ImageCarousel carousel = bottomSheetView.findViewById(R.id.carousel);
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