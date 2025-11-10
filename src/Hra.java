import fri.shapesge.Manazer;
import fri.shapesge.engine.Game;

public class Hra {
  private Hrac hrac;
  private Manazer manazer;

  public Hra() {
    this.manazer = new Manazer();
    Game.getGame();
    this.hrac = new Hrac(500, 700);
    this.manazer.spravujObjekt(this.hrac);
  }
}
