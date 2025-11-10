import fri.shapesge.Obrazok;

public class OblastHraca {
  private int lavyOkraj;
  private int pravyOkraj;
  private int hornyOkraj;
  private int dolnyOkraj;
  private Obrazok predelenie; //obrazok ma 1200x25px

  public OblastHraca() {
    this.predelenie = new Obrazok("assets/predelenie.png", 0, 475);
    predelenie.zobraz();
    this.lavyOkraj = 0;
    this.pravyOkraj = 1200;
    this.hornyOkraj = 500;
    this.dolnyOkraj = 800;
  }

  public int getLavyOkraj() {
    return lavyOkraj;
  }

  public int getPravyOkraj() {
    return pravyOkraj;
  }

  public int getHornyOkraj() {
    return hornyOkraj;
  }

  public int getDolnyOkraj() {
    return dolnyOkraj;
  }
}
