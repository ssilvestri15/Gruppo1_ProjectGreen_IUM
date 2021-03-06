package com.boris.projectgreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SegnalazioneAdapter extends RecyclerView.Adapter<SegnalazioneAdapter.ViewHolder> {
    
    public interface OnSegnalazioneListener {
        void onItemClick(Segnalazione segnalazione);
    }

    private ArrayList<Segnalazione> list;
    private OnSegnalazioneListener listener;

    public SegnalazioneAdapter(ArrayList<Segnalazione> list, OnSegnalazioneListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.segnalazioni_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView citta;
        private TextView via;
        private TextView data;
        private TextView num;
        private TextView tipo;
        private MaterialCardView partecipanti;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            citta = itemView.findViewById(R.id.posto_name);
            via = itemView.findViewById(R.id.via_name);
            data = itemView.findViewById(R.id.data_name);
            num = itemView.findViewById(R.id.num_p);
            tipo = itemView.findViewById(R.id.txt_tipo);
            partecipanti = itemView.findViewById(R.id.mcv_partecipanti);

        }


        public void bind(Segnalazione segnalazione, OnSegnalazioneListener listener) {

            imageView.setImageResource(segnalazione.getImgFirst());
            citta.setText(segnalazione.getCitta());
            via.setText(segnalazione.getVia());
            if(segnalazione.getDataPulizia() != null) {
                tipo.setText("Organizzata per:");
                data.setText(segnalazione.getDataPulizia());
                partecipanti.setVisibility(View.VISIBLE);
                num.setText(segnalazione.getNum()+"");
            } else {
                data.setText(segnalazione.getData());
                partecipanti.setVisibility(View.GONE);
            }

            itemView.setOnClickListener( view -> {
                listener.onItemClick(segnalazione);
            });


        }
    }
}
