package net.meerkat.instrument.moneymarket;

import net.meerkat.identifier.currency.USD;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.temporal.date.count.DateArithmetic;

import javax.annotation.Nonnull;

public class TreasuryBill extends MoneyMarketSecurity<USD> {

    public TreasuryBill(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final CashPayment<USD> purchase,
            final CashPayment<USD> redemption) {
        super(name, identifiers, issuerId, purchase, redemption);
    }

    @Nonnull
    @Override
    public FixedInterestRate impliedRate() {
        final Money<USD> discountSpread = this.discountSpread();
        final DateArithmetic dayCount = DateArithmetic.ACT_360;
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
