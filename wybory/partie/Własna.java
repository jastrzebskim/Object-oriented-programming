package wybory.partie;

import wybory.Działanie;
import wybory.Okręg;

import java.util.ArrayList;

public class Własna extends Partia {

    public Własna(String nazwa, int pieniądze) {
        super(nazwa, pieniądze);
    }

    //Strategia "leniwa"
    protected NajlepszeRozwiązanie znajdźNajlepsze(ArrayList<Okręg> okręgi, ArrayList<Działanie> działania) {
        NajlepszeRozwiązanie naj = new NajlepszeRozwiązanie(null, null);
        for (Okręg okręg : okręgi) {
            if (okręg.liczbaWyborców() == 0) continue;
            for (Działanie działanie : działania) {
                if (okręg.liczbaWyborców() * działanie.sumaWektora() <= budżet) {
                    naj.ustawNajlepszyOkręg(okręg);
                    naj.ustawNajlepszeDziałanie(działanie);
                    break;
                }
            }
        }
        return naj;
    }
}
