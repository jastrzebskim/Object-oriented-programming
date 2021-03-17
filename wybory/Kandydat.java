package wybory;

import wybory.partie.Partia;

import java.util.ArrayList;

public class Kandydat {
    private String imię;
    private String nazwisko;
    private Partia partia;
    private int numerNaLiście;
    private ArrayList<Integer> cechy;
    private int liczbaGłosów;

    public Kandydat(String imię, String nazwisko, Partia partia, int numerNaLiście, ArrayList<Integer> cechy) {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.partia = partia;
        this.numerNaLiście = numerNaLiście;
        this.cechy = cechy;
    }

    public String imię() {
        return imię;
    }

    public String nazwisko() {
        return nazwisko;
    }

    public int numerNaLiście() {
        return numerNaLiście;
    }

    public int liczbaCech() {
        return cechy.size();
    }

    public Partia partia() {
        return this.partia;
    }

    public void ustawNumerNaLiście(int nowyNumer) {
        numerNaLiście = nowyNumer;
    }

    public void dodajGłos() {
        liczbaGłosów++;
    }

    public int cecha(int numer) {
        return cechy.get(numer - 1);
    }

    public int liczbaGłosów() { return liczbaGłosów; }
}
