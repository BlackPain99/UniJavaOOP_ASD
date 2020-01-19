package org.OOP.ESAME_TAXI.TAXI.COSTRUTTORI;

import org.OOP.ESAME_TAXI.ECCEZIONI.ArgomentiMancanti;

public interface Builder {

    Object crea() throws ArgomentiMancanti;
}

