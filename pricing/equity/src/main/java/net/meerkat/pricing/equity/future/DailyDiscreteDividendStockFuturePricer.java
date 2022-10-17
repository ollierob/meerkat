package net.meerkat.pricing.equity.future;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.future.StockFuture;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.equity.stock.StockPriceProvider;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

import java.time.Instant;

public class DailyDiscreteDividendStockFuturePricer implements StockFuturePricer<Instant> {

    private final StockPriceProvider priceProvider;

    public DailyDiscreteDividendStockFuturePricer(final StockPriceProvider priceProvider) {
        this.priceProvider = priceProvider;
    }

    @Override
    public <C extends CurrencyId> ShiftablePrice<C> price(
            final Instant time,
            final StockFuture future,
            final C currency,
            final InstrumentPriceShifts shifts)
            throws InstrumentPriceException {

        final var equityPrice = priceProvider.get(time, future.underlyingId());

        throw new UnsupportedOperationException();
    }

}
