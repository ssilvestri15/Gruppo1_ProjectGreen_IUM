package com.boris.projectgreen;

import java.util.ArrayList;

public class ListaUtenti {
    private ArrayList<Utente> utenti;


    public ListaUtenti() {
        utenti = new ArrayList<>();
    }

    public boolean aggiungiUtente(Utente utente) {
        if(cercaUtentePerEmail(utente) != -1) return false;
        utenti.add(utente);
        return true;
    }

    public boolean aggiornaUtente(Utente utente) {
        int i = cercaUtentePerEmail(utente);
        if(i != -1) {
            utenti.set(i, utente);
            return true;
        } else return false;
    }

    public int cercaUtentePerEmail(Utente utente) {
        if(utenti.contains(utente)) return utenti.indexOf(utente);
        return -1;
    }
}
