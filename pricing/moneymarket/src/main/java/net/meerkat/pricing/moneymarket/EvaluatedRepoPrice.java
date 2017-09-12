package net.meerkat.pricing.moneymarket;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class EvaluatedRepoPrice<C extends CurrencyId> implements RepoPrice<C> {

    private final Money<C> value;

    public EvaluatedRepoPrice(final Money<C> value) {
        this.value = value;
    }

    @Override
    public Money<C> value() {
        return value;
    }

    @Override
    @Deprecated
    public EvaluatedRepoPrice<C> evaluate() {
        return this;
    }

    @Override
    public Map<String, Object> explain() {
        throw new UnsupportedOperationException(); //TODO
    }

}
