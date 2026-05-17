package efekty;

import hrac.Hrac;

/**
 * Trieda reprezentuje docasne spomalovacie posobenie na hraca.
 * Pocas trvania znizuje rychlost pohybu a po vyprsani ju vracia na default hodnotu.
 */
public class SpomalenieEfektPosobenie implements EfektPosobenie {

    private Hrac hrac;
    private int trvanie;

    /**
     * Vytvori novu instanciu spomalovacieho posobenia pre daneho hraca.
     *
     * @param hrac hrac, na ktoreho sa efekt aplikuje
     */
    public SpomalenieEfektPosobenie(Hrac hrac) {
        this.hrac = hrac;
        this.trvanie = 150;
    }

    /**
     * Aktivuje efekt a nastavi hracovi nizsiu rychlost pohybu.
     */
    @Override
    public void priAktivacii() {
        this.hrac.setRychlost(3);
    }

    /**
     * Vykona logiku efektu pri jednom hernom tiku.
     * Odpocitava zostavajuce trvanie efektu.
     */
    @Override
    public void tikEfektu() {
        this.trvanie--;
    }

    /**
     * Ukonci efekt a vrati hracovi predvolenu rychlost pohybu.
     */
    @Override
    public void priVyprchani() {
        this.hrac.setRychlostNaDefault();
    }

    /**
     * Vrati zostavajuce trvanie efektu v tikoch.
     *
     * @return zostavajuci pocet tikov efektu
     */
    @Override
    public int getTrvanie() {
        return this.trvanie;
    }
}
