package org.OOP.ESAME_COMANDE.COMANDE;

/**
 * Rappresenta un produttore di gestori di comande
 *
 */
public class ProduttoreGestoreComande implements Produttore{

    /**
     * Produce un gestore di comande. (Observer)
     * @return una nuova istanza di un gestore di comande
     */
    @Override
    public GestoreComande produci() {
        return new GestoreComande();
    }

}
