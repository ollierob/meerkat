package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.BondPricer.BondPriceException;
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
    private final BondPricer<LocalDate> bondPricer;

    public NativeBondFuturePricer(
            final CheapestToDeliverProvider<T> getCheapestToDeliver,
            final BondPricer<LocalDate> bondPricer) {
        this.getCheapestToDeliver = getCheapestToDeliver;
        this.bondPricer = bondPricer;
    }

    @Override
    public <C extends CurrencyId> BondFuturePrice.Shiftable<C> price(final T temporal, final BondFuture bondFuture, final C currency) {
        try {
            final CheapestToDeliver<?> cheapestToDeliver = this.cheapestToDeliver(temporal, bondFuture);
            final BondPrice.Shiftable<C> price = bondPricer.price(bondFuture.deliveryDates().earliest(), cheapestToDeliver.bond(), currency);
            return new NativeBondFuturePrice<>(cheapestToDeliver.bond(), cheapestToDeliver.conversionFactor(), price, BondFutureShifts.none());
        } catch (final BondPriceException bex) {
            throw new BondFuturePriceException("Failed to price cheapest to deliver!", bex);
        }
    }

    @Nonnull
    private CheapestToDeliver<?> cheapestToDeliver(final T temporal, final BondFuture bondFuture) {
        return getCheapestToDeliver.get(temporal, bondFuture.securityIds())
                .orElseThrow(() -> new BondFuturePriceException("Could not determine cheapest to deliver!"));
    }

    private static final class NativeBondFuturePrice<C extends CurrencyId>
            implements BondFuturePrice.Shiftable<C> {

        private final Bond cheapestToDeliver;
        private final BigDecimal conversionFactor;
        private final BondPrice.Shiftable<C> bondPrice;
        private final BondFutureShifts shifts;

        NativeBondFuturePrice(
                final Bond cheapestToDeliver,
                final BigDecimal conversionFactor,
                final BondPrice.Shiftable<C> bondPrice,
                final BondFutureShifts shifts) {
            this.cheapestToDeliver = cheapestToDeliver;
            this.conversionFactor = conversionFactor;
            this.bondPrice = bondPrice;
            this.shifts = shifts;
        }

        @Nonnull
        BondPrice<C> shiftedBondPrice() {
            return shiftedPrice.get();
        }

        private final Lazy<BondPrice<C>> shiftedPrice = Lazy.loadOnceNonnull(() -> this.computeShiftedBondPrice());

        @Nonnull
        BondPrice.Shiftable<C> computeShiftedBondPrice() {
            return shifts.shift(bondPrice);
        }

        @Nonnull
        BigDecimal shiftedConversionFactor() {
            return shifts.shiftConversionFactor(conversionFactor);
        }

        @Override
        public Money<C> cleanValue() {
            return this.shiftedBondPrice().cleanValue().over(this.shiftedConversionFactor());
        }

        @Override
        public Money<C> dirtyValue() {
            return this.shiftedBondPrice().dirtyValue().over(this.shiftedConversionFactor());
        }

        @Override
        public CheapestToDeliver<C> cheapestToDeliver() {
            return new CheapestToDeliver<C>() {

                @Override
                public Bond bond() {
                    return cheapestToDeliver;
                }

                @Override
                public BigDecimal conversionFactor() {
                    return shiftedConversionFactor();
                }

                @Override
                public BondPrice<C> price() {
                    return shiftedBondPrice();
                }

            };
        }

        @Override
        public BondFuturePrice.Shiftable<C> shift(final BondFutureShifts shifts) {
            return new NativeBondFuturePrice<>(cheapestToDeliver, conversionFactor, bondPrice, shifts);
        }

    }

}
