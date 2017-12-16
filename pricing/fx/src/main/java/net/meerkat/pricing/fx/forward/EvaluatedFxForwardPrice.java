package net.meerkat.pricing.fx.forward;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.EvaluatedPrice;

/**
 *
 * @author Ollie
 */
public class EvaluatedFxForwardPrice<C extends CurrencyId> extends EvaluatedPrice<C> implements FxForwardPrice<C> {

    private final Number forwardPoints;

    public EvaluatedFxForwardPrice(final Money<C> value, final Number forwardPoints) {
        super(value);
        this.forwardPoints = forwardPoints;
    }

    @Override
    public Number forwardPoints() {
        return forwardPoints;
    }

    @Override
    public EvaluatedFxForwardPrice<C> evaluate() {
        return this;
    }

}
