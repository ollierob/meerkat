package net.meerkat.pricing.equity.option;

import java.time.LocalDate;

import net.meerkat.Explainable.ExplanationBuilder;
import net.meerkat.Explained;
import net.meerkat.calculate.fx.ExchangeRatesProvider;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.option.Warrant;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.equity.StockPricer;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author Ollie
 */
public class DatedWarrantPricer extends AbstractOptionPricer<LocalDate, Warrant> implements WarrantPricer<LocalDate> {

    private final StockPricer<LocalDate> stockPricer;

    public DatedWarrantPricer(final StockPricer<LocalDate> stockPricer, final ExchangeRatesProvider<LocalDate> exchangeRates) {
        super(exchangeRates);;
        this.stockPricer = stockPricer;
    }

    @Override
    protected <C extends CurrencyId> Price.Valued<C> underlyingPrice(final C currencyId, final LocalDate date, final Warrant warrant, final ExchangeRates fxRates) {
        //TODO use FX rates
        return stockPricer.price(date, warrant.underlying(), currencyId);
    }

    @Override
    protected <C extends CurrencyId> Explained<Money<C>> timeValue(final C currencyId, final LocalDate date, final Warrant warrant, final ExchangeRates fxRates) {
        final Years toExpiration = warrant.exercise().yearsToExpiration(date);
        if (!toExpiration.isPositive()) {
            return new Explained<>(Money.zero(currencyId), new ExplanationBuilder().put("expiration", toExpiration));
        }
        throw new UnsupportedOperationException(); //TODO
    }

}
