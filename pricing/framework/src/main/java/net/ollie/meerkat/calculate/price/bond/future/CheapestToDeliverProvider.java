package net.ollie.meerkat.calculate.price.bond.future;

import java.time.temporal.Temporal;

import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.bond.future.BondFuture;

/**
 *
 * @author Ollie
 */
public interface CheapestToDeliverProvider<T extends Temporal> {

    Bond get(T temporal, BondFuture future);

}
