package net.meerkat.money.interest;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.exception.UnavailableInterestRateException;
import net.ollie.goat.data.BiProvider;

/**
 * Provider of {@link InterestRate interest rates} used for discounting.
 *
 * @author ollie
 */
public interface DiscountRateProvider<T> extends BiProvider<T, CurrencyId, InterestRate> {

    @Override
    default InterestRate require(final T time, final CurrencyId currency) throws UnavailableInterestRateException {
        return this.require(time, currency, UnavailableInterestRateException::new);
    }

}
