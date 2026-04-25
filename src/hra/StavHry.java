package hra;

/**
 * Enum hra.StavHry reprezentuje aktualny stav hry.
 */
public enum StavHry {

    /**
     * hra.Hra sa nachadza v hlavnom menu
     */
    MENU,

    /**
     * hra.Hra prave prebieha
     */
    BEZI,

    /**
     * hra.Hra je pozastavena
     */
    PAUZA,

    /**
     * hra.Hra je ukoncena
     */
    KONIEC
}