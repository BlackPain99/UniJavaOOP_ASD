package org.OOP.DESIGN_PATTERNS.OBSERVER;

public class ObserverMatch implements Observer {

    private String id;
    private String score;

    public ObserverMatch(String id){
        this.id = id;
    }

    @Override
    public void update(Object o){

        System.out.println("(observer-"+id+")risultato: "+ (String) score);
        this.score = (String) score;

    }
}
