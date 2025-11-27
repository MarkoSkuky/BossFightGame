import fri.shapesge.Manazer;
import java.util.ArrayList;

public class ManazerStriel {

  private ArrayList<Strela> hracoveStrely;
  private ArrayList<Strela> bossoveStrely;
  private Boss boss;
  private Hrac hrac;

  public ManazerStriel(Hrac hrac, Boss boss) {
    this.hracoveStrely = new ArrayList<>();
    this.bossoveStrely = new ArrayList<>();
    this.boss = boss;
    this.hrac = hrac;
  }

  public void pridajStrelu(Strela strela) {
    if (strela.getJeHracova()) {
      this.hracoveStrely.add(strela);
    } else {
      this.bossoveStrely.add(strela);
    }
  }

  public void tikStrely() {
    for (int i = 0; i < this.hracoveStrely.size(); i++) {
      Strela s = this.hracoveStrely.get(i);
      if (s.getJeAktivna()) {
        s.tik();
      } else {
        this.hracoveStrely.remove(i);
        i--;
      }
    }

    for (int i = 0; i < this.bossoveStrely.size(); i++) {
      Strela s = this.bossoveStrely.get(i);
      if (s.getJeAktivna()) {
        s.tik();
      } else {
        this.bossoveStrely.remove(i);
        i--;
      }
    }
  }

  public void koliziaBossovejStrely() {

  }
  }