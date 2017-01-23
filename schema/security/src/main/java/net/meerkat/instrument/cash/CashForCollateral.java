package net.meerkat.instrument.cash;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.security.Instrument;

/**
 *
 * @author ollie
 */
public interface CashForCollateral<C extends Instrument> extends Instrument {

    @Nonnull
    Money<?> principal();

    @Nonnull
    C collateral();

}
