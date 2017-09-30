package net.meerkat.pricing.equity.option;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.Explainable.ExplanationBuilder;
import net.meerkat.Explained;
import net.meerkat.calculate.fx.ExchangeRatesProvider;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.option.Warrant;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.equity.StockPricer;
import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.goat.temporal.date.years.Years;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author Ollie
 */
public class DatedWarrantPricer implements WarrantPricer<LocalDate> {

    private final StockPricer<LocalDate> stockPricer;
    private final ExchangeRatesProvider<LocalDate> exchangeRates;

    public DatedWarrantPricer(final StockPricer<LocalDate> stockPricer, final ExchangeRatesProvider<LocalDate> exchangeRates) {
        this.stockPricer = stockPricer;
        this.exchangeRates = exchangeRates;
    }

    @Override
    public <C extends CurrencyId> WarrantPrice.Shiftable<C> price(
            final LocalDate date,
            final Warrant warrant,
            final C currency,
            final InstrumentShifts shifts)
            throws InstrumentPriceException {
        final ExchangeRates fxRates = exchangeRates.get(date);
        return new DatedWarrantPrice<>(date, warrant, currency, fxRates);
    }

    protected <C extends CurrencyId> Price.Valued<C> stockPrice(final C currencyId, final LocalDate date, final Warrant warrant) {
        return stockPricer.price(date, warrant.underlying(), currencyId);
    }

    protected <C extends CurrencyId> Money<C> exercisePrice(final C currencyId, final Warrant warrant, final ExchangeRates fxRates) {
        return fxRates.convert(warrant.strike(), currencyId);
    }

    protected <C extends CurrencyId> Explained<Money<C>> intrinsicValue(final C currencyId, final LocalDate date, final Warrant warrant, final ExchangeRates fxRates) {
        final Price.Valued<C> stockPrice = this.stockPrice(currencyId, date, warrant);
        final Money<C> exercisePrice = this.exercisePrice(currencyId, warrant, fxRates);
        final Money<C> intrinsic = stockPrice.value().minus(exercisePrice).times(warrant.exercise().contractMultiplier());
        return new Explained<>(intrinsic, new ExplanationBuilder().put("stock price", stockPrice).put("exercise price", exercisePrice));
    }

    protected <C extends CurrencyId> Explained<Money<C>> timeValue(final C currencyId, final LocalDate date, final Warrant warrant, final ExchangeRates fxRates) {
        final Years toExpiration = warrant.exercise().yearsToExpiration(date);
        if (!toExpiration.isPositive()) {
            return new Explained<>(Money.zero(currencyId), new ExplanationBuilder().put("expiration", toExpiration));
        }
        throw new UnsupportedOperationException(); //TODO
    }

    private final class DatedWarrantPrice<C extends CurrencyId> implements WarrantPrice.Shiftable<C> {

        private final LocalDate date;
        private final Warrant warrant;
        private final C currencyId;
        private final ExchangeRates fxRates;

        DatedWarrantPrice(final LocalDate date, final Warrant warrant, final C currencyId, final ExchangeRates fxRates) {
            this.date = date;
            this.warrant = warrant;
            this.currencyId = currencyId;
            this.fxRates = fxRates;
        }

        @Override
        public C currencyId() {
            return currencyId;
        }

        private final Lazy<Explained<Money<C>>> intrinsicValue = Lazy.loadOnce(() -> {
            final DatedWarrantPrice<C> px = this;
            return DatedWarrantPricer.this.intrinsicValue(px.currencyId, px.date, px.warrant, px.fxRates);
        });

        Explained<Money<C>> explainedIntrinsicValue() {
            return intrinsicValue.get();
        }

        @Override
        public Money<C> intrinsicValue() {
            return this.explainedIntrinsicValue().value();
        }

        private final Lazy<Explained<Money<C>>> timeValue = Lazy.loadOnce(() -> {
            final DatedWarrantPrice<C> px = this;
            return DatedWarrantPricer.this.timeValue(px.currencyId, px.date, px.warrant, px.fxRates);
        });

        Explained<Money<C>> explainedTimeValue() {
            return timeValue.get();
        }

        @Override
        public Money<C> timeValue() {
            return this.explainedTimeValue().value();
        }

        @Override
        public DatedWarrantPrice<C> shift(final InstrumentShifts shifts) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("currency", currencyId)
                    .put("warrant", warrant)
                    .put("intrinsic value", this.explainedIntrinsicValue())
                    .put("time value", this.explainedTimeValue());
        }

    }

}
