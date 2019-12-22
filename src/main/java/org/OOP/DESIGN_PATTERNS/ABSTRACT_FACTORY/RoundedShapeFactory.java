package org.OOP.DESIGN_PATTERNS.ABSTRACT_FACTORY;

public class RoundedShapeFactory extends AbstractFactory {

    @Override
    Shape getShape(String shapeType) {

        if(shapeType == null){
            return null;
        }

        if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new RoundedRectangle();
        }

        if(shapeType.equalsIgnoreCase("SQUARE")){
            return new RoundedSquare();
        }

        return null;
    }
}
