package net.meerkat.pricing.equity.option;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.EvaluatedPrice;

/**
 *
 * @author Ollie
 */
public class EvaluatedWarrantPrice<C extends CurrencyId>
        extends EvaluatedPrice<C>
        implements WarrantPrice<C> {

    private final Money<C> intrinsicValue;

    public EvaluatedWarrantPrice(final Money<C> intrinsicValue, final Money<C> value) {
        super(value);
        this.intrinsicValue = intrinsicValue;
    }

    @Override
    public Money<C> intrinsicValue() {
        return intrinsicValue;
    }

    @Override
    public Money<C> timeValue() {
        return this.value().minus(intrinsicValue);
    }

    @Override
    @Deprecated
    public EvaluatedWarrantPrice<C> evaluate() {
        return this;
    }

}
