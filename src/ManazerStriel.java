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
        if (strela.jeHracova()) {
            this.hracoveStrely.add(strela);
        } else {
            this.bossoveStrely.add(strela);
        }
    }

    public void tikStrely() {
        for (Strela s : this.hracoveStrely) {
            s.tik();
            this.koliziaHracovejStrely(s);
        }
        this.hracoveStrely.removeIf(strela -> !strela.jeAktivna()); //tuto metodu som si nasiel na nete

        for (Strela s : this.bossoveStrely) {
            s.tik();
            this.koliziaBossovejStrely(s);
        }
        this.bossoveStrely.removeIf(strela -> !strela.jeAktivna()); //tuto metodu som si nasiel na nete
    }

    private void koliziaHracovejStrely(Strela strela) {
        if (strela.jeHracova()
            && strela.getPoziciaX() > this.boss.getLavyHitbox()
            && strela.getPoziciaX() < this.boss.getPravyHitbox()
            && strela.getHornyHitbox() < this.boss.getDolnyHitbox()
            && strela.getDolnyHitbox() > this.boss.getHornyHitbox()) {
            strela.setAktivna(false);
            strela.skryObrazok();
            this.boss.uberZivoty();
        }
    }

    private void koliziaBossovejStrely(Strela strela) {
        if (!strela.jeHracova()
            && strela.getPoziciaX() > this.hrac.getLavyHitbox()
            && strela.getPoziciaX() < this.hrac.getPravyHitbox()
            && strela.getHornyHitbox() < this.hrac.getDolnyHitbox()
            && strela.getDolnyHitbox() > this.hrac.getHornyHitbox()) {
            strela.setAktivna(false);
            strela.skryObrazok();
            this.hrac.uberZivoty();
        }
    }
    public void restart() {
        for (Strela s : this.hracoveStrely) {
            s.skryObrazok();
        }
        for (Strela s : this.bossoveStrely) {
            s.skryObrazok();
        }
        this.hracoveStrely.clear();
        this.bossoveStrely.clear();
    }
}