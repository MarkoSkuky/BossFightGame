package utils;

/**
 * Trieda poskytuje pomocnu metodu na detekciu kolizii medzi objektmi s hitboxom.
 */
public class ManazerKolizii {
    /**
     * Vytvori novy manazer kolizii.
     */
    public ManazerKolizii() {

    }

    /**
     * Zisti, ci dva objekty koliduju na zaklade prekrytia ich hitboxov.
     *
     * @param a prvy kolidujuci objekt
     * @param b druhy kolidujuci objekt
     * @return true ak sa hitboxy objektov prekryvaju, inak false
     */
    public boolean kolizia(Collidable a, Collidable b) {
        return a.getLavyHitbox() < b.getPravyHitbox()
            && a.getPravyHitbox() > b.getLavyHitbox()
            && a.getHornyHitbox() < b.getDolnyHitbox()
            && a.getDolnyHitbox() > b.getHornyHitbox();
    }
}
