package net.meerkat.pricing.shifts;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
public interface PriceShifts extends SecurityShifts {

    @Nonnull
    <C extends CurrencyId> Money<C> shift(@Nonnull Money<C> price);

}
