package com.boris.projectgreen;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Manifestazione implements Parcelable {
    private String titolo;
    private String luogo;
    private String data;
    private String ora;
    private int partecipanti;
    private boolean like;
    private boolean partecipa;
    private ArrayList<Integer> img;

    public Manifestazione() {
    }


    public Manifestazione(String titolo, String luogo, String data, String ora, int partecipanti, boolean like, boolean partecipa, ArrayList<Integer> img) {
        this.titolo = titolo;
        this.luogo = luogo;
        this.data = data;
        this.ora = ora;
        this.partecipanti = partecipanti;
        this.like = like;
        this.partecipa = partecipa;
        this.img = img;

    }

    protected Manifestazione(Parcel in) {
        titolo = in.readString();
        luogo = in.readString();
        data = in.readString();
        ora = in.readString();
        partecipanti = in.readInt();
        like = in.readByte() != 0;
        partecipa = in.readByte() != 0;
    }

    public static final Creator<Manifestazione> CREATOR = new Creator<Manifestazione>() {
        @Override
        public Manifestazione createFromParcel(Parcel in) {
            return new Manifestazione(in);
        }

        @Override
        public Manifestazione[] newArray(int size) {
            return new Manifestazione[size];
        }
    };

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public int getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(int partecipanti) {
        this.partecipanti = partecipanti;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isPartecipa() {
        return partecipa;
    }

    public void setPartecipa(boolean partecipa) {
        this.partecipa = partecipa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Integer> getImg() {
        return img;
    }

    public void setImg(ArrayList<Integer> img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titolo);
        dest.writeString(luogo);
        dest.writeString(data);
        dest.writeString(ora);
        dest.writeInt(partecipanti);
        dest.writeByte((byte) (like ? 1 : 0));
        dest.writeByte((byte) (partecipa ? 1 : 0));
    }
}
