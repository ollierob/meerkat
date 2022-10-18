package net.meerkat.instrument.equity.pair;

public interface LegRelationship {

    <R> R handleWith(Handler<R> handler);

    interface Handler<R> {

        R handle(SellMinusBuyRelationship sellMinusBuy);

    }

}
