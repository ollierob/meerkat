package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface BondPricer {

    @Nonnull
    default <C extends CurrencyId> BondPrice<C> price(
            final LocalDate valuationDate,
            final Bond bond,
            final BondShifts shifts,
            final C currency) {
        return bond.handleWith(this.priceContext(valuationDate, currency, shifts));
    }

    <C extends CurrencyId> BondPriceContext<C> priceContext(LocalDate valuationDate, C currency, BondShifts shifts);

    interface BondPriceContext<C extends CurrencyId> extends Bond.Handler<BondPrice<C>> {

    }

}
