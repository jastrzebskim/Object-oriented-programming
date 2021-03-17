package wybory.wyborcy;

import wybory.Kandydat;
import wybory.Okręg;
import wybory.partie.Partia;

import java.util.ArrayList;

public class MaksymalizującyJednocechowyPartyjny extends JednocechowyPartyjny {
    public MaksymalizującyJednocechowyPartyjny(String imię, String nazwisko, Okręg okręg,
                                              int nrCechy, Partia wybrana) {
        super(imię, nazwisko, okręg, nrCechy, wybrana);
    }

    @Override
    public Kandydat wybierzKandydata() {
        ArrayList<Kandydat> kandydaciPartyjni = okręg.listaPartyjna(ulubiona);
        return kandydatZCechą(cecha, false, kandydaciPartyjni);
    }
}
