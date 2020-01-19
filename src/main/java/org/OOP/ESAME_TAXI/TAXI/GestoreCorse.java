package org.OOP.ESAME_TAXI.TAXI;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Rappresenta un gestore delle prenotazioni e dei taxi
 */
class GestoreCorse {

    private final long MAX_ATTESA_PRENOTAZIONE = 60;

    private Map<String, InfoPrenotazione> prenotazioniOdierne;
    private Map<String, InfoPrenotazione> storicoPrenotazioni;
    private VerificatoreDisponibiltaTaxi verificatoreTaxi;

    GestoreCorse(GestoreConCodice<Taxi> registroTaxi) {
        prenotazioniOdierne = new HashMap<>();
        storicoPrenotazioni = new HashMap<>();
        verificatoreTaxi = new VerificatoreDisponibiltaTaxi(registroTaxi, prenotazioniOdierne);
    }

    List<Corsa> getReportGiornaliero() {

        return prenotazioniOdierne.values()
                .stream()
                .filter(info -> info.getStato().equals(StatiPrenotazione.ACCETTATA) && info.getDatiPrenotazione().getMomento().before(Calendar.getInstance()))
                .map(InfoPrenotazione::getCorsa)
                .collect(Collectors.toList());
    }

    InfoPrenotazione infoPrenotazione(String codicePrenotazione){
        return this.storicoPrenotazioni.get(codicePrenotazione);
    }




}
