package net.meerkat.instrument.moneymarket;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.FixedInterestSecurity;
import net.meerkat.instrument.cash.CashPayment;

/**
 *
 * @author ollie
 */
public interface Repo<C extends CurrencyId>
        extends FixedInterestSecurity<C> {

    @Nonnull
    CashPayment<C> repurchasePrice();

    @Nonnull
    InstrumentId collateralId();

}
