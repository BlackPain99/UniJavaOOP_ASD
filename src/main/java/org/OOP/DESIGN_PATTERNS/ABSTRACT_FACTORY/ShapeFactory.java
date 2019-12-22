package org.OOP.DESIGN_PATTERNS.ABSTRACT_FACTORY;

public class ShapeFactory extends AbstractFactory {

    @Override
    Shape getShape(String shapeType) {

        if(shapeType == null) {
            return null;
        }

        if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }

        if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }

        return null;
    }

}
