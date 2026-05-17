package efekty;

import fri.shapesge.Obrazok;
import hrac.Hrac;

public class HubickyEfektPosobenie implements EfektPosobenie {

    private Hrac hrac;
    private int trvanie;
    private Obrazok posobenieObrazok;

    public HubickyEfektPosobenie(Hrac hrac) {
        this.hrac = hrac;
        this.trvanie = 180;
        this.posobenieObrazok = new Obrazok("assets/hubickyPosobenie.png");
        this.posobenieObrazok.zmenPolohu(-150, -50);
        this.posobenieObrazok.zobraz();
    }

    @Override
    public void priAktivacii() {
        this.hrac.setInvertovanyPohyb(true);
    }

    @Override
    public void tikEfektu() {
        this.trvanie--;
        this.posobenieObrazok.zobraz();
    }

    @Override
    public void priVyprchani() {
        this.hrac.setInvertovanyPohyb(false);
        this.posobenieObrazok.skry();
    }

    @Override
    public int getTrvanie() {
        return this.trvanie;
    }
}
