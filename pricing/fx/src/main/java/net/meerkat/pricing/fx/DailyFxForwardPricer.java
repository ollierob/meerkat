package net.meerkat.pricing.fx;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.Explained;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.fx.forward.DeliverableFxForward;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRatesProvider;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.meerkat.pricing.shifts.WrappedInstrumentPriceShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.suppliers.lazy.Lazy;

/**
 *
 * @author ollie
 */
public class DailyFxForwardPricer<T> implements InstrumentPricer<LocalDate, DeliverableFxForward<?, ?>> {
    
    private final InterestRateInterpolator interestRateInterpolator;
    private final InterestRatesProvider<LocalDate> interestRates;
    
    public DailyFxForwardPricer(
            final InterestRateInterpolator interestRateInterpolator,
            final InterestRatesProvider<LocalDate> interestRates) {
        this.interestRateInterpolator = interestRateInterpolator;
        this.interestRates = interestRates;
    }
    
    @Override
    public <C extends CurrencyId> FxForwardPrice.Shiftable<C> price(
            final LocalDate date,
            final DeliverableFxForward<?, ?> forward,
            final C currency,
            final InstrumentPriceShifts shifts)
            throws InstrumentPriceException {
        throw new UnsupportedOperationException();
    }
    
    protected <B extends CurrencyId, C extends CurrencyId> Explained<ExchangeRate<B, C>> forwardRate(final FxForwardPricingContext<B, C> context) {
        final Percentage counterRate = context.counterRate().annualRate().plus(Percentage.oneHundred());
        final Percentage baseRate = context.baseRate().annualRate().plus(Percentage.oneHundred());
        final ExchangeRate<B, C> spotRate = context.spotRate();
        final BigDecimalFraction forwardMid = spotRate.midRate().times(counterRate).over(baseRate); //FIXME do bid/offer
        final ExchangeRate<B, C> forwardRate = ExchangeRate.between(spotRate.from(), spotRate.to(), forwardMid);
        return new Explained<>(
                forwardRate,
                ex -> ex.put("counter rate", counterRate)
                        .put("base rate", baseRate)
                        .put("spot rate", spotRate));
    }
    
    private class DailyFxForwardPrice<B extends CurrencyId, C extends CurrencyId, X extends CurrencyId>
            implements FxForwardPrice.Shiftable<X>, FxForwardPricingContext<B, C> {
        
        private final LocalDate date;
        private final DeliverableFxForward<B, C> forward;
        private final X valuationCurrency;
        private final ExchangeRates fxRates;
        private final FixedInterestRate baseRate;
        private final FixedInterestRate counterRate;
        private final WrappedInstrumentPriceShifts shifts;
        
        DailyFxForwardPrice(
                final LocalDate date,
                final DeliverableFxForward<B, C> forward,
                final X valuationCurrency,
                final ExchangeRates fxRates,
                final FixedInterestRate baseRate,
                final FixedInterestRate counterRate,
                final WrappedInstrumentPriceShifts shifts) {
            this.date = date;
            this.forward = forward;
            this.valuationCurrency = valuationCurrency;
            this.fxRates = fxRates;
            this.baseRate = baseRate;
            this.counterRate = counterRate;
            this.shifts = shifts;
        }
        
        @Override
        public ExchangeRate<B, C> spotRate() {
            return this.spotRate(forward.baseCurrencyId(), forward.counterCurrencyId());
        }
        
        private <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> spotRate(final F fromId, final T toId) {
            final ExchangeRate<F, T> spot = fxRates.rate(fromId, toId);
            return shifts.shift(spot);
        }
        
        @Override
        public FixedInterestRate baseRate() {
            return shifts.shift(baseRate);
        }
        
        @Override
        public FixedInterestRate counterRate() {
            return shifts.shift(counterRate);
        }
        
        private final Lazy<Explained<ExchangeRate<B, C>>> forwardRate = Lazy.loadOnce(() -> DailyFxForwardPricer.this.forwardRate(this));
        
        @Override
        public Money<X> value() {
            final Money<C> givenForward = forward.counterAmount();
            final Money<C> calculatedForward = this.forwardRate().convertAtMid(forward.baseAmount());
            final Money<C> forwardDifference = calculatedForward.minus(givenForward);
            final Money<C> presentDifference = this.presentValue(forwardDifference, counterRate);
            return this.spotRate(forwardDifference.currencyId(), this.currencyId()).convertAtMid(presentDifference);
        }
        
        private <C extends CurrencyId> Money<C> presentValue(final Money<C> amount, final InterestRate rate) {
            return rate.discount(amount, date, forward.settlementDate(), interestRateInterpolator);
        }
        
        ExchangeRate<B, C> forwardRate() {
            return forwardRate.get().value();
        }
        
        @Override
        public BigDecimalFraction forwardPoints() {
            final BigDecimalFraction spotMid = this.spotRate().midRate();
            final BigDecimalFraction forwardMid = this.forwardRate().midRate();
            return forwardMid.minus(spotMid);
        }
        
        @Override
        public DailyFxForwardPrice<B, C, X> shift(final InterestRateShifts shifts) {
            return new DailyFxForwardPrice<>(date, forward, valuationCurrency, fxRates, baseRate, counterRate, new WrappedInstrumentPriceShifts(shifts));
        }
        
        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("currency", valuationCurrency)
                    .put("forward", forward)
                    .put("shifts", shifts)
                    .put("spot rate", this.spotRate())
                    .put("forward rate", forwardRate.get());
        }
        
    }
    
}
