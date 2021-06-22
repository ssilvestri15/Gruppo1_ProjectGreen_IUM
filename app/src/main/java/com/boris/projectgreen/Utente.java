package com.boris.projectgreen;

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
    private ArrayList<Integer> fotoProfilo;
    private int ruolo;

    public Utente() {
    }

    public Utente(String nome, String cognome, String indirizzo, String citta, String dataNascita, String email, String password, ArrayList<Integer> fotoProfilo, int ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.fotoProfilo = fotoProfilo;
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

    public ArrayList<Integer> getFotoProfilo() {
        return fotoProfilo;
    }

    public void setFotoProfilo(ArrayList<Integer> fotoProfilo) {
        this.fotoProfilo = fotoProfilo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", dataNascita=" + dataNascita +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ruolo=" + ruolo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(email, utente.email);
    }
}
