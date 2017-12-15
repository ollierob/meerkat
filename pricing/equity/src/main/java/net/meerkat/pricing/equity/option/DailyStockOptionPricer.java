package net.meerkat.pricing.equity.option;

import net.meerkat.Explained;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentProvider;
import net.meerkat.instrument.equity.Stock;
import net.meerkat.instrument.equity.option.StockOption;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRateProvider;
import net.meerkat.money.price.MoneyPrice;
import net.meerkat.pricing.equity.StockPricer;
import net.meerkat.temporal.date.years.Years;

import java.time.LocalDate;

/**
 * @author Ollie
 */
public class DailyStockOptionPricer extends AbstractOptionPricer<LocalDate, StockOption> {

    private final StockPricer<LocalDate> stockPricer;
    private final InstrumentProvider<LocalDate, ? extends Stock> stockProvider;

    public DailyStockOptionPricer(
            final StockPricer<LocalDate> stockPricer,
            final ExchangeRateProvider<LocalDate> exchangeRates,
            final InstrumentProvider<LocalDate, ? extends Stock> stockProvider) {
        super(exchangeRates);
        this.stockPricer = stockPricer;
        this.stockProvider = stockProvider;
    }

    @Override
    protected <C extends CurrencyId> MoneyPrice<C> underlyingPrice(final OptionPricingContext<C, StockOption, LocalDate> context) {
        final Stock stock = stockProvider.require(context.valuationDate(), context.option().underlyingId());
        return stockPricer.price(context.valuationTime(), stock, context.currencyId(), context.underlyingShifts());
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
