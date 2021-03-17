package wybory.partie;

import wybory.Działanie;
import wybory.Okręg;
import wybory.partie.Partia;

import java.util.ArrayList;

abstract public class StrategiaKosztowa extends Partia {

    public StrategiaKosztowa (String nazwa, int pieniądze) {
        super(nazwa, pieniądze);
    }

    abstract protected int koszt();
    abstract protected boolean warunek(int kosztAktualny, int koszt);

    @Override
    protected NajlepszeRozwiązanie znajdźNajlepsze(ArrayList<Okręg> okręgi, ArrayList<Działanie> działania) {
        int koszt = koszt();
        NajlepszeRozwiązanie naj = new NajlepszeRozwiązanie(null, null);
        for (Okręg okręg : okręgi) {
            if (okręg.liczbaWyborców() == 0) continue;
            for (Działanie działanie : działania) {
                int kosztAktualny = okręg.liczbaWyborców() * działanie.sumaWektora();
                if (warunek(kosztAktualny, koszt) && kosztAktualny <= budżet) {
                    naj.ustawNajlepszyOkręg(okręg);
                    naj.ustawNajlepszeDziałanie(działanie);
                    koszt = kosztAktualny;
                }
            }
        }
        return naj;
    }
}