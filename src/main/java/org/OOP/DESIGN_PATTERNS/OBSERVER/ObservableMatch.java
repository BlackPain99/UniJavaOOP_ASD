package org.OOP.DESIGN_PATTERNS.OBSERVER;


import java.util.ArrayList;
import java.util.List;

/**
 * realizzare un sistema di notifiche per gli aggiornamenti di un risultato di un match sportivo.
 * Un match il cui risultato cambia nel tempo.
 * N observer che vogliono abbonarsi a tale match per essere aggiornati sul risultato del match.
 */

/**
 * quando il matchScore è aggiornato con il relativo setter, ObservableMatch notificherà
 * gli observer registrati a tale match chiamando il metodo update().
 * Per essere in grado di fare ciò, ObservableMatch ha bisogno di tenere il riferimento
 * degli observer registrati, nel nostro caso con la lista observers.
 */

public class ObservableMatch {

    private String matchScore;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer channel){
        //notifico il risultato iniziale non appena un observer si sottoscrive
        channel.update(this.matchScore);
        this.observers.add(channel);
    }

    public void removeObserver(Observer channel){
        this.observers.remove(channel);
    }

    public void setMatchScore(String newScore){
        this.matchScore = newScore;
        for(Observer observer : this.observers){
            observer.update(this.matchScore);
        }
    }

    public ObservableMatch(){
        this.matchScore = "0-0";
    }

}
