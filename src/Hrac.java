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
  private boolean ideVlavo;
  private boolean ideVpravo;
  private boolean ideHore;
  private boolean ideDole;

  public Hrac(int polohaX, int polohaY, ManazerStriel manazerStriel, Hra hra) {
    this.hra = hra;
    this.manazerStriel = manazerStriel;
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
    Strela strela = new Strela(this.polohaX + this.sirkaHraca / 2 - 6, this.polohaY, true);
    this.manazerStriel.pridajStrelu(strela);
  }

  public void uberZivoty() {

  }

  public void tik() {
    OblastPohybu oblastPohybu = new OblastPohybu(0, 1200, 500, 800);
    if (this.ideVlavo) {
      if (oblastPohybu.getLavyOkraj() < this.getLavyHitbox() - 4) {
        this.polohaX -= rychlost;
        zmenPolohu();
      }
    }
    if (this.ideVpravo) {
      if (oblastPohybu.getPravyOkraj() > this.getPravyHitbox() + 10) {
        this.polohaX += rychlost;
        zmenPolohu();
      }
    }
    if (this.ideHore) {
      if (oblastPohybu.getHornyOkraj() < this.getHornyHitbox()) {
        this.polohaY -= rychlost;
        zmenPolohu();
      }
    }
    if (this.ideDole) {
      if (oblastPohybu.getDolnyOkraj() > this.getDolnyHitbox()) {
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
