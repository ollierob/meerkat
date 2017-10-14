package net.meerkat.pricing.equity.option;

import java.time.LocalDate;

import net.meerkat.Explained;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.option.StockOption;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.equity.StockPricer;
import net.ollie.goat.temporal.date.years.Years;
import net.meerkat.money.fx.ExchangeRateProvider;

/**
 *
 * @author Ollie
 */
public class DailyStockOptionPricer extends AbstractOptionPricer<LocalDate, StockOption> {

    private final StockPricer<LocalDate> stockPricer;

    public DailyStockOptionPricer(final StockPricer<LocalDate> stockPricer, final ExchangeRateProvider<LocalDate> exchangeRates) {
        super(exchangeRates);
        this.stockPricer = stockPricer;
    }

    @Override
    protected <C extends CurrencyId> Price.Valued<C> underlyingPrice(final OptionPricingContext<C, StockOption, LocalDate> context) {
        return stockPricer.price(context.valuationTime(), context.option().underlying(), context.currencyId(), context.underlyingShifts());
    }

    @Override
    protected <C extends CurrencyId> Explained<Money<C>> explainExtrinsicValue(final OptionPricingContext<C, StockOption, LocalDate> context) {
        final Years toExpiration = context.yearsToExpiration();
        if (!toExpiration.isPositive()) {
            return new Explained<>(Money.zero(context.currencyId()), ex -> ex.put("expiration", toExpiration));
        }
        throw new UnsupportedOperationException(); //TODO
    }

}
