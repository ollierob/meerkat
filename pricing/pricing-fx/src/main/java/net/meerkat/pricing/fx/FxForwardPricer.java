package net.meerkat.pricing.fx;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.fx.forward.FxForward;
import net.meerkat.money.Money;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.shifts.ExchangeRateShifts;
import net.meerkat.pricing.shifts.ExchangeRateShifts.ExchangeRateShifter;

/**
 *
 * @author ollie
 */
public class FxForwardPricer<T> implements InstrumentPricer<LocalDate, FxForward<?, ?>> {

    @Override
    public <C extends CurrencyId> FxPrice.Shiftable<C> price(
            final LocalDate date,
            final FxForward<?, ?> forward,
            final C currency)
            throws InstrumentException, InstrumentPriceException {
        return new FxForwardPrice<>(date, forward, currency, ExchangeRateShifts.NONE);
    }

    private class FxForwardPrice<B extends CurrencyId, C extends CurrencyId, X extends CurrencyId>
            implements FxPrice.Shiftable<X>, ExchangeRateShifter {

        private final LocalDate date;
        private final FxForward<B, C> forward;
        private final X currency;
        private final ExchangeRateShifts shifts;

        FxForwardPrice(final LocalDate date, final FxForward<B, C> forward, final X currency, final ExchangeRateShifts shifts) {
            this.date = date;
            this.forward = forward;
            this.currency = currency;
            this.shifts = shifts;
        }

        @Override
        public Money<X> value() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Shiftable<X> shift(final ExchangeRateShifts shifts) {
            return new FxForwardPrice<>(date, forward, currency, shifts);
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("currency", currency)
                    .put("forward", forward)
                    .put("shifts", shifts);
        }

    }

}
