package efekty;

import fri.shapesge.Obrazok;
import hrac.Hrac;

/**
 * Trieda reprezentuje docasne posobenie hubickoveho efektu na hraca.
 * Počas trvania invertuje ovladanie hraca a vykresli sa obrazok halucinacii.
 */
public class HubickyEfektPosobenie implements EfektPosobenie {

    private Hrac hrac;
    private int trvanie;
    private Obrazok posobenieObrazok;

    /**
     * Vytvori novu instanciu posobenia hubickoveho efektu pre daneho hraca.
     *
     * @param hrac hrac, na ktoreho sa bude efekt aplikovat
     */
    public HubickyEfektPosobenie(Hrac hrac) {
        this.hrac = hrac;
        this.trvanie = 180;
        this.posobenieObrazok = new Obrazok("assets/hubickyPosobenie.png");
        this.posobenieObrazok.zmenPolohu(-150, -50);
        this.posobenieObrazok.zobraz();
    }

    /**
     * Aktivuje efekt a zapne invertovane ovladanie hraca.
     */
    @Override
    public void priAktivacii() {
        this.hrac.setInvertovanyPohyb(true);
    }

    /**
     * Odpocitava trvanie a zobrazuje obrazok halucinacii po okrajoch.
     */
    @Override
    public void tikEfektu() {
        this.trvanie--;
        this.posobenieObrazok.zobraz();
    }

    /**
     * Ukonci posobenie efektu, vypne invertovane ovladanie a skryje obrazok.
     */
    @Override
    public void priVyprchani() {
        this.hrac.setInvertovanyPohyb(false);
        this.posobenieObrazok.skry();
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
