package net.meerkat.pricing.fx;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.exception.InstrumentException;
import net.meerkat.instrument.fx.forward.DeliverableFxForward;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts.InterestRateShifter;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class DailyFxForwardPricer<T> implements InstrumentPricer<LocalDate, DeliverableFxForward<?, ?>> {

    private final InterestRates interestRates;

    public DailyFxForwardPricer(final InterestRates interestRates) {
        this.interestRates = interestRates;
    }

    @Override
    public <C extends CurrencyId> FxForwardPrice.Shiftable<C> price(
            final LocalDate date,
            final DeliverableFxForward<?, ?> forward,
            final C currency,
            final InstrumentPriceShifts shifts)
            throws InstrumentException, InstrumentPriceException {
        throw new UnsupportedOperationException();
    }

    private class DailyFxForwardPrice<B extends CurrencyId, C extends CurrencyId, X extends CurrencyId>
            implements FxForwardPrice.Shiftable<X>, InterestRateShifter {

        private final LocalDate date;
        private final DeliverableFxForward<B, C> forward;
        private final X valuationCurrency;
        private final ExchangeRate<B, C> spotFxRate;
        private final FixedInterestRate baseRate;
        private final FixedInterestRate counterRate;
        private final InterestRateShifts shifts;

        DailyFxForwardPrice(LocalDate date, DeliverableFxForward<B, C> forward, X currency, ExchangeRate<B, C> spotFxRate,
                final FixedInterestRate baseRate,
                final FixedInterestRate counterRate,
                final InterestRateShifts shifts) {
            this.date = date;
            this.forward = forward;
            this.valuationCurrency = currency;
            this.spotFxRate = spotFxRate;
            this.baseRate = baseRate;
            this.counterRate = counterRate;
            this.shifts = shifts;
        }

        FixedInterestRate baseRate() {
            return this.shift(baseRate, shifts);
        }

        FixedInterestRate counterRate() {
            return this.shift(counterRate, shifts);
        }

        @Override
        public Money<X> value() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        private ExchangeRate<B, C> calculateForwardMidRate(final BigDecimalFraction spotRate) {
            final Percentage multiplier = this.counterRate().annualRate().plus(Percentage.oneHundred());
            final Percentage divisor = this.baseRate().annualRate().plus(Percentage.oneHundred());
            final BigDecimalFraction forward = spotRate.times(multiplier).over(divisor);
            return ExchangeRate.between(spotFxRate.from(), spotFxRate.to(), forward);
        }

        @Override
        public BigDecimalFraction forwardPoints() {
            final BigDecimalFraction spotRate = spotFxRate.midRate();
            final ExchangeRate<B, C> calculatedForwardRate = this.calculateForwardMidRate(spotRate);
            return calculatedForwardRate.midRate().minus(spotRate);
        }

        @Override
        public DailyFxForwardPrice<B, C, X> shift(final InterestRateShifts shifts) {
            return new DailyFxForwardPrice<>(date, forward, valuationCurrency, spotFxRate, baseRate, counterRate, shifts);
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("currency", valuationCurrency)
                    .put("forward", forward)
                    .put("base rate", baseRate)
                    .put("counter rate", counterRate)
                    .put("shifted base rate", this.baseRate())
                    .put("shifted counter rate", this.counterRate())
                    .put("shifts", shifts);
        }

    }

}
