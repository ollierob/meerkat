package net.meerkat.pricing.equity.option;

import net.meerkat.pricing.option.OptionPriceShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.option.Option;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.option.OptionPrice;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 *
 * @author Ollie
 */
public interface OptionPricer<T, O extends Option<?>> extends InstrumentPricer<T, O> {

    @Override
    default <C extends CurrencyId> OptionPrice.Shiftable<C> price(T temporal, O option, C currency) throws InstrumentPriceException {
        return this.price(temporal, option, currency, OptionPriceShifts.none());
    }

    @Override
    default <C extends CurrencyId> OptionPrice.Shiftable<C> price(T temporal, O option, C currency, InstrumentPriceShifts shifts) throws InstrumentPriceException {
        return this.price(temporal, option, currency, OptionPriceShifts.cast(shifts));
    }

    <C extends CurrencyId> OptionPrice.Shiftable<C> price(T temporal, O instrument, C currency, OptionPriceShifts shifts) throws InstrumentPriceException;

}
