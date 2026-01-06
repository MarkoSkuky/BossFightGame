import fri.shapesge.Obrazok;
/**
 * Trieda boss zabezpecuje spravanie a vykreslovanie bossa
 */
public class Boss {
    private int polohaX;
    private int polohaY;
    private final int sirkaBossa = 110;
    private final int vyskaBossa = 110;
    private Obrazok obrazokBossa;
    private int rychlost = 8;
    private final Hrac hrac;
    private ManazerStriel manazerStriel;
    private ManazerNepriatelov manazerNepriatelov;
    private final BossHpBar bossHpBar;
    private int smerPohybuHorizontal;
    private int smerPohybuVertical;
    private int cooldown;
    private int spawnCooldown;
    private final OblastPohybu oblastPohybu;
    private FazaBossa fazaBossa;
    private boolean jeMrtvy;

    /**
     * Vytvori novy objekt bossa a inicializuje jeho pociatocnehodnoty a stavy.
     *
     * @param hrac referencia na hraca s ktorym boss bude interagovat
     */
    public Boss(Hrac hrac) {
        this.polohaX = 545;
        this.polohaY = 200;
        this.hrac = hrac;
        this.smerPohybuHorizontal = 1;
        this.smerPohybuVertical = 1;
        this.cooldown = 60;
        this.spawnCooldown = 0;
        this.oblastPohybu = new OblastPohybu(0, 1200, 0, 500);
        this.bossHpBar = new BossHpBar();
        this.fazaBossa = FazaBossa.PRVA;
        this.obrazokBossa = new Obrazok(this.fazaBossa.getObrazok(), this.polohaX, this.polohaY);
        this.obrazokBossa.zobraz();
        this.jeMrtvy = false;
    }

    /**
     * Vykona urcite kroky bossa po kazdom hernom tiku, na aktualnej fazy bossa
     */
    public void tik() {
        switch (this.fazaBossa) {
            case PRVA:
                this.pohybBossaPrvaFaza();
                break;
            case DRUHA:
                this.pohybBossaDruhaFaza();
                break;
            case TRETIA:
                this.pohybBossaTretiaFaza();
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

        if (this.spawnCooldown == 0) {
            this.manazerNepriatelov.pridajNepriatela(4);
            this.spawnCooldown = 200;
        } else {
            this.spawnCooldown--;
        }
    }

    private void pohybBossaTretiaFaza() {
        this.pohybBossaPrvaFaza();
        if (this.spawnCooldown == 0) {
            this.manazerNepriatelov.pridajNepriatela(6);
            this.spawnCooldown = 80;
        } else {
            this.spawnCooldown--;
        }
    }

    private void vystrel() {
        if (this.vidiHraca() && this.fazaBossa != FazaBossa.TRETIA) {
            Strela strela = new Strela(this.polohaX + 55, this.polohaY, false, TypStrely.KLASICKA);
            this.manazerStriel.pridajStrelu(strela);
        } else if (this.vidiHraca() && this.fazaBossa == FazaBossa.TRETIA) {
            Strela strela = new Strela(this.polohaX + 55, this.polohaY, false, TypStrely.ZIGZAG);
            this.manazerStriel.pridajStrelu(strela);
        }
    }

    /**
     * Odcita bossovi nejaky pocet zivotov a kontroluje ci zivoty nema na nule, ak ano, jeMrtvy = true
     */
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

    private boolean vidiHraca() {
        return this.polohaX + 15 > this.hrac.getLavyHitbox() && this.polohaX + 75 < this.hrac.getPravyHitbox();
    }

    private void aktualizujFazu() {
        if (this.bossHpBar.getHp() <= 600 && this.bossHpBar.getHp() > 300 && this.fazaBossa != FazaBossa.DRUHA) {
            this.fazaBossa = FazaBossa.DRUHA;
            this.obrazokBossa.zmenObrazok(this.fazaBossa.getObrazok());
        }
        if (this.bossHpBar.getHp() <= 300 && this.fazaBossa != FazaBossa.TRETIA) {
            this.fazaBossa = FazaBossa.TRETIA;
            this.obrazokBossa.zmenObrazok(this.fazaBossa.getObrazok());
            this.rychlost = 10;
            this.polohaY = 80;
        }
    }

    /**
     * Zisti ci je boss mrtvy
     *
     * @return true ak je boss mrtvu, inak false
     */
    public boolean getJeMrtvy() {
        return this.jeMrtvy;
    }

    /**
     * Vrati lavy hitbox bossa
     *
     * @return aktualnu suradnicu X laveho hitboxu
     */
    public int getLavyHitbox() {
        return this.polohaX;
    }

    /**
     * Vrati pravy hitbox bossa
     *
     * @return aktualnu suradnicu X praveho hitboxu
     */
    public int getPravyHitbox() {
        return this.polohaX + this.sirkaBossa;
    }

    /**
     * Vrati horny hitbox bossa
     *
     * @return aktualnu suradnicu Y horneho hitboxu
     */
    public int getHornyHitbox() {
        return this.polohaY;
    }

    /**
     * Vrati dolny hitbox bossa
     *
     * @return aktualnu suradnicu Y dolneho hitboxu
     */
    public int getDolnyHitbox() {
        return this.polohaY + this.vyskaBossa;
    }

    /**
     * Nastavi manazer striel, ktory bude spravovat strely bossa.
     *
     * @param manazerStriel manazer striel
     */
    public void setManazerStriel(ManazerStriel manazerStriel) {
        this.manazerStriel = manazerStriel;
    }

    /**
     * Nastavi manazer nepriatelov, ktory bude spravovat nepriatelov vytvorenych bossom.
     *
     * @param manazerNepriatelov manazer nepriatelov
     */
    public void setManazerNepriatelov(ManazerNepriatelov manazerNepriatelov) {
        this.manazerNepriatelov = manazerNepriatelov;
    }

    /**
     * Nastavi vsetky hodnoty a stavy bossa do pociatocneho stavu
     */
    public void restart() {
        this.bossHpBar.restart();
        this.rychlost = 8;
        this.polohaX = 545;
        this.polohaY = 200;
        this.smerPohybuHorizontal = 1;
        this.smerPohybuVertical = 1;
        this.cooldown = 60;
        this.spawnCooldown = 0;
        this.fazaBossa = FazaBossa.PRVA;
        this.jeMrtvy = false;
        this.obrazokBossa.zmenPolohu(this.polohaX, this.polohaY);
        this.obrazokBossa.zmenObrazok("assets/boss1.png");
    }
}
