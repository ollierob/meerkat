package net.ollie.meerkat.price.bond.future;

import java.math.BigDecimal;
import java.time.temporal.Temporal;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePrice;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePricer;
import net.ollie.meerkat.calculate.price.bond.future.BondFutureShifts;
import net.ollie.meerkat.calculate.price.bond.future.CheapestToDeliver;
import net.ollie.meerkat.calculate.price.bond.future.CheapestToDeliverProvider;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.future.BondFuture;

/**
 *
 * @author Ollie
 */
public class NativeBondFuturePricer<T extends Temporal>
        implements BondFuturePricer<T> {

    private final CheapestToDeliverProvider<T> getCheapestToDeliver;
    private final Function<? super T, ExchangeRateCalculator> getExchangeRates;

    public NativeBondFuturePricer(
            final CheapestToDeliverProvider<T> getCheapestToDeliver,
            final Function<? super T, ExchangeRateCalculator> getExchangeRates) {
        this.getCheapestToDeliver = getCheapestToDeliver;
        this.getExchangeRates = getExchangeRates;
    }

    @Override
    public <C extends CurrencyId> BondFuturePrice<C> price(final T temporal, final BondFuture bondFuture, final C currency) {
        final CheapestToDeliver<?> cheapestToDeliver = this.cheapestToDeliver(temporal, bondFuture);
        final ExchangeRateCalculator exchangeRates = getExchangeRates.apply(temporal);
        return new NativeBondFuturePrice<>(cheapestToDeliver, bondFuture.conversionFactor(), exchangeRates, BondFutureShifts.none());
    }

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

    private final class NativeBondFuturePrice<C extends CurrencyId>
            implements BondFuturePrice<C> {

        private final CheapestToDeliver<?> cheapestToDeliver;
        private final BigDecimal conversionFactor;
        private final ExchangeRateCalculator exchangeRates;
        private final BondFutureShifts shifts;

        NativeBondFuturePrice(
                final CheapestToDeliver<?> cheapestToDeliver,
                final BigDecimal conversionFactor,
                final ExchangeRateCalculator exchangeRates,
                final BondFutureShifts shifts) {
            this.cheapestToDeliver = cheapestToDeliver;
            this.conversionFactor = conversionFactor;
            this.exchangeRates = exchangeRates;
            this.shifts = shifts;
        }

        private final Lazy<BondPrice<C>> bondPrice = Lazy.loadOnceNonnull(this::computeBondPrice);

        private BondPrice<C> bondPrice() {
            return bondPrice.get();
        }

        @Nonnull
        private BondPrice<C> computeBondPrice() {
            final BondPrice<?> price = cheapestToDeliver.price();
            //return unshiftedBondPrice.shift(shifts.bondShifts());
            throw new UnsupportedOperationException();
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
        public CheapestToDeliver<C> cheapestToDeliver() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public BondFuturePrice<C> shift(final BondFutureShifts shifts) {
            return new NativeBondFuturePrice<>(cheapestToDeliver, conversionFactor, exchangeRates, shifts);
        }

    }

}
