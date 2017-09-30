package net.meerkat.pricing.equity.option;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.option.Warrant;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.equity.EquityDerivativePricer;

/**
 *
 * @author Ollie
 */
public interface WarrantPricer<T> extends EquityDerivativePricer<T, Warrant> {

    @Override
    <C extends CurrencyId> WarrantPrice<C> price(T temporal, Warrant instrument, C currency) throws InstrumentPriceException;

}
