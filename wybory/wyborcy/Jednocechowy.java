package wybory.wyborcy;

import wybory.Kandydat;
import wybory.Okręg;

import java.util.ArrayList;
import java.util.Random;

abstract public class Jednocechowy extends Wyborca {
    protected int cecha;

    public Jednocechowy(String imię, String nazwisko, Okręg okręg, int cecha) {
        super(imię, nazwisko, okręg);
        this.cecha = cecha;
    }

    /*zwraca kandydata z najmniejszą lub największą cechą - jeśli czyMin równa się true,
    wówczas szuka kandydata z najmniejszą
    Losuje spośród kandydatów z wartościami najmniejszymi/największymi
     */
    public Kandydat kandydatZCechą(int numerCechy, boolean czyMin, ArrayList<Kandydat> kandydaci) {
        int minimum = 101;
        int maksimum = - 101;
        ArrayList<Kandydat> kandydaciWyróżnieni = new ArrayList<Kandydat>();
        for (Kandydat kandydat: kandydaci) {
            if (czyMin && kandydat.cecha(numerCechy) < minimum) {
                minimum = kandydat.cecha(numerCechy);
            }
            else if (kandydat.cecha(numerCechy) > maksimum) {
                maksimum = kandydat.cecha(numerCechy);
            }
        }

        for (Kandydat kandydat : kandydaci) {
            if (czyMin) {
                if (kandydat.cecha(numerCechy) == minimum) {
                    kandydaciWyróżnieni.add(kandydat);
                }
            }
            else {
                if (kandydat.cecha(numerCechy) == maksimum) {
                    kandydaciWyróżnieni.add(kandydat);
                }
            }
        }

        Random r = new Random();
        return kandydaciWyróżnieni.get(r.nextInt(kandydaciWyróżnieni.size()));
    }
}
