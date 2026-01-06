import java.util.ArrayList;

/**
 * Trieda ManazerStriel zabezpecuje spravu hracovych a bossovych striel.
 */
public class ManazerStriel {

    private ArrayList<Strela> hracoveStrely;
    private ArrayList<Strela> bossoveStrely;
    private Boss boss;
    private Hrac hrac;

    /**
     * Vytvori manazer striel a referenciu na hraca a bossa.
     *
     * @param hrac referencia na hraca
     * @param boss referencia na bossa
     */
    public ManazerStriel(Hrac hrac, Boss boss) {
        this.hracoveStrely = new ArrayList<>();
        this.bossoveStrely = new ArrayList<>();
        this.boss = boss;
        this.hrac = hrac;
    }

    /**
     * Prida novu strelu do zoznamu podla toho, ci patri hracovi alebo bossovi.
     *
     * @param strela strela, ktora sa ma pridat
     */
    public void pridajStrelu(Strela strela) {
        if (strela.jeHracova()) {
            this.hracoveStrely.add(strela);
        } else {
            this.bossoveStrely.add(strela);
        }
    }

    /**
     * Aktualizuje vsetky strely v hre, kontroluje kolizie s hracom a bossom a odstranuje neaktivne strely.
     */
    public void tikStrely() {
        for (Strela s : this.hracoveStrely) {
            s.tik();
            this.koliziaHracovejStrely(s);
        }
        this.hracoveStrely.removeIf(strela -> !strela.jeAktivna());

        for (Strela s : this.bossoveStrely) {
            s.tik();
            this.koliziaBossovejStrely(s);
        }
        this.bossoveStrely.removeIf(strela -> !strela.jeAktivna());
    }

    private void koliziaHracovejStrely(Strela strela) {
        if (strela.jeHracova()
            && strela.getPoziciaX() > this.boss.getLavyHitbox()
            && strela.getPoziciaX() < this.boss.getPravyHitbox()
            && strela.getHornyHitbox() < this.boss.getDolnyHitbox()
            && strela.getDolnyHitbox() > this.boss.getHornyHitbox()) {
            strela.setAktivna(false);
            strela.skryObrazok();
            this.boss.uberZivoty();
        }
    }

    private void koliziaBossovejStrely(Strela strela) {
        if (!strela.jeHracova()
            && strela.getPoziciaX() > this.hrac.getLavyHitbox()
            && strela.getPoziciaX() < this.hrac.getPravyHitbox()
            && strela.getHornyHitbox() < this.hrac.getDolnyHitbox()
            && strela.getDolnyHitbox() > this.hrac.getHornyHitbox()) {
            strela.setAktivna(false);
            strela.skryObrazok();
            this.hrac.uberZivoty();
        }
    }

    /**
     * Odstrani vsetky strely z hry a nastavi manazer do pociatocneho stavu.
     */
    public void restart() {
        for (Strela s : this.hracoveStrely) {
            s.skryObrazok();
        }
        for (Strela s : this.bossoveStrely) {
            s.skryObrazok();
        }
        this.hracoveStrely.clear();
        this.bossoveStrely.clear();
    }
}