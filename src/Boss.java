import fri.shapesge.Obrazok;

public class Boss {
    private int polohaX;
    private int polohaY;
    private final int sirkaBossa = 110;
    private final int vyskaBossa = 110;
    private Obrazok obrazokBossa;
    private int rychlost = 8;
    private Hrac hrac;
    private ManazerStriel manazerStriel;
    private ManazerNepriatelov manazerNepriatelov;
    private BossHpBar bossHpBar;
    private int smerPohybuHorizontal;
    private int smerPohybuVertical;
    private int cooldown;
    private int spawnCooldown;
    private OblastPohybu oblastPohybu;
    private FazaBossa fazaBossa;
    private boolean jeMrtvy;

    public Boss(int polohaX, int polohaY, Hrac hrac) {
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.hrac = hrac;
        this.smerPohybuHorizontal = 1;
        this.smerPohybuVertical = 1;
        this.cooldown = 60;
        this.spawnCooldown = 0;
        this.oblastPohybu = new OblastPohybu(0, 1200, 0, 500);
        this.bossHpBar = new BossHpBar();
        this.fazaBossa = FazaBossa.PRVA;
        this.obrazokBossa = new Obrazok(this.fazaBossa.getObrazok(), polohaX, polohaY);
        this.obrazokBossa.zobraz();
        this.jeMrtvy = false;
    }

    public void tik() {
        switch (this.fazaBossa) {
            case PRVA: this.pohybBossaPrvaFaza();
            break;
            case DRUHA: this.pohybBossaDruhaFaza();
            break;
            case TRETIA: this.pohybBossaTretiaFaza();
            break;
        }
    }

    private void pohybBossaPrvaFaza() {
        if (this.smerPohybuHorizontal == 1) {
            this.polohaX += this.rychlost * this.smerPohybuHorizontal;
            this.zmenPolohu();
            if (this.getPravyHitbox() >= this.oblastPohybu.getPravyOkraj()) {
                this.smerPohybuHorizontal *= -1;
            }
        } else {
            this.polohaX += this.rychlost * this.smerPohybuHorizontal;
            this.zmenPolohu();
            if (this.getLavyHitbox() <= this.oblastPohybu.getLavyOkraj()) {
                this.smerPohybuHorizontal *= -1;
            }
        }
        if (this.cooldown == 0 && this.vidiHraca()) {
            this.vystrel();
            this.cooldown = 12;
        }
        if (this.cooldown != 0) {
            this.cooldown--;
        }
    }

    private void pohybBossaDruhaFaza() {
        this.polohaX += this.rychlost * this.smerPohybuHorizontal;
        if (this.getPravyHitbox() >= this.oblastPohybu.getPravyOkraj()) {
            this.smerPohybuHorizontal = -1;
        }
        if (this.getLavyHitbox() <= this.oblastPohybu.getLavyOkraj()) {
            this.smerPohybuHorizontal = 1;
        }

        this.polohaY += this.rychlost * this.smerPohybuVertical;
        if (this.getDolnyHitbox() >= this.oblastPohybu.getDolnyOkraj() - 30) {
            this.smerPohybuVertical = -1;
        }
        if (this.getHornyHitbox() <= this.oblastPohybu.getHornyOkraj()) {
            this.smerPohybuVertical = 1;
        }

        this.zmenPolohu();

        if (this.cooldown == 0 && this.vidiHraca()) {
            this.vystrel();
            this.cooldown = 8;
        }
        if (this.cooldown > 0) {
            this.cooldown--;
        }
        this.obrazokBossa.zmenObrazok(this.fazaBossa.getObrazok());

        if (this.spawnCooldown == 0) {
            this.manazerNepriatelov.pridajNepriatela();
            this.spawnCooldown = 200;
        } else {
            this.spawnCooldown--;
        }

    }

    private void pohybBossaTretiaFaza() {

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
        this.bossHpBar.uberZivoty();
        this.aktualizujFazu();
        if (this.bossHpBar.getHp() <= 0) {
            this.jeMrtvy = true;
        }
    }

    private void zmenPolohu() {
        this.obrazokBossa.zmenPolohu(this.polohaX, this.polohaY);
    }

    public boolean vidiHraca() {
        if (this.polohaX + 15 > this.hrac.getLavyHitbox() && this.polohaX + 75 < this.hrac.getPravyHitbox()) {
            return true;
        }
        return false;
    }

    private void aktualizujFazu() {
        if (this.bossHpBar.getHp() <= 600 && this.bossHpBar.getHp() > 300 && this.fazaBossa != FazaBossa.DRUHA) {
            this.fazaBossa = FazaBossa.DRUHA;
        }
        if (this.bossHpBar.getHp() <= 300 && this.fazaBossa != FazaBossa.TRETIA) {
            this.fazaBossa = FazaBossa.TRETIA;
        }
    }

    public boolean getJeMrtvy() {
        return this.jeMrtvy;
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

    public void setManazerStriel(ManazerStriel manazerStriel) {
        this.manazerStriel = manazerStriel;
    }

    public void setManazerNepriatelov(ManazerNepriatelov manazerNepriatelov) {
        this.manazerNepriatelov = manazerNepriatelov;
    }


}
