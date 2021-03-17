package wybory.wyborcy;

import wybory.Okręg;
import wybory.partie.Partia;

abstract public class JednocechowyPartyjny extends Jednocechowy {
    protected Partia ulubiona;

    public JednocechowyPartyjny(String imię, String nazwisko, Okręg okręg,
                                int nrCechy, Partia ulubiona) {
        super(imię, nazwisko, okręg, nrCechy);
        this.ulubiona = ulubiona;
    }
}
