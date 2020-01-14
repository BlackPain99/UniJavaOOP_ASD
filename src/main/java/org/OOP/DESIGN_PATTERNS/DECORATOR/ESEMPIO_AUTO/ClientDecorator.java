package org.OOP.DESIGN_PATTERNS.DECORATOR.ESEMPIO_AUTO;

public class ClientDecorator {

    public static void main(String[] args) {

        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
        System.out.println("\n*****");


    }
}
