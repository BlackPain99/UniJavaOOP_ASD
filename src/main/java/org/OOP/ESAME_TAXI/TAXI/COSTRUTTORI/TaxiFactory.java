package org.OOP.ESAME_TAXI.TAXI.COSTRUTTORI;

import org.OOP.ESAME_TAXI.TAXI.Guidatore;
import org.OOP.ESAME_TAXI.TAXI.Taxi;
import org.OOP.ESAME_TAXI.TAXI.TaxiAutonomo;
import org.OOP.ESAME_TAXI.TAXI.TaxiGuidato;

public class TaxiFactory {

    public Taxi creaTaxiAutonomo(String targa, Integer numeroPosti, String produttoreSw){
        return new TaxiAutonomo(targa, numeroPosti, produttoreSw);
    }

    public Taxi creaTaxiGuidato(String targa, Integer numeroPosti, Guidatore guidatore){
        return new TaxiGuidato(targa, numeroPosti, guidatore);

    }
}
