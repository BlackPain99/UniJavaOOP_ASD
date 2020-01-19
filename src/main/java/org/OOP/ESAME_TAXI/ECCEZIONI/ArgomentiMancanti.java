package org.OOP.ESAME_TAXI.ECCEZIONI;


public class ArgomentiMancanti extends Exception {

    private String nomeParametro;

    public ArgomentiMancanti(String nomeParametro){
        this.nomeParametro = nomeParametro;
    }

    public String getNomeParametro(){
        return nomeParametro;
    }

}
