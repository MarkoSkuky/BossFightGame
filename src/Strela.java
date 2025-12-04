import fri.shapesge.Obrazok;

public class Strela {
    //13x20 rozmer
    private final int rychlost = 4;
    private int poziciaX;
    private int poziciaY;
    private Obrazok obrazok;
    private boolean aktivna;
    private boolean hracova;


    public Strela(int poziciaX, int poziciaY, boolean jeHracova) {
        this.obrazok = new Obrazok("assets/strela.png", poziciaX, poziciaY);
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.obrazok.zobraz();
        this.aktivna = true;
        this.hracova = jeHracova;
    }

    public void tik() {
        if (this.hracova) {
            this.hracovaStrela();
        } else {
            this.bossovaStrela();
        }
    }

    private void hracovaStrela() {
        if (this.aktivna) {
            this.poziciaY -= this.rychlost;
            this.aktualizujPolohu();
        }
        if (this.poziciaY <= 0) {
            this.aktivna = false;
            this.obrazok.skry();
        }
    }

    private void bossovaStrela() {
        if (this.aktivna) {
            this.poziciaY += this.rychlost;
            this.aktualizujPolohu();
        }
        if (this.poziciaY >= 800) {
            this.aktivna = false;
            this.obrazok.skry();
        }
    }

    public void skryObrazok() {
        this.obrazok.skry();
    }

    private void aktualizujPolohu() {
        this.obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
    }

    public boolean jeAktivna() {
        return this.aktivna;
    }

    public int getDolnyHitbox() {
        return this.poziciaY + 20;
    }

    public int getHornyHitbox() {
        return this.poziciaY;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }

    //vrati priblizne stred strely
    public int getPoziciaX() {
        return this.poziciaX + 6;
    }

    public boolean jeHracova() {
        return this.hracova;
    }
}
