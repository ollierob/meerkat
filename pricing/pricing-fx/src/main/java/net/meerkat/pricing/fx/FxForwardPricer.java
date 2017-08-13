package net.meerkat.pricing.fx;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.fx.forward.FxForward;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateProvider;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.shifts.InterestRateShifts;
import net.meerkat.pricing.shifts.InterestRateShifts.InterestRateShifter;

/**
 *
 * @author ollie
 */
public class FxForwardPricer<T> implements InstrumentPricer<LocalDate, FxForward<?, ?>> {

    private final InterestRateProvider interestRates;

    public FxForwardPricer(final InterestRateProvider interestRates) {
        this.interestRates = interestRates;
    }

    @Override
    public <C extends CurrencyId> FxForwardPrice.Shiftable<C> price(
            final LocalDate date,
            final FxForward<?, ?> forward,
            final C currency)
            throws InstrumentException, InstrumentPriceException {
        throw new UnsupportedOperationException();
    }

    private class Price<B extends CurrencyId, C extends CurrencyId, X extends CurrencyId>
            implements FxForwardPrice.Shiftable<X>, InterestRateShifter {

        private final LocalDate date;
        private final FxForward<B, C> forward;
        private final X currency;
        private final ExchangeRate<B, C> spotFxRate;
        private final InterestRate baseRate;
        private final InterestRate counterRate;
        private final InterestRateShifts shifts;

        Price(LocalDate date, FxForward<B, C> forward, X currency, ExchangeRate<B, C> spotFxRate, InterestRate baseRate, InterestRate counterRate, InterestRateShifts shifts) {
            this.date = date;
            this.forward = forward;
            this.currency = currency;
            this.spotFxRate = spotFxRate;
            this.baseRate = baseRate;
            this.counterRate = counterRate;
            this.shifts = shifts;
        }

        InterestRate baseRate() {
            return this.shift(baseRate, shifts);
        }

        InterestRate counterRate() {
            return this.shift(counterRate, shifts);
        }

        @Override
        public Money<X> value() {
            final ExchangeRate<B, C> impliedForwardRate = forward.exchangeRate();
            throw new UnsupportedOperationException("Not supported yet.");
        }

        private ExchangeRate<B, C> calculateForwardRate() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Number forwardPoints() {
            final ExchangeRate<B, C> calculatedForwardRate = this.calculateForwardRate();
            return calculatedForwardRate.rate().minus(spotFxRate.rate());
        }

        @Override
        public Price<B, C, X> shift(final InterestRateShifts shifts) {
            return new Price<>(date, forward, currency, spotFxRate, baseRate, counterRate, shifts);
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("currency", currency)
                    .put("forward", forward)
                    .put("base rate", baseRate)
                    .put("counter rate", counterRate)
                    .put("shifts", shifts);
        }

    }

}
