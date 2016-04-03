package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface BondPricer<T extends Temporal> {

    @Nonnull
    default <C extends CurrencyId> BondPrice<C> price(
            final T valuation,
            final Bond bond,
            final C currency) {
        return bond.handleWith(this.priceContext(valuation, currency));
    }

    @Nonnull
    default <C extends CurrencyId> BondPrice<C> price(
            final T valuation,
            final Bond bond,
            final C currency,
            final BondShifts shifts) {
        return this.price(valuation, bond, currency).shift(shifts);
    }

    <C extends CurrencyId> BondPriceContext<C> priceContext(T valuation, C currency);

    interface BondPriceContext<C extends CurrencyId> extends Bond.Handler<BondPrice<C>> {

    }

}
