import fri.shapesge.Obrazok;

import java.util.Random;

public class LietajuciNepriatel {
    private int rychlost;
    private int poziciaX;
    private int poziciaY;
    private Obrazok obrazok;
    private Hrac hrac;
    private boolean jeNadHracom;
    private boolean jeAktivny;
    private boolean pada;

    public LietajuciNepriatel(Hrac hrac, int rychlost) {
        Random random = new Random();
        int nahodnaStrana = random.nextInt(2);
        this.hrac = hrac;
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
        this.rychlost = rychlost;
        this.pada = false;
    }

    public void tik() {
        if (!this.jeNadHracom) {
            this.najdiHraca();
        } else {
            this.padaj();
            if (!this.pada) {
                this.obrazok.zmenObrazok("assets/vrtulnikPada.png");
                this.pada = true;
            }
        }
    }

    private void najdiHraca() {
        int cielX = this.hrac.getLavyHitbox();

        if (this.poziciaX < cielX) {
            this.posunVpravo();
        } else if (this.poziciaX > cielX + 10) {
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
