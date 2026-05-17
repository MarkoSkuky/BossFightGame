package efekty;

import hrac.Hrac;

import java.util.Random;

/**
 * Trieda reprezentuje padajuci item so shield efektom.
 * Po kolizii s hracom aktivuje docasnu nesmrtelnost.
 */
public class ShieldEfektItem extends EfektItem {

    /**
     * Vytvori novy shield efekt item na nahodnej Xovej pozicii.
     */
    public ShieldEfektItem() {
        super(vygenerujX(), -50, "assets/shieldEfekt.png", 4);
    }

    /**
     * Vykona logiku shield efekt itemu v jednom hernom tiku.
     * Stara sa o pohyb nadol a deaktivaciu po opusteni hernej plochy.
     */
    @Override
    public void tik() {
        if (!this.jeAktivny()) {
            return;
        }

        this.posunY(this.getRychlost());
        this.aktualizujPolohu();

        if (this.getHornyHitbox() >= 830) {
            this.deaktivuj();
        }
    }

    /**
     * Aplikuje shield posobenie na hraca.
     *
     * @param hrac hrac, na ktoreho sa efekt aplikuje
     */
    @Override
    public void aplikujEfekt(Hrac hrac) {
        hrac.pridajEfektDoZoznamu(new ShieldEfektPosobenie(hrac));
    }

    private static int vygenerujX() {
        Random random = new Random();
        return random.nextInt(100, 1150);
    }
}
