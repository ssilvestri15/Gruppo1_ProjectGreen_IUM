package com.boris.projectgreen;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ManifestazioneAdapter extends RecyclerView.Adapter<ManifestazioneAdapter.ViewHolder> {

    public interface OnManifestazioneListener{
        void onItemClick(Manifestazione m);
    }


    private ArrayList<Manifestazione> lista;
    private OnManifestazioneListener listener;

    public ManifestazioneAdapter(ArrayList<Manifestazione> lista, OnManifestazioneListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ManifestazioneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.manifestazioni_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ManifestazioneAdapter.ViewHolder holder, int position) {
        holder.bind(lista.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nome, luogo, data, ora, partecipanti;
        private MaterialButton like, partecipa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.txtNomeManifestazione);
            luogo = itemView.findViewById(R.id.txtLuogo);
            data = itemView.findViewById(R.id.txtData);
            ora = itemView.findViewById(R.id.txtOra);
            partecipanti = itemView.findViewById(R.id.txtPartecipanti);
            like = itemView.findViewById(R.id.btnMiPiace);
            partecipa = itemView.findViewById(R.id.btnPartecipa);


        }

        public void bind(Manifestazione m, OnManifestazioneListener listener){
            nome.setText(m.getTitolo());
            luogo.setText(m.getLuogo());
            data.setText(m.getData());
            ora.setText(m.getOra());
            partecipanti.setText(m.getPartecipanti() + "");
            itemView.setOnClickListener(v -> listener.onItemClick(m));

            like.setOnClickListener(v -> {
                if(!m.isLike()) {
                    like.setTextColor(Color.parseColor("#3EA851"));
                    like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_iconfinder_icon, 0, 0, 0);
                    m.setLike(true);
                }
                else {
                    like.setTextColor(Color.BLACK);
                    like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like_outline_24, 0, 0, 0);
                    m.setLike(false);
                }
            });

            partecipa.setOnClickListener(v -> {
                if(!m.isPartecipa()) {
                    partecipa.setTextColor(Color.parseColor("#3EA851"));
                    partecipa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_star_outline_green_24, 0, 0, 0);
                    m.setPartecipanti(m.getPartecipanti() + 1);
                    partecipanti.setText(m.getPartecipanti() + "");
                    m.setPartecipa(true);
                } else {
                    partecipa.setTextColor(Color.BLACK);
                    partecipa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_star_outline_24, 0, 0, 0);
                    m.setPartecipanti(m.getPartecipanti() - 1);
                    partecipanti.setText(m.getPartecipanti() + "");
                    m.setPartecipa(false);
                }
            });
        }

    }
}
