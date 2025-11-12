import fri.shapesge.Obrazok;
import fri.shapesge.Stvorec;

public class Strela {
  //13x20
  private final int rychlost = 4;
  private int poziciaX;
  private int poziciaY;
  private Obrazok obrazok;
  private boolean jeAktivna;

  public Strela(int poziciaX, int poziciaY) {
    this.obrazok = new Obrazok("assets/strela.png", poziciaX, poziciaY);
    this.poziciaX = poziciaX;
    this.poziciaY = poziciaY;
    this.obrazok.zobraz();
    this.jeAktivna = true;
  }

  public void tik() {
    if (this.jeAktivna) {
      this.poziciaY -= rychlost;
      this.aktualizujPolohu();
    }
    if (this.poziciaY <= 0) {
      this.jeAktivna = false;
      this.obrazok.skry();
    }
  }

  private void aktualizujPolohu() {
    obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
  }

  public boolean getJeAktivna() {
    return this.jeAktivna;
  }
}
