package net.meerkat.instrument.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.FixedInterestSecurity;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

import javax.annotation.Nonnull;

public abstract class MoneyMarketSecurity<C extends CurrencyId>
        extends IssuedSecurity
        implements FixedInterestSecurity {

    private final CashPayment<C> purchase, redemption;

    protected MoneyMarketSecurity(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final CashPayment<C> purchase,
            final CashPayment<C> redemption) {
        super(name, identifiers, issuerId);
        this.purchase = purchase;
        this.redemption = redemption;
    }

    @Nonnull
    @Override
    public CashPayment<C> purchase() {
        return purchase;
    }

    public CashPayment<C> redemption() {
        return redemption;
    }

    public Money<C> discountSpread() {
        return redemption.paymentAmount().minus(purchase.paymentAmount());
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("purchase", purchase);
    }

}
