package net.meerkat.instrument.equity.pair;

import net.meerkat.money.Money;

import java.util.Map;

import static net.meerkat.instrument.equity.pair.CashAndRatioTerms.NONE;

public record SellMinusBuyRelationship(Map<EquityPair.Leg, CashAndRatioTerms> terms)
        implements HasRatioRelationship, HasCashRelationship {

    @Override
    public Money<?> getCash(final EquityPair.Leg leg) {
        return terms.getOrDefault(leg, NONE).cash();
    }

    @Override
    public double getRatio(final EquityPair.Leg leg) {
        return terms.getOrDefault(leg, NONE).ratio();
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
