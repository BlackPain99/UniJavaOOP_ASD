package org.OOP.ESAME_COMANDE;

/**
 * Rappresenta un produttore di gestori di menů
 *
 */
public class ProduttoreGestoreMenu implements Produttore {

    /**
     * Produce un gestore di menů. (Observer)
     * @return una nuova istanza di un gestore di menů
     */
    @Override
    public GestoreMenu produci() {
        return new GestoreMenu();
    }

}
