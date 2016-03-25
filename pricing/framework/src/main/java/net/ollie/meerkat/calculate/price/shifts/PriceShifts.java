package net.ollie.meerkat.calculate.price.shifts;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author Ollie
 */
public interface PriceShifts extends SecurityShifts {

    <C extends CurrencyId> Money<C> shiftPrice(Money<C> price);

}
