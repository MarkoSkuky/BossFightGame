import fri.shapesge.Obrazok;

import java.util.Random;

/**
 * Trieda reprezentuje lietajuceho nepriatela, ktory najde hraca a spadne nanho.
 */
public class LietajuciNepriatel {
    private int rychlost;
    private int poziciaX;
    private int poziciaY;
    private Obrazok obrazok;
    private Hrac hrac;
    private boolean jeNadHracom;
    private boolean jeAktivny;
    private boolean pada;

    /**
     * Vytvori noveho lietajuceho nepriatela na nahodnej strane obrazovky.
     *
     * @param hrac     referencia na hraca, ktoreho nepriatel prenasleduje
     * @param rychlost rychlost pohybu nepriatela
     */
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

    /**
     * Vykona spravanie nepriatela pri kazdom hernom tiku.
     * Nepriatel sa najskor presuva nad hraca a potom na neho pada.
     */
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

    /**
     * Zisti, ci je nepriatel stale aktivny.
     *
     * @return true ak je nepriatel aktivny, inak false
     */
    public boolean getJeAktivny() {
        return this.jeAktivny;
    }

    /**
     * Vrati lavy hitbox nepriatela.
     *
     * @return suradnica X laveho hitboxu
     */
    public int getLavyHitbox() {
        return this.poziciaX;
    }

    /**
     * Vrati pravy hitbox nepriatela.
     *
     * @return suradnica X praveho hitboxu
     */
    public int getPravyHitbox() {
        return this.poziciaX + 90;
    }

    /**
     * Vrati horny hitbox nepriatela.
     *
     * @return suradnica Y horneho hitboxu
     */
    public int getHornyHitbox() {
        return this.poziciaY;
    }

    /**
     * Vrati dolny hitbox nepriatela.
     *
     * @return suradnica Y dolneho hitboxu
     */
    public int getDolnyHitbox() {
        return this.poziciaY + 90;
    }

    /**
     * Skryje nepriatela a oznaci ho ako neaktivneho.
     */
    public void skry() {
        this.obrazok.skry();
        this.jeAktivny = false;
    }
}