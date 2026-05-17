package efekty;

import hrac.Hrac;

import java.util.Random;

public class ShieldEfektItem extends EfektItem {

    public ShieldEfektItem() {
        Random random = new Random();
        super(vygenerujX(), -50, "assets/shieldEfekt.png", 4);
    }

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

    @Override
    public void aplikujEfekt(Hrac hrac) {
        hrac.pridajEfektDoZoznamu(new ShieldEfektPosobenie(hrac));
    }

    private static int vygenerujX() {
        Random random = new Random();
        return random.nextInt(100, 1150);
    }
}
