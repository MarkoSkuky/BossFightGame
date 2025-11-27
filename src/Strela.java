import fri.shapesge.Obrazok;
import fri.shapesge.Stvorec;

public class Strela {
  //13x20
  private final int rychlost = 4;
  private int poziciaX;
  private int poziciaY;
  private Obrazok obrazok;
  private boolean jeAktivna;
  private boolean jeHracova;


  public Strela(int poziciaX, int poziciaY, boolean jeHracova) {
    this.obrazok = new Obrazok("assets/strela.png", poziciaX, poziciaY);
    this.poziciaX = poziciaX;
    this.poziciaY = poziciaY;
    this.obrazok.zobraz();
    this.jeAktivna = true;
    this.jeHracova = jeHracova;
  }

  public void tik() {
    if (this.jeHracova) {
      this.hracovaStrela();
    } else {
      this.bossovaStrela();
    }
  }

  private void hracovaStrela() {
    if (this.jeAktivna) {
      this.poziciaY -= rychlost;
      this.aktualizujPolohu();
    }
    if (this.poziciaY <= 0) {
      this.jeAktivna = false;
      this.obrazok.skry();
    }
  }

  private void bossovaStrela() {
    if (this.jeAktivna) {
      this.poziciaY += rychlost;
      this.aktualizujPolohu();
    }
    if (this.poziciaY >= 800) {
      this.jeAktivna = false;
      this.obrazok.skry();
    }
  }

  public void skryObrazok() {
    this.obrazok.skry();
  }

  private void aktualizujPolohu() {
    obrazok.zmenPolohu(this.poziciaX, this.poziciaY);
  }

  public boolean getJeAktivna() {
    return this.jeAktivna;
  }

  public int getPoziciaX() {
    return this.poziciaX;
  }

  public int getPoziciaY() {
    return this.poziciaY;
  }

  public boolean getJeHracova() {
    return this.jeHracova;
  }
}
