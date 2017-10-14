package net.meerkat.money.interest;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.exception.UnknownInterestRateException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface InterestRateSnapshot extends Provider<InterestRateId, InterestRate> {

    @Override
    default InterestRate require(final InterestRateId key) throws UnknownInterestRateException {
        return this.require(key, UnknownInterestRateException::new);
    }

    @Nonnull
    InterestRate discountRate(CurrencyId currencyId);

}
