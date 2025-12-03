import fri.shapesge.Obrazok;

public class Boss {
    private Hra hra;
    private int polohaX;
    private int polohaY;
    private final int sirkaBossa = 110;
    private final int vyskaBossa = 110;
    private Obrazok obrazokBossa;
    private int rychlost = 8;
    private Hrac hrac;
    private ManazerStriel manazerStriel;
    private BossHpBar bossHpBar;
    private int hp;
    private int smerPohybu;
    private int cooldown;

    public Boss(int polohaX, int polohaY, ManazerStriel manazerStriel, BossHpBar bossHpBar, Hra hra, Hrac hrac) {
        this.hra = hra;
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.manazerStriel = manazerStriel;
        this.bossHpBar = bossHpBar;
        this.obrazokBossa = new Obrazok("assets/boss1.png", polohaX, polohaY);
        this.obrazokBossa.zobraz();
        this.hrac = hrac;
        this.hp = 1000;
        this.smerPohybu = 1;
        this.cooldown = 0;
    }

    public void tik() {
        this.pohybBossaPrvaFaza();
        if (this.cooldown == 0 && this.vidiHraca()) {
            this.vystrel();
            this.cooldown = 4;
        }
        if (this.cooldown != 0) {
            this.cooldown--;
        }
    }

    private void pohybBossaPrvaFaza() {
        OblastPohybu oblastPohybu = new OblastPohybu(0, 1200, 0, 500);
        if (this.smerPohybu == 1) {
            this.polohaX += this.rychlost * this.smerPohybu;
            this.zmenPolohu();
            if (this.getPravyHitbox() >= oblastPohybu.getPravyOkraj()) {
                this.smerPohybu *= -1;
            }
        } else {
            this.polohaX += this.rychlost * this.smerPohybu;
            this.zmenPolohu();
            if (this.getLavyHitbox() <= oblastPohybu.getLavyOkraj()) {
                this.smerPohybu *= -1;
            }
        }
    }

    public void utok1() {

    }

    public void utok2() {

    }

    public void vystrel() {
        if (this.vidiHraca()) {
            Strela strela = new Strela(this.polohaX + 55, this.polohaY, false);
            this.manazerStriel.pridajStrelu(strela);
        }
    }

    public void uberZivoty() {

    }

    private void zmenPolohu() {
        this.obrazokBossa.zmenPolohu(this.polohaX, this.polohaY);
    }

    public boolean vidiHraca() {
        if (this.polohaX + 55 > this.hrac.getLavyHitbox() && this.polohaX + 55 < this.hrac.getPravyHitbox()) {
            return true;
        }
        return false;
    }

    public int getLavyHitbox() {
        return this.polohaX;
    }

    public int getPravyHitbox() {
        return this.polohaX + this.sirkaBossa;
    }

    public int getHornyHitbox() {
        return this.polohaY;
    }

    public int getDolnyHitbox() {
        return this.polohaY + this.vyskaBossa;
    }


}
