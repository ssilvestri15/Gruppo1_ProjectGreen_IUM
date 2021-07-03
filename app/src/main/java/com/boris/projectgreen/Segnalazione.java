package com.boris.projectgreen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class Segnalazione {

    private String citta;
    private String via;
    private String data;
    private int num;
    private String dataPulizia, oraPulizia, rifiuti;
    private boolean partecipo;
    private ArrayList<Integer> img;

    public Segnalazione() {
        makeRandom();
    }

    private void makeRandom() {

        String name = getRandomName();
        String street = getRandomStreet();
        String date = getRandomDate();
        String rif = getRandomRifiuti();
        int n = new Random().nextInt(98) + 1;

        this.citta = name;
        this.via = street;
        this.data = date;
        this.num = 0;
        this.img = Utils.getRandomImage();
        this.rifiuti = rif;
        this.dataPulizia = null;
        this.oraPulizia = null;
        this.partecipo = false;

    }

    public Segnalazione(String citta, String via, String data, int num, ArrayList<Integer> img) {
        this.citta = citta;
        this.via = via;
        this.data = data;
        this.num = num;
        this.img = img;
    }

    public Segnalazione(String citta, String via, String rifiuti, String data, int num) {
        this.citta = citta;
        this.via = via;
        this.rifiuti = rifiuti;
        this.data = data;
        this.num = num;
        this.img = Utils.getRandomImage();
        this.dataPulizia = null;
        this.oraPulizia = null;
        this.partecipo = false;
    }


    public String getCitta() {
        return citta;
    }

    public String getVia() {
        return via;
    }

    public String getData() {
        return data;
    }

    public int getNum() {
        return num;
    }

    public void incrementaNum() { this.num++; }

    public void decrementaNum() { if(this.num > 0) this.num--; }

    public String getDataPulizia() { return dataPulizia; }

    public void setDataPulizia(String dataPulizia) { this.dataPulizia = dataPulizia; }

    public String getOraPulizia() { return oraPulizia; }

    public void setOraPulizia(String oraPulizia) { this.oraPulizia = oraPulizia; }

    public boolean isPartecipa() { return this.partecipo; }

    public void setPartecipo(boolean partecipo) { this.partecipo = partecipo; }

    public String getRifiuti() { return this.rifiuti; }

    public void setRifiuti(String rifiuti) { this.rifiuti = rifiuti; }

    public int getImgFirst() {
        return img.get(0);
    }

    public ArrayList<Integer> getImgList() {
        return img;
    }

    private String getRandomStreet() {
        return "Via "+getRandomName();
    }

    private String getRandomName() {
        String[] array = new String[]{
                "Agrigento",
                "Alessandria",
                "Ancona",
                "Aosta",
                "L'Aquila",
                "Arezzo",
                "Ascoli-Piceno",
                "Asti",
                "Avellino",
                "Bari",
                "Barletta-Andria-Trani",
                "Belluno",
                "Benevento",
                "Bergamo",
                "Biella",
                "Bologna",
                "Bolzano",
                "Brescia",
                "Brindisi",
                "Cagliari",
                "Caltanissetta",
                "Campobasso",
                "Carbonia Iglesias",
                "Caserta",
                "Catania",
                "Catanzaro",
                "Chieti",
                "Como",
                "Cosenza",
                "Cremona",
                "Crotone",
                "Cuneo",
                "Enna",
                "Fermo",
                "Ferrara",
                "Firenze",
                "Foggia",
                "Forli-Cesena",
                "Frosinone",
                "Genova",
                "Gorizia",
                "Grosseto",
                "Imperia",
                "Isernia",
                "La-Spezia",
                "Latina",
                "Lecce",
                "Lecco",
                "Livorno",
                "Lodi",
                "Lucca",
                "Macerata",
                "Mantova",
                "Massa-Carrara",
                "Matera",
                "Medio Campidano",
                "Messina",
                "Milano",
                "Modena",
                "Monza-Brianza",
                "Napoli",
                "Novara",
                "Nuoro",
                "Ogliastra",
                "Olbia Tempio",
                "Oristano",
                "Padova",
                "Palermo",
                "Parma",
                "Pavia",
                "Perugia",
                "Pesaro-Urbino",
                "Pescara",
                "Piacenza",
                "Pisa",
                "Pistoia",
                "Pordenone",
                "Potenza",
                "Prato",
                "Ragusa",
                "Ravenna",
                "Reggio-Calabria",
                "Reggio-Emilia",
                "Rieti",
                "Rimini",
                "Roma",
                "Rovigo",
                "Salerno",
                "Sassari",
                "Savona",
                "Siena",
                "Siracusa",
                "Sondrio",
                "Taranto",
                "Teramo",
                "Terni",
                "Torino",
                "Trapani",
                "Trento",
                "Treviso",
                "Trieste",
                "Udine",
                "Varese",
                "Venezia",
                "Verbania",
                "Vercelli",
                "Verona",
                "Vibo-Valentia",
                "Vicenza",
                "Viterbo"};

        return array[new Random().nextInt(array.length)];
    }

    private String getRandomRifiuti() {
        String[] array = new String[] {
                "Plastica",
                "Vetro",
                "Ferro",
                "Carta",
                "Elettronici" };
        return array[new Random().nextInt(array.length)];
    }

    private String getRandomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, (new Random().nextInt(28) + 1));
        calendar.set(Calendar.MONTH, (new Random().nextInt(12) + 1));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        return formatter.format(calendar.getTime());
    }

}
