package efekty;

import fri.shapesge.Obrazok;
import hrac.Hrac;

public abstract class EfektItem {
    private int poziciaX;
    private int poziciaY;
    private Obrazok obrazok;
    private final int rychlost;
    private int smerPohybuX;
    private int prejdenaVzdialenostX;
    private int smerPohybuY;
    private int prejdenaVzdialenostY;
    private boolean aktivny;

    public EfektItem(int poziciaX, int poziciaY, String cestaKObrazku, int rychlost) {
        this.rychlost = rychlost;
        this.obrazok = new Obrazok(cestaKObrazku, poziciaX, poziciaY);
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.obrazok.zobraz();
        this.aktivny = true;
        this.smerPohybuX = 1;
        this.prejdenaVzdialenostX = 0;
        this.smerPohybuY = 1;
        this.prejdenaVzdialenostY = 0;
    }

    public abstract void tik();

    public abstract void aplikujEfekt(Hrac hrac);

    /**
     * Vrati dolny hitbox efektu.
     *
     * @return Y suradnicu dolneho hitboxu efektu
     */
    public int getDolnyHitbox() {
        return this.poziciaY + 50;
    }

    /**
     * Vrati horny hitbox efektu.
     *
     * @return Y suradnicu horneho hitboxu efektu
     */
    public int getHornyHitbox() {
        return this.poziciaY;
    }

    /**
     * Vrati lavy hitbox efektu.
     *
     * @return X suradnicu laveho hitboxu
     */
    public int getLavyHitbox() {
        return this.poziciaX;
    }

    /**
     * Vrati pravy hitbox efektu.
     *
     * @return X suradnicu praveho hitboxu s offsetom
     */
    public int getPravyHitbox() {
        return this.poziciaX + 50;
    }

    /**
     * Skryje obrazok efektu.
     */
    public void skryObrazok() {
        this.obrazok.skry();
    }

    /**
     * Zisti, ci je efekt aktivny.
     *
     * @return true ak je efekt aktivny, inak false
     */
    public boolean jeAktivny() {
        return this.aktivny;
    }

    /**
     * Nastavi stav efektu ci je aktivny alebo neaktivny.
     *
     * @param aktivny nova hodnota aktivity efektu
     */
    public void setAktivny(boolean aktivny) {
        this.aktivny = aktivny;
    }

    /**
     * Deaktivuje efekt a skryje jeho obrazok.
     */
    protected void deaktivuj() {
        this.aktivny = false;
        this.skryObrazok();
    }

    /**
     * Vrati rychlost efektu.
     *
     * @return rychlost pohybu efektu
     */
    protected int getRychlost() {
        return this.rychlost;
    }

    /**
     * Posunie efekt v smere Y osi.
     *
     * @param posun pocet pixelov na posun
     */
    protected void posunY(int posun) {
        this.poziciaY += posun;
    }

    /**
     * Posunie efekt v smere X osi.
     *
     * @param posun pocet pixelov na posun
     */
    protected void posunX(int posun) {
        this.poziciaX += posun;
    }

    /**
     * Zvysi prejdenu vzdialenost v smere X osi.
     *
     * @param presun pocet pixelov na pripocitanie
     */
    protected void zvysPrejdenuVzdialenostX(int presun) {
        this.prejdenaVzdialenostX += presun;
    }

    /**
     * Zmeni smer pohybu v smere X osi.
     */
    protected void zmenSmerPohybuX() {
        this.smerPohybuX *= -1;
    }

    /**
     * Vrati smer pohybu v smere X osi.
     *
     * @return smer pohybu X (-1 alebo 1)
     */
    protected int getSmerPohybuX() {
        return this.smerPohybuX;
    }

    /**
     * Vrati prejdenu vzdialenost v smere X osi.
     *
     * @return prejdena vzdialenost X
     */
    protected int getPrejdenaVzdialenostX() {
        return this.prejdenaVzdialenostX;
    }

    /**
     * Zvysi prejdenu vzdialenost v smere Y osi.
     *
     * @param presun pocet pixelov na pripocitanie
     */
    protected void zvysPrejdenuVzdialenostY(int presun) {
        this.prejdenaVzdialenostY += presun;
    }

    /**
     * Zmeni smer pohybu v smere Y osi.
     */
    protected void zmenSmerPohybuY() {
        this.smerPohybuY *= -1;
    }

    /**
     * Vrati smer pohybu v smere Y osi.
     *
     * @return smer pohybu Y (-1 alebo 1)
     */
    protected int getSmerPohybuY() {
        return this.smerPohybuY;
    }

    /**
     * Vrati prejdenu vzdialenost v smere Y osi.
     *
     * @return prejdena vzdialenost Y
     */
    protected int getPrejdenaVzdialenostY() {
        return this.prejdenaVzdialenostY;
    }

    protected void aktualizujPolohu() {
        this.obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
    }

    protected void zmenUhol(int stupne) {
        this.obrazok.zmenUhol(stupne);
    }
}

