public class KlasickaStrela extends Strela {

    /**
     * Vytvori novu strelu s danym typom, poziciou a vlastnikom.
     *
     * @param poziciaX      pociatocna X suradnica strely
     * @param poziciaY      pociatocna Y suradnica strely
     * @param jeHracova     urcuje, ci strelu vystrelil hrac alebo boss
     */
    public KlasickaStrela(int poziciaX, int poziciaY, boolean jeHracova) {
        super(poziciaX, poziciaY, jeHracova, "assets/strela.png", 20);
    }

    @Override
    public void tik() {
        if (!this.jeAktivna()) {
            return;
        }

        if (this.jeHracova()) {
            this.posunY(-this.getRychlost());
        } else {
            this.posunY(this.getRychlost());
        }

        this.aktualizujPolohu();

        if (this.getPoziciaY() <= 0 || this.getPoziciaY() >= 800) {
            this.deaktivuj();
        }
    }
}
