package org.OOP.TAXI_PARTIAL;

import java.util.ArrayList;
import java.util.List;

class GestoreElementi<T> implements GestoreConCodice<T> {

    private List<T> elementi = new ArrayList<T>();


    @Override
    public void aggiungi(T nuovo) {
        elementi.add(nuovo);

    }

    @Override
    public boolean ePresente(T daCercare) {
        return elementi.contains(daCercare);
    }

    @Override
    public void rimuovi(T daRimuovere) {
        elementi.remove(daRimuovere);

    }

    @Override
    public boolean ePresente(Integer codice) {

        for(T e : elementi){
            if(e.hashCode() == codice){
                return true;
            }
        }

        return false;
    }

    @Override
    public List<T> getComponenti() {
        return elementi;
    }
}
