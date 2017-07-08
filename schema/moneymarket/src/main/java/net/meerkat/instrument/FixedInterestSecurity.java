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
    CashPayment<C> near();

    @Nonnull
    CashPayment<C> far();

    @Nonnull
    FixedInterestRate impliedRate();

    @Override
    default CurrencyId currencyId() {
        return this.near().currencyId();
    }

}
