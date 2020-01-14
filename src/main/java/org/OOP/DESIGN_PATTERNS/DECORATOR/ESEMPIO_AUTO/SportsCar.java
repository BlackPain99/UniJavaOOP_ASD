package org.OOP.DESIGN_PATTERNS.DECORATOR.ESEMPIO_AUTO;

public class SportsCar extends CarDecorator {

    public SportsCar(Car c) {
        super(c);
    }

    public void assemble(){
        super.assemble();
        System.out.print(" Adding features of Sports Car.");
    }

}
