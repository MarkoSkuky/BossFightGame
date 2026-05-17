package strely;

import boss.Boss;
import hrac.Hrac;
import utils.ManazerKolizii;

import java.util.ArrayList;

/**
 * Trieda strely.ManazerStriel zabezpecuje spravu hracovych a bossovych striel.
 */
public class ManazerStriel {

    private ArrayList<Strela> hracoveStrely;
    private ArrayList<Strela> bossoveStrely;
    private Boss boss;
    private Hrac hrac;
    private ManazerKolizii manazerKolizii;

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
        this.manazerKolizii = new ManazerKolizii();
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
        this.hracoveStrely.forEach(strela -> {
            strela.tik();
            this.koliziaHracovejStrely(strela);
        });
        this.hracoveStrely.removeIf(strela -> !strela.jeAktivna());

        for (Strela s : this.bossoveStrely) {
            s.tik();
            this.koliziaBossovejStrely(s);
        }
        this.bossoveStrely.removeIf(strela -> !strela.jeAktivna());
    }

    private void koliziaHracovejStrely(Strela strela) {
        if (strela.jeHracova() && this.manazerKolizii.kolizia(strela, this.boss)) {
            strela.setAktivna(false);
            strela.skryObrazok();
            this.boss.uberZivoty();
        }
    }

    private void koliziaBossovejStrely(Strela strela) {
        if (!strela.jeHracova() && this.manazerKolizii.kolizia(strela, this.hrac)) {
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