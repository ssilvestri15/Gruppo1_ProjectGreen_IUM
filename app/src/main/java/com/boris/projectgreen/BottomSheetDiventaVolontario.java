package com.boris.projectgreen;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

public class BottomSheetDiventaVolontario extends BottomSheetDialogFragment {
    private MaterialCheckBox ccb1, ccb2, ccb3;
    private MaterialButton btnProponiti, okay, btn1;
    private TextView titolo, sottotiolo;
    private Dialog dialog;



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
        btn1 = view.findViewById(R.id.btnDiventaVolontario);

        ccb1.setOnClickListener(v -> {
            if(ccb2.isChecked() && ccb3.isChecked()) btnProponiti.setEnabled(true);
            else btnProponiti.setEnabled(false);
        });

        ccb2.setOnClickListener(v -> {
            if(ccb1.isChecked() && ccb3.isChecked()) btnProponiti.setEnabled(true);
            else btnProponiti.setEnabled(false);
        });

        ccb3.setOnClickListener(v -> {
            if(ccb2.isChecked() && ccb1.isChecked()) btnProponiti.setEnabled(true);
            else btnProponiti.setEnabled(false);
        });

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        okay = dialog.findViewById(R.id.btnOkay);
        titolo = dialog.findViewById(R.id.txtTitolo);
        titolo.setText("GRAZIE!");

        sottotiolo = dialog.findViewById(R.id.txtSottotitolo);
        sottotiolo.setText("A breve riceverai una email con le istruzioni da seguire.");

        okay.setOnClickListener( v1 -> {
            dialog.dismiss();
            this.dismiss();
        });


        btnProponiti.setOnClickListener(v -> {
            dialog.show();
        });
        return view;
    }
}
