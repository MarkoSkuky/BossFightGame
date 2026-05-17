package efekty;

import hrac.Hrac;
import utils.ManazerKolizii;

import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda spravuje spawn, aktualizaciu a kolizie efektovych itemov v hre.
 * Zabezpecuje aj ich odstranenie a reset pri restarte hry.
 */
public class ManazerEfektov {
    private final Hrac hrac;
    private final ArrayList<EfektItem> efekty;
    private final Random random;
    private int spawnCooldown;
    private ManazerKolizii manazerKolizii;

    /**
     * Vytvori novy manazer efektov pre daneho hraca.
     *
     * @param hrac referencia na hraca
     */
    public ManazerEfektov(Hrac hrac) {
        this.hrac = hrac;
        this.efekty = new ArrayList<>();
        this.random = new Random();
        this.spawnCooldown = 0;
        this.manazerKolizii = new ManazerKolizii();
    }

    private void pridajEfekt(EfektItem efektItem) {
        this.efekty.add(efektItem);
    }

    /**
     * Vykona logiku manazera efektov pri jednom hernom tiku.
     * Stara sa o spawn novych efektov, ich pohyb, kolizie s hracom a vycistenie neaktivnych efektov.
     */
    public void tikEfektov() {
        if (this.spawnCooldown > 0) {
            this.spawnCooldown--;
        } else if (this.random.nextInt(1000) < 100) {
            int nahodnyEfekt = this.random.nextInt(100);
            if (nahodnyEfekt < 50) {
                this.pridajEfekt(new ShieldEfektItem());
            } else if (nahodnyEfekt < 80) {
                this.pridajEfekt(new SpomalenieEfektItem());
            } else {
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
        if (efektItem.jeAktivny() && this.manazerKolizii.kolizia(efektItem, this.hrac)) {
            efektItem.aplikujEfekt(this.hrac);
            efektItem.deaktivuj();
        }
    }

    /**
     * Resetuje manazer efektov do pociatocneho stavu.
     * Skryje vsetky aktivne efekty, vycisti ich zoznam a vynuluje spawn cooldown.
     */
    public void restart() {
        for (EfektItem efektItem : this.efekty) {
            efektItem.skryObrazok();
            efektItem.setAktivny(false);
        }
        this.efekty.clear();
        this.spawnCooldown = 0;
    }
}
