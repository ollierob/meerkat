package net.meerkat.pricing.equity.option;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

import net.meerkat.Explainable;
import net.meerkat.Explained;
import net.meerkat.calculate.fx.ExchangeRatesProvider;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.option.Warrant;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.equity.StockPricer;

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
    public <C extends CurrencyId> WarrantPrice<C> price(
            final LocalDate date,
            final Warrant warrant,
            final C currency)
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
        final Money<C> intrinsic = stockPrice.value().minus(exercisePrice).times(warrant.contractMultiplier());
        return new Explained<>(intrinsic, new Explainable.ExplanationBuilder().put("stock price", stockPrice).put("exercise price", exercisePrice));
    }

    protected <C extends CurrencyId> Explained<Money<C>> timeValue(final C currencyId, final LocalDate date, final Warrant warrant, final ExchangeRates fxRates) {
        final Period timeToExpiration = warrant.exercise().timeToExpiration(date);
        throw new UnsupportedOperationException(); //TODO
    }

    private final class DatedWarrantPrice<C extends CurrencyId> implements WarrantPrice<C> {

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

        Explained<Money<C>> intrinsic() {
            return DatedWarrantPricer.this.intrinsicValue(currencyId, date, warrant, fxRates);
        }

        @Override
        public Money<C> intrinsicValue() {
            return this.intrinsic().value();
        }

        Explained<Money<C>> time() {
            return DatedWarrantPricer.this.timeValue(currencyId, date, warrant, fxRates);
        }

        @Override
        public Money<C> timeValue() {
            return this.time().value();
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("currency", currencyId)
                    .put("warrant", warrant)
                    .put("intrinsic value", this.intrinsic())
                    .put("time value", this.time());
        }

    }

}
