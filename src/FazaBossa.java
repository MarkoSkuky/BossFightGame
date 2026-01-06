/**
 * Tato trieda reprezentuje aktualnu fazu bossa, kazda faza ma priradeny aj obrazok bossa.
 */
public enum FazaBossa {

    /**
     * Prva faza.
     */
    PRVA("assets/boss1.png"),

    /**
     * Druha faza.
     */
    DRUHA("assets/boss2.png"),

    /**
     * Tretia faza.
     */
    TRETIA("assets/boss3.png");

    private final String obrazok;

    /**
     * Vytvori novu fazu bossa s priradenym obrazkom.
     *
     * @param obrazok cesta k obrazku fazy bossa
     */
    FazaBossa(String obrazok) {
        this.obrazok = obrazok;
    }

    /**
     * Vrati cestu k obrazku bossa aktualnej fazy.
     *
     * @return cesta k obrazku bossa
     */
    public String getObrazok() {
        return this.obrazok;
    }
}