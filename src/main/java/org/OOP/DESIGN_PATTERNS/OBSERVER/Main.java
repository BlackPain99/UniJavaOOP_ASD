package org.OOP.DESIGN_PATTERNS.OBSERVER;

public class Main {

    public static void main(String[] args) {

        ObservableMatch match = new ObservableMatch();
        Observer observer1 = new ObserverMatch("0");
        Observer observer2 = new ObserverMatch("1");

        match.addObserver(observer1);
        match.addObserver(observer2);

        match.setMatchScore("1-0");

        match.removeObserver(observer2);
        match.setMatchScore("2-0");

    }
}
