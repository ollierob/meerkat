package net.ollie.meerkat.pricing.bond.future;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePricer;
import net.ollie.meerkat.calculate.price.bond.future.CheapestToDeliverProvider;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.bond.future.BondFuture;

/**
 *
 * @author Ollie
 */
public class NativeBondFuturePricer<T extends Temporal>
        implements BondFuturePricer<T> {

    private final CheapestToDeliverProvider<T> getCheapestToDeliver;
    private final BondPricer<T> bondPricer;

    public NativeBondFuturePricer(
            final CheapestToDeliverProvider<T> getCheapestToDeliver,
            final BondPricer<T> bondPricer) {
        this.getCheapestToDeliver = getCheapestToDeliver;
        this.bondPricer = bondPricer;
    }

    @Override
    public <C extends CurrencyId> BondPrice<C> price(final T temporal, final BondFuture future, final C currency) {
        final Bond cheapestToDeliver = getCheapestToDeliver.get(temporal, future);
        final BondPrice<C> bondPrice = bondPricer.price(temporal, cheapestToDeliver, currency);
        //TODO apply conversion factor
        throw new UnsupportedOperationException();
    }

}
