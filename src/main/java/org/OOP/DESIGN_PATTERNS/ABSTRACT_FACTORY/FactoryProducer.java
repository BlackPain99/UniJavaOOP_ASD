package org.OOP.DESIGN_PATTERNS.ABSTRACT_FACTORY;

public class FactoryProducer {

    public static AbstractFactory getFactory(boolean rounded){

        if(rounded){
            return new RoundedShapeFactory();
        } else {
            return new ShapeFactory();
        }

    }
}
