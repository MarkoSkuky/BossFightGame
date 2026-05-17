package efekty;

/**
 * Rozhranie pre posobenie efektu na hraca, ktore trva len docasne.
 * Definuje cely zivotny cyklus efektu od aktivacie cez priebezny tik
 * az po vyprchanie.
 */
public interface EfektPosobenie {
    /**
     * Co sa ma vykonat pri aplikovani efektu.
     */
    void priAktivacii();

    /**
     * Co sa ma diat po kazdom tiku pocas trvania efektu.
     */
    void tikEfektu();

    /**
     * Ukonci efekt po vyprsani jeho trvania a vrati stav hraca do normalu.
     */
    void priVyprchani();

    /**
     * Vrati zostavajuce trvanie efektu v tikoch.
     *
     * @return zostavajuci pocet tikov efektu
     */
    int getTrvanie();
}
