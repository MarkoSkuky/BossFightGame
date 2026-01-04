import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

public class Hra {
    private final Hrac hrac;
    private final Manazer manazer;
    private final ManazerStriel manazerStriel;
    private final ManazerNepriatelov manazerNepriatelov;
    private final Boss boss;
    private StavHry stavHry;
    private final Obrazok start;
    private final Obrazok unpause;
    private final Obrazok quit;
    private final Obrazok restart;
    private final Obrazok ciernePozadie;
    private final Obrazok vyhra;
    private final Obrazok prehra;

    public Hra() {
        this.manazer = new Manazer();
        this.hrac = new Hrac();
        this.boss = new Boss(this.hrac);
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
        this.start = new Obrazok("assets/start.png", 450, 289);
        this.unpause = new Obrazok("assets/unpause.png", 450, 307);
        this.restart = new Obrazok("assets/restart.png", 450, 414);
        this.quit = new Obrazok("assets/quit.png", 450, 521);
        this.vyhra = new Obrazok("assets/vyhra.png", 400, 270);
        this.prehra = new Obrazok("assets/prehra.png", 400, 270);
        predelenie.zobraz();

        this.stavHry = StavHry.MENU;
        this.ciernePozadie = new Obrazok("assets/ciernePozadie.png", 0, 0);
        this.zobrazMenu();
    }

    public void gameTik() {
        if (this.stavHry == StavHry.BEZI) {
            this.hrac.tik();
            this.boss.tik();
            this.manazerStriel.tikStrely();
            this.manazerNepriatelov.tikNepriatelov();
            if (this.hrac.getJeMrtvy()) {
                this.gameOver(false);
            } else if (this.boss.getJeMrtvy()) {
                this.gameOver(true);
            }
        }
    }

    public void vyberSuradnice(int x, int y) {
        if (this.stavHry == StavHry.MENU) {
            if (x > 450 && x < 750 && y > 289 && y < 387) {
                this.skryMenu();
                this.stavHry = StavHry.BEZI;
            }
            return;
        }

        if (this.stavHry == StavHry.PAUZA) {
            if (x > 450 && x < 750 && y > 307 && y < 394) {
                this.pauza();
            }
            if (x > 450 && x < 750 && y > 414 && y < 501) {
                this.restartGame();
            }
            if (x > 450 && x < 750 && y > 521 && y < 612) {
                System.exit(0);
            }
        }

        if (this.stavHry == StavHry.KONIEC) {
            if (x > 450 && x < 750 && y > 414 && y < 501) {
                this.restartGame();
                this.prehra.skry();
                this.vyhra.skry();
            }
            if (x > 450 && x < 750 && y > 521 && y < 612) {
                System.exit(0);
            }
        }
    }

    public void pauza() {

        if (this.stavHry == StavHry.BEZI) {
            this.stavHry = StavHry.PAUZA;
            this.ciernePozadie.zobraz();
            this.zobrazUI();
        } else if (this.stavHry == StavHry.PAUZA) {
            this.stavHry = StavHry.BEZI;
            this.ciernePozadie.skry();
            this.skryUI();
        }
    }

    private void skryUI() {
        this.quit.skry();
        this.restart.skry();
        this.unpause.skry();
        this.start.skry();
    }

    private void zobrazUI() {
        this.quit.zobraz();
        this.restart.zobraz();
        this.unpause.zobraz();
    }

    private void gameOver(boolean vyhra) {
        this.stavHry = StavHry.KONIEC;
        if (vyhra) {
            System.out.println("hrac vyhral");
            this.vyhra.zobraz();
        } else {
            System.out.println("hrac prehral");
            this.prehra.zobraz();
        }
        this.quit.zobraz();
        this.restart.zobraz();
    }

    private void zobrazMenu() {
        this.ciernePozadie.zobraz();
        this.start.zobraz();
    }

    private void skryMenu() {
        this.start.skry();
        this.ciernePozadie.skry();
    }

    private void restartGame() {
        this.hrac.restart();
        this.boss.restart();
        this.manazerStriel.restart();
        this.manazerNepriatelov.restart();
        this.skryUI();
        this.ciernePozadie.skry();
        this.stavHry = StavHry.BEZI;
    }
}

