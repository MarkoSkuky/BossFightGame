import fri.shapesge.Obrazok;

public class Boss {
  private Hra hra;
  private int polohaX;
  private int polohaY;
  private final int sirkaBossa = 110;
  private final int vyskaBossa = 110;
  private Obrazok obrazokBossa;
  private int rychlost = 8;
  private Hrac hrac;
  private OblastBossa oblastBossa;
  private ManazerStriel manazerStriel;
  private BossHpBar bossHpBar;
  private int hp;
  private int smerPohybu;

  public Boss(int polohaX, int polohaY, ManazerStriel manazerStriel, BossHpBar bossHpBar, Hra hra, Hrac hrac) {
    this.hra = hra;
    this.polohaX = polohaX;
    this.polohaY = polohaY;
    this.manazerStriel = manazerStriel;
    this.bossHpBar = bossHpBar;
    this.obrazokBossa = new Obrazok("assets/boss1.png", polohaX, polohaY);
    this.obrazokBossa.zobraz();
    this.oblastBossa = new OblastBossa();
    this.hrac = hrac;
    this.hp = 1000;
    this.smerPohybu = 1;
  }

  public void tik() {
      if (this.getPravyHitbox() < this.oblastBossa.getPravyOkraj()) {
        this.polohaX += this.rychlost * smerPohybu;
        this.zmenPolohu();
      }
  }

  public void utok1() {

  }

  public void utok2() {

  }

  public void vystrel() {

  }

  public void uberZivoty() {

  }

  private void zmenPolohu() {
    this.obrazokBossa.zmenPolohu(this.polohaX, this.polohaY);
  }

  public boolean vidiHraca() {
    if (this.polohaX + 55 > hrac.getLavyHitbox() && this.polohaX + 55 < hrac.getPravyHitbox()) {
      return true;
    }
    return false;
  }

  public int getLavyHitbox() {
    return this.polohaX;
  }

  public int getPravyHitbox() {
    return this.polohaX + this.sirkaBossa;
  }

  public int getHornyHitbox() {
    return this.polohaY;
  }

  public int getDolnyHitbox() {
    return this.polohaY + this.vyskaBossa;
  }


}
