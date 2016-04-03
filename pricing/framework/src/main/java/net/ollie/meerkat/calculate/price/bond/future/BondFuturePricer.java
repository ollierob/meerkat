package net.ollie.meerkat.calculate.price.bond.future;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.SecurityPricer;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.future.BondFuture;

/**
 *
 * @author Ollie
 */
public interface BondFuturePricer<T extends Temporal>
        extends SecurityPricer<T, BondFuture> {

    @Override
    <C extends CurrencyId> BondFuturePrice<C> price(T temporal, BondFuture future, C currency);

}
