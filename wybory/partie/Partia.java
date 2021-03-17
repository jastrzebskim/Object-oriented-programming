package wybory.partie;

import wybory.Działanie;
import wybory.Okręg;

import java.util.ArrayList;

abstract public class Partia {
    protected String nazwa;
    protected int budżet;

    public Partia(String nazwa, int budżet) {
        this.nazwa = nazwa;
        this.budżet = budżet;
    }

    public String nazwa() {
        return nazwa;
    }

    public boolean porównajNazwę(String napis) {
        return nazwa.compareTo(napis) == 0;
    }

    //klasa trzymająca informację o okręgu oraz działaniu wybranych w ramach przeprowadzenia kampanii
    protected class NajlepszeRozwiązanie {
        private Okręg najlepszyOkręg;
        private Działanie najlepszeDziałanie;

        public NajlepszeRozwiązanie(Okręg o, Działanie d) {
            najlepszyOkręg = o;
            najlepszeDziałanie = d;
        }

        public Działanie najlepszeDziałanie() {
            return najlepszeDziałanie;
        }

        public Okręg najlepszyOkręg() {
            return najlepszyOkręg;
        }

        public void ustawNajlepszeDziałanie(Działanie d) {
            najlepszeDziałanie = d;
        }

        public void ustawNajlepszyOkręg(Okręg o) {
            najlepszyOkręg = o;
        }
    }

    //znajduje okręg i działanie do przeprowadzenia kampanii według preferencji
    abstract protected NajlepszeRozwiązanie znajdźNajlepsze(ArrayList<Okręg> okręgi, ArrayList<Działanie> działania);

    public boolean przeprowadźKampanię(ArrayList<Okręg> okręgi, ArrayList<Działanie> działania) {
        NajlepszeRozwiązanie kamp = znajdźNajlepsze(okręgi, działania);

        if (kamp.najlepszyOkręg() == null) return false;
        kamp.najlepszyOkręg().zmieńWagi(kamp.najlepszeDziałanie());
        budżet -= kamp.najlepszyOkręg().liczbaWyborców() * kamp.najlepszeDziałanie().sumaWektora();
        return true;
    }
}
