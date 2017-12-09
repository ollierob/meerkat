package net.meerkat.instrument.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.FixedInterestSecurity;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;

import javax.annotation.Nonnull;
import java.util.Optional;

public abstract class MoneyMarketSecurity<C extends CurrencyId>
        extends IssuedSecurity
        implements FixedInterestSecurity {

    private final CashPayment<C> purchase, redemption;
    private final FixedInterestRate impliedRate;

    protected MoneyMarketSecurity(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final CashPayment<C> purchase,
            final CashPayment<C> redemption,
            final FixedInterestRate impliedRate) {
        super(name, identifiers, issuerId);
        this.purchase = purchase;
        this.redemption = redemption;
        this.impliedRate = impliedRate;
    }

    @Nonnull
    @Override
    public CashPayment<C> purchase() {
        return purchase;
    }

    public CashPayment<C> redemption() {
        return redemption;
    }

    @Nonnull
    @Override
    public Optional<? extends FixedInterestRate> impliedRate() {
        return Optional.ofNullable(impliedRate);
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
