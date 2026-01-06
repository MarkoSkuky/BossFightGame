import java.util.ArrayList;

/**
 * Trieda ManazerNepriatelov zabezpecuje spravu a aktualizaciu lietajucich nepriatelov v hre.
 */
public class ManazerNepriatelov {

    private final Hrac hrac;
    private final ArrayList<LietajuciNepriatel> lietajuciNepriatelia;

    /**
     * Vytvori manazer nepriatelov.
     *
     * @param hrac referencia na hraca, s ktorym nepriatelia interaguju
     */
    public ManazerNepriatelov(Hrac hrac) {
        this.hrac = hrac;
        this.lietajuciNepriatelia = new ArrayList<>();
    }

    /**
     * Aktualizuje vsetkych nepriatelov v hre, kontroluje kolizie s hracom a odstranuje neaktivnych nepriatelov.
     */
    public void tikNepriatelov() {
        for (LietajuciNepriatel n : this.lietajuciNepriatelia) {
            n.tik();
            this.koliziaNepriatelaSHracom(n);
        }
        this.lietajuciNepriatelia.removeIf(
            lietajuciNepriatel -> !lietajuciNepriatel.getJeAktivny()
        );
    }

    /**
     * Prida noveho lietajuceho nepriatela do hry.
     *
     * @param rychlostNepriatela rychlost pohybu nepriatela
     */
    public void pridajNepriatela(int rychlostNepriatela) {
        this.lietajuciNepriatelia.add(
            new LietajuciNepriatel(this.hrac, rychlostNepriatela)
        );
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

    /**
     * Odstrani vsetkych nepriatelov a nastavi manazer do pociatocneho stavu.
     */
    public void restart() {
        for (LietajuciNepriatel n : this.lietajuciNepriatelia) {
            n.skry();
        }
        this.lietajuciNepriatelia.clear();
    }
}