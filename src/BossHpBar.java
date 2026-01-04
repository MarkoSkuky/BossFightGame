import fri.shapesge.Obdlznik;

public class BossHpBar {

    private final Obdlznik hpBar;
    private int hp;

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

    public void uberZivoty() {
        this.hp -= 30;
        if (this.hp <= 0) {
            this.hp = 0;
        }
        this.hpBar.zmenStrany(this.hp, 15);
    }

    public int getHp() {
        return this.hp;
    }

    public void restart() {
        this.hp = 800;
        this.hpBar.zmenStrany(this.hp, 15);
    }
}