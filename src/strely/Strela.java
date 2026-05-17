package strely;

import fri.shapesge.Obrazok;
import utils.Collidable;

/**
 * Abstraktna trieda Strela reprezentuje strelu v hre, ktoru moze vystrelit hrac alebo boss.
 * Zabezpecuje jej pohyb, spravanie a vykreslovanie.
 */
public abstract class Strela implements Collidable {
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
     * @param cestaKObrazku cesta k obrazku strely
     * @param rychlost rychlost pohybu strely
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

    /**
     * Aktualizuje polohu obrazka strely podla aktualnych suradnic.
     */
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
    @Override
    public int getDolnyHitbox() {
        return this.poziciaY + 20;
    }

    /**
     * Vrati horny hitbox strely.
     *
     * @return Y suradnicu horneho hitboxu strely
     */
    @Override
    public int getHornyHitbox() {
        return this.poziciaY;
    }

    /**
     * Vrati lavy hitbox strely.
     *
     * @return X suradnicu laveho hitboxu strely
     */
    @Override
    public int getLavyHitbox() {
        return this.poziciaX;
    }

    /**
     * Vrati pravy hitbox strely.
     *
     * @return X suradnicu praveho hitboxu strely
     */
    @Override
    public int getPravyHitbox() {
        return this.poziciaX + 25;
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
     * Vrati aktualnu Y suradnicu strely.
     *
     * @return aktualnu Y suradnicu strely
     */
    public int getPoziciaY() {
        return this.poziciaY;
    }

    /**
     * Deaktivuje strelu a skryje jej obrazok.
     */
    protected void deaktivuj() {
        this.aktivna = false;
        this.skryObrazok();
    }

    /**
     * Vrati rychlost pohybu strely.
     *
     * @return rychlost strely
     */
    protected int getRychlost() {
        return this.rychlost;
    }

    /**
     * Posunie strelu v smere osi Y.
     *
     * @param posun hodnota posunu po osi Y
     */
    protected void posunY(int posun) {
        this.poziciaY += posun;
    }

    /**
     * Posunie strelu v smere osi X.
     *
     * @param posun hodnota posunu po osi X
     */
    protected void posunX(int posun) {
        this.poziciaX += posun;
    }

    /**
     * Zvysi prejdenu vzdialenost strely v smere osi X.
     *
     * @param presun hodnota, o ktoru sa vzdialenost zvysi
     */
    protected void zvysPrejdenuVzdialenostX(int presun) {
        this.prejdenaVzdialenostX += presun;
    }

    /**
     * Obrati smer pohybu strely v osi X.
     */
    protected void zmenSmerPohybuX() {
        this.smerPohybuX *= -1;
    }

    /**
     * Vrati aktualny smer pohybu strely v osi X.
     *
     * @return smer pohybu v osi X
     */
    protected int getSmerPohybuX() {
        return this.smerPohybuX;
    }

    /**
     * Vrati prejdenu vzdialenost strely v osi X.
     *
     * @return prejdena vzdialenost v osi X
     */
    protected int getPrejdenaVzdialenostX() {
        return this.prejdenaVzdialenostX;
    }

    /**
     * Zvysi prejdenu vzdialenost strely v smere osi Y.
     *
     * @param presun hodnota, o ktoru sa vzdialenost zvysi
     */
    protected void zvysPrejdenuVzdialenostY(int presun) {
        this.prejdenaVzdialenostY += presun;
    }

    /**
     * Obrati smer pohybu strely v osi Y.
     */
    protected void zmenSmerPohybuY() {
        this.smerPohybuY *= -1;
    }

    /**
     * Vrati aktualny smer pohybu strely v osi Y.
     *
     * @return smer pohybu v osi Y
     */
    protected int getSmerPohybuY() {
        return this.smerPohybuY;
    }

    /**
     * Vrati prejdenu vzdialenost strely v osi Y.
     *
     * @return prejdena vzdialenost v osi Y
     */
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
