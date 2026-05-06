package efekty;

import hrac.Hrac;

import java.util.ArrayList;
import java.util.Random;

public class ManazerEfektov {
    private final Hrac hrac;
    private final ArrayList<Efekt> efekty;
    private final Random random;
    private int spawnCooldown;

    public ManazerEfektov(Hrac hrac) {
        this.hrac = hrac;
        this.efekty = new ArrayList<>();
        this.random = new Random();
        this.spawnCooldown = 0;
    }

    private void pridajEfekt(Efekt efekt) {
        this.efekty.add(efekt);
    }

    public void tikEfektov() {
        if (this.spawnCooldown > 0) {
            this.spawnCooldown--;
        } else if (this.random.nextInt(1000) < 3) {
//            int nahodnyEfekt = this.random.nextInt(2);
//            switch (nahodnyEfekt) {
//                case 0: this.pridajEfekt(new ShieldEfekt());
//                break;
//                case 1: this.pridajEfekt(new SpomalenieEfekt());
//                break;
//            }
            this.pridajEfekt(new ShieldEfekt());
            this.spawnCooldown = 350; //4s
        }

        if (!this.efekty.isEmpty()) {
            this.efekty.forEach((efekt) -> {
                efekt.tik();
                this.koliziaEfektuSHracom(efekt);
            });
            this.efekty.removeIf((efekt) -> !efekt.jeAktivny());
        }
    }

    private void koliziaEfektuSHracom(Efekt efekt) {
        if (efekt.jeAktivny()
            && efekt.getPravyHitbox() > this.hrac.getLavyHitbox()
            && efekt.getLavyHitbox() < this.hrac.getPravyHitbox()
            && efekt.getDolnyHitbox() > this.hrac.getHornyHitbox()
            && efekt.getHornyHitbox() < this.hrac.getDolnyHitbox()) {
            efekt.aplikujEfekt(this.hrac);
            efekt.deaktivuj();
        }
    }
}
