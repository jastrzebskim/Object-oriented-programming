package wybory;

import java.util.ArrayList;
import java.lang.Math;

public class Działanie {
    private ArrayList<Integer> wektor;

    public Działanie(ArrayList<Integer> nowyWektor) {
        wektor = nowyWektor;
    }

    public int waga(int numerWagi) {
        return wektor.get(numerWagi - 1);
    }

    public int sumaWektora() {
        int suma = 0;

        for (Integer liczba : wektor) {
            suma += Math.abs(liczba);
        }

        return suma;
    }
}
