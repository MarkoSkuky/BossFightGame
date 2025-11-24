import fri.shapesge.Manazer;
import fri.shapesge.engine.Game;
import java.util.ArrayList;

public class Hra {
  private Hrac hrac;
  private Manazer manazer;
  private ManazerStriel manazerStriel;
  private Boss boss;
  private BossHpBar bossHpBar;

  public Hra() {
    this.manazer = new Manazer();
    this.manazerStriel = new ManazerStriel();
    Game.getGame();
    this.hrac = new Hrac(500, 700, this.manazerStriel, this);
    this.boss = new Boss(500, 200, this.manazerStriel, this.bossHpBar, this, hrac);
    this.manazer.spravujObjekt(this.hrac);
    this.manazer.spravujObjekt(this.boss);
    this.manazer.spravujObjekt(this.manazerStriel);
  }
}
