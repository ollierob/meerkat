package net.meerkat.instrument.equity.future;

import net.meerkat.instrument.equity.Equity;

public abstract class AbstractEquityFuture<E extends Equity> implements EquityFuture<E> {

    private final E equity;

    protected AbstractEquityFuture(final E equity) {
        this.equity = equity;
    }

}
