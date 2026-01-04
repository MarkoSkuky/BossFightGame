import fri.shapesge.Obrazok;

public class Strela {
    private int poziciaX;
    private int poziciaY;
    private Obrazok obrazok;
    private boolean aktivna;
    private boolean hracova;
    private final int rychlost;
    private TypStrely typStrely;
    private int smerX;
    private int presunX;



    public Strela(int poziciaX, int poziciaY, boolean jeHracova, TypStrely typStrely) {
        if (typStrely == TypStrely.KLASICKA) {
            this.obrazok = new Obrazok("assets/strela.png", poziciaX, poziciaY);
            this.rychlost = 20;
        } else {
            this.obrazok = new Obrazok("assets/zigzagstrela.png", poziciaX, poziciaY);
            this.rychlost = 6;
        }
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.obrazok.zobraz();
        this.aktivna = true;
        this.hracova = jeHracova;
        this.typStrely = typStrely;
        this.smerX = -1;
        this.presunX = 0;
    }

    public void tik() {
        if (this.hracova) {
            this.hracovaStrela();
        } else if (this.typStrely == TypStrely.KLASICKA) {
            this.bossovaStrela();
        } else if (this.typStrely == TypStrely.ZIGZAG) {
            this.bossovaZigZagStrela();
        }
    }

    private void hracovaStrela() {
        if (this.aktivna) {
            this.poziciaY -= this.rychlost;
            this.aktualizujPolohu();
        }
        if (this.poziciaY <= 0) {
            this.aktivna = false;
            this.obrazok.skry();
        }
    }

    private void bossovaZigZagStrela() {
        if (this.aktivna) {
            this.poziciaY += this.rychlost;
            this.poziciaX += this.smerX * this.rychlost;
            this.presunX += this.rychlost;
            if (this.presunX >= 150) {
                this.smerX *= -1;
                this.presunX = 0;
            }
            this.aktualizujPolohu();
        }
        if (this.poziciaY >= 800) {
            this.aktivna = false;
            this.obrazok.skry();
        }
    }

    private void bossovaStrela() {
        if (this.aktivna) {
            this.poziciaY += this.rychlost;
            this.aktualizujPolohu();
        }
        if (this.poziciaY >= 800) {
            this.aktivna = false;
            this.obrazok.skry();
        }
    }

    public void skryObrazok() {
        this.obrazok.skry();
    }

    private void aktualizujPolohu() {
        this.obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
    }

    public boolean jeAktivna() {
        return this.aktivna;
    }

    public int getDolnyHitbox() {
        return this.poziciaY + 20;
    }

    public int getHornyHitbox() {
        return this.poziciaY;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }

    public int getPoziciaX() {
        return this.poziciaX + 6;
    }

    public boolean jeHracova() {
        return this.hracova;
    }
}
