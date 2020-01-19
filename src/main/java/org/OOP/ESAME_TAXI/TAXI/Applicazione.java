package org.OOP.ESAME_TAXI.TAXI;

import org.OOP.ESAME_TAXI.TAXI.COSTRUTTORI.TaxiBuilder;
import org.OOP.ESAME_TAXI.TAXI.COSTRUTTORI.TaxiFactory;
import sun.net.www.ApplicationLaunchException;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe rappresenta l'applicazione che espone le API per la gestione e la prenotazione
 * dei taxi registrati.
 */
public class Applicazione {

    private GestoreConCodice<Guidatore> registroGuidatori;
    private GestoreConCodice<Persona> registroPasseggeri;
    private GestoreConCodice<Taxi> registroTaxi;
    private final TaxiBuilder taxiBuilder; //final: una volta impostato il vaolore non si potrà cambiare
    private final TaxiFactory taxiFactory;
    private GestoreCorse registroCorse;

    /**
     * Costruttore (Creator).
     * Crea una nuova istanza della classe Applicazione.
     */
    public Applicazione(){

        //Utilizzo di classe anonima (solo per esercizio, meglio fare come per registroPasseggeri e registroTaxi)
        this.registroGuidatori = new GestoreConCodice<Guidatore>() {

            @Override
            public void aggiungi(Guidatore nuovo) {

            }

            @Override
            public boolean ePresente(Guidatore daCercare) {
                return false;
            }

            @Override
            public void rimuovi(Guidatore daRimuovere) {

            }

            @Override
            public boolean ePresente(Integer codice) {
                return false;
            }

            @Override
            public List<Guidatore> getComponenti() {
                return null;
            }

            private List<Guidatore> guidatori = new ArrayList<>();


        }

    }



}
