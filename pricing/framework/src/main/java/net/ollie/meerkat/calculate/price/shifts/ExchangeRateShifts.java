package net.ollie.meerkat.calculate.price.shifts;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.ExchangeRate;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateShifts extends SecurityShifts {

    <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shiftExchangeRate(ExchangeRate<F, T> rate);

}
