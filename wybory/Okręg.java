package wybory;

import wybory.partie.Partia;
import wybory.wyborcy.Wyborca;

import java.util.ArrayList;

public class Okręg {
    private int numerOkręgu;
    private int liczbaWyborców;
    private ArrayList<Wyborca> wyborcy;
    private ArrayList<Kandydat> kandydaci;
    private int podstawowaLiczbaWyborców;
    private Okręg połączonyZ;//okręg z numerem o jeden mniejszym

    public Okręg(int numerOkręgu) {
        this.numerOkręgu = numerOkręgu;
        wyborcy = new ArrayList<Wyborca>();
        kandydaci = new ArrayList<Kandydat>();
        this.podstawowaLiczbaWyborców = 0;
        połączonyZ = null;
        liczbaWyborców = 0;
    }

    public void ustawPodstawowąLiczbęWyborców(int liczba) {
        podstawowaLiczbaWyborców = liczba;
    }

    public int podstawowaLiczbaWyborców() {
        return  podstawowaLiczbaWyborców;
    }
    public ArrayList<Kandydat> listaKandydatów() {
        ArrayList<Kandydat> kand = new ArrayList<Kandydat>();
        for (Kandydat kandydat: kandydaci) {
            kand.add(kandydat);
        }
        return kand;
    }

    public int numerOkręgu() {
        return numerOkręgu;
    }

    public boolean niepołączony() {
        return połączonyZ == null;
    }

    public ArrayList<Wyborca> listaWyborców() {
        ArrayList<Wyborca> wyb = new ArrayList<Wyborca>();
        for (Wyborca wyborca: wyborcy) {
            wyb.add(wyborca);
        }
        return wyb;
    }

    public int liczbaWyborców() {
        return liczbaWyborców;
    }

    public void połączZ (Okręg okręg) {
        połączonyZ = okręg;
    }

    public void dodajKandydata (Kandydat kandydat, int liczbaPartii) {
        if (połączonyZ == null) {
            kandydaci.add(kandydat);
        }
        else {
            kandydat.ustawNumerNaLiście(kandydat.numerNaLiście() +
                    połączonyZ.podstawowaLiczbaWyborców()/(10*liczbaPartii));
            połączonyZ.dodajKandydata(kandydat, liczbaPartii);
        }
    }

    public void dodajWyborcę(Wyborca wyborca, int liczbaPartii) {
        if (połączonyZ == null) {
            wyborcy.add(wyborca);
            liczbaWyborców++;
        } else {
            wyborca.zmieńAtrybuty(połączonyZ, liczbaPartii);
            połączonyZ.dodajWyborcę(wyborca, liczbaPartii);
        }
    }

    public ArrayList<Kandydat> listaPartyjna(Partia partia) {
        ArrayList<Kandydat> kandydaciPartyjni = new ArrayList<Kandydat>();

        for (Kandydat kandydat: kandydaci) {
            if (kandydat.partia() == partia) {
                kandydaciPartyjni.add(kandydat);
            }
        }

        return kandydaciPartyjni;
    }

    public void zmieńWagi(Działanie działanie) {
        for (Wyborca wyborca : wyborcy) {
            wyborca.zmieńWagi(działanie);
        }
    }

    public void głosuj() {
        for (Wyborca wyborca : wyborcy) {
            wyborca.zagłosuj();
        }
    }

    public int[] policzGłosy(ArrayList<Partia> partie) {
        int[] głosy = new int[partie.size()];
        for (int i = 0; i < głosy.length; i++) głosy[i] = 0;

        for (int i = 0; i < kandydaci.size(); i++) {
            for (int j = 0; j < partie.size(); j++) {
                if (kandydaci.get(i).partia() == partie.get(j)) {
                    głosy[j] += kandydaci.get(i).liczbaGłosów();
                }
            }
        }
        return głosy;
    }
}
