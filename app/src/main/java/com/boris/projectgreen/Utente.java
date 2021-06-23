package com.boris.projectgreen;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


public class Utente {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String citta;
    private String dataNascita;
    private String email;
    private String password;
    private int ruolo;
    private int livello;
    private int progressiLivello;
    private int segnalazione;
    private int progressiSegnalazione;
    private int donazione;
    private int progressiDonazione;
    private int partecipazioni;
    private int progressiPartecipazioni;
    private static SharedPreferences s;

    public Utente() {
    }

    public Utente(String nome, String cognome, String indirizzo, String citta, String dataNascita, String email, String password, int ruolo, int livello, int progressiLivello, int segnalazione, int progressiSegnalazione, int donazione, int progressiDonazione, int partecipazioni, int progressiPartecipazioni) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.livello = livello;
        this.progressiLivello = progressiLivello;
        this.segnalazione = segnalazione;
        this.progressiSegnalazione = progressiSegnalazione;
        this.donazione = donazione;
        this.progressiDonazione = progressiDonazione;
        this.partecipazioni = partecipazioni;
        this.progressiPartecipazioni = progressiPartecipazioni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public int getProgressiLivello() {
        return progressiLivello;
    }

    public void setProgressiLivello(int progressiLivello) {
        this.progressiLivello = progressiLivello;
    }

    public int getSegnalazione() {
        return segnalazione;
    }

    public void setSegnalazione(int segnalazione) {
        this.segnalazione = segnalazione;
    }

    public int getProgressiSegnalazione() {
        return progressiSegnalazione;
    }

    public void setProgressiSegnalazione(int progressiSegnalazione) {
        this.progressiSegnalazione = progressiSegnalazione;
    }

    public int getDonazione() {
        return donazione;
    }

    public void setDonazione(int donazione) {
        this.donazione = donazione;
    }

    public int getProgressiDonazione() {
        return progressiDonazione;
    }

    public void setProgressiDonazione(int progressiDonazione) {
        this.progressiDonazione = progressiDonazione;
    }

    public int getPartecipazioni() {
        return partecipazioni;
    }

    public void setPartecipazioni(int partecipazioni) {
        this.partecipazioni = partecipazioni;
    }

    public int getProgressiPartecipazioni() {
        return progressiPartecipazioni;
    }

    public void setProgressiPartecipazioni(int progressiPartecipazioni) {
        this.progressiPartecipazioni = progressiPartecipazioni;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", dataNascita='" + dataNascita + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ruolo=" + ruolo +
                ", livello=" + livello +
                ", progressiLivello=" + progressiLivello +
                ", segnalazione=" + segnalazione +
                ", progressiSegnalazione=" + progressiSegnalazione +
                ", donazione=" + donazione +
                ", progressiDonazione=" + progressiDonazione +
                ", partecipazioni=" + partecipazioni +
                ", progressiPartecipazioni=" + progressiPartecipazioni +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(email, utente.email);
    }

    public static void salva(Context c, Utente x){
        s = c.getSharedPreferences("utente", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();
        e.putString("nome", x.getNome());
        e.putString("cognome", x.getCognome());
        e.putString("citta", x.getCitta());
        e.putString("indirizzo", x.getIndirizzo());
        e.putString("nascita", x.getDataNascita());
        e.putString("email", x.getEmail());
        e.putString("password", x.getPassword());
        e.putInt("ruolo", x.getRuolo());
        e.putInt("livello", x.getLivello());
        e.putInt("progressiLivello", x.getProgressiLivello());
        e.putInt("segnalazione", x.getSegnalazione());
        e.putInt("progressisegnalazione", x.getProgressiSegnalazione());
        e.putInt("donazione", x.getDonazione());
        e.putInt("progressidonazione", x.getProgressiDonazione());
        e.putInt("partecipazione", x.getPartecipazioni());
        e.putInt("progressipartecipazione", x.getProgressiPartecipazioni());
        e.apply();
    }

    public static Utente cerca(Context c){
        SharedPreferences s = c.getSharedPreferences("utente", Context.MODE_PRIVATE);
        Utente u = new Utente();
        u.setNome(s.getString("nome", ""));
        u.setCognome(s.getString("cognome", ""));
        u.setCitta(s.getString("citta",""));
        u.setIndirizzo(s.getString("indirizzo",""));
        u.setEmail( s.getString("email",""));
        u.setDataNascita(s.getString("nascita",""));
        u.setRuolo(s.getInt("ruolo",0));
        u.setPassword(s.getString("password",""));
        u.setLivello(s.getInt("livello", 0));
        u.setLivello(s.getInt("progressiLivello", 0));
        u.setLivello(s.getInt("segnalazione", 0));
        u.setLivello(s.getInt("progressisegnalazione", 0));
        u.setLivello(s.getInt("donazione", 0));
        u.setLivello(s.getInt("progressidonazione", 0));
        u.setLivello(s.getInt("partecipazione", 0));
        u.setLivello(s.getInt("progressipartecipazione", 0));
        return u;

    }
}
