/**
 * Trieda OblastPohybu reprezentuje obmedzenu oblast, v ktorej sa moze objekt pohybovat.
 */
public class OblastPohybu {

    private int lavyOkraj;
    private int pravyOkraj;
    private int hornyOkraj;
    private int dolnyOkraj;

    /**
     * Vytvori novu oblast pohybu so zadanymi hranicami.
     *
     * @param lavyOkraj lavy okraj oblasti
     * @param pravyOkraj pravy okraj oblasti
     * @param hornyOkraj horny okraj oblasti
     * @param dolnyOkraj dolny okraj oblasti
     */
    public OblastPohybu(int lavyOkraj, int pravyOkraj, int hornyOkraj, int dolnyOkraj) {
        this.lavyOkraj = lavyOkraj;
        this.pravyOkraj = pravyOkraj;
        this.hornyOkraj = hornyOkraj;
        this.dolnyOkraj = dolnyOkraj;
    }

    /**
     * Vrati lavy okraj oblasti pohybu.
     *
     * @return suradnica laveho okraja
     */
    public int getLavyOkraj() {
        return this.lavyOkraj;
    }

    /**
     * Vrati pravy okraj oblasti pohybu.
     *
     * @return suradnica praveho okraja
     */
    public int getPravyOkraj() {
        return this.pravyOkraj;
    }

    /**
     * Vrati horny okraj oblasti pohybu.
     *
     * @return suradnica horneho okraja
     */
    public int getHornyOkraj() {
        return this.hornyOkraj;
    }

    /**
     * Vrati dolny okraj oblasti pohybu.
     *
     * @return suradnica dolneho okraja
     */
    public int getDolnyOkraj() {
        return this.dolnyOkraj;
    }
}