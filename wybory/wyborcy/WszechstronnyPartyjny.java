package wybory.wyborcy;

import wybory.Kandydat;
import wybory.Okręg;
import wybory.partie.Partia;

import java.util.ArrayList;

public class WszechstronnyPartyjny extends Wszechstronny {
    private Partia ulubiona;

    public WszechstronnyPartyjny(String imię, String nazwisko, Okręg okręg,
                                 ArrayList<Integer> cechy, Partia wybrana) {
        super(imię, nazwisko, okręg, cechy);
        ulubiona = wybrana;
    }

    @Override
    public Kandydat wybierzKandydata() { return wybierzKandydataZListy(okręg.listaPartyjna(ulubiona)); }
}
