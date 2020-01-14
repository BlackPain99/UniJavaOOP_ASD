package org.OOP.DESIGN_PATTERNS.DECORATOR.ESEMPIO_AUTO;

public class CarDecorator implements Car {

    Car decoratedCar;

    public CarDecorator(Car c){
        this.decoratedCar = c;
    }

    @Override
    public void assemble() {
        this.decoratedCar.assemble();
    }

}
