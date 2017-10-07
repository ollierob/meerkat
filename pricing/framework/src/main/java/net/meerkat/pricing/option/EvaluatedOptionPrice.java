package net.meerkat.pricing.option;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.EvaluatedPrice;
import net.meerkat.money.price.Price;

/**
 *
 * @author Ollie
 */
public class EvaluatedOptionPrice<C extends CurrencyId>
        extends EvaluatedPrice<C>
        implements OptionPrice<C> {

    private final Price<C> underlyingPrice;
    private final Money<C> intrinsicValue;

    public EvaluatedOptionPrice(
            final Price<C> underlyingPrice,
            final Money<C> intrinsicValue,
            final Money<C> value) {
        super(value);
        this.underlyingPrice = underlyingPrice;
        this.intrinsicValue = intrinsicValue;
    }

    @Override
    public Price<C> underlyingPrice() {
        return underlyingPrice;
    }

    @Override
    public Money<C> intrinsicValue() {
        return intrinsicValue;
    }

    @Override
    public Money<C> extrinsicValue() {
        return this.value().minus(intrinsicValue);
    }

    @Override
    @Deprecated
    public EvaluatedOptionPrice<C> evaluate() {
        return this;
    }

}
