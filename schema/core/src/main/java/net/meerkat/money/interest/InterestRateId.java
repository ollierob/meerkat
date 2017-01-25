package net.meerkat.money.interest;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;

/**
 *
 * @author Ollie
 */
public interface InterestRateId extends HasCurrencyId {

    static InterestRateId named(final String name, final CurrencyId currencyId) {
        return new NamedInterestRateId(name, currencyId);
    }

}
