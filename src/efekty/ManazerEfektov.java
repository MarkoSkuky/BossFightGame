package efekty;

import hrac.Hrac;

import java.util.ArrayList;
import java.util.Random;

public class ManazerEfektov {
    private final Hrac hrac;
    private final ArrayList<EfektItem> efekty;
    private final Random random;
    private int spawnCooldown;

    public ManazerEfektov(Hrac hrac) {
        this.hrac = hrac;
        this.efekty = new ArrayList<>();
        this.random = new Random();
        this.spawnCooldown = 0;
    }

    private void pridajEfekt(EfektItem efektItem) {
        this.efekty.add(efektItem);
    }

    public void tikEfektov() {
        if (this.spawnCooldown > 0) {
            this.spawnCooldown--;
        } else if (this.random.nextInt(1000) < 30) {
            int nahodnyEfekt = this.random.nextInt(100);
            if (nahodnyEfekt < 50) {
                this.pridajEfekt(new ShieldEfektItem());
            } else if (nahodnyEfekt < 80) {
                this.pridajEfekt(new SpomalenieEfektItem());
            } else {
                this.pridajEfekt(new HubickyEfektItem());
                this.pridajEfekt(new HubickyEfektItem());
            }
            this.spawnCooldown = 200;
        }

        if (!this.efekty.isEmpty()) {
            this.efekty.forEach((efektItem) -> {
                efektItem.tik();
                this.koliziaEfektuSHracom(efektItem);
            });
            this.efekty.removeIf((efektItem) -> !efektItem.jeAktivny());
        }
    }


    private void koliziaEfektuSHracom(EfektItem efektItem) {
        if (efektItem.jeAktivny()
            && efektItem.getPravyHitbox() > this.hrac.getLavyHitbox()
            && efektItem.getLavyHitbox() < this.hrac.getPravyHitbox()
            && efektItem.getDolnyHitbox() > this.hrac.getHornyHitbox()
            && efektItem.getHornyHitbox() < this.hrac.getDolnyHitbox()) {
            efektItem.aplikujEfekt(this.hrac);
            efektItem.deaktivuj();
        }
    }

    public void restart() {
        for (EfektItem efektItem : this.efekty) {
            efektItem.skryObrazok();
            efektItem.setAktivny(false);
        }
        this.efekty.clear();
        this.spawnCooldown = 0;
    }
}
