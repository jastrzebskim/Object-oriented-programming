package wybory.partie;

public class Skromna extends StrategiaKosztowa {

    public Skromna(String nazwa, int pieniądze) {super(nazwa, pieniądze);}

    @Override
    protected int koszt() {
        return 2000000000;
    }

    @Override
    protected boolean warunek(int kosztAktualny, int koszt) {
        return kosztAktualny < koszt;
    }
}
