package utils;

public class ManazerKolizii {
    public ManazerKolizii() {

    }

    public boolean kolizia(Collidable a, Collidable b) {
        return a.getLavyHitbox() < b.getPravyHitbox()
            && a.getPravyHitbox() > b.getLavyHitbox()
            && a.getHornyHitbox() < b.getDolnyHitbox()
            && a.getDolnyHitbox() > b.getHornyHitbox();
    }
}
