package net.meerkat.instrument.moneymarket;

import net.meerkat.identifier.currency.USD;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.interest.fixed.FixedInterestRate;

public class TreasuryBill extends MoneyMarketSecurity<USD> {

    public TreasuryBill(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final CashPayment<USD> purchase,
            final CashPayment<USD> redemption,
            final FixedInterestRate impliedRate) {
        super(name, identifiers, issuerId, purchase, redemption, impliedRate);
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
