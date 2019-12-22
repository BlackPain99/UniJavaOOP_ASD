package org.OOP.DESIGN_PATTERNS.BUILDER;

public class Main {

    public static void main(String[] args) {

        AnimalBuilder animalBuilder = AnimalBuilder.newBuilder("0000001")
                .name("0000001")
                .pedigreeName("PlutoSecondo")
                .owner("Marco Rossi")
                .race("labrador")
                .residence("Via x")
                .isVaccinated(true)
                .isChampion(false)
                .sons(null)
                .sex(Animal.Sex.MALE)
                .weight(40.5)
                .height(30.0);

        Animal animal3A = animalBuilder.build();
        Animal animal3AClone = animalBuilder.build();
        Animal animal3B = animalBuilder.sex(Animal.Sex.FEMALE).build();
    }
}
