package org.OOP.DESIGN_PATTERNS.BUILDER;

import java.util.List;

public class Animal {

    public enum Sex{
        MALE,
        FEMALE
    }

    /*
    pedigreeCode campo obbligatori e non modifica
    */
    private final String id;

    private String name;
    private String pedigreeName;

    private String owner;
    private String race;
    private String residence;
    private Boolean isVaccinated;
    private Boolean isChampion;
    private List sons;
    private Sex sex;
    private Double weight;
    private Double height;

    //getter e setter

    public Animal(String name, String pedigreeName, String id, String owner, String race, String residence, Boolean isVaccinated, Boolean isChampion, List sons, Sex sex, Double weight, Double height) {
        this.name = name;
        this.pedigreeName = pedigreeName;
        this.id = id;
        this.owner = owner;
        this.race = race;
        this.residence = residence;
        this.isVaccinated = isVaccinated;
        this.isChampion = isChampion;
        this.sons = sons;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
    }

    public Animal(String id,String name, String pedigreeName) {
        this.name = name;
        this.pedigreeName = pedigreeName;
        this.id = id;
    }

    public Animal(String id, String owner, String race, String residence) {
        this.id = id;
        this.owner = owner;
        this.race = race;
        this.residence = residence;
    }

    public Animal(String id){
        this.id = id;
    }



}
