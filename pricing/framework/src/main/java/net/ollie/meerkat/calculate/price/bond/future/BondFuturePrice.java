package net.ollie.meerkat.calculate.price.bond.future;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author ollie
 */
public interface BondFuturePrice<C extends CurrencyId>
        extends SecurityPrice<C> {

    @Nonnull
    Bond cheapestToDeliver();

    @Nonnull
    BondPrice<C> bondPrice();

    @CheckReturnValue
    BondFuturePrice<C> shift(BondFutureShifts shifts);

}
