import fri.shapesge.Obrazok;

import java.util.ArrayList;

public class HracHpBar {
    private int poziciaX;
    private int poziciaY;
    private ArrayList<Obrazok> srdcia;

    public HracHpBar() {
        this.poziciaX = 5;
        this.poziciaY = 505;
        this.srdcia = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.srdcia.add(new Obrazok("assets/srdce.png", this.poziciaX + (i * 45), this.poziciaY));
        }
        for (Obrazok o : this.srdcia) {
            o.zobraz();
        }
    }

    public void pridajZivot() {
        int suradnicaX = this.poziciaX + (this.srdcia.size() * 45);
        this.srdcia.add(new Obrazok("assets/srdce.png", suradnicaX, this.poziciaY));
        this.srdcia.get(this.srdcia.size() - 1).zobraz();
    }

    public void uberZivot() {
        if (!this.srdcia.isEmpty()) {
            this.srdcia.get(this.srdcia.size() - 1).skry();
            this.srdcia.remove(this.srdcia.size() - 1);
        }
    }

    public int getPocetZivotov() {
        return this.srdcia.size();
    }
}

