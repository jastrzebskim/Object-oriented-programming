package wybory.wyborcy;

import wybory.Kandydat;
import wybory.Okręg;

public class MinimalizującyJednocechowy extends Jednocechowy {

    public MinimalizującyJednocechowy(String imię, String nazwisko, Okręg okręg, int nrCechy) {
        super(imię, nazwisko, okręg, nrCechy);
    }

    @Override
    public Kandydat wybierzKandydata() {
        return kandydatZCechą(cecha, true, okręg.listaKandydatów());
    }
}
