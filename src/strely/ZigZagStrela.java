package strely;

/**
 * Trieda reprezentuje zig-zag strelu, ktora sa pohybuje dolu a zaroven meni smer v osi X.
 */
public class ZigZagStrela extends Strela {

    /**
     * Vytvori novu strelu s danym typom, poziciou a vlastnikom.
     *
     * @param poziciaX  pociatocna X suradnica strely
     * @param poziciaY  pociatocna Y suradnica strely
     * @param jeHracova urcuje, ci strelu vystrelil hrac alebo boss
     */
    public ZigZagStrela(int poziciaX, int poziciaY, boolean jeHracova) {
        super(poziciaX, poziciaY, jeHracova, "assets/zigzagstrela.png", 6);
    }

    /**
     * Vykona pohyb zig-zag strely pri jednom hernom tiku.
     * Strela sa pohybuje po osi Y a po osi X sa pohybuje zlava doprava.
     */
    @Override
    public void tik() {
        if (this.jeAktivna()) {
            this.posunY(this.getRychlost());
            this.posunX(this.getRychlost() * this.getSmerPohybuX());
            this.zvysPrejdenuVzdialenostX(this.getRychlost());
            if (this.getPrejdenaVzdialenostX() >= 150) {
                this.zmenSmerPohybuX();
                this.zvysPrejdenuVzdialenostX(-this.getPrejdenaVzdialenostX());
            }
            this.aktualizujPolohu();
        }
        if (this.getPoziciaY() >= 800) {
            this.deaktivuj();
        }
    }
}
