package net.meerkat.pricing.fx;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.shifts.InterestRateShifts;
import net.meerkat.pricing.shifts.InterestRateShifts.InterestRateShifter;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.instrument.fx.forward.DeliverableFxForward;

/**
 *
 * @author ollie
 */
public class FxForwardPricer<T> implements InstrumentPricer<LocalDate, DeliverableFxForward<?, ?>> {

    private final InterestRates interestRates;

    public FxForwardPricer(final InterestRates interestRates) {
        this.interestRates = interestRates;
    }

    @Override
    public <C extends CurrencyId> FxForwardPrice.Shiftable<C> price(
            final LocalDate date,
            final DeliverableFxForward<?, ?> forward,
            final C currency)
            throws InstrumentException, InstrumentPriceException {
        throw new UnsupportedOperationException();
    }

    private class Price<B extends CurrencyId, C extends CurrencyId, X extends CurrencyId>
            implements FxForwardPrice.Shiftable<X>, InterestRateShifter {

        private final LocalDate date;
        private final DeliverableFxForward<B, C> forward;
        private final X currency;
        private final ExchangeRate<B, C> spotFxRate;
        private final FixedInterestRate baseRate;
        private final FixedInterestRate counterRate;
        private final InterestRateShifts shifts;

        Price(LocalDate date, DeliverableFxForward<B, C> forward, X currency, ExchangeRate<B, C> spotFxRate,
                final FixedInterestRate baseRate,
                final FixedInterestRate counterRate,
                final InterestRateShifts shifts) {
            this.date = date;
            this.forward = forward;
            this.currency = currency;
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
        public Money<X> bid() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Money<X> offer() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        private ExchangeRate<B, C> calculateForwardRate(final BigDecimalFraction spotRate) {
            final Percentage multiplier = this.counterRate().annualRate().plus(Percentage.oneHundred());
            final Percentage divisor = this.baseRate().annualRate().plus(Percentage.oneHundred());
            final BigDecimalFraction forward = spotRate.times(multiplier).over(divisor);
            return ExchangeRate.between(spotFxRate.from(), spotFxRate.to(), forward);
        }

        @Override
        public Number bidForwardPoints() {
            final BigDecimalFraction spotRate = spotFxRate.bidRate();
            final ExchangeRate<B, C> calculatedForwardRate = this.calculateForwardRate(spotRate);
            return calculatedForwardRate.bidRate().minus(spotRate);
        }

        @Override
        public Number offerForwardPoints() {
            final BigDecimalFraction offer = spotFxRate.offerRate();
            final ExchangeRate<B, C> calculatedForwardRate = this.calculateForwardRate(offer);
            return calculatedForwardRate.offerRate().minus(offer);
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
                    .put("shifted base rate", this.baseRate())
                    .put("shifted counter rate", this.counterRate())
                    .put("shifts", shifts);
        }

    }

}
