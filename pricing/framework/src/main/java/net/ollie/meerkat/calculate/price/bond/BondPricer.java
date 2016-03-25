package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPricer;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author ollie
 */
public interface BondPricer<T extends Temporal, B extends Bond> extends SecurityPricer<T, B> {

    @Override
    default <C extends CurrencyId> BondPrice<C> price(final T temporal, final B bond, final C currency) {
        return this.price(temporal, bond, BondShifts.NONE, currency);
    }

    @Nonnull
    <C extends CurrencyId> BondPrice<C> price(T temporal, B bond, BondShifts shifts, C currency);

}
