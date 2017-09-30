package net.meerkat.pricing.equity.option;

import net.meerkat.pricing.option.OptionPrice;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.option.Warrant;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.equity.EquityDerivativePricer;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author Ollie
 */
public interface WarrantPricer<T> extends EquityDerivativePricer<T, Warrant> {

    @Override
    <C extends CurrencyId> OptionPrice.Shiftable<C> price(
            T temporal,
            Warrant instrument,
            C currency,
            InstrumentShifts shifts)
            throws InstrumentPriceException;

}
