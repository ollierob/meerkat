package net.ollie.meerkat.price.bond.future;

import java.math.BigDecimal;
import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePrice;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePricer;
import net.ollie.meerkat.calculate.price.bond.future.BondFutureShifts;
import net.ollie.meerkat.calculate.price.bond.future.CheapestToDeliverProvider;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;
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
    public <C extends CurrencyId> BondFuturePrice<C> price(final T temporal, final BondFuture future, final C currency) {
        final Bond cheapestToDeliver = getCheapestToDeliver.get(temporal, future);
        final BondPrice<C> bondPrice = bondPricer.price(temporal, cheapestToDeliver, currency);
        return new NativeBondFuturePrice<>(cheapestToDeliver, future.conversionFactor(), bondPrice, BondFutureShifts.none());
    }

    private final class NativeBondFuturePrice<C extends CurrencyId>
            implements BondFuturePrice<C> {

        private final Bond cheapestToDeliver;
        private final BigDecimal conversionFactor;
        private final BondPrice<C> unshiftedBondPrice;
        private final BondFutureShifts shifts;

        NativeBondFuturePrice(
                final Bond cheapestToDeliver,
                final BigDecimal conversionFactor,
                final BondPrice<C> bondPrice,
                final BondFutureShifts shifts) {
            this.cheapestToDeliver = cheapestToDeliver;
            this.conversionFactor = conversionFactor;
            this.unshiftedBondPrice = bondPrice;
            this.shifts = shifts;
        }

        private final Lazy<BondPrice<C>> bondPrice = Lazy.loadOnceNonnull(this::computeBondPrice);

        @Override
        public BondPrice<C> bondPrice() {
            return bondPrice.get();
        }

        @Nonnull
        private BondPrice<C> computeBondPrice() {
            return unshiftedBondPrice.shift(shifts.bondShifts());
        }

        BigDecimal conversionFactor() {
            return shifts.shiftConversionFactor(conversionFactor);
        }

        @Override
        public Money<C> cleanValue() {
            return this.bondPrice().cleanValue().times(this.conversionFactor());
        }

        @Override
        public Money<C> dirtyValue() {
            return this.cleanValue().plus(this.bondPrice().accruedInterest());
        }

        @Override
        public Bond cheapestToDeliver() {
            return cheapestToDeliver;
        }

        @Override
        public BondFuturePrice<C> shift(final BondFutureShifts shifts) {
            return new NativeBondFuturePrice<>(cheapestToDeliver, conversionFactor, unshiftedBondPrice, shifts);
        }

    }

}
