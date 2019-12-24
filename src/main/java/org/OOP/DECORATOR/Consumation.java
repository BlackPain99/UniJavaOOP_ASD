package org.OOP.DECORATOR;


/**
 * Component: Prima di tutto abbiamo bisogno di una prima classe astratta
 * da cui deriveranno tutti i prodotti della nostra paninoteca:
 * la classe Consumation modellerà la generica consumazione del cliente alla paninoteca Rossi,
 * la quale sarà identificato da un nome del prodotto venduto e prezzo.
 */
public abstract class Consumation {

    String productName = "";

    public String getProductName(){
        return productName;
    }

    public abstract double getPrice();

}
