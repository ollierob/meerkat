package net.meerkat.identifier;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.identifier.instrument.HasInstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.identifier.market.HasMarketId;
import net.meerkat.identifier.market.Mic;
import net.meerkat.identifier.instrument.Isin;

/**
 *
 * @author Ollie
 */
public class IsinAndMarketId
        implements InstrumentInMarketId, HasInstrumentId, HasMarketId, HasCurrencyId, Explainable {

    private static final long serialVersionUID = 1L;

    private final Isin isin;
    private final Mic market;
    private final CurrencyIso currency;

    public IsinAndMarketId(final Isin isin, final Mic market, final CurrencyIso currency) {
        this.isin = isin;
        this.market = market;
        this.currency = currency;
    }

    @Override
    public Isin instrumentId() {
        return isin;
    }

    @Override
    public InstrumentIds instrumentIds() {
        return HasInstrumentId.super.instrumentIds();
    }

    @Override
    public Mic marketId() {
        return market;
    }

    @Override
    public CurrencyIso currencyId() {
        return currency;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("isin", isin)
                .put("market", market)
                .put("currency", currency);
    }

}
