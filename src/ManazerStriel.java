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
        for (Strela s : this.hracoveStrely) {
            s.tik();
        }
        this.hracoveStrely.removeIf(strela -> !strela.getJeAktivna());

        for (Strela s : this.bossoveStrely) {
            s.tik();
        }
        this.bossoveStrely.removeIf(strela -> !strela.getJeAktivna());
    }

    public void koliziaBossovejStrely() {

    }
}