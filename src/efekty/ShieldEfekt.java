package efekty;

import hrac.Hrac;

import java.util.Random;

public class ShieldEfekt extends Efekt {

    public ShieldEfekt() {
        Random random = new Random();
        super(random.nextInt(1000) + 70, -50, "assets/shieldEfekt.png", 4);
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
        hrac.aplikujShield();
    }
}
