package net.ollie.meerkat.calculate.var;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrency;
import net.meerkat.money.fx.ExchangeRates;

/**
 * Value at risk (VaR).
 *
 * @author Ollie
 */
public interface ValueAtRisk extends HasCurrency {

    @Nonnull
    Money<?> atRisk();

    @Nonnull
    default <R extends Currency> Money<R> atRisk(final ExchangeRates rates, final R currency) {
        return rates.convert(this.atRisk(), currency);
    }

    @Override
    default Currency currency() {
        return this.atRisk().currency();
    }

}
