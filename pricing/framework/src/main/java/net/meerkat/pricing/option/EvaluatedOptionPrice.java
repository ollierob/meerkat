package net.meerkat.pricing.option;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.EvaluatedPrice;

/**
 *
 * @author Ollie
 */
public class EvaluatedOptionPrice<C extends CurrencyId>
        extends EvaluatedPrice<C>
        implements OptionPrice<C> {

    private final Money<C> intrinsicValue;

    public EvaluatedOptionPrice(final Money<C> intrinsicValue, final Money<C> value) {
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
    public EvaluatedOptionPrice<C> evaluate() {
        return this;
    }

}
