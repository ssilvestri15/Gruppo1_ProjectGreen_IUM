package com.boris.projectgreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

public class BottomSheetManifestazione extends BottomSheetDialogFragment {
    private TextView titolo, luogo, data, ora, partecipanti;
    private MaterialButton miPiace, partecipa, condividi;

    public BottomSheetManifestazione() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_manifestazione, container, false);
        titolo = view.findViewById(R.id.txtNomeManifestazione);
        luogo = view.findViewById(R.id.txtLuogoBS);
        data = view.findViewById(R.id.txtDataBS);
        ora = view.findViewById(R.id.txtOraBS);
        partecipanti = view.findViewById(R.id.txtPartecipantiBS);
        miPiace = view.findViewById(R.id.btnMiPiaceBS);
        partecipa = view.findViewById(R.id.btnPartecipaBS);
        condividi = view.findViewById(R.id.btnCondividiBS);
        return view;
    }
}
