package net.meerkat.pricing.equity.option;

import java.time.LocalDate;

import net.meerkat.Explained;
import net.meerkat.money.fx.ExchangeRatesProvider;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.option.StockOption;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.equity.StockPricer;
import net.meerkat.pricing.option.OptionPriceShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author Ollie
 */
public class DatedStockOptionPricer extends AbstractOptionPricer<LocalDate, StockOption> {

    private final StockPricer<LocalDate> stockPricer;

    public DatedStockOptionPricer(final StockPricer<LocalDate> stockPricer, final ExchangeRatesProvider<LocalDate> exchangeRates) {
        super(exchangeRates);
        this.stockPricer = stockPricer;
    }

    @Override
    protected <C extends CurrencyId> Price.Valued<C> underlyingPrice(
            final C currencyId,
            final LocalDate date,
            final StockOption option,
            final InstrumentPriceShifts stockShifts) {
        return stockPricer.price(date, option.underlying(), currencyId, stockShifts);
    }

    @Override
    protected <C extends CurrencyId> Explained<Money<C>> extrinsicValue(
            final C currencyId,
            final LocalDate date,
            final StockOption option,
            final ExchangeRates fxRates,
            final OptionPriceShifts optionShifts) {
        final Years toExpiration = option.exercise().yearsToExpiration(date);
        if (!toExpiration.isPositive()) {
            return new Explained<>(Money.zero(currencyId), ex -> ex.put("expiration", toExpiration));
        }
        throw new UnsupportedOperationException(); //TODO
    }

}
