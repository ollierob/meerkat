package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface BondPriceHandler {

    @Nonnull
    default <C extends CurrencyId> BondPrice<C> price(
            final LocalDate date,
            final Bond bond,
            final BondShifts shifts,
            final C currency) {
        return bond.handleWith(this.priceContext(date, currency, shifts));
    }

    <C extends CurrencyId> BondPriceContext<C> priceContext(LocalDate date, C currency, BondShifts shifts);

    interface BondPriceContext<C extends CurrencyId> extends Bond.Handler<BondPrice<C>> {

    }

}
