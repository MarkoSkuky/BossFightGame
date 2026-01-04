public enum FazaBossa {

    PRVA("assets/boss1.png"),
    DRUHA("assets/boss2.png"),
    TRETIA("assets/boss3.png");

    private final String obrazok;

    FazaBossa(String obrazok) {
        this.obrazok = obrazok;
    }

    public String getObrazok() {
        return this.obrazok;
    }
}