package org.OOP.DESIGN_PATTERNS.DECORATOR.ESEMPIO_HAMBURGER;

public class CheeseBurger extends Consumation {

    public CheeseBurger(){
        productName = "CheeseBurger";
    }

    @Override
    public double getPrice() {
        return 2.50;
    }

}
