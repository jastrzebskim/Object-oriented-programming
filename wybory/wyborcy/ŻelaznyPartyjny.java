package wybory.wyborcy;

import wybory.Kandydat;
import wybory.Okręg;
import wybory.partie.Partia;

import java.util.ArrayList;
import java.util.Random;

public class ŻelaznyPartyjny extends Wyborca {
    private Partia ulubiona;

    public ŻelaznyPartyjny(String imię, String nazwisko, Okręg okręg, Partia wybrana) {
        super(imię, nazwisko, okręg);
        ulubiona = wybrana;
    }

    protected Kandydat pobierzKandydata(ArrayList<Kandydat> kandydaci) {
        Random r = new Random();
        int numerWybranego = r.nextInt(kandydaci.size());
        return kandydaci.get(numerWybranego);
    }

    @Override
    public Kandydat wybierzKandydata() {
        ArrayList<Kandydat> kandydaci = okręg.listaPartyjna(ulubiona);
        return pobierzKandydata(kandydaci);
    }
}
