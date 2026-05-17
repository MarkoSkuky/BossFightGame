package utils;

/**
 * Rozhranie pre objekty, ktore maju hitbox a moze u nich nastat kolizia.
 */
public interface Collidable {
    /**
     * Vrati lavy okraj hitboxu objektu.
     *
     * @return X suradnicu laveho hitboxu
     */
    int getLavyHitbox();

    /**
     * Vrati pravy okraj hitboxu objektu.
     *
     * @return X suradnicu praveho hitboxu
     */
    int getPravyHitbox();

    /**
     * Vrati horny okraj hitboxu objektu.
     *
     * @return Y suradnicu horneho hitboxu
     */
    int getHornyHitbox();

    /**
     * Vrati dolny okraj hitboxu objektu.
     *
     * @return Y suradnicu dolneho hitboxu
     */
    int getDolnyHitbox();
}
