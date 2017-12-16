package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.issuer.IssuerId;

public class DepositoryReceipt extends AbstractEquity {

    public DepositoryReceipt(String name, InstrumentIds identifiers, IssuerId issuerId) {
        super(name, identifiers, issuerId);
    }

    @Override
    public <R> R handleWith(final Equity.Handler<R> handler) {
        return handler.handle(this);
    }

}
