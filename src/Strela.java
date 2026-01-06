import fri.shapesge.Obrazok;

/**
 * Trieda Strela reprezentuje strelu v hre, ktoru moze vystrelit hrac alebo boss.
 * Zabezpecuje jej pohyb, spravanie a vykreslovanie.
 */
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

    /**
     * Vytvori novu strelu s danym typom, poziciou a vlastnikom.
     *
     * @param poziciaX  pociatocna X suradnica strely
     * @param poziciaY  pociatocna Y suradnica strely
     * @param jeHracova urcuje, ci strelu vystrelil hrac alebo boss
     * @param typStrely typ strely (KLASICKA alebo ZIGZAG)
     */
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

    /**
     * Vykona pohyb strely podla jej typu a vlastnika po kazdom hernom tiku.
     */
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

    /**
     * Skryje obrazok strely.
     */
    public void skryObrazok() {
        this.obrazok.skry();
    }

    private void aktualizujPolohu() {
        this.obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
    }

    /**
     * Zisti, ci je strela aktivna.
     *
     * @return true ak je strela aktivna, inak false
     */
    public boolean jeAktivna() {
        return this.aktivna;
    }

    /**
     * Vrati dolny hitbox strely.
     *
     * @return Y suradnicu dolneho hitboxu strely
     */
    public int getDolnyHitbox() {
        return this.poziciaY + 20;
    }

    /**
     * Vrati horny hitbox strely.
     *
     * @return Y suradnicu horneho hitboxu strely
     */
    public int getHornyHitbox() {
        return this.poziciaY;
    }

    /**
     * Meni stav strely ci je na aktivnu alebo neaktivnu.
     *
     * @param aktivna nova hodnota aktivity strely
     */
    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }

    /**
     * Vrati X suradnicu strely upravenu pre potreby hitboxu.
     *
     * @return aktualnu X suradnicu strely
     */
    public int getPoziciaX() {
        return this.poziciaX + 6;
    }

    /**
     * Zisti, ci strelu vystrelil hrac.
     *
     * @return true ak je strela hracova, inak false
     */
    public boolean jeHracova() {
        return this.hracova;
    }
}
