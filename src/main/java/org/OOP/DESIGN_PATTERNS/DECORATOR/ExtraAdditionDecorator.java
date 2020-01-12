package org.OOP.DESIGN_PATTERNS.DECORATOR;

/**
 * Decorator: Abbiamo poi bisogno della classe ExtraAdditionDecorator
 * che modellerà ogni possibile aggiunta non prevista ad un prodotto:
 * ad esempio un ingrediente aggiuntivo in un panino.
 * Tale classe fa da classe base per i Decorator,
 * ovvero modella tutti gli ingredienti aggiuntivi.
 */
public abstract class ExtraAdditionDecorator extends Consumation{

    protected Consumation consumation;

    @Override
    public abstract String getProductName();

}
