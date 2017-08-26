package net.meerkat.pricing.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class EvaluatedFxForwardPrice<C extends CurrencyId> implements FxForwardPrice<C> {

    @Override
    public Number bidForwardPoints() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Number offerForwardPoints() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Money<C> bid() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Money<C> offer() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public EvaluatedFxForwardPrice<C> evaluate() {
        return this;
    }

}
