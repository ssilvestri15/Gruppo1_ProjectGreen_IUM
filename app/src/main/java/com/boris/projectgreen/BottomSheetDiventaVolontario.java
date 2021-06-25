package com.boris.projectgreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

public class BottomSheetDiventaVolontario extends BottomSheetDialogFragment {
    private MaterialCheckBox ccb1, ccb2, ccb3;
    private MaterialButton btnProponiti;

    public BottomSheetDiventaVolontario() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_diventavolontario, container, false);
        ccb1 = view.findViewById(R.id.ccb1);
        ccb2 = view.findViewById(R.id.ccb2);
        ccb3 = view.findViewById(R.id.ccb3);
        btnProponiti = view.findViewById(R.id.btnProponiti);
        btnProponiti.setClickable(false);

        ccb1.setOnClickListener(v -> {
            if(ccb2.isChecked() && ccb3.isChecked()) btnProponiti.setClickable(true);
            else btnProponiti.setClickable(false);
        });

        ccb2.setOnClickListener(v -> {
            if(ccb1.isChecked() && ccb3.isChecked()) btnProponiti.setClickable(true);
            else btnProponiti.setClickable(false);
        });

        ccb3.setOnClickListener(v -> {
            if(ccb2.isChecked() && ccb1.isChecked()) btnProponiti.setClickable(true);
            else btnProponiti.setClickable(false);
        });

        btnProponiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
