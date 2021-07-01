package com.boris.projectgreen;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

    public static ArrayList<Integer> getRandomImage() {
        ArrayList<Integer> temp = new ArrayList<>();
        int[] array = new int[]{R.drawable.trash_1,R.drawable.trash_2,R.drawable.trash_3,R.drawable.trash_4,R.drawable.trash_5};
        int x = new Random().nextInt(5)+1;
        for (int i=0; i<x; i++){
            temp.add(array[new Random().nextInt(5)]);
        }
        return temp;
    }

    public static ArrayList<Segnalazione> listaSegnalazioni = null;

    public static ArrayList<Manifestazione> listaManifestazione = null;
}
