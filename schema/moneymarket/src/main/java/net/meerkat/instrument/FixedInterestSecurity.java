package net.meerkat.instrument;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public interface FixedInterestSecurity<C extends CurrencyId>
        extends Security, HasCurrencyId {

    @Nonnull
    CashPayment<C> purchasePrice();

    @Nonnull
    FixedInterestRate impliedRate();

    @Override
    default CurrencyId currencyId() {
        return this.purchasePrice().currencyId();
    }

}
