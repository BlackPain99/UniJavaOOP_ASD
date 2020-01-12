package org.OOP.DESIGN_PATTERNS.DECORATOR;

public class Main {

    public static void main(String[] args) {

        Consumation hamburger = new Hamburger();
        System.out.println("Prodotto:" + hamburger.productName + " di prezzo " + String.format("%.2f", hamburger.getPrice()));

        Consumation cheeseburger = new CheeseBurger();

        Consumation hamburgerConMaionese = new ExtraMaioneseDecorator(hamburger);
        System.out.println("Prodotto:" + hamburgerConMaionese.getProductName() + " di prezzo " + String.format("%.2f", hamburgerConMaionese.getPrice()));

        Consumation hamburgerConMaioneseKetchup = new ExtraKetchupDecorator(new ExtraMaioneseDecorator(hamburger));
        System.out.println("Prodotto:" + hamburgerConMaioneseKetchup.getProductName() + " di prezzo " + String.format("%.2f", hamburgerConMaioneseKetchup.getPrice()));

        Consumation cheeseburgerConMaionese = new ExtraMaioneseDecorator(cheeseburger);
        System.out.println("Prodotto:" + cheeseburgerConMaionese.getProductName() + " di prezzo " + String.format("%.2f", cheeseburgerConMaionese.getPrice()));

    }
}
