package efekty;

import hrac.Hrac;

import java.util.Random;

/**
 * Trieda reprezentuje pohyblivy item so spomalovacim efektom.
 * Po kolizii s hracom aktivuje docasne spomalenie pohybu.
 */
public class SpomalenieEfektItem extends EfektItem {

    private int uholObrazku;
    private int smerPohybuX;


    /**
     * Vytvori novy spomalovaci efekt item s nahodnou poziciou.
     */
    public SpomalenieEfektItem() {
        super(vygenerujX(), vygenerujY(), "assets/spomalenieEfekt.png", 4);
        if (this.getPravyHitbox() < 500) {
            this.smerPohybuX = 1;
        } else {
            this.smerPohybuX = -1;
        }
        this.uholObrazku = 0;
    }

    /**
     * Vykona logiku efektu pri jednom hernom tiku.
     * Zabezpecuje horizontalny aj vertikalny pohyb, rotaciu obrazku a deaktivaciu mimo obrazovky.
     */
    @Override
    public void tik() {
        if (!this.jeAktivny()) {
            return;
        }

        this.posunX(this.getRychlost() * this.smerPohybuX);

        this.posunY(this.getSmerPohybuY());
        this.zvysPrejdenuVzdialenostY(1);

        if (this.getPrejdenaVzdialenostY() >= 34) {
            this.zmenSmerPohybuY();
            this.zvysPrejdenuVzdialenostY(-this.getPrejdenaVzdialenostY());
        }
        this.aktualizujPolohu();

        this.uholObrazku += 3;
        this.zmenUhol(this.uholObrazku);

        if (this.getLavyHitbox() >= 1400 || this.getLavyHitbox() <= -250) {
            this.deaktivuj();
        }
    }


    /**
     * Aplikuje spomalenie na hraca.
     *
     * @param hrac hrac, na ktoreho sa efekt aplikuje
     */
    @Override
    public void aplikujEfekt(Hrac hrac) {
        hrac.pridajEfektDoZoznamu(new SpomalenieEfektPosobenie(hrac));
    }

    private static int vygenerujX() {
        Random random = new Random();
        return random.nextBoolean() ? -100 : 1300;
    }

    private static int vygenerujY() {
        Random random = new Random();
        return random.nextInt(490, 720);
    }
}
