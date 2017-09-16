package net.meerkat.pricing.interest;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.calculate.fx.ExchangeRatesProvider;
import net.meerkat.calculate.interest.InterestRatesProvider;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.interest.swap.InterestRateSwap;
import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.shifts.ExchangeRateShifts.ExchangeRateShifter;

/**
 *
 * @author Ollie
 */
public class DatedInterestRateSwapPricer implements InterestRateSwapPricer<LocalDate> {

    private final InterestRatesProvider<LocalDate> interestRatesProvider;
    private final ExchangeRatesProvider<LocalDate> fxRatesProvider;

    public DatedInterestRateSwapPricer(final InterestRatesProvider<LocalDate> interestRatesProvider, final ExchangeRatesProvider<LocalDate> fxRatesProvider) {
        this.interestRatesProvider = interestRatesProvider;
        this.fxRatesProvider = fxRatesProvider;
    }

    @Override
    public <C extends CurrencyId> Price.Valued<C> price(
            final LocalDate date,
            final InterestRateSwap instrument,
            final C currency,
            final InterestRateDerivativeShifts shifts)
            throws InstrumentException {
        return new InterestRateSwapPrice<>(date, instrument, currency, interestRatesProvider.require(date), fxRatesProvider.require(date), shifts);
    }

    private class InterestRateSwapPrice<C extends CurrencyId>
            implements Price.Valued<C>, ExchangeRateShifter {

        private final LocalDate date;
        private final InterestRateSwap swap;
        private final C currency;
        private final InterestRates interestRates;
        private final ExchangeRates fxRates;
        private final InterestRateDerivativeShifts shifts;

        public InterestRateSwapPrice(final LocalDate date, final InterestRateSwap swap, final C currency, final InterestRates interestRates, final ExchangeRates fxRates, final InterestRateDerivativeShifts shifts) {
            this.date = date;
            this.swap = swap;
            this.currency = currency;
            this.interestRates = interestRates;
            this.fxRates = fxRates;
            this.shifts = shifts;
        }

        @Override
        public Money<C> value() {
            return swap.legsAfter(date)
                    .transform(this::netValue)
                    .reduce(Money.zero(currency), Money::plus);
        }

        ExchangeRates fxRates() {
            return shifts.shift(fxRates);
        }

        <P extends CurrencyId, R extends CurrencyId> Money<C> netValue(final InterestRateSwapLeg<P, R> leg) {
            final ExchangeRates fxRates = this.fxRates();
            final Money<C> discountPay = fxRates.convert(this.discount(leg.payNotional()), currency);
            final Money<C> discountReceive = fxRates.convert(this.discount(leg.receiveNotional()), currency);
            return discountReceive.minus(discountPay);
        }

        <D extends CurrencyId> Money<D> discount(final Money<D> notional) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("swap", swap)
                    .put("shifts", shifts);
        }

    }

}
