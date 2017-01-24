package net.ollie.meerkat.calculate.var;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;

/**
 * Value at risk (VaR).
 *
 * @author Ollie
 */
public interface ValueAtRisk extends HasCurrencyId {

    @Nonnull
    Money<?> atRisk();

    @Nonnull
    default <R extends CurrencyId> Money<R> atRisk(final ExchangeRates rates, final R currency) {
        return rates.convert(this.atRisk(), currency);
    }

    @Override
    default CurrencyId currencyId() {
        return this.atRisk().currencyId();
    }

}
