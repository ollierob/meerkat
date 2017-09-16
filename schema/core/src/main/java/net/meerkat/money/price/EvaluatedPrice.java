package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class EvaluatedPrice<C extends CurrencyId> implements Price.Valued<C> {

    private final Money<C> value;

    public EvaluatedPrice(final Money<C> value) {
        this.value = value;
    }

    @Override
    public Money<C> value() {
        return value;
    }

    @Override
    @Deprecated
    public EvaluatedPrice<C> evaluate() {
        return this;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder().put("value", value);
    }

}
