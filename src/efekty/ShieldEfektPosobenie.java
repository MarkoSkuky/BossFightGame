package efekty;

import fri.shapesge.Obrazok;
import hrac.Hrac;

/**
 * Trieda reprezentuje docasne shield posobenie na hraca.
 * Pocas trvania efektu udrziava stit nad hracom a zapina nesmrtelnost.
 */
public class ShieldEfektPosobenie implements EfektPosobenie {

    private int trvanie;
    private Obrazok obrazokShield;
    private Hrac hrac;
    private static final int SHIELD_OFFSET = 4;

    /**
     * Vytvori novu instanciu shield posobenia pre daneho hraca.
     *
     * @param hrac hrac, na ktoreho sa efekt aplikuje
     */
    public ShieldEfektPosobenie(Hrac hrac) {
        this.trvanie = 250;
        this.hrac = hrac;
        this.obrazokShield = new Obrazok(
            "assets/shieldHracov.png",
            this.hrac.getLavyHitbox(),
            this.hrac.getHornyHitbox()
        );
    }

    /**
     * Aktivuje shield efekt a zapne nesmrtelnost hraca.
     */
    @Override
    public void priAktivacii() {
        this.obrazokShield.zobraz();
        this.hrac.setNesmrtelnost(true);
    }

    /**
     * Vykona logiku efektu pri jednom hernom tiku.
     * Aktualizuje polohu obrazku stitu a odpocitava trvanie efektu.
     */
    @Override
    public void tikEfektu() {
        this.obrazokShield.zmenPolohu(
            this.hrac.getLavyHitbox() - SHIELD_OFFSET
            , this.hrac.getHornyHitbox() - SHIELD_OFFSET);
        this.obrazokShield.zobraz();
        this.trvanie--;
    }

    /**
     * Ukonci shield efekt, vypne nesmrtelnost a skryje obrazok stitu.
     */
    @Override
    public void priVyprchani() {
        this.hrac.setNesmrtelnost(false);
        this.obrazokShield.skry();
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
