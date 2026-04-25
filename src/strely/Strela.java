package strely;

import fri.shapesge.Obrazok;

/**
 * Trieda strely.Strela reprezentuje strelu v hre, ktoru moze vystrelit hrac alebo boss.
 * Zabezpecuje jej pohyb, spravanie a vykreslovanie.
 */
public abstract class Strela {
    private int poziciaX;
    private int poziciaY;
    private Obrazok obrazok;
    private boolean aktivna;
    private boolean hracova;
    private final int rychlost;
    private int smerPohybuX;
    private int prejdenaVzdialenostX;
    private int smerPohybuY;
    private int prejdenaVzdialenostY;

    /**
     * Vytvori novu strelu s danym typom, poziciou a vlastnikom.
     *
     * @param poziciaX  pociatocna X suradnica strely
     * @param poziciaY  pociatocna Y suradnica strely
     * @param jeHracova urcuje, ci strelu vystrelil hrac alebo boss
     */
    public Strela(int poziciaX, int poziciaY, boolean jeHracova, String cestaKObrazku, int rychlost) {
        this.rychlost = rychlost;
        this.obrazok = new Obrazok(cestaKObrazku, poziciaX, poziciaY);
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.obrazok.zobraz();
        this.aktivna = true;
        this.hracova = jeHracova;
        this.smerPohybuX = -1;
        this.prejdenaVzdialenostX = 0;
        this.smerPohybuY = 1;
        this.prejdenaVzdialenostY = 0;
    }

    /**
     * Vykona pohyb strely podla jej typu a vlastnika po kazdom hernom tiku.
     */
    public abstract void tik();

    /**
     * Skryje obrazok strely.
     */
    public void skryObrazok() {
        this.obrazok.skry();
    }

    protected void aktualizujPolohu() {
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

    public int getPoziciaY() {
        return this.poziciaY;
    }

    protected void deaktivuj() {
        this.aktivna = false;
        this.skryObrazok();
    }

    protected int getRychlost() {
        return this.rychlost;
    }

    protected void posunY(int posun) {
        this.poziciaY += posun;
    }

    protected void posunX(int posun) {
        this.poziciaX += posun;
    }

    protected void zvysPrejdenuVzdialenostX(int presun) {
        this.prejdenaVzdialenostX += presun;
    }

    protected void zmenSmerPohybuX() {
        this.smerPohybuX *= -1;
    }

    protected int getSmerPohybuX() {
        return this.smerPohybuX;
    }

    protected int getPrejdenaVzdialenostX() {
        return this.prejdenaVzdialenostX;
    }

    protected void zvysPrejdenuVzdialenostY(int presun) {
        this.prejdenaVzdialenostY += presun;
    }

    protected void zmenSmerPohybuY() {
        this.smerPohybuY *= -1;
    }

    protected int getSmerPohybuY() {
        return this.smerPohybuY;
    }

    protected int getPrejdenaVzdialenostY() {
        return this.prejdenaVzdialenostY;
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
