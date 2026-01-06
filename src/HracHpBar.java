import fri.shapesge.Obrazok;

import java.util.ArrayList;

/**
 * Tato trieda zabezpecuje vykreslovanie hracovho HP baru a spravuje aktualny pocet zivotov hraca.
 */
public class HracHpBar {
    private int poziciaX;
    private int poziciaY;
    private ArrayList<Obrazok> srdcia;

    /**
     * Vytvori novy HP bar pre hraca a inicializuje pociatocny pocet zivotov.
     */
    public HracHpBar() {
        this.poziciaX = 5;
        this.poziciaY = 505;
        this.srdcia = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.srdcia.add(new Obrazok("assets/srdce.png",
                this.poziciaX + (i * 45), this.poziciaY));
        }
        for (Obrazok o : this.srdcia) {
            o.zobraz();
        }
    }

    /**
     * Prida hracovi jeden zivot a zobrazi nove srdce.
     */
    public void pridajZivot() {
        int suradnicaX = this.poziciaX + (this.srdcia.size() * 45);
        this.srdcia.add(new Obrazok("assets/srdce.png", suradnicaX, this.poziciaY));
        this.srdcia.get(this.srdcia.size() - 1).zobraz();
    }

    /**
     * Odcita hracovi jeden zivot a skryje posledne srdce.
     */
    public void uberZivot() {
        if (!this.srdcia.isEmpty()) {
            this.srdcia.get(this.srdcia.size() - 1).skry();
            this.srdcia.remove(this.srdcia.size() - 1);
        }
    }

    /**
     * Vrati aktualny pocet zivotov hraca.
     *
     * @return aktualny pocet zivotov
     */
    public int getPocetZivotov() {
        return this.srdcia.size();
    }

    /**
     * Resetuje HP bar hraca do pociatocneho stavu
     * a obnovi pociatocny pocet zivotov.
     */
    public void restart() {
        for (Obrazok o : this.srdcia) {
            o.skry();
        }
        this.srdcia.clear();
        for (int i = 0; i < 3; i++) {
            Obrazok srdce = new Obrazok("assets/srdce.png",
                this.poziciaX + (i * 45), this.poziciaY);
            srdce.zobraz();
            this.srdcia.add(srdce);
        }
    }
}