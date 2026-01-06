import fri.shapesge.Obrazok;

/**
 * Trieda Hrac zabezpecuje pohyb hraca, dashovanie, strelbu.
 */
public class Hrac {

    private ManazerStriel manazerStriel;
    private int polohaX;
    private int polohaY;
    private final int sirkaHraca = 90;
    private final int vyskaHraca = 90;
    private final Obrazok obrazokHraca;
    private final int rychlost = 8;
    private boolean ideVlavo;
    private boolean ideVpravo;
    private boolean ideHore;
    private boolean ideDole;
    private final OblastPohybu oblastPohybu;
    private final HracHpBar hracHpBar;
    private int nesmrtelnostCooldown;
    private int strelaCooldown;
    private boolean jeViditelny;
    private boolean dashuje;
    private int dashTimer;
    private int dashSmer;
    private boolean jeMrtvy;

    /**
     * Vytvori instanciu hraca a inicializuje pociatocne hodnoty a stavy.
     */
    public Hrac() {
        this.jeViditelny = true;
        this.polohaX = 550;
        this.polohaY = 700;
        this.obrazokHraca = new Obrazok("assets/hracPredok.png", this.polohaX, this.polohaY);
        this.obrazokHraca.zobraz();
        this.oblastPohybu = new OblastPohybu(0, 1200, 500, 800);
        this.hracHpBar = new HracHpBar();
        this.nesmrtelnostCooldown = 0;
        this.strelaCooldown = 0;
        this.dashuje = false;
        this.dashTimer = 0;
        this.dashSmer = 0;
        this.jeMrtvy = false;
        this.obrazokHraca.zmenPolohu(this.polohaX, this.polohaY);
        this.obrazokHraca.zobraz();
        this.obrazokHraca.zmenObrazok("assets/hracPredok.png");
        this.jeViditelny = true;
    }

    /**
     * Zacne pohyb hraca doprava.
     */
    public void posunVpravo() {
        this.ideVpravo = true;
    }

    /**
     * Zacne pohyb hraca dolava;
     */
    public void posunVlavo() {
        this.ideVlavo = true;
    }

    /**
     * Ukonci pohyb hraca doprava.
     */
    public void uvolniVpravo() {
        this.ideVpravo = false;
    }

    /**
     * Ukonci pohyb hraca dolava.
     */
    public void uvolniVlavo() {
        this.ideVlavo = false;
    }

    /**
     * Zacne pohyb hraca hore.
     */
    public void posunHore() {
        this.ideHore = true;
    }

    /**
     * Zacne pohyb hraca dole.
     */
    public void posunDole() {
        this.ideDole = true;
    }

    /**
     * Ukonci pohyb hraca hore.
     */
    public void uvolniHore() {
        this.ideHore = false;
    }

    /**
     * Ukonci pohyb hraca dole.
     */
    public void uvolniDole() {
        this.ideDole = false;
    }

    /**
     * Vystreli strelu ak uz nema cooldown na strielanie.
     */
    public void aktivuj() {
        if (this.strelaCooldown <= 0) {
            Strela strela = new Strela(this.polohaX + this.sirkaHraca / 2 - 6, this.polohaY, true, TypStrely.KLASICKA);
            this.manazerStriel.pridajStrelu(strela);
            this.strelaCooldown = 60;
        }
    }

    /**
     * aktivuje dash hraca do strany do ktorej sa prave pohybuje.
     */
    public void dash() {
        if (!this.dashuje) {
            if (this.ideVpravo) {
                this.dashSmer = 1;
            } else if (this.ideVlavo) {
                this.dashSmer = -1;
            } else {
                return;
            }

            this.dashuje = true;
            this.dashTimer = 5;
            this.obrazokHraca.zmenObrazok("assets/hracDash.png");
        }
    }

    /**
     * Odcita hracovi jeden zivot a aktivuje kratku nesmrtelnost.
     * Ak hrac uz nema ziadne zivoty, jeMrtvy = true.
     */
    public void uberZivoty() {
        if (this.nesmrtelnostCooldown == 0) {
            this.hracHpBar.uberZivot();
            this.nesmrtelnostCooldown = 70;
        }
        if (this.hracHpBar.getPocetZivotov() == 0) {
            this.jeMrtvy = true;
        }
    }

    private void spravHracaNesmrtelnym() {
        if (this.nesmrtelnostCooldown > 0) {
            this.nesmrtelnostCooldown--;

            if (this.nesmrtelnostCooldown % 10 == 0) {
                if (this.jeViditelny) {
                    this.obrazokHraca.skry();
                    this.jeViditelny = false;
                } else {
                    this.obrazokHraca.zobraz();
                    this.jeViditelny = true;
                }
            }
        } else {
            if (!this.jeViditelny) {
                this.obrazokHraca.zobraz();
                this.jeViditelny = true;
            }
        }
    }

    /**
     * Metoda, ktora sa vykonava pri kazdom hernom tiku.
     * Stara sa o pohyb hraca, dash, cooldowny a vykreslovanie.
     */
    public void tik() {
        this.spravHracaNesmrtelnym();

        if (this.dashuje) {
            int novyX = this.polohaX + this.dashSmer * 30;

            if (novyX >= this.oblastPohybu.getLavyOkraj()
                && novyX + this.sirkaHraca <= this.oblastPohybu.getPravyOkraj()) {
                this.polohaX = novyX;
                this.zmenPolohu();
                this.dashTimer--;
            } else {
                this.dashTimer = 0;
            }

            if (this.dashTimer <= 0) {
                this.dashuje = false;
                this.dashSmer = 0;
                this.obrazokHraca.zmenObrazok("assets/hracPredok.png");
            }
        }

        if (this.ideVlavo) {
            if (this.oblastPohybu.getLavyOkraj() < this.getLavyHitbox() - 4) {
                this.polohaX -= this.rychlost;
                this.zmenPolohu();
            }
        }
        if (this.ideVpravo) {
            if (this.oblastPohybu.getPravyOkraj() > this.getPravyHitbox() + 10) {
                this.polohaX += this.rychlost;
                this.zmenPolohu();
            }
        }
        if (this.ideHore) {
            if (this.oblastPohybu.getHornyOkraj() < this.getHornyHitbox()) {
                this.polohaY -= this.rychlost;
                this.zmenPolohu();
            }
        }
        if (this.ideDole) {
            if (this.oblastPohybu.getDolnyOkraj() > this.getDolnyHitbox()) {
                this.polohaY += this.rychlost;
                this.zmenPolohu();
            }
        }
        if (this.strelaCooldown > 0) {
            this.strelaCooldown--;
        }
    }

    /**
     * Zisti, ci je hrac mrtvy.
     *
     * @return true ak je hrac mrtvy, inak false
     */
    public boolean getJeMrtvy() {
        return this.jeMrtvy;
    }

    /**
     * Vrati lavy hitbox hraca.
     *
     * @return X-ova suradnica laveho hitboxu
     */
    public int getLavyHitbox() {
        return this.polohaX;
    }

    /**
     * Vrati pravy hitbox hraca.
     *
     * @return X-ova suradnica praveho hitboxu
     */
    public int getPravyHitbox() {
        return this.polohaX + this.sirkaHraca;
    }

    /**
     * Vrati horny hitbox hraca.
     *
     * @return Y-ova suradnica horneho hitboxu
     */
    public int getHornyHitbox() {
        return this.polohaY;
    }

    /**
     * Vrati dolny hitbox hraca.
     *
     * @return Y-ova suradnica dolneho hitboxu
     */
    public int getDolnyHitbox() {
        return this.polohaY + this.vyskaHraca;
    }

    private void zmenPolohu() {
        this.obrazokHraca.zmenPolohu(this.polohaX, this.polohaY);
    }

    /**
     * Nastavi hracovi manazera striel ktory spravuje hracove strely.
     *
     * @param manazerStriel manazer striel
     */
    public void setManazerStriel(ManazerStriel manazerStriel) {
        this.manazerStriel = manazerStriel;
    }

    /**
     * Nastavi vsetky hodnoty a stavy hraca do pociatocneho stavu
     */
    public void restart() {
        this.hracHpBar.restart();
        this.polohaX = 550;
        this.polohaY = 700;
        this.obrazokHraca.zmenPolohu(this.polohaX, this.polohaY);
        this.nesmrtelnostCooldown = 0;
        this.dashuje = false;
        this.dashTimer = 0;
        this.dashSmer = 0;
        this.jeMrtvy = false;
    }

}
