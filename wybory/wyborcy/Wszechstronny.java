package wybory.wyborcy;

import wybory.Działanie;
import wybory.Kandydat;
import wybory.Okręg;

import java.util.ArrayList;

public class Wszechstronny extends Wyborca {
    private ArrayList<Integer> wagi;

    public Wszechstronny(String imię, String nazwisko, Okręg okręg, ArrayList<Integer> cechy) {
        super(imię, nazwisko, okręg);
        this.wagi = cechy;
    }

    public Kandydat wybierzKandydataZListy(ArrayList<Kandydat> kandydaci) {
        int maksimum = -2000000000;
        int sumaWażona = 0;
        Kandydat zwycięzca = null;

        for (Kandydat kandydat: kandydaci) {
            sumaWażona = 0;
            for (int i = 1; i <= kandydat.liczbaCech(); i++) {
                sumaWażona += kandydat.cecha(i) * wagi.get(i - 1);
            }

            if (sumaWażona > maksimum) {
                maksimum = sumaWażona;
                zwycięzca = kandydat;
            }
        }

        return zwycięzca;
    }

    @Override
    public void zmieńWagi(Działanie działanie) {
        ArrayList<Integer> noweWagi = new ArrayList<Integer>();
        for (int i = 0; i < wagi.size(); i++) {
            int waga = wagi.get(i) + działanie.waga(i + 1);

            if (waga < -100) waga = -100;
            if (waga > 100) waga = 100;

            noweWagi.add(waga);
        }

        wagi = noweWagi;
    }

    @Override
    public int waga(int numerWagi) {
        return wagi.get(numerWagi - 1);
    }

    @Override
    public Kandydat wybierzKandydata() {
        return wybierzKandydataZListy(okręg.listaKandydatów());
    }
}
