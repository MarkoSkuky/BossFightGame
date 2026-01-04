import java.util.ArrayList;

public class ManazerNepriatelov {
    private final Hrac hrac;
    private final ArrayList<LietajuciNepriatel> lietajuciNepriatelia;

    public ManazerNepriatelov(Hrac hrac) {
        this.hrac = hrac;
        this.lietajuciNepriatelia = new ArrayList<>();
    }

    public void tikNepriatelov() {
        for (LietajuciNepriatel n : this.lietajuciNepriatelia) {
            n.tik();
            this.koliziaNepriatelaSHracom(n);
        }
        this.lietajuciNepriatelia.removeIf(lietajuciNepriatel -> !lietajuciNepriatel.getJeAktivny()); //tuto metodu som si nasiel na nete
    }

    public void pridajNepriatela(int rychlostNepriatela) {
        this.lietajuciNepriatelia.add(new LietajuciNepriatel(this.hrac, rychlostNepriatela));
    }

    private void koliziaNepriatelaSHracom(LietajuciNepriatel n) {
        if (n.getJeAktivny()
            && n.getPravyHitbox() > this.hrac.getLavyHitbox()
            && n.getLavyHitbox() < this.hrac.getPravyHitbox()
            && n.getDolnyHitbox() > this.hrac.getHornyHitbox()
            && n.getHornyHitbox() < this.hrac.getDolnyHitbox()) {
            n.skry();
            this.hrac.uberZivoty();
        }
    }
    public void restart() {
        for (LietajuciNepriatel n : this.lietajuciNepriatelia) {
            n.skry();
        }

        this.lietajuciNepriatelia.clear();
    }
}
