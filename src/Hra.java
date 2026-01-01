import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

public class Hra {
    private Hrac hrac;
    private final Manazer manazer;
    private final ManazerStriel manazerStriel;
    private final ManazerNepriatelov manazerNepriatelov;
    private final Boss boss;
    private StavHry stavHry;

    public Hra() {
        this.manazer = new Manazer();
        this.hrac = new Hrac(500, 700);
        this.boss = new Boss(500, 200, this.hrac);
        this.manazerStriel = new ManazerStriel(this.hrac, this.boss);
        this.manazerNepriatelov = new ManazerNepriatelov(this.hrac);
        this.boss.setManazerStriel(this.manazerStriel);
        this.boss.setManazerNepriatelov(this.manazerNepriatelov);
        this.hrac.setManazerStriel(this.manazerStriel);
        this.manazer.spravujObjekt(this.hrac);
        this.manazer.spravujObjekt(this.boss);
        this.manazer.spravujObjekt(this.manazerStriel);
        this.manazer.spravujObjekt(this.manazerNepriatelov);
        this.manazer.spravujObjekt(this);
        Obrazok predelenie = new Obrazok("assets/predelenie.png", 0, 475);
        predelenie.zobraz();
        this.stavHry = StavHry.BEZI;
    }

    public void tik() {
        if (this.stavHry == StavHry.BEZI) {
            if (this.hrac.getJeMrtvy()) {
                this.gameOver(false);
            }
            if (this.boss.getJeMrtvy()) {
                this.gameOver(true);
            }
        }
    }

    public void pauza() {
        if (this.stavHry == StavHry.BEZI) {
            this.stavHry = StavHry.PAUZA;
        } else if (this.stavHry == StavHry.PAUZA) {
            this.stavHry = StavHry.BEZI;
        }
    }

    private void gameOver(boolean vyhra) {
        this.stavHry = StavHry.KONIEC;
        if (vyhra) {
            System.out.println("hrac vyhral");
        } else {
            System.out.println("hrac prehral");
        }
    }
}
