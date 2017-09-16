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
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.money.price.Price;

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
            final C currency) throws InstrumentException {
        return new InterestRateSwapPrice<>(date, instrument, currency, interestRatesProvider.require(date), fxRatesProvider.require(date));
    }

    private class InterestRateSwapPrice<C extends CurrencyId> implements Price.Valued<C> {

        private final LocalDate date;
        private final InterestRateSwap swap;
        private final C currency;
        private final InterestRates interestRates;
        private final ExchangeRates fxRates;

        public InterestRateSwapPrice(final LocalDate date, final InterestRateSwap swap, final C currency, final InterestRates interestRates, final ExchangeRates fxRates) {
            this.date = date;
            this.swap = swap;
            this.currency = currency;
            this.interestRates = interestRates;
            this.fxRates = fxRates;
        }

        InterestRate payRate(final InterestRateSwapLeg<?, ?> leg) {
            return leg.payRate().resolve(interestRates);
        }

        InterestRate receiveRate(final InterestRateSwapLeg<?, ?> leg) {
            return leg.receiveRate().resolve(interestRates);
        }

        @Override
        public Money<C> value() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("swap", swap);
        }

    }

}
