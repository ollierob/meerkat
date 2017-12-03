package net.meerkat.instrument.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.interest.fixed.FixedInterestRate;

import javax.annotation.Nonnull;

/**
 * @author ollie
 */
public class CertificateOfDeposit<C extends CurrencyId> extends MoneyMarketSecurity<C> {

    private final FixedInterestRate impliedRate;

    public CertificateOfDeposit(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final CashPayment<C> purchase,
            final CashPayment<C> redemption,
            final FixedInterestRate impliedRate) {
        super(name, identifiers, issuerId, purchase, redemption);
        this.impliedRate = impliedRate;
    }

    @Nonnull
    @Override
    public FixedInterestRate impliedRate() {
        return impliedRate;
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }
}
