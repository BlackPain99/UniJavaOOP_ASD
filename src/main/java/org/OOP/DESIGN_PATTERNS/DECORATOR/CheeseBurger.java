package org.OOP.DESIGN_PATTERNS.DECORATOR;

public class CheeseBurger extends Consumation {

    public CheeseBurger(){
        productName = "CheeseBurger";
    }

    @Override
    public double getPrice() {
        return 2.50;
    }

}