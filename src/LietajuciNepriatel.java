import fri.shapesge.Obrazok;

import java.util.Random;

public class LietajuciNepriatel {
    private final int rychlost = 2;
    private int poziciaX;
    private int poziciaY;
    private Obrazok obrazok;
    private int ciel;
    private boolean jeNadHracom;
    private boolean jeAktivny;

    public LietajuciNepriatel(int ciel) {
        Random random = new Random();
        int nahodnaStrana = random.nextInt(2);
        this.ciel = ciel;
        this.jeAktivny = true;
        this.poziciaY = 250;
        if (nahodnaStrana == 0) {
            this.poziciaX = -90;
        } else {
            this.poziciaX = 1200;
        }
        this.obrazok = new Obrazok("assets/vrtulnikLeti.png", this.poziciaX, this.poziciaY);
        this.obrazok.zobraz();
        this.jeNadHracom = false;
    }

    public void tik() {
        if (!this.jeNadHracom) {
            this.najdiHraca();
        } else {
            this.padaj();
            this.obrazok.zmenObrazok("assets/vrtulnikPada.png");
        }
    }

    private void najdiHraca() {
        if (this.poziciaX < this.ciel) {
            this.posunVpravo();
        } else if (this.poziciaX > this.ciel) {
            this.posunVlavo();
        } else {
            this.jeNadHracom = true;
        }
    }

    private void posunVpravo() {
        this.poziciaX += this.rychlost;
        this.obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
    }

    private void posunVlavo() {
        this.poziciaX -= this.rychlost;
        this.obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
    }

    private void padaj() {
        if (this.poziciaY < 1000) {
            this.poziciaY += this.rychlost;
            this.obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
        } else {
            this.obrazok.skry();
            this.jeAktivny = false;
        }
    }


    public boolean getJeAktivny() {
        return this.jeAktivny;
    }

    public int getLavyHitbox() {
        return this.poziciaX;
    }

    public int getPravyHitbox() {
        return this.poziciaX + 90;
    }

    public int getHornyHitbox() {
        return this.poziciaY;
    }

    public int getDolnyHitbox() {
        return this.poziciaY + 90;
    }

    public void skry() {
        this.obrazok.skry();
        this.jeAktivny = false;
    }
}
