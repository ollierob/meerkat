package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.goat.currency.Currency;
import net.ollie.goat.money.Money;
import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.BondPricer.BondPriceException;
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
    public <C extends Currency> BondFuturePrice.Shiftable<C> price(final T temporal, final BondFuture bondFuture, final C currency) {
        try {
            final CheapestToDeliver<?> cheapestToDeliver = this.cheapestToDeliver(temporal, bondFuture);
            final BondPrice.Shiftable<C> cheapestToDeliverPrice = bondPricer.price(bondFuture.dates().earliest(), cheapestToDeliver.bond(), currency);
            return new NativeBondFuturePrice<>(cheapestToDeliver.bond(), cheapestToDeliver.conversionFactor(), cheapestToDeliverPrice, BondFutureShifts.none());
        } catch (final BondPriceException bex) {
            throw new BondFuturePriceException("Failed to price cheapest to deliver!", bex);
        }
    }

    @Nonnull
    private CheapestToDeliver<?> cheapestToDeliver(final T temporal, final BondFuture bondFuture) {
        return getCheapestToDeliver.get(temporal, bondFuture.securityIds())
                .orElseThrow(() -> new BondFuturePriceException("Could not determine cheapest to deliver!"));
    }

    private static final class NativeBondFuturePrice<C extends Currency>
            implements BondFuturePrice.Shiftable<C> {

        private final Bond cheapestToDeliver;
        private final BigDecimal conversionFactor;
        private final BondPrice.Shiftable<C> cheapestToDeliverPrice;
        private final BondFutureShifts shifts;

        NativeBondFuturePrice(
                final Bond cheapestToDeliver,
                final BigDecimal conversionFactor,
                final BondPrice.Shiftable<C> cheapestToDeliverPrice,
                final BondFutureShifts shifts) {
            this.cheapestToDeliver = cheapestToDeliver;
            this.conversionFactor = conversionFactor;
            this.cheapestToDeliverPrice = cheapestToDeliverPrice;
            this.shifts = shifts;
        }

        @Nonnull
        BondPrice<C> shiftedBondPrice() {
            return shiftedPrice.get();
        }

        private final Lazy<BondPrice<C>> shiftedPrice = Lazy.loadOnceNonnull(() -> this.computeShiftedBondPrice());

        @Nonnull
        private BondPrice.Shiftable<C> computeShiftedBondPrice() {
            return shifts.shift(cheapestToDeliverPrice);
        }

        @Nonnull
        BigDecimal shiftedConversionFactor() {
            return shifts.shiftConversionFactor(conversionFactor);
        }

        @Override
        public Money<C> clean() {
            return this.shiftedBondPrice().clean().over(this.shiftedConversionFactor());
        }

        @Override
        public Money<C> dirty() {
            return this.shiftedBondPrice().dirty().over(this.shiftedConversionFactor());
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
            return new NativeBondFuturePrice<>(cheapestToDeliver, conversionFactor, cheapestToDeliverPrice, shifts);
        }

        @Override
        public ExplanationBuilder explain() {
            return BondFuturePrice.Shiftable.super.explain()
                    .put("cheapest to deliver price", cheapestToDeliverPrice)
                    .put("conversion factor", conversionFactor)
                    .put("shifts", shifts)
                    .put("shifted conversion factor", this.shiftedConversionFactor())
                    .put("shifted cheapest to deliver price", this.shiftedBondPrice());
        }

    }

}
