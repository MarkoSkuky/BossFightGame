package efekty;
import hrac.Hrac;
import java.util.Random;

public class HubickyEfektItem extends EfektItem {

    private static final String[] ZOZNAM_OBRAZKOV = {
        "assets/hubicky/hubickyEfektItem1.png",
        "assets/hubicky/hubickyEfektItem2.png",
        "assets/hubicky/hubickyEfektItem3.png",
        "assets/hubicky/hubickyEfektItem4.png"
    };
    private int smerAnimacie = 1;
    private int obrazokIndex = 0;
    private int pocitadloTikov = 0;
    private static final int ANIMACIA_DELAY = 8;


    public HubickyEfektItem() {
        super(vygenerujX(), -100, "assets/hubicky/hubickyEfektItem1.png", vygenerujRychlost());
    }


    @Override
    public void tik() {
        if (!this.jeAktivny()) {
            return;
        }

        this.animaciaHubicky();
        this.posunY(this.getRychlost());
        this.aktualizujPolohu();

        if (this.getHornyHitbox() >= 830) {
            this.deaktivuj();
        }
    }

    @Override
    public void aplikujEfekt(Hrac hrac) {
        hrac.pridajEfektDoZoznamu(new HubickyEfektPosobenie(hrac));
    }

    private void animaciaHubicky() {
        this.pocitadloTikov++;

        if (this.pocitadloTikov < ANIMACIA_DELAY) {
            return;
        }

        this.pocitadloTikov = 0;
        this.obrazokIndex += this.smerAnimacie;

        if (this.obrazokIndex >= ZOZNAM_OBRAZKOV.length - 1) {
            this.obrazokIndex = ZOZNAM_OBRAZKOV.length - 1;
            this.smerAnimacie = -1;
        } else if (this.obrazokIndex <= 0) {
            this.obrazokIndex = 0;
            this.smerAnimacie = 1;
        }
        this.zmenObrazok(ZOZNAM_OBRAZKOV[this.obrazokIndex]);
    }

    private static int vygenerujX() {
        Random random = new Random();
        return random.nextInt(100, 1150);
    }

    private static int vygenerujRychlost() {
        Random random = new Random();
        return random.nextInt(4) + 1;
    }
}
