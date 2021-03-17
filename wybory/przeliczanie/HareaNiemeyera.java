package wybory.przeliczanie;

import wybory.partie.Partia;

import java.util.*;

public class HareaNiemeyera extends MetodaLiczeniaGłosów {
    public String nazwa() {
        return "Hare’a-Niemeyera";
    }

    protected int[] policzMandaty(int[] głosy, ArrayList<Partia> partie, int liczbaMandatów) {
        int[] liczbyMandatów = new int[partie.size()];

        int rozdzieloneMandaty = 0;
        int wszystkieGłosy = 0;

        for (int i = 0; i < głosy.length; i++) {
            wszystkieGłosy += głosy[i];
        }

        for (int i = 0; i < głosy.length; i++) {
            liczbyMandatów[i] =  (głosy[i] * liczbaMandatów)/wszystkieGłosy;
            rozdzieloneMandaty += liczbyMandatów[i];
        }

        double[] poPrzecinku = new double[liczbyMandatów.length];

        for (int i = 0; i < poPrzecinku.length; i++) {
            poPrzecinku[i] = (głosy[i]*liczbaMandatów)/wszystkieGłosy;
            poPrzecinku[i] -= liczbyMandatów[i];
        }

        double ostatniNajwiekszy = 200000000;
        double maks;

        HashSet<Integer> doWyrównania = new HashSet<Integer>();
        int losowy;
        int obecnyIndeks;
        int obecniePrzeglądany = 0;

        /*dobieranie mandatów - zbieranie w HashSet numerów partii o największych liczbach
         po przecinku i losowanie między nimi
         */
        while (liczbaMandatów > rozdzieloneMandaty) {
            maks = -2000000000;
            for (int i = 0; i < poPrzecinku.length; i++) {
                if (maks < poPrzecinku[i] && poPrzecinku[i] < ostatniNajwiekszy) {
                    maks = poPrzecinku[i];
                }
            }

            //najwieksza ostatnio rozpatrywana liczba po przecinku
            ostatniNajwiekszy = maks;

            for (int i = 0; i < poPrzecinku.length; i++) {
                if (poPrzecinku[i] == ostatniNajwiekszy) {
                    doWyrównania.add(i);
                }
            }

            Random r = new Random();
            int wygrywający = 0;

            while (liczbaMandatów > rozdzieloneMandaty && doWyrównania.size() > 0) {
                obecnyIndeks = 0;
                /*iterator po zbiorze, zawierającym numery partii o obecnie rozpatrywanej
                 największej wartości po przecinku
                 */
                Iterator<Integer> it = doWyrównania.iterator();
                losowy = r.nextInt(doWyrównania.size());
                while (it.hasNext()) {
                    obecniePrzeglądany = it.next();

                    //gdy dojdzie do wylosowanego indeksu partii - dodaje głosy partii
                    if (obecnyIndeks == losowy) {
                        liczbyMandatów[obecniePrzeglądany]++;
                        wygrywający = obecniePrzeglądany;
                        rozdzieloneMandaty++;
                    }

                    obecnyIndeks++;
                }

                doWyrównania.remove(wygrywający);

                if (liczbaMandatów <= rozdzieloneMandaty) {
                    break;
                }
            }
        }
        return liczbyMandatów;
    }
}
