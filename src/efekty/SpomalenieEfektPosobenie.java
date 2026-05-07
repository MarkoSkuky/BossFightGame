package efekty;

import hrac.Hrac;

public class SpomalenieEfektPosobenie implements EfektPosobenie {

    private Hrac hrac;
    private int trvanie;

    public SpomalenieEfektPosobenie(Hrac hrac) {
        this.hrac = hrac;
        this.trvanie = 150;
    }

    @Override
    public void priAktivacii() {
        this.hrac.setRychlost(3);
    }

    @Override
    public void tikEfektu() {
        this.trvanie--;
    }

    @Override
    public void priVyprchani() {
        this.hrac.setRychlostNaDefault();
    }

    @Override
    public int getTrvanie() {
        return this.trvanie;
    }
}
