import fri.shapesge.Stvorec;

public class Strela {
  private int velkostStrely;
  private int poziciaX;
  private int poziciaY;
  private Stvorec strela;

  public Strela(int velkostStrely, int poziciaX, int poziciaY) {
    this.strela = new Stvorec(poziciaX, poziciaY);
    this.strela.zmenStranu(velkostStrely);
  }
}
