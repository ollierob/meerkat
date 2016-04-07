package net.ollie.meerkat.price.bond.future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePrice;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePricer;
import net.ollie.meerkat.calculate.price.bond.future.BondFutureShifts;
import net.ollie.meerkat.calculate.price.bond.future.CheapestToDeliver;
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
    private final BondPricer<LocalDate> bondPricer;

    public NativeBondFuturePricer(
            final CheapestToDeliverProvider<T> getCheapestToDeliver,
            final BondPricer<LocalDate> bondPricer) {
        this.getCheapestToDeliver = getCheapestToDeliver;
        this.bondPricer = bondPricer;
    }

    @Override
    public <C extends CurrencyId> BondFuturePrice<C> price(final T temporal, final BondFuture bondFuture, final C currency) {
        final CheapestToDeliver<?> cheapestToDeliver = this.cheapestToDeliver(temporal, bondFuture);
        final BondPrice<C> price = bondPricer.price(bondFuture.deliveryDates().earliest(), cheapestToDeliver.bond(), currency);
        return new NativeBondFuturePrice<>(cheapestToDeliver.bond(), cheapestToDeliver.conversionFactor(), price, BondFutureShifts.none());
    }

    @Nonnull
    private CheapestToDeliver<?> cheapestToDeliver(final T temporal, final BondFuture bondFuture) {
        return getCheapestToDeliver.get(temporal, bondFuture.securityIds())
                .orElseThrow(() -> new BondFuturePriceException("Could not determine cheapest to deliver"));
    }

    static final class BondFuturePriceException extends PriceException {

        private static final long serialVersionUID = 1L;

        BondFuturePriceException(final String message) {
            super(message);
        }

    }

    private static final class NativeBondFuturePrice<C extends CurrencyId>
            implements BondFuturePrice<C> {

        private final Bond cheapestToDeliver;
        private final BigDecimal conversionFactor;
        private final BondPrice<C> bondPrice;
        private final BondFutureShifts shifts;

        NativeBondFuturePrice(
                final Bond cheapestToDeliver,
                final BigDecimal conversionFactor,
                final BondPrice<C> bondPrice,
                final BondFutureShifts shifts) {
            this.cheapestToDeliver = cheapestToDeliver;
            this.conversionFactor = conversionFactor;
            this.bondPrice = bondPrice;
            this.shifts = shifts;
        }

        private final Lazy<BondPrice<C>> shiftedPrice = Lazy.loadOnceNonnull(() -> this.shiftPrice());

        BondPrice<C> shiftedPrice() {
            return shiftedPrice.get();
        }

        @Nonnull
        BondPrice<C> shiftPrice() {
            return shifts.shift(bondPrice);
        }

        @Nonnull
        BigDecimal shiftedConversionFactor() {
            return shifts.shiftConversionFactor(conversionFactor);
        }

        @Override
        public Money<C> cleanValue() {
            return this.shiftPrice().cleanValue().over(this.shiftedConversionFactor());
        }

        @Override
        public Money<C> dirtyValue() {
            return this.shiftPrice().dirtyValue().over(this.shiftedConversionFactor());
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
                    return shiftPrice();
                }

            };
        }

        @Override
        public BondFuturePrice<C> shift(final BondFutureShifts shifts) {
            return new NativeBondFuturePrice<>(cheapestToDeliver, conversionFactor, bondPrice, shifts);
        }

    }

}
