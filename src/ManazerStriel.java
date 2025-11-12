import fri.shapesge.Manazer;
import java.util.ArrayList;

public class ManazerStriel {
  private ArrayList<Strela> strelyHraca;

  public ManazerStriel() {
    this.strelyHraca = new ArrayList<Strela>();
  }

  public void pridajStrelu(Strela strela) {
    this.strelyHraca.add(strela);
  }

  public void tikStrely() {
    for (int i = 0; i < this.strelyHraca.size(); i++) {
      if (this.strelyHraca.get(i).getJeAktivna()) {
        this.strelyHraca.get(i).tik();
      } else {
        this.strelyHraca.remove(i);
        i--;
      }
    }
  }
}
