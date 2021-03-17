package wybory.wyborcy;

import wybory.Działanie;
import wybory.Kandydat;
import wybory.Okręg;

abstract public class Wyborca {
    protected String imię;
    protected String nazwisko;
    protected Okręg okręg;
    protected Kandydat wybranyKandydat;

    public Wyborca(String imię, String nazwisko, Okręg okręg) {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.okręg = okręg;
        wybranyKandydat = null;
    }

    public String imię() {
        return imię;
    }

    public String nazwisko() {
        return nazwisko;
    }

    public Kandydat wybranyKandydat() {
        return wybranyKandydat;
    }

    public void zmieńWagi(Działanie działanie) {}

    public int waga(int numerWagi) {return 0;}

    public void zmieńAtrybuty(Okręg okręg, int liczbaPartii) {
        this.okręg = okręg;
    }

    abstract public Kandydat wybierzKandydata();

    public void zagłosuj() {
        Kandydat wybraniec = wybierzKandydata();
        wybraniec.dodajGłos();
        wybranyKandydat = wybraniec;
    }
}
