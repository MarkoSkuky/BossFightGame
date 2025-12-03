import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import fri.shapesge.engine.Game;

public class Hra {
    private Obrazok predelenie;
    private Hrac hrac;
    private Manazer manazer;
    private ManazerStriel manazerStriel;
    private Boss boss;
    private BossHpBar bossHpBar;

    public Hra() {
        this.manazer = new Manazer();
        this.manazerStriel = new ManazerStriel(this.hrac, this.boss);
        Game.getGame();
        this.hrac = new Hrac(500, 700, this.manazerStriel, this);
        this.boss = new Boss(500, 200, this.manazerStriel, this.bossHpBar, this, this.hrac);
        this.manazer.spravujObjekt(this.hrac);
        this.manazer.spravujObjekt(this.boss);
        this.manazer.spravujObjekt(this.manazerStriel);
        this.predelenie = new Obrazok("assets/predelenie.png", 0, 475);
        this.predelenie.zobraz();
    }
}
