import fri.shapesge.Obdlznik;

/**
 * Tato trieda zabezpecuje vykreslovanie bossovho HP baru a spravuje aktualny stav zivotov
 */
public class BossHpBar {

    private final Obdlznik hpBar;
    private int hp;

    /**
     * Vytvori novy HP bar a inicializuje pociatocne hodnoty zivotov.
     */
    public BossHpBar() {
        this.hp = 800;
        Obdlznik obrys = new Obdlznik(190, 10);
        obrys.zmenStrany(820, 35);
        obrys.zmenFarbu("white");

        Obdlznik vnutro = new Obdlznik(200, 20);
        vnutro.zmenStrany(800, 15);
        vnutro.zmenFarbu("black");

        this.hpBar = new Obdlznik(200, 20);
        this.hpBar.zmenStrany(this.hp, 15);
        this.hpBar.zmenFarbu("red");

        obrys.zobraz();
        vnutro.zobraz();
        this.hpBar.zobraz();
    }

    /**
     * Odcita bossovi pevne stanovany pocet zivotov
     * a aktualizuje sirku HP baru.
     */
    public void uberZivoty() {
        this.hp -= 30;
        if (this.hp <= 0) {
            this.hp = 0;
        }
        this.hpBar.zmenStrany(this.hp, 15);
    }

    /**
     * Vrati aktualny pocet zivotov bossa.
     *
     * @return aktualna hodnota zivotov bossa
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Nastavi zivoty bossa na pociatocnu hodnotu
     * a aktualizuje zobrazenie HP baru.
     */
    public void restart() {
        this.hp = 800;
        this.hpBar.zmenStrany(this.hp, 15);
    }
}