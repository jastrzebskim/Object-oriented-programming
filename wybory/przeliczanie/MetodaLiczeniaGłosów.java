package wybory.przeliczanie;

import wybory.Kandydat;
import wybory.Okręg;
import wybory.partie.Partia;
import wybory.wyborcy.Wyborca;

import java.util.ArrayList;

abstract public class MetodaLiczeniaGłosów {

    abstract protected int[] policzMandaty(int[] głosy, ArrayList<Partia> partie, int liczbaMandatów);

    abstract protected String nazwa();

    //buduje Stringa finałowego z całej metody przeliczania i liczy głosy
    public String liczGłosy(ArrayList<Okręg> okręgi, ArrayList<Partia> partie) {
        String s = "Metoda: ";
        StringBuilder sB = new StringBuilder(s);
        sB.append(nazwa());
        sB.append(System.lineSeparator());
        int[] mandatyWszystkichPartii = new int[partie.size()];

        for (int i = 0; i < mandatyWszystkichPartii.length; i++) {
            mandatyWszystkichPartii[i] = 0;
        }
        for (int i = 0; i < okręgi.size(); i++) {
            if (okręgi.get(i).liczbaWyborców() == 0) continue;
            else if (i < okręgi.size() - 1 && !okręgi.get(i + 1).niepołączony()) {
                sB.append(okręgi.get(i).numerOkręgu()).append(" ");
                sB.append(okręgi.get(i + 1).numerOkręgu()).append(System.lineSeparator());
            }
            else {
                sB.append(Integer.toString(i)).append(System.lineSeparator());
            }

            ArrayList<Wyborca> wyborcy = okręgi.get(i).listaWyborców();

            sB.append("Podsumowanie wyborów wyborców:").append(System.lineSeparator());
            for (Wyborca wyborca : wyborcy) {
                sB.append(wyborca.imię()).append(" ").append(wyborca.nazwisko()).append(" ");
                sB.append(wyborca.wybranyKandydat().imię()).append(" ").append(
                        wyborca.wybranyKandydat().nazwisko()).append(System.lineSeparator());
            }

            ArrayList<Kandydat> kandydaci = okręgi.get(i).listaKandydatów();

            sB.append("Wyniki kandydatów:").append(System.lineSeparator());
            for (Kandydat kandydat : kandydaci) {
                sB.append(kandydat.imię()).append(" ").append(kandydat.nazwisko()).append(" ")
                        .append(kandydat.partia().nazwa()).append(" ").append(kandydat.numerNaLiście())
                        .append(" ").append(kandydat.liczbaGłosów()).append(System.lineSeparator());
            }

            int[] głosy = okręgi.get(i).policzGłosy(partie);
            int[] mandatyPartii = policzMandaty(głosy, partie, okręgi.get(i).liczbaWyborców()/10);

            for (int j = 0; j < mandatyPartii.length; j++) {
                mandatyWszystkichPartii[j] += mandatyPartii[j];
            }

            sB.append("Liczba mandatów dla partii w okręgu:").append(System.lineSeparator());
            for (int j = 0; j < partie.size(); j++) {
                sB.append(partie.get(j).nazwa()).append(" ").append(mandatyPartii[j]).append(System.lineSeparator());
            }
        }

        sB.append("Podsumowanie:").append(System.lineSeparator());
        for (int i = 0; i < partie.size(); i++) {
            sB.append(partie.get(i).nazwa()).append(" ").append(mandatyWszystkichPartii[i])
                    .append(System.lineSeparator());
        }
        return sB.toString();
    }
}
