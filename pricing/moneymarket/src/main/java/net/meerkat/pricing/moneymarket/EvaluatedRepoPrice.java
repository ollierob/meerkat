package net.meerkat.pricing.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.EvaluatedPrice;

/**
 *
 * @author Ollie
 */
public class EvaluatedRepoPrice<C extends CurrencyId> extends EvaluatedPrice<C> implements RepoPrice<C> {

    public EvaluatedRepoPrice(final Money<C> value) {
        super(value);
    }

    @Override
    @Deprecated
    public EvaluatedRepoPrice<C> evaluate() {
        return this;
    }

}
