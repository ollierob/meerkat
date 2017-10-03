package net.meerkat.pricing.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.EvaluatedPrice;

/**
 *
 * @author Ollie
 */
public class EvaluatedFxForwardPrice<C extends CurrencyId> extends EvaluatedPrice<C> implements FxForwardPrice<C> {

    public EvaluatedFxForwardPrice(Money<C> value) {
        super(value);
    }

    @Override
    public Number forwardPoints() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public EvaluatedFxForwardPrice<C> evaluate() {
        return this;
    }

}
