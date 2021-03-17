package wybory.partie;

import wybory.Działanie;
import wybory.Kandydat;
import wybory.Okręg;
import wybory.wyborcy.Wyborca;

import java.util.ArrayList;

public class Zachłanna extends Partia {

    public Zachłanna(String nazwa, int pieniądze) {super(nazwa, pieniądze);}

    //oblicza wagę, którą miałby dany wyborca po odbyciu kampanii
    private int podejrzewanaWaga(int obecnaWaga, int zmianaWagi) {
        obecnaWaga += zmianaWagi;
        if (zmianaWagi > 100) return 100;
        if (zmianaWagi < -100) return -100;
        else return obecnaWaga;
    }

    @Override
    protected NajlepszeRozwiązanie znajdźNajlepsze(ArrayList<Okręg> okręgi, ArrayList<Działanie> działania) {
        int sumaWażona1 = 0;
        int sumaWażona2 = 0;
        int największaRóżnica = -2000000000;
        NajlepszeRozwiązanie naj = new NajlepszeRozwiązanie(null, null);
        for (Okręg okręg : okręgi) {
            if (okręg.liczbaWyborców() == 0) continue;
            for (Działanie działanie : działania) {
                sumaWażona1 = 0;
                sumaWażona2 = 0;
                ArrayList<Kandydat> partyjni = okręg.listaPartyjna(this);
                ArrayList<Wyborca> wyborcyOkręgu = okręg.listaWyborców();

                for (Kandydat obecnyKandydat : partyjni) {
                    for (Wyborca wyborca : wyborcyOkręgu) {
                        for (int i = 1; i <= obecnyKandydat.liczbaCech(); i++) {
                            sumaWażona1 += obecnyKandydat.cecha(i) * wyborca.waga(i);
                            sumaWażona2 += obecnyKandydat.cecha(i) *
                                    podejrzewanaWaga(wyborca.waga(i), działanie.waga(i));
                        }
                    }
                }

                if (sumaWażona2 - sumaWażona1 > największaRóżnica &&
                        okręg.liczbaWyborców() * działanie.sumaWektora() <= budżet) {
                    naj.ustawNajlepszeDziałanie(działanie);
                    naj.ustawNajlepszyOkręg(okręg);
                    największaRóżnica = sumaWażona2 - sumaWażona1;
                }
            }
        }
        return naj;
    }
}
