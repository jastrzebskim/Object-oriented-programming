package wybory.wyborcy;

import wybory.Kandydat;
import wybory.Okręg;

public class MaksymalizującyJednocechowy extends Jednocechowy {

    public MaksymalizującyJednocechowy(String imię, String nazwisko, Okręg okręg,
                                        int nrCechy) {
        super(imię, nazwisko, okręg, nrCechy);
    }

    @Override
    public Kandydat wybierzKandydata() {
        return kandydatZCechą(cecha, false, okręg.listaKandydatów());
    }
}
