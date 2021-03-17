package wybory.przeliczanie;

import wybory.partie.Partia;

import java.util.ArrayList;
import java.util.Random;

abstract public class MetodaDzielnikowa  extends MetodaLiczeniaGłosów {

    abstract public int różnica();

    public int[] policzMandaty(int[] głosy, ArrayList<Partia> partie, int liczbaMandatów) {
        int[] dzielniki = new int[głosy.length];
        int[] liczbyMandatów = new int[głosy.length];
        for (int i = 0; i < dzielniki.length; i++) {
            dzielniki[i] = 1;
            liczbyMandatów[i] = 0;
        }

        int maks = -1;
        ArrayList<Integer> partieRówne = new ArrayList<Integer>();
        Random r = new Random();
        int wybrana;
        for (int i = 0; i < liczbaMandatów; i++) {
            maks = -1;
            partieRówne.clear();

            for (int j = 0; j < głosy.length; j++) {
                if (głosy[j]/dzielniki[j] > maks) {
                    maks = głosy[j]/dzielniki[j];
                }
            }

            for (int j = 0; j < głosy.length; j++) {
                if (głosy[j]/dzielniki[j] == maks) {
                    partieRówne.add(j);
                }
            }

            wybrana = r.nextInt(partieRówne.size());
            liczbyMandatów[partieRówne.get(wybrana)]++;
            dzielniki[partieRówne.get(wybrana)] += różnica();

        }

        return liczbyMandatów;
    }
}
