package org.OOP.ESAME_TAXI.TAXI;

import org.OOP.ESAME_TAXI.ECCEZIONI.ArgomentiMancanti;

import java.util.Calendar;

public class Guidatore extends Persona implements Patentato {

    private String numeroPatente;

    public Guidatore(String nome, String cognome, Calendar data, String numeroPatente) throws ArgomentiMancanti {
        super(nome, cognome, data);

        if(numeroPatente == null || numeroPatente.isEmpty()) {
            throw new ArgomentiMancanti("numero patente");
        }

        this.numeroPatente = numeroPatente;

    }


    @Override
    public String getNumeroPatente() {
        return this.numeroPatente;
    }

}
