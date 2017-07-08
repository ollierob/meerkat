package net.meerkat.instrument.moneymarket;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.FixedInterestSecurity;

/**
 *
 * @author ollie
 */
public interface Repo<C extends CurrencyId>
        extends FixedInterestSecurity<C> {

    @Nonnull
    InstrumentIds collateralId();

}
