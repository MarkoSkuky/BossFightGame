import fri.shapesge.Obrazok;

public class Hrac {

  private ManazerStriel manazerStriel;
  private Hra hra;
  private int polohaX;
  private int polohaY;
  private final int sirkaHraca = 90;
  private final int vyskaHraca = 90;
  private Obrazok obrazokHraca; //velkost obrazku je 90x90px
  private final int rychlost = 8;
  private OblastHraca oblastHraca;
  private boolean ideVlavo;
  private boolean ideVpravo;
  private boolean ideHore;
  private boolean ideDole;

  public Hrac(int polohaX, int polohaY, ManazerStriel manazerStriel, Hra hra) {
    this.hra = hra;
    this.manazerStriel = manazerStriel;
    this.oblastHraca = new OblastHraca();
    this.obrazokHraca = new Obrazok("assets/hracPredok.png", polohaX, polohaY);
    this.obrazokHraca.zobraz();
    this.polohaX = polohaX;
    this.polohaY = polohaY;
  }

  public void posunVpravo() {
    this.ideVpravo = true;
  }

  public void posunVlavo() {
    this.ideVlavo = true;
  }

  public void uvolniVpravo() {
    this.ideVpravo = false;
  }

  public void uvolniVlavo() {
    this.ideVlavo = false;
  }

  public void posunHore() {
    this.ideHore = true;
  }

  public void posunDole() {
    this.ideDole = true;
  }

  public void uvolniHore() {
    this.ideHore = false;
  }

  public void uvolniDole() {
    this.ideDole = false;
  }

  public void aktivuj() {
    Strela strela = new Strela(this.polohaX + this.sirkaHraca / 2 - 6, this.polohaY);
    this.manazerStriel.pridajStrelu(strela);
  }

  public void tik() {
    if (this.ideVlavo) {
      if (this.oblastHraca.getLavyOkraj() < this.getLavyHitbox() - 4) {
        this.polohaX -= rychlost;
        zmenPolohu();
      }
    }
    if (this.ideVpravo) {
      if (this.oblastHraca.getPravyOkraj() > this.getPravyHitbox() + 10) {
        this.polohaX += rychlost;
        zmenPolohu();
      }
    }
    if (this.ideHore) {
      if (this.oblastHraca.getHornyOkraj() < this.getHornyHitbox()) {
        this.polohaY -= rychlost;
        zmenPolohu();
      }
    }
    if (this.ideDole) {
      if (this.oblastHraca.getDolnyOkraj() > this.getDolnyHitbox()) {
        this.polohaY += rychlost;
        zmenPolohu();
      }
    }
  }

  public int getLavyHitbox() {
    return this.polohaX;
  }

  public int getPravyHitbox() {
    return this.polohaX + this.sirkaHraca;
  }

  public int getHornyHitbox() {
    return this.polohaY;
  }

  public int getDolnyHitbox() {
    return this.polohaY + this.vyskaHraca;
  }

  private void zmenPolohu() {
    this.obrazokHraca.zmenPolohu(this.polohaX, this.polohaY);
  }

}
