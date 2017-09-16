package net.meerkat.pricing.interest;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.interest.swap.InterestRateSwap;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.InstrumentPriceException;

/**
 *
 * @author Ollie
 */
public class DatedInterestRateSwapPricer implements InterestRateSwapPricer<LocalDate> {

    @Override
    public <C extends CurrencyId> Price.Valued<C> price(
            final LocalDate date,
            final InterestRateSwap instrument,
            final C currency) throws InstrumentException, InstrumentPriceException {
        return new InterestRateSwapPrice<>(date, instrument, currency);
    }

    private class InterestRateSwapPrice<C extends CurrencyId> implements Price.Valued<C> {

        private final LocalDate date;
        private final InterestRateSwap swap;
        private final C currency;

        InterestRateSwapPrice(LocalDate date, InterestRateSwap swap, C currency) {
            this.date = date;
            this.swap = swap;
            this.currency = currency;
        }

        @Override
        public Money<C> value() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public Map<String, Object> explain() {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
