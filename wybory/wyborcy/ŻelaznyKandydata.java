package wybory.wyborcy;

import wybory.Kandydat;
import wybory.Okręg;
import wybory.partie.Partia;

import java.util.ArrayList;

public class ŻelaznyKandydata extends ŻelaznyPartyjny {
    private int numerKandydata;

    public ŻelaznyKandydata(String imię, String nazwisko, Okręg okręg,
                            Partia wybrana, int numer) {
        super(imię, nazwisko, okręg, wybrana);
        numerKandydata = numer;
    }

    @Override
    public void zmieńAtrybuty(Okręg okręg, int liczbaPartii) {
        numerKandydata += okręg.podstawowaLiczbaWyborców()/(10 * liczbaPartii);
        this.okręg = okręg;
    }

    @Override
    protected Kandydat pobierzKandydata(ArrayList<Kandydat> kandydaci) {
        return kandydaci.get(numerKandydata - 1);
    }
}
