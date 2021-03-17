package wybory;

import wybory.partie.*;
import wybory.przeliczanie.DHondta;
import wybory.przeliczanie.HareaNiemeyera;
import wybory.przeliczanie.MetodaLiczeniaGłosów;
import wybory.przeliczanie.SainteLague;
import wybory.wyborcy.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcesWyborczy {
    private ArrayList<Okręg> okręgi;
    private ArrayList<Partia> partie;
    private ArrayList<Działanie> działania;

    public ProcesWyborczy() {
        okręgi = new ArrayList<Okręg>();
        partie = new ArrayList<Partia>();
        działania = new ArrayList<Działanie>();
    }

    private void stwórzOkręgi(int liczbaOkręgów) {
        for (int i = 0; i < liczbaOkręgów; i++) {
            okręgi.add(new Okręg(i + 1));
        }
    }

    private void przyjmijPartie(int liczbaPartii, Scanner s) {
        ArrayList<String> nazwyPartii = new ArrayList<String>();
        ArrayList<Integer> budżety = new ArrayList<Integer>();
        String rodzajPartii;

        for (int i = 0; i < liczbaPartii; i++) {
            nazwyPartii.add(s.next());
        }

        for (int i = 0; i < liczbaPartii; i++) {
            budżety.add(s.nextInt());
        }

        for (int i = 0; i < liczbaPartii; i++) {
            rodzajPartii = s.next();
            if (rodzajPartii.compareTo("R") == 0) {
                partie.add(new ZRozmachem(nazwyPartii.get(i), budżety.get(i)));
            }
            else if (rodzajPartii.compareTo("S") == 0) {
                partie.add(new Skromna(nazwyPartii.get(i), budżety.get(i)));
            }
            else if (rodzajPartii.compareTo("W") == 0) {
                partie.add(new Własna(nazwyPartii.get(i), budżety.get(i)));
            }
            else if (rodzajPartii.compareTo("Z") == 0) {
                partie.add(new Zachłanna(nazwyPartii.get(i), budżety.get(i)));
            }
        }
    }

    private void połączOkręgi(int liczbaPar, Scanner s) {
        for (int i = 0; i < liczbaPar; i++) {
            String para = s.next();
            int liczba = 0;
            for (int j = 1; j < para.length(); j++) {
                if (Character.isDigit(para.charAt(j))) {
                    liczba *= 10;
                    liczba += Character.getNumericValue(para.charAt(j));
                } else break;
            }

            okręgi.get(liczba).połączZ(okręgi.get(liczba - 1));
        }
    }

    private Partia znajdźPartięONazwie(String nazwa) {
        Partia partiaKandydata = null;
        for (Partia partia : partie) {
            if (partia.porównajNazwę(nazwa)) {
                partiaKandydata = partia;
            }
        }
        return partiaKandydata;
    }

    private void zbierzKandydatów(int sumaKandydatów, int liczbaCech, Scanner s, int liczbaPartii) {
        String imię;
        String nazwisko;
        int numerOkręgu;
        String nazwaPartii;
        int pozycjaNaLiście;
        ArrayList<Integer> cechy;
        for (int i = 0; i < sumaKandydatów; i++) {
            imię = s.next();
            nazwisko = s.next();
            numerOkręgu = s.nextInt();
            nazwaPartii = s.next();
            pozycjaNaLiście = s.nextInt();
            cechy = new ArrayList<Integer>();

            for (int j = 0; j < liczbaCech; j++) {
                cechy.add(s.nextInt());
            }
            Partia partiaKandydata = znajdźPartięONazwie(nazwaPartii);
            okręgi.get(numerOkręgu - 1).dodajKandydata(new Kandydat(imię, nazwisko, partiaKandydata,
                     pozycjaNaLiście, cechy), liczbaPartii);
        }
    }

    private Wyborca zwróćŻelaznego(String imię, String nazwisko, int numerOkręgu, int typ, Scanner s) {
        String nazwaPartii = s.next();
        Partia ulubiona = znajdźPartięONazwie(nazwaPartii);

        if (typ == 2) {
            int pozycjaKandydata = s.nextInt();
            return new ŻelaznyKandydata(imię, nazwisko, okręgi.get(numerOkręgu - 1),
                    ulubiona, pozycjaKandydata);
        }

        return new ŻelaznyPartyjny(imię, nazwisko, okręgi.get(numerOkręgu - 1), ulubiona);
    }

    private Wyborca zwróćJednocechowego(String imię, String nazwisko, int numerOkręgu, int typ, Scanner s) {
        int nrCechy = s.nextInt();

        if (typ == 6 || typ == 7) {
            Partia ulubiona = znajdźPartięONazwie(s.next());

            if (typ == 6) {
                return new MinimalizującyJednocechowyPartyjny(imię, nazwisko,
                        okręgi.get(numerOkręgu - 1), nrCechy, ulubiona);
            }
            else {
                return new MaksymalizującyJednocechowyPartyjny(imię, nazwisko,
                        okręgi.get(numerOkręgu - 1), nrCechy, ulubiona);
            }
        }

        if (typ == 3) new MinimalizującyJednocechowy(imię, nazwisko,
                okręgi.get(numerOkręgu - 1), nrCechy);
        return new MaksymalizującyJednocechowy(imię, nazwisko,
                okręgi.get(numerOkręgu - 1), nrCechy);
    }

    private Wyborca zwróćWszechstronego(String imię, String nazwisko, int numerOkręgu, int typ,
                                        int liczbaCech, Scanner s) {
        ArrayList<Integer> wagi = new ArrayList<Integer>();
        for (int j = 0; j < liczbaCech; j++)
            wagi.add(s.nextInt());

        if (typ == 8) {
            Partia ulubiona = znajdźPartięONazwie(s.next());
            return new WszechstronnyPartyjny(imię,nazwisko, okręgi.get(numerOkręgu - 1), wagi, ulubiona);
        }
        return new Wszechstronny(imię, nazwisko, okręgi.get(numerOkręgu - 1), wagi);
    }

    private void zbierzWyborców(int liczbaWyborców, int liczbaCech, Scanner s, int liczbaPartii) {
        String imię;
        String nazwisko;
        int numerOkręgu;
        int typ;

        for (int i = 0; i < liczbaWyborców; i++) {
            imię = s.next();
            nazwisko = s.next();
            numerOkręgu = s.nextInt();
            typ = s.nextInt();

            Wyborca wyborca = null;

            if (typ == 1 || typ == 2) {
                wyborca = zwróćŻelaznego(imię, nazwisko, numerOkręgu, typ, s);
            }
            else if (typ == 3 || typ == 4 || typ == 6 || typ == 7) {
                wyborca = zwróćJednocechowego(imię, nazwisko, numerOkręgu, typ, s);
            }
            else {
                wyborca = zwróćWszechstronego(imię, nazwisko, numerOkręgu, typ, liczbaCech, s);
            }
            okręgi.get(numerOkręgu - 1).dodajWyborcę(wyborca, liczbaPartii);
        }
    }

    private void zbierzDane(String ścieżka) throws FileNotFoundException {
        Scanner s = new Scanner(new File(ścieżka));
        int liczbaOkręgów = s.nextInt();
        int liczbaPartii = s.nextInt();
        int liczbaDziałań = s.nextInt();
        int liczbaCech = s.nextInt();

        stwórzOkręgi(liczbaOkręgów);

        int liczbaPar = s.nextInt();
        połączOkręgi(liczbaPar, s);

        przyjmijPartie(liczbaPartii, s);

        ArrayList<Integer> liczbaWyborców = new ArrayList<Integer>();
        int sumaWyborców = 0;

        for (int i = 0; i < liczbaOkręgów; i++) {
            int liczbaWyb = s.nextInt();
            liczbaWyborców.add(liczbaWyb);
            okręgi.get(i).ustawPodstawowąLiczbęWyborców(liczbaWyb);
            sumaWyborców += liczbaWyborców.get(i);
        }

        zbierzKandydatów(sumaWyborców/10 * liczbaPartii, liczbaCech, s, liczbaPartii);

        zbierzWyborców(sumaWyborców, liczbaCech, s, liczbaPartii);
        for (int i = 0; i < liczbaDziałań; i++) {
            ArrayList<Integer> noweCechy = new ArrayList<Integer>();
            for (int j = 0; j < liczbaCech; j++) {
                noweCechy.add(s.nextInt());
            }
            działania.add(new Działanie(noweCechy));
        }
    }

    private ArrayList<Okręg> kopiujOkręgi() {
        ArrayList<Okręg> kopiaOkręgów = new ArrayList<Okręg>();

        for (Okręg okręg : okręgi) {
            kopiaOkręgów.add(okręg);
        }

        return kopiaOkręgów;
    }

    private ArrayList<Partia> kopiujPartie() {
        ArrayList<Partia> kopiaPartii = new ArrayList<Partia>();

        for (Partia partia : partie) {
            kopiaPartii.add(partia);
        }

        return kopiaPartii;
    }

    private ArrayList<Działanie> kopiujDziałania() {
        ArrayList<Działanie> kopiaDziałań = new ArrayList<Działanie>();

        for (Działanie działanie : działania) {
            kopiaDziałań.add(działanie);
        }

        return kopiaDziałań;
    }

    public void przeprowadźWybory(String ścieżkaWejścia) {
        try {
            zbierzDane(ścieżkaWejścia);
        }
        catch(FileNotFoundException e) {
            System.out.println("Plik wejściowy nie istnieje.");
            return;
        }

        boolean koniecKampanii = false;
        while (!koniecKampanii) {
            koniecKampanii = true;
            for (Partia partia : partie) {
                if (partia.przeprowadźKampanię(kopiujOkręgi(), kopiujDziałania()) == true) {
                    koniecKampanii = false;
                }
            }
        }

        for (Okręg okręg : okręgi) {
            okręg.głosuj();
        }

        MetodaLiczeniaGłosów DHon = new DHondta();
        MetodaLiczeniaGłosów SaiLa = new SainteLague();
        MetodaLiczeniaGłosów HaNie = new HareaNiemeyera();

        System.out.println(DHon.liczGłosy(kopiujOkręgi(),kopiujPartie()));
        System.out.println(SaiLa.liczGłosy(kopiujOkręgi(), kopiujPartie()));
        System.out.println(HaNie.liczGłosy(kopiujOkręgi(), kopiujPartie()));
    }
}
