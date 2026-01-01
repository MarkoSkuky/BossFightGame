import fri.shapesge.Obrazok;

public class Hrac {

    private ManazerStriel manazerStriel;
    private int polohaX;
    private int polohaY;
    private final int sirkaHraca = 90;
    private final int vyskaHraca = 90;
    private Obrazok obrazokHraca;
    private final int rychlost = 8;
    private boolean ideVlavo;
    private boolean ideVpravo;
    private boolean ideHore;
    private boolean ideDole;
    private OblastPohybu oblastPohybu;
    private HracHpBar hracHpBar;
    private int nesmrtelnostCooldown;
    private boolean jeViditelny;
    private boolean dashuje;
    private int dashTimer;
    private int dashSmer;
    private boolean jeMrtvy;


    public Hrac(int polohaX, int polohaY) {
        this.obrazokHraca = new Obrazok("assets/hracPredok.png", polohaX, polohaY);
        this.obrazokHraca.zobraz();
        this.jeViditelny = true;
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.oblastPohybu = new OblastPohybu(0, 1200, 500, 800);
        this.hracHpBar = new HracHpBar();
        this.nesmrtelnostCooldown = 0;
        this.dashuje = false;
        this.dashTimer = 0;
        this.dashSmer = 0;
        this.jeMrtvy = false;
    }

    public void posunVpravo() {
        this.ideVpravo = true;
    }

    public void posunVlavo() {
        this.ideVlavo = true;
    }

    public void uvolniVpravo() {
        this.ideVpravo = false;
    }

    public void uvolniVlavo() {
        this.ideVlavo = false;
    }

    public void posunHore() {
        this.ideHore = true;
    }

    public void posunDole() {
        this.ideDole = true;
    }

    public void uvolniHore() {
        this.ideHore = false;
    }

    public void uvolniDole() {
        this.ideDole = false;
    }

    public void aktivuj() {
        Strela strela = new Strela(this.polohaX + this.sirkaHraca / 2 - 6, this.polohaY, true);
        this.manazerStriel.pridajStrelu(strela);
    }

    public void dash() {
        if (this.dashuje) {
            return;
        }

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

            return;
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
    }

    public boolean getJeMrtvy() {
        return this.jeMrtvy;
    }

    public int getLavyHitbox() {
        return this.polohaX;
    }

    public int getPravyHitbox() {
        return this.polohaX + this.sirkaHraca;
    }

    public int getHornyHitbox() {
        return this.polohaY;
    }

    public int getDolnyHitbox() {
        return this.polohaY + this.vyskaHraca;
    }

    private void zmenPolohu() {
        this.obrazokHraca.zmenPolohu(this.polohaX, this.polohaY);
    }

    public void setManazerStriel(ManazerStriel manazerStriel) {
        this.manazerStriel = manazerStriel;
    }

}
