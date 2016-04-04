package net.ollie.meerkat.calculate.price.bond.future;

import java.time.temporal.Temporal;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.future.BondFuture;
import net.ollie.meerkat.calculate.price.SecurityTypePricer;

/**
 *
 * @author Ollie
 */
public interface BondFuturePricer<T extends Temporal>
        extends SecurityTypePricer<T, BondFuture> {

    @Override
    <C extends CurrencyId> BondFuturePrice<C> price(T temporal, BondFuture future, C currency);

}
