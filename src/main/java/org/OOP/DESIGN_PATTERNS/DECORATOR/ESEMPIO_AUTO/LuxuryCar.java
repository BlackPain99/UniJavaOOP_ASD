package org.OOP.DESIGN_PATTERNS.DECORATOR.ESEMPIO_AUTO;

public class LuxuryCar extends CarDecorator {

    public LuxuryCar(Car c) {
        super(c);
    }

    public void assemble(){
        super.assemble();
        System.out.print(" Adding features of Luxury Car.");
    }
}
