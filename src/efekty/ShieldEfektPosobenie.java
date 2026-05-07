package efekty;

import fri.shapesge.Obrazok;
import hrac.Hrac;

public class ShieldEfektPosobenie implements EfektPosobenie {

    private int trvanie;
    private Obrazok obrazokShield;
    private Hrac hrac;
    public static final int SHIELD_OFFSET = 5;

    public ShieldEfektPosobenie(Hrac hrac) {
        this.trvanie = 250;
        this.hrac = hrac;
        this.obrazokShield = new Obrazok(
            "assets/shieldHracov.png",
            this.hrac.getLavyHitbox(),
            this.hrac.getHornyHitbox()
        );
    }

    @Override
    public void priAktivacii() {
        this.obrazokShield.zobraz();
        this.hrac.setNesmrtelnost(true);
    }

    @Override
    public void tikEfektu() {
        this.obrazokShield.zmenPolohu(
            this.hrac.getLavyHitbox()  - SHIELD_OFFSET
            , this.hrac.getHornyHitbox()  - SHIELD_OFFSET);
        this.trvanie--;
    }

    @Override
    public void priVyprchani() {
        this.hrac.setNesmrtelnost(false);
        this.obrazokShield.skry();
    }

    @Override
    public int getTrvanie() {
        return this.trvanie;
    }
}
