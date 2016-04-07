package net.ollie.meerkat.price.bond.future;

import java.math.BigDecimal;
import java.time.temporal.Temporal;
import java.util.function.Function;

import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.SpotExchangedBondPrice;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePrice;
import net.ollie.meerkat.calculate.price.bond.future.BondFuturePricer;
import net.ollie.meerkat.calculate.price.bond.future.BondFutureShifts;
import net.ollie.meerkat.calculate.price.bond.future.CheapestToDeliver;
import net.ollie.meerkat.calculate.price.bond.future.CheapestToDeliverProvider;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.money.fx.ExchangeRate;
import net.ollie.meerkat.security.bond.Bond;
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
        return new NativeBondFuturePrice<>(currency, cheapestToDeliver, exchangeRates, BondFutureShifts.none());
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

    private static final class NativeBondFuturePrice<C extends CurrencyId>
            implements BondFuturePrice<C> {

        private final C currencyId;
        private final CheapestToDeliver<?> cheapestToDeliver;
        private final ExchangeRateCalculator exchangeRates;
        private final BondFutureShifts shifts;

        NativeBondFuturePrice(
                final C currencyId,
                final CheapestToDeliver<?> cheapestToDeliver,
                final ExchangeRateCalculator exchangeRates,
                final BondFutureShifts shifts) {
            this.currencyId = currencyId;
            this.cheapestToDeliver = cheapestToDeliver;
            this.exchangeRates = exchangeRates;
            this.shifts = shifts;
        }

        BigDecimal shiftedConversionFactor() {
            return this.cheapestToDeliver().conversionFactor();
        }

        //FIXME this should be valued on the delivery date.
        BondPrice<C> shiftedBondPrice() {
            return this.cheapestToDeliver().price();
        }

        @Override
        public Money<C> cleanValue() {
            return this.shiftedBondPrice().cleanValue().over(this.shiftedConversionFactor());
        }

        @Override
        public Money<C> dirtyValue() {
            return this.shiftedBondPrice().dirtyValue().over(this.shiftedConversionFactor());
        }

        private final Lazy<CheapestToDeliver<C>> shiftedCheapestToDeliver = Lazy.loadOnceNonnull(NativeCheapestToDeliver::new);

        @Override
        public CheapestToDeliver<C> cheapestToDeliver() {
            return shiftedCheapestToDeliver.get();
        }

        @Override
        public BondFuturePrice<C> shift(final BondFutureShifts shifts) {
            return new NativeBondFuturePrice<>(currencyId, cheapestToDeliver, exchangeRates, shifts);
        }

        final class NativeCheapestToDeliver implements CheapestToDeliver<C> {

            @Override
            public BigDecimal conversionFactor() {
                return shifts.shiftConversionFactor(cheapestToDeliver.conversionFactor());
            }

            @Override
            public Bond bond() {
                return cheapestToDeliver.bond();
            }

            @Override
            public BondPrice<C> price() {
                return this.shiftSpotFx(cheapestToDeliver.price());
            }

            private <F extends CurrencyId> SpotExchangedBondPrice<F, C> shiftSpotFx(final BondPrice<F> bondPrice) {
                final ExchangeRate<F, C> rate = exchangeRates.rate(bondPrice.currencyId(), currencyId);
                final ExchangeRate<F, C> shiftedRate = shifts.shift(rate);
                return new SpotExchangedBondPrice<>(bondPrice, shiftedRate);
            }

        }

    }

}
